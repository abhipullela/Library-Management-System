package library.models;

import library.interfaces.Searchable;

public class Book implements Searchable {
    private String bookId;
    private String title;
    private String author;
    private String genre;

    public Book (String BookId, String title, String author, String genre) {
        this.bookId = BookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
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

    @Override
    public String toString() {
        return String.format("[%s] \"%s\" by %s | %s ",
            bookId, title, author, genre);
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