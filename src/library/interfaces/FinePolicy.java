package library.interfaces;

import java.time.LocalDate;

public interface FinePolicy {
    double calculateFine(LocalDate dueDate, LocalDate returnDate);
}