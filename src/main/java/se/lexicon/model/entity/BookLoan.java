package se.lexicon.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class BookLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer loanId;

    private LocalDate loanDate;

    private LocalDate dueDate;

    private boolean returned;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    private AppUser borrower;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    private Book book;

    public BookLoan() {
    }

    public BookLoan(LocalDate loanDate, LocalDate dueDate, AppUser borrower, Book book) {
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returned = false;
        this.borrower = borrower;
        borrower.addLoanOfBook(this);
        this.book = book;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public AppUser getBorrower() {
        return borrower;
    }

    public void setBorrower(AppUser borrower) {
        this.borrower = borrower;
        borrower.addLoanOfBook(this);
        this.returned = false;
    }
    public void removeBorrower(){
        this.borrower.returnBookLoan(this);
        this.returned = true;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookLoan bookLoan = (BookLoan) o;
        return returned == bookLoan.returned && Objects.equals(loanId, bookLoan.loanId) && Objects.equals(loanDate, bookLoan.loanDate) && Objects.equals(dueDate, bookLoan.dueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanId, loanDate, dueDate, returned);
    }
}