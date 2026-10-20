package library.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import library.interfaces.FinePolicy;

public class StandardFinePolicy implements FinePolicy {
    private static final double FINE_PER_DAY = 2.0;

    @Override
    public double calculateFine(LocalDate dueDate, LocalDate returnDate) {
        if (dueDate == null || returnDate == null) {
            throw new IllegalArgumentException("Dates cannot be null");
        }
        if (!returnDate.isAfter(dueDate)) {
            return 0.0;
        }

        long overdueDays = ChronoUnit.DAYS.between(dueDate, returnDate);
        return overdueDays * FINE_PER_DAY;
    }
}