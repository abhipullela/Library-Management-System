package library.models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Loan {
    private final String loanId;
    private final Member member;
    private final BookCopy bookCopy;
    private final LocalDate checkoutDate;
    private final LocalDate dueDate;

    private LocalDate returnDate;

    public Loan(String loanId, Member member, BookCopy bookCopy , LocalDate checkoutDate) {
        if (loanId == null || loanId.isBlank()) {
            throw new IllegalArgumentException("Loan ID cannot be empty");
        }
        if (member == null) {
            throw new IllegalArgumentException("Member cannot be null");
        }
        if (bookCopy == null) {
            throw new IllegalArgumentException("Book copy cannot be null");
        }
        if (checkoutDate == null) {
            throw new IllegalArgumentException("Checkout date cannot be null");
        }

        if (!bookCopy.isAvailable()) {
            throw new IllegalStateException("Book copy is not available");
        }

        this.loanId = loanId;
        this.member = member;
        this.bookCopy = bookCopy;
        this.checkoutDate = checkoutDate;
        this.dueDate = checkoutDate.plusDays(member.getLoanPeriodDays());
        this.returnDate = null;

        bookCopy.markAsBorrowed();
    }

    public String getLoanId() {
        return loanId;
    }

    public Member getMember() {
        return member;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public boolean isReturned() {
        return returnDate != null;
    }

    public boolean isOverdue() {
        return !isReturned() && LocalDate.now().isAfter(dueDate);
    }

    public long getOverdueDays() {
        LocalDate effectiveDate = isReturned() ? returnDate : LocalDate.now();
        if (!effectiveDate.isAfter(dueDate)) {
            return 0;
        }
        return ChronoUnit.DAYS.between(dueDate, effectiveDate);
    }

    public void returnBook(LocalDate date) {
        if (isReturned()) {
            throw new IllegalStateException("This loan has already been returned");
        }

        if (date == null) {
            throw new IllegalArgumentException("Return date cannot be null");
        }
        if (date.isBefore(checkoutDate)) {
            throw new IllegalArgumentException("Return date cannot be before checkout date");
        }

        bookCopy.markAsReturned();
        returnDate = date;
    }

    @Override
    public String toString() {
        return "Loan ID: " + loanId + " | Member: " + member.getName() + " | Book: " + bookCopy.getBook().getTitle() + " | Checkout: " + checkoutDate + " | Due: " + dueDate + " | Returned: " + (returnDate == null ? "Not returned" : returnDate);
    }
}
