package library.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import library.models.Book;
import library.models.BookCopy;
import library.models.Member;
import library.repositories.BookRepository;
import library.repositories.BookCopyRepository;
import library.repositories.MemberRepository;
import library.exceptions.BookNotFoundException;

public class LibraryService {
    private final BookRepository bookRepository;
    private final BookCopyRepository copyRepository;
    private final MemberRepository memberRepository;

    public LibraryService(BookRepository bookRepository, BookCopyRepository copyRepository, MemberRepository memberRepository) {
        if (bookRepository == null || copyRepository == null || memberRepository == null) {
            throw new IllegalArgumentException("Repositories cannot be null");
        }
        this.bookRepository = bookRepository;
        this.copyRepository = copyRepository;
        this.memberRepository = memberRepository;
    }

    public void addBook(Book book) {
        bookRepository.add(book);
    }

    public void addBookCopy(BookCopy copy) {
        if (copy == null) {
            throw new IllegalArgumentException("Book copy cannot be null");
        }
        String bookId = copy.getBook().getBookId();
        if (bookRepository.findById(bookId) == null) {
            throw new BookNotFoundException("Book must be registered before adding its copies");
        }
        copyRepository.add(copy);
    }

    public Book findBookById(String bookId) {
        return bookRepository.findById(bookId);
    }

    public Collection<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> searchBooks(String query) {
        List<Book> results = new ArrayList<>();

        for (Book book : bookRepository.findAll()) {
            if (book.matches(query)) {
                results.add(book);
            }
        }

        return results;
    }

    public void registerMember(Member member) {
        memberRepository.add(member);
    }

    public Member findMemberById(String memberId) {
        return memberRepository.findById(memberId);
    }

    public Collection<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public BookCopy findCopyById(String copyId) {
        return copyRepository.findById(copyId);
    }

    public Collection<BookCopy> getAllCopies() {
        return copyRepository.findAll();
    }
}
