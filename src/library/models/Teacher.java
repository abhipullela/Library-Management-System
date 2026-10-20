package library.models;

public class Teacher extends Member {
    private final String employeeId;

    public Teacher(String memberId, String name, String email, String employeeId) {
        super(memberId, name, email);
        if (employeeId == null || employeeId.isBlank()) {
            throw new IllegalArgumentException("Employee ID cannot be empty");
        }
        this.employeeId = employeeId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    @Override
    public int getBorrowingLimit() {
        return 5;
    }

    @Override
    public int getLoanPeriodDays() {
        return 30;
    }

    @Override
    public String toString() {
        return String.format("Teacher | %s | Employee ID: %s" ,super.toString() ,employeeId);
    }
}
