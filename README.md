<<<<<<< HEAD

=======
>>>>>>> 95c4ade (commit 1: project setup and README)
# 📚 Library Management System

A Java OOP project implementing a fully functional library system.

## Features
- Book management (add/remove/search)
- Member management (Student & Teacher)
- Borrow & Return system
- Fine calculation (₹2/day after 14 days)

## OOP Concepts Used
- Classes & Objects
- Encapsulation
- Inheritance
- Polymorphism
- Interfaces
- Custom Exceptions

## Project Structure
src/library/
├── models/        → Book, Member, Student, Teacher
├── interfaces/    → Borrowable, Searchable
├── services/      → LibraryService, FineCalculator
├── exceptions/    → Custom exceptions
└── Main.java      → Entry point
└── Main.java      → Entry point

## Detailed Proposed Project Structure
src/
└── library/
    ├── Main.java
    ├── models/
    │   ├── Book.java
    │   ├── BookCopy.java
    │   ├── Member.java
    │   ├── Student.java
    │   ├── Teacher.java
    │   └── Loan.java
    ├── interfaces/
    │   ├── Searchable.java
    │   └── FinePolicy.java
    ├── services/
    │   ├── LibraryService.java
    │   ├── LoanService.java
    │   └── FineCalculator.java
    ├── repositories/
    │   ├── BookRepository.java
    │   ├── MemberRepository.java
    │   └── LoanRepository.java
    ├── exceptions/
    │   ├── BookNotFoundException.java
    │   ├── BookUnavailableException.java
    │   ├── MemberNotFoundException.java
    │   └── LoanException.java
    └── persistence/
        └── FileStorage.java

test/
└── library/
    ├── services/
    └── models/

docs/
└── class-diagram.md
