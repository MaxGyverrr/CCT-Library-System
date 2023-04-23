/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.time.LocalDate;

/**
 * 
 * @author Maxwell
 * @author Renan
 */
public class ReturnedBooks {

    private String studentId;
    private String studentName;
    private String bookId;
    private String title;
    private String authorfirstname;
    private String authorlastname;
    private String genre;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private boolean borrowed;

    public ReturnedBooks(String studentId, String studentName, String bookId, String title, String authorfirstname, String authorlastname, String genre, LocalDate borrowDate, LocalDate returnDate) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.bookId = bookId;
        this.title = title;
        this.authorfirstname = authorfirstname;
        this.authorlastname = authorlastname;
        this.genre = genre;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.borrowed = true; // book is marked as borrowed when a new borrowing is created
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorFirstName() {
        return authorfirstname;
    }

    public void setAuthorFirstName(String authorfirstname) {
        this.authorfirstname = authorfirstname;
    }

    public String getAuthorLastName() {
        return authorlastname;
    }

    public void setAuthorLastName(String authorlastname) {
        this.authorlastname = authorlastname;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isBorrowed() {
        return borrowDate != null;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public boolean isReturned() {
        return returnDate != null;
    }

    @Override
    public String toString() {
        String returnDateString = (returnDate == null) ? "not returned yet" : returnDate.toString();
        return "Borrowing{"
                + ", studentId=" + studentId
                + ", bookId='" + bookId + '\''
                + ", borrowDate=" + borrowDate
                + ", returnDate=" + returnDateString
                + '}';
    }
}
