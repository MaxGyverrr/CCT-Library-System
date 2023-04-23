/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package search;

import entities.Books;
import entities.Borrowings;
import entities.ReturnedBooks;
import entities.Students;
import entities.WaitingList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Maxwell
 * @author Renan
 */
public class Search {
    /**
     * Creating a method to read the data from the CSV file
     *
     * @param fileName
     */
    public static List<Borrowings> readBorrowFromCSV(String fileName) {
        List<Borrowings> borrowList = new ArrayList<>();
        String line;

        try {
            // If file don't exist it creates
            File yourFile = new File(fileName);
            yourFile.createNewFile(); // if file already exists will do nothing
        } catch (IOException e) {
            System.out.println("Error when trying to load CSV: " + e.getMessage());
        }

        try ( BufferedReader br = new BufferedReader(new FileReader("borrowed_books.csv"))) {
            // read the rest of the lines
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String studentId = data[0];
                String studentName = data[1];
                String bookId = data[2];
                String title = data[3];
                String authorfirstname = data[4];
                String authorlastname = data[5];
                String genre = data[6];
                String borDate = data[7];
                LocalDate borrowDate = LocalDate.parse(borDate);

                Borrowings borrowings = new Borrowings(studentId, studentName, bookId, title, authorfirstname, authorlastname, genre, borrowDate);
                borrowList.add(borrowings);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return borrowList;
    }

    public static List<Books> readBooksFromCSV(String fileName) {
        List<Books> booksList = new ArrayList<>();
        String line;

        try {
            // If file don't exist it creates
            File yourFile = new File(fileName);
            yourFile.createNewFile(); // if file already exists will do nothing
        } catch (IOException e) {
            System.out.println("Error when trying to load CSV: " + e.getMessage());
        }

        try ( BufferedReader br = new BufferedReader(new FileReader("MOCK_DATA.csv"))) {
            // read the first line (column headers)
            line = br.readLine();
            // read the rest of the lines
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String id = data[0];
                String authorFirstName = data[1];
                String authorLastName = data[2];
                String title = data[3];
                String genre = data[4];
                Books book = new Books(id, authorFirstName, authorLastName, title, genre);
                booksList.add(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return booksList;
    }

    public static List<Students> readStudentsFromCSV(String fileName) {
        List<Students> studentList = new ArrayList<>();
        String line;

        try {
            // If file don't exist it creates
            File yourFile = new File(fileName);
            yourFile.createNewFile(); // if file already exists will do nothing
        } catch (IOException e) {
            System.out.println("Error when trying to load CSV: " + e.getMessage());
        }

        try ( BufferedReader br = new BufferedReader(new FileReader("students.csv"))) {
            // read the rest of the lines
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String id = data[0];
                String name = data[1];
                String address = data[2];

                Students students = new Students(id, name, address);
                studentList.add(students);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    public static List<WaitingList> readWaitingListCSV(String fileName) {
        List<WaitingList> waitingLists = new ArrayList<>();
        String line;

        try {
            // If file don't exist it creates
            File yourFile = new File(fileName);
            yourFile.createNewFile(); // if file already exists will do nothing
        } catch (IOException e) {
            System.out.println("Error when trying to load CSV: " + e.getMessage());
        }

        try ( BufferedReader br = new BufferedReader(new FileReader("waitlist.csv"))) {
            // read the rest of the lines
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String studentId = data[0];
                String studentName = data[1];
                String bookId = data[2];
                String title = data[3];

                WaitingList waitingList = new WaitingList(studentId, studentName, bookId, title);
                waitingLists.add(waitingList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return waitingLists;
    }
    
    public static List<ReturnedBooks> readRetunedBooksFromCSV(String fileName) {
        List<ReturnedBooks> returnetList = new ArrayList<>();
        String line;

        try {
            // If file don't exist it creates
            File yourFile = new File(fileName);
            yourFile.createNewFile(); // if file already exists will do nothing
        } catch (IOException e) {
            System.out.println("Error when trying to load CSV: " + e.getMessage());
        }

        try ( BufferedReader br = new BufferedReader(new FileReader("returned_books.csv"))) {
            // read the rest of the lines
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String studentId = data[0];
                String studentName = data[1];
                String bookId = data[2];
                String title = data[3];
                String authorfirstname = data[4];
                String authorlastname = data[5];
                String genre = data[6];
                String borDate = data[7];
                String retDate = data[8];
                LocalDate borrowDate = LocalDate.parse(borDate);
                LocalDate returnDate = LocalDate.parse(retDate);

                ReturnedBooks returnings = new ReturnedBooks(studentId, studentName, bookId, title, authorfirstname, authorlastname, genre, borrowDate, returnDate);
                returnetList.add(returnings);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnetList;
    }

}

