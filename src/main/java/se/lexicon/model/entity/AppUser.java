package se.lexicon.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appUserId;

    @Column(unique = true)
    private String username;

    private String password;

    private LocalDate regDate;

    @OneToOne(cascade = CascadeType.ALL)
    private Details userDetails;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH}, mappedBy = "borrower", fetch = FetchType.EAGER)
    private List<BookLoan> loans = new ArrayList<>();

    public AppUser() {
    }

    public AppUser(String username, String password, LocalDate regDate, String email, String name, LocalDate birthDay) {
        this.username = username;
        this.password = password;
        this.regDate = regDate;
        this.setUserDetails(new Details(email, name, birthDay));
    }

    public int getAppUserId() {
        return appUserId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public Details getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(Details userDetails) {
        this.userDetails = userDetails;
    }

    public void removeUserDetails(){
        this.userDetails = null;
    }

    public void addLoanOfBook(BookLoan bookLoan){
        loans.add(bookLoan);
        bookLoan.setBorrower(this);
        bookLoan.setReturned(false);
    }
    public void returnBookLoan(BookLoan bookLoan){
        bookLoan.setReturned(true);
        loans.remove(bookLoan);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return appUserId == appUser.appUserId && Objects.equals(username, appUser.username) && Objects.equals(password, appUser.password) && Objects.equals(regDate, appUser.regDate) && Objects.equals(userDetails, appUser.userDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appUserId, username, password, regDate, userDetails);
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "appUserId=" + appUserId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", regDate=" + regDate +
                ", userDetails=" + userDetails +
                '}';
    }
}
