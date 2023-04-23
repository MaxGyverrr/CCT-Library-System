/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pages;

import entities.Books;
import entities.Borrowings;
import entities.ReturnedBooks;
import entities.Students;
import entities.Waiting;
import entities.WaitingList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import search.Search;

/**
 *
 * @author Maxwell
 * @author Renan
 */
public class BorrowPage {

    Search csvBorrow = new Search();
    private List<Borrowings> borrowings;
    private List<Books> booksList;
    private List<Waiting> waitingArray = new ArrayList<>();
    private Search search = new Search();

    public BorrowPage() {
        this.borrowings = new ArrayList<>();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("");
            System.out.println("########## BORROWING WINDOW ##########");
            System.out.println("");
            System.out.println("PLEASE, CHOOSE AN OPTION: ");
            System.out.println("1. Borrow a Book");
            System.out.println("2. Return a Book");
            System.out.println("3. List all Borrowed Books");
            System.out.println("4. List all Returned Books");
            System.out.println("5. Wait List");
            System.out.println("");
            System.out.println("0. Return to main menu");
            System.out.println("");
            System.out.print("--> ");

            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                System.out.println("-------------------------------------");
                scanner.next(); // consume the invalid input
                continue; // restart the loop
            }

            switch (choice) {
                case 1:
                    borrowBook();
                    break;
                case 2:
                    returnBook();
                    break;
                case 3:
                    listBorrows();
                    break;
                case 4:
                    listReturnedBooks();
                    break;
                case 5:
                    listWaiting();
                    break;
                case 0:
                    System.out.println("-------------------------");
                    System.out.println("Returning to main menu...");
                    System.out.println("-------------------------");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    System.out.println("---------------------------------");
                    break;
            }
        }
    }

    public void borrowBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        System.out.println("****Borrowing a book****");
        System.out.println("Enter your Student ID:");
        System.out.print("--> ");
        String studentId = scanner.nextLine(); // consume the newline character

        System.out.println("Enter Book ID or Book Title to be borrowed:");
        System.out.print("--> ");
        String input = scanner.nextLine();

        Students student = null;
        Search csvStudents = new Search();
        List<Students> studentList = csvStudents.readStudentsFromCSV("students.csv");
        for (Students b : studentList) {
            if (b.getId().equalsIgnoreCase(studentId)) {
                student = b;
                break;
            }
        }

        if (student == null) {
            System.out.println("--------------------------------");
            System.out.println("Student not found in the system.");
            System.out.println("--------------------------------");
            return;
        }

        // Find the book in the system with the matching ID or title
        Books book = null;
        Search csvBook = new Search();
        List<Books> booksList = csvBook.readBooksFromCSV("MOCK_DATA.csv");

        for (Books b : booksList) {
            if (b.getId().equalsIgnoreCase(input) || b.getTitle().equalsIgnoreCase(input)) {
                book = b;
                break;
            }
        }

        if (book == null) {
            System.out.println("-----------------------------");
            System.out.println("Book not found in the system.");
            System.out.println("-----------------------------");
            return;
        }

        // Check if the book is already borrowed
        List<Borrowings> borrowlist = search.readBorrowFromCSV("borrowed_books.csv");
        List<WaitingList> waitingLists = search.readWaitingListCSV("waitlist.csv");
        WaitingList waitingList = null;
        for (Borrowings b : borrowlist) {
            if (b.getBookId().equalsIgnoreCase(book.getId()) && b.getReturnDate() == null) {
                // The book is already borrowed
                System.out.println("---------------------------------------------------------");
                System.out.println("This book is already borrowed by another student.");
                System.out.println("Do you want to add the student to the waiting list? (Y/N)");
                System.out.println("---------------------------------------------------------");
                System.out.print("--> ");
                String response = scanner.nextLine().toUpperCase();
                if (response.equals("Y")) {
                    boolean studentExists = false;
                    for (WaitingList wl : waitingLists) {
                        if (wl.getId().equalsIgnoreCase(studentId) && wl.getBookID().equalsIgnoreCase(book.getId())) {
                            studentExists = true;
                            break;
                        }
                    }
                    if (studentExists) {
                        System.out.println("----------------------------------------------------------");
                        System.out.println("This student is already on the waiting list for this book.");
                        System.out.println("----------------------------------------------------------");
                        return;
                    }
                    Waiting waiting = null;
                    for (Waiting w : waitingArray) {
                        if (w.getBookId().equals(book.getId())) {
                            waiting = w;
                            break;
                        }
                    }
                    if (waiting == null) {
                        waiting = new Waiting(student.getName(), book.getId(), book.getTitle());
                        waitingArray.add(waiting);
                    }
                    waiting.addStudentIdToWaitingList(studentId);

                    System.out.println("------------------------------------------------------");
                    System.out.println("You have been added to the waiting list for this book.");
                    waiting.printWaitingList();

                    try ( PrintWriter writer = new PrintWriter(new FileWriter("waitlist.csv", true))) {
                        writer.print(studentId); // Getting Students ID
                        writer.print("," + student.getName()); // Getting Books ID
                        writer.print("," + book.getId()); // Getting Books ID
                        writer.print("," + book.getTitle()); // Getting Books Title
                        writer.println();
                    } catch (IOException e) {
                        System.out.println("Error writing to file: " + e.getMessage());
                    }
                }
                return;
            }
        }

        // Create a new Borrowings object with the Books object and the current date
        Borrowings borrowing = new Borrowings(studentId, student.getName(), book.getId(), book.getTitle(), book.getAuthorFirstName(), book.getAuthorLastName(), book.getGenre(), LocalDate.now());

        // Add the Borrowings object to the borrowings list
        borrowings.add(borrowing);

        // Write the borrowed book details to a text file
        try ( PrintWriter writer = new PrintWriter(new FileWriter("borrowed_books.csv", true))) {
            //Borrowed Book
            writer.print(studentId); // Getting Students ID
            writer.print("," + student.getName());
            writer.print("," + book.getId()); // Getting Books ID
            writer.print("," + book.getTitle()); // Getting Books Title
            writer.print("," + book.getAuthorFirstName()); // Getting Author First Name
            writer.print("," + book.getAuthorLastName()); // Getting Author Last Name
            writer.print("," + book.getGenre()); // Getting Genre from Books
            writer.print("," + LocalDate.now().toString()); // Getting the Borrow Date
            writer.println();

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
        System.out.println("---------------------------");
        System.out.println("Book borrowed successfully!");
        System.out.println("---------------------------");
    }

    public void returnBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        System.out.println("****Returning a book****");
        System.out.println("Enter your Student ID:");
        System.out.print("--> ");
        String studentId = scanner.nextLine();

        System.out.println("Enter Book Title to be returned:");
        System.out.print("--> ");
        String bookTitle = scanner.nextLine();

        // Find the Borrowings object in the borrowings list with the matching student ID and book title
        List<Borrowings> borrowlist = search.readBorrowFromCSV("borrowed_books.csv");
        Borrowings borrowing = null;
        for (Borrowings b : borrowlist) {
            if (b.getStudentId().equalsIgnoreCase(studentId) && b.getTitle().equalsIgnoreCase(bookTitle)) {
                borrowing = b;
                break;
            }
        }

        if (borrowing == null) {
            System.out.println("--------------------------------");
            System.out.println("No matching borrow record found.");
            System.out.println("--------------------------------");
            return;
        }

        // Remove the Borrowings object from the borrowings list
        borrowlist.remove(borrowing);

        // Write the updated borrowings list to the borrowed_books.csv file
        try ( PrintWriter writer = new PrintWriter(new FileWriter("borrowed_books.csv"))) {
            for (Borrowings b : borrowlist) {
                writer.print(b.getStudentId()); // Getting Students ID
                writer.print("," + b.getStudentName()); // Getting Students Name
                writer.print("," + b.getBookId()); // Getting Books ID
                writer.print("," + b.getTitle()); // Getting Books Title
                writer.print("," + b.getAuthorFirstName()); // Getting Author First Name
                writer.print("," + b.getAuthorLastName()); // Getting Author Last Name
                writer.print("," + b.getGenre()); // Getting Genre from Books
                writer.print("," + b.getBorrowDate().toString()); // Borrow Date
                writer.println();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        // Write the returned book details to a text file
        try ( PrintWriter writer = new PrintWriter(new FileWriter("returned_books.csv", true))) {
            writer.print(studentId); // Getting Students ID
            writer.print("," + borrowing.getStudentName()); // Getting Students Name
            writer.print("," + borrowing.getBookId()); // Getting Books ID
            writer.print("," + borrowing.getTitle()); // Getting Books Title
            writer.print("," + borrowing.getAuthorFirstName()); // Getting Author First Name
            writer.print("," + borrowing.getAuthorLastName()); // Getting Author Last Name
            writer.print("," + borrowing.getGenre()); // Getting Genre from Books
            writer.print("," + LocalDate.now().toString()); // Borrow Date
            writer.print("," + borrowing.getBorrowDate().toString()); //Return Date
            writer.println();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        // Check if there are any students on the waitlist for the returned book
        List<WaitingList> waitlist = search.readWaitingListCSV("waitlist.csv");
        WaitingList nextStudent = null;
        for (WaitingList w : waitlist) {
            if (w.getBookTitle().equalsIgnoreCase(bookTitle)) {
                nextStudent = w;
                break;
            }
        }

        if (nextStudent == null) {
            System.out.println("---------------------------");
            System.out.println("Book returned successfully!");
            System.out.println("---------------------------");
            return;
        }

        // Ask the next student if they want to borrow the book
        System.out.println("---------------------------------------------------");
        System.out.println("The next student on the waitlist for this book is: ");
        System.out.println("--> " + nextStudent.getStudentName());
        String answer = "";
        boolean validInput = false;

        while (!validInput) {
            System.out.println("---------------------------------------------------");
            System.out.println("Do you want to lend the book to this student? (Y/N)");
            System.out.print("--> ");
            answer = scanner.nextLine().toLowerCase();

            if (answer.equals("y") || answer.equals("n")) {
                validInput = true;
            } else {
                System.out.println("Invalid input. Please enter Y or N.");
            }
        }

        if (answer.equals("y")) {
            // Remove the next student from the waitlist
            waitlist.remove(nextStudent);

            // Add the book to the borrowings list with the next student as the borrower
            Borrowings newBorrowing = new Borrowings(nextStudent.getId(), nextStudent.getStudentName(), nextStudent.getBookID(), nextStudent.getBookTitle(), borrowing.getAuthorFirstName(), borrowing.getAuthorLastName(), borrowing.getGenre(), LocalDate.now());
            borrowlist.add(newBorrowing);

            // Write the updated borrowings list to the borrowed_books.csv file
            try ( PrintWriter writer = new PrintWriter(new FileWriter("borrowed_books.csv"))) {
                for (Borrowings b : borrowlist) {
                    writer.print(b.getStudentId()); // Getting Students ID
                    writer.print("," + b.getStudentName()); // Getting Students Name
                    writer.print("," + b.getBookId()); // Getting Books ID
                    writer.print("," + b.getTitle()); // Getting Books Title
                    writer.print("," + b.getAuthorFirstName()); // Getting Author First Name
                    writer.print("," + b.getAuthorLastName()); // Getting Author Last Name
                    writer.print("," + b.getGenre()); // Getting Genre from Books
                    writer.print("," + b.getBorrowDate().toString()); // Borrow Date
                    writer.println();
                }
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
            // Write the updated waitlist to the waitlist.csv file
            try ( PrintWriter writer = new PrintWriter(new FileWriter("waitlist.csv"))) {
                for (WaitingList w : waitlist) {
                    writer.print(w.getId()); // Getting Students ID
                    writer.print("," + w.getStudentName()); // Getting Students Name
                    writer.print("," + w.getBookID()); // Getting Books ID
                    writer.print("," + w.getBookTitle()); // Getting Books Title
                    writer.println();
                }
                writer.close();
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        } else if (answer.equals("n")) {
            // Remove the student from the waitlist
            waitlist.remove(nextStudent);

            // Write the updated waitlist to the waitlist.csv file
            try ( PrintWriter writer = new PrintWriter(new FileWriter("waitlist.csv"))) {
                for (WaitingList w : waitlist) {
                    writer.print(w.getId()); // Getting Students ID
                    writer.print("," + w.getStudentName()); // Getting Students Name
                    writer.print("," + w.getBookID()); // Getting Books ID
                    writer.print("," + w.getBookTitle()); // Getting Books Title
                    writer.println();
                }
                writer.close();
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        }

    }

    public void listBorrows() {
        // list all borrowed books
        List<Borrowings> borrowlist = search.readBorrowFromCSV("borrowed_books.csv");
        System.out.println("---------------------------");
        System.out.println("List of all borrowed books:");
        System.out.println("---------------------------");

        for (Borrowings borrowing : borrowlist) {
            System.out.println("");
            System.out.println("Student ID: " + borrowing.getStudentId());
            System.out.println("Student Name: " + borrowing.getStudentName());
            System.out.println("Book ID: " + borrowing.getBookId());
            System.out.println("Book Title: " + borrowing.getTitle());
            System.out.println("Author First Name: " + borrowing.getAuthorFirstName());
            System.out.println("Author Last Name: " + borrowing.getAuthorLastName());
            System.out.println("Genre: " + borrowing.getGenre());
            System.out.println("----->> Borrow Date: " + borrowing.getBorrowDate() + " ----->>");
            System.out.println("__________________________________");

        }
    }

    public void listReturnedBooks() {
        // list all borrowed books
        List<ReturnedBooks> returnedList = search.readRetunedBooksFromCSV("returned_books.csv");
        System.out.println("---------------------------");
        System.out.println("List of all returned books:");
        System.out.println("---------------------------");

        for (ReturnedBooks returnedBooks : returnedList) {
            System.out.println("");
            System.out.println("Student ID: " + returnedBooks.getStudentId());
            System.out.println("Student Name: " + returnedBooks.getStudentName());
            System.out.println("Book ID: " + returnedBooks.getBookId());
            System.out.println("Book Title: " + returnedBooks.getTitle());
            System.out.println("Author First Name: " + returnedBooks.getAuthorFirstName());
            System.out.println("Author Last Name: " + returnedBooks.getAuthorLastName());
            System.out.println("Genre: " + returnedBooks.getGenre());
            System.out.println("------>> Borrow Date: " + returnedBooks.getBorrowDate() + " ----->>");
            System.out.println("<<--- Return Date: " + returnedBooks.getReturnDate() + " <<-----");
            System.out.println("__________________________________");

        }
    }

    public void listWaiting() {
        // list all borrowed books
        List<WaitingList> waitingLists = search.readWaitingListCSV("waitlist.csv");
        System.out.println("------------------------------");
        System.out.println("List of all book on wait list:");
        System.out.println("------------------------------");

        for (WaitingList waitingList : waitingLists) {
            System.out.println("");
            System.out.println("Student ID: " + waitingList.getId());
            System.out.println("Student Name: " + waitingList.getStudentName());
            System.out.println("Book ID: " + waitingList.getBookID());
            System.out.println("Book Title: " + waitingList.getBookTitle());
            System.out.println("__________________________________");

        }
    }
}

