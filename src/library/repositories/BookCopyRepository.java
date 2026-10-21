package library.repositories;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import library.models.BookCopy;

public class BookCopyRepository {
    private final Map<String, BookCopy> copies = new HashMap<>();

    public void add(BookCopy copy) {
        if (copy == null) {
            throw new IllegalArgumentException("Book copy cannot be null");
        }

        if (copies.containsKey(copy.getCopyId())) {
            throw new IllegalArgumentException("Copy ID already exists");
        }

        copies.put(copy.getCopyId(), copy);
    }

    public BookCopy findById(String copyId) {
        return copies.get(copyId);
    }

    public boolean remove(String copyId) {
        return copies.remove(copyId) != null;
    }

    public Collection<BookCopy> findAll() {
        return copies.values();
    }

    public int count() {
        return copies.size();
    }
}
