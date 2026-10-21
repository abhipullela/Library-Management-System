package library.repositories;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import library.models.Loan;

public class LoanRepository {
    private final Map<String, Loan> loans = new HashMap<>();

    public void add(Loan loan) {
        if (loan == null) {
            throw new IllegalArgumentException("Loan cannot be null");
        }

        if (loans.containsKey(loan.getLoanId())) {
            throw new IllegalArgumentException("Loan ID already exists");
        }

        loans.put(loan.getLoanId(), loan);
    }

    public Loan findById(String loanId) {
        return loans.get(loanId);
    }

    public boolean remove(String loanId) {
        return loans.remove(loanId) != null;
    }

    public Collection<Loan> findAll() {
        return loans.values();
    }

    public int count() {
        return loans.size();
    }
}
