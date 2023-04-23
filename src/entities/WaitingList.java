/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 * 
 * @author Maxwell
 * @author Renan
 */
public class WaitingList {

    private String id;
    private String studentname;
    private String bookID;
    private String booktitle;

    public WaitingList(String id, String studentname, String bookID, String booktitle) {
        this.id = id;
        this.studentname = studentname;
        this.bookID = bookID;
        this.booktitle = booktitle;
    }

    // getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentname;
    }

    public void setStudentName(String studentname) {
        this.studentname = studentname;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookTitle() {
        return booktitle;
    }

    public void setBookTitle(String booktitle) {
        this.booktitle = booktitle;
    }

    // toString method
    @Override
    public String toString() {
        return "Waiting List{"
                + "id=" + id
                + "bookid=" + bookID
                + ", name='" + booktitle + '\''
                + '}';
    }
}
