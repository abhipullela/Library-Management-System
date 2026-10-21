package library.repositories;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import library.models.Book;

public class BookRepository {
    private final Map<String, Book> books = new HashMap<>();

    public void add(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }

        if (books.containsKey(book.getBookId())) {
            throw new IllegalArgumentException("Book ID already exists");
        }

        books.put(book.getBookId(), book);
    }

    public Book findById(String bookId) {
        return books.get(bookId);
    }

    public boolean remove(String bookId) {
        return books.remove(bookId) != null;
    }

    public Collection<Book> findAll() {
        return books.values();
    }

    public int count() {
        return books.size();
    }
}
