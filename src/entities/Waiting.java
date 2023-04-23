/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Maxwell
 * @author Renan
 */
public class Waiting {

    private List<String> studentIds;
    private List<String> nameStudent;
    private List<String> bookId;
    private List<String> bookTitle;

    public Waiting(String nameStudent, String bookId, String bookTitle) {
        this.studentIds = new ArrayList<>();
        this.nameStudent = new ArrayList<>();
        this.bookId = new ArrayList<>();
        this.bookTitle = new ArrayList<>();
        this.nameStudent.add(nameStudent);
        this.bookId.add(bookId);
        this.bookTitle.add(bookTitle);
    }

    public void addStudentIdToWaitingList(String studentId) {
        studentIds.add(studentId);
    }

    public List<String> getStudentIds() {
        return studentIds;
    }

    public List<String> getNameStudent() {
        return nameStudent;
    }

    public List<String> getBookId() {
        return bookId;
    }

    public List<String> getBookTitle() {
        return bookTitle;
    }

    public void printWaitingList() {
        for (int i = 0; i < studentIds.size(); i++) {
            System.out.println("Student ID: " + studentIds.get(i));
            System.out.println("Student Name: " + nameStudent.get(i));
            System.out.println("Book Title: " + bookTitle.get(i));
            System.out.println("__________________________________");
        }
    }
}
