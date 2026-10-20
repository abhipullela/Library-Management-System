package library.models;

public class BookCopy {
    public enum Status {
        AVAILABLE,
        ON_LOAN,
        LOST,
        DAMAGED
    }

    private final String copyId;
    private final Book book;
    private Status status;

    public BookCopy(String copyId, Book book) {
        if (copyId == null || copyId.isBlank()) {
            throw new IllegalArgumentException("Copy ID cannot be empty");
        }

        // this.book = Objects.requireNonNull(book, "Book cannot be null");
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }
        this.book = book;
        this.copyId = copyId;
        this.status = Status.AVAILABLE;
    };

    public String getCopyId() {
        return copyId;
    }

    public Book getBook() {
        return book;
    }

    public Status getStatus() {
        return status;
    }

    public boolean isAvailable() {
        return status == Status.AVAILABLE;
    }

    public void markAsBorrowed() {
        if (!isAvailable()) {
            throw new IllegalStateException("This copy is not available for borrowing");
        }
        status = Status.ON_LOAN;
    }

    public void markAsReturned() {
        if (status != Status.ON_LOAN) {
            throw new IllegalStateException("This copy is not currently on loan");
        }
        status = Status.AVAILABLE;
    }

    public void markAsLost() {
        if (status != Status.ON_LOAN && status != Status.AVAILABLE) {
            throw new IllegalStateException("This copy cannot be marked as lost");
        }
        status = Status.LOST;
    }

    public void markAsDamaged() {
        if (status == Status.LOST) {
            throw new IllegalStateException("A lost copy cannot be marked as damaged");
        }
        status = Status.DAMAGED;
    }

    @Override
    public String toString() {
        return "Copy ID: " + copyId + " | Book: " + book.getTitle() + " | Status: " + status;
        // return String.format("Copy ID: %s | Book: %s | Status: %s", copyId, book.getTitle(), status);
    }
}