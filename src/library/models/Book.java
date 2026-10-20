package library.models;

import java.time.LocalDate;
import library.interfaces.Searchable;

public class Book implements Searchable {
    private String bookId;
    private String title;
    private String author;
    private String genre;
    private boolean isAvailable;
    private LocalDate borrowDate;

    public Book (String BookId, String title, String author, String genre, boolean isAvailable, LocalDate BorrowDate) {
        this.bookId = BookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isAvailable = isAvailable;
        this.borrowDate = BorrowDate;   
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setAvailable(boolean available)  { this.isAvailable = available; }
    public void setBorrowedDate(LocalDate date)  { this.borrowDate = date; }

    @Override
    public String toString() {
        return String.format("[%s] \"%s\" by %s | %s | %s",
            bookId, title, author, genre,
            isAvailable ? "Available" : "Borrowed");
    }   

    @Override 
    public boolean matches(String query) {
        if (query==null || query.isBlank()) {
            return false;
        }

        String search = query.toLowerCase();

        return title.toLowerCase().contains(search) || author.toLowerCase().contains(search) || genre.toLowerCase().contains(search) || bookId.toLowerCase().contains(search);
    }
}