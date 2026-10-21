package library.services;

import java.time.LocalDate;
import java.util.Collection;

import library.interfaces.FinePolicy;
import library.models.BookCopy;
import library.models.Loan;
import library.models.Member;
import library.repositories.BookCopyRepository;
import library.repositories.LoanRepository;
import library.repositories.MemberRepository;
import library.exceptions.MemberNotFoundException;
import library.exceptions.BookCopyNotAvailableException;
import library.exceptions.BorrowingLimitExceededException;
import library.exceptions.LoanNotFoundException;

public class LoanService {
    private final BookCopyRepository copyRepository;
    private final MemberRepository memberRepository;
    private final LoanRepository loanRepository;
    private final FinePolicy finePolicy;

    public LoanService(BookCopyRepository copyRepository, MemberRepository memberRepository, LoanRepository loanRepository, FinePolicy finePolicy) {
        if (copyRepository == null || memberRepository == null || loanRepository == null || finePolicy == null) {
            throw new IllegalArgumentException("Dependencies cannot be null");
        }

        this.copyRepository = copyRepository;
        this.memberRepository = memberRepository;
        this.loanRepository = loanRepository;
        this.finePolicy = finePolicy;
    }

    public Loan borrowBook(String loanId, String memberId, String copyId, LocalDate checkoutDate) {
        Member member = memberRepository.findById(memberId);
        if (member == null) {
            throw new MemberNotFoundException("Member not found");
        }

        BookCopy copy = copyRepository.findById(copyId);
        if (copy == null) {
            throw new IllegalArgumentException("Book copy not found");
        }

        if (!copy.isAvailable()) {
            throw new BookCopyNotAvailableException("Book copy is not available");
        }

        if (checkoutDate == null) {
            throw new IllegalArgumentException("Checkout date cannot be null");
        }

        if (loanId == null || loanId.isBlank()) {
            throw new IllegalArgumentException("Loan ID cannot be empty");
        }

        if (loanRepository.findById(loanId) != null) {
            throw new IllegalArgumentException("Loan ID already exists");
        }

        int activeLoans = 0;

        for (Loan loan : loanRepository.findAll()) {
            if (loan.getMember().getMemberId().equals(memberId) && !loan.isReturned()) {
                activeLoans++;
            }
        }

        if (activeLoans >= member.getBorrowingLimit()) {
            throw new BorrowingLimitExceededException("Member has reached the borrowing limit");
        }

        Loan loan = new Loan(loanId, member, copy, checkoutDate);

        loanRepository.add(loan);

        return loan;
    }

    public double returnBook(String loanId, LocalDate returnDate) {
        Loan loan = loanRepository.findById(loanId);

        if (loan == null) {
            throw new LoanNotFoundException("Loan not found");
        }

        if (returnDate == null) {
            throw new IllegalArgumentException("Return date cannot be null");
        }

        if (loan.isReturned()) {
            throw new IllegalStateException("This loan has already been returned");
        }

        if (returnDate.isBefore(loan.getCheckoutDate())) {
            throw new IllegalArgumentException("Return date cannot be before checkout date");
        }

        double fine = finePolicy.calculateFine(loan.getDueDate(), returnDate);

        loan.returnBook(returnDate);

        return fine;
    }

    public Loan findLoanById(String loanId) {
        return loanRepository.findById(loanId);
    }

    public Collection<Loan> getAllLoans() {
        return loanRepository.findAll();
    }
}
