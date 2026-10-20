package library.models;

public class Student extends Member {
    private final String studentId;

    public Student(String memberId, String name, String email, String studentId) {
        super(memberId, name, email);
        if (studentId == null || studentId.isBlank()) {
            throw new IllegalArgumentException("Student ID cannot be empty");
        }
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }

    @Override 
    public int getBorrowingLimit() {
        return 3;
    }

    @Override
    public int getLoanPeriodDays() {
        return 14;
    }

    @Override 
    public String toString() {
        return "Student | " + super.toString() + " | Student ID: " + studentId;
    }
}
