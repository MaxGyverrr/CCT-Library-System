/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pages;

import entities.Books;
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
public class BookPage {

    Search search = new Search();
    List<Books> booksList = search.readBooksFromCSV("MOCK_DATA.csv");

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("");
            System.out.println("########## BOOKS WINDOW ##########");
            System.out.println("");
            System.out.println("PLEASE, CHOOSE AN OPTION: ");
            System.out.println("1. List all Books");
            System.out.println("2. Sort by Authors");
            System.out.println("3. Sort by Title");
            System.out.println("4. Search for a Book");
            System.out.println("");
            System.out.println("0 - Go back to the main menu");
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
                    allBooks();
                    break;
                case 2:
                    byAuthor();
                    break;
                case 3:
                    byTitle();
                    break;
                case 4:
                    searchBook();
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

    public void allBooks() {
        // list all books
        System.out.println("Listing All Books:");
        System.out.println("__________________________________");
        for (Books book : booksList) {
            System.out.println("");
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthorFirstName() + " " + book.getAuthorLastName());
            System.out.println("Genre: " + book.getGenre());
            System.out.println("__________________________________");
        }
    }

    public void byAuthor() {
        // list authors sortByAuthor
        List<Books> sortByAuthor = sortByAuthor(booksList);
        System.out.println("Listing All Authors Sorted By Name:");
        System.out.println("__________________________________");
        for (Books book : sortByAuthor) {
            System.out.println("");
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthorFirstName() + " " + book.getAuthorLastName());
            System.out.println("Genre: " + book.getGenre());
            System.out.println("__________________________________");
        }
    }

    public void byTitle() {
        // list authors sortByTitle
        List<Books> sortedByTitle = sortByTitle(booksList);
        System.out.println("Listing All Books Sorted By Title:");
        System.out.println("__________________________________");
        for (Books book : sortedByTitle) {
            System.out.println("");
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthorFirstName() + " " + book.getAuthorLastName());
            System.out.println("Genre: " + book.getGenre());
            System.out.println("__________________________________");
        }
    }

    private List<Books> sortByTitle(List<Books> books) {
        for (int i = 0; i < books.size() - 1; i++) {
            for (int j = 0; j < books.size() - i - 1; j++) {
                // Compare titles case-insensitively
                if (books.get(j + 1).getTitle().compareToIgnoreCase(books.get(j).getTitle()) < 0) {
                    // Swap books in-place
                    Books temp = books.get(j);
                    books.set(j, books.get(j + 1));
                    books.set(j + 1, temp);
                }
            }
        }
        return books;
    }

    private List<Books> sortByAuthor(List<Books> books) {
        for (int i = 0; i < books.size() - 1; i++) {
            for (int j = 0; j < books.size() - i - 1; j++) {
                // Compare titles case-insensitively
                if (books.get(j + 1).getAuthorFirstName().compareToIgnoreCase(books.get(j).getAuthorFirstName()) < 0) {
                    // Swap books in-place
                    Books temp = books.get(j);
                    books.set(j, books.get(j + 1));
                    books.set(j + 1, temp);
                }
            }
        }
        return books;
    }

    public void searchBook() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the Author's Name: (If you don't know, just press Enter to continue!)");
        System.out.print("--> ");
        String authorname = scanner.nextLine();

        System.out.println("Enter Book Title: (If you don't know, just press Enter to continue!)");
        System.out.print("--> ");
        String booktitle = scanner.nextLine().trim();

        List<Books> result = new ArrayList<>();

        for (Books books : booksList) {
            if ((authorname.isEmpty() || books.getAuthorFirstName().concat(books.getAuthorLastName()).toLowerCase().contains(authorname.toLowerCase()))
                    && (booktitle.isEmpty() || books.getTitle().toLowerCase().contains(booktitle.toLowerCase()))) {
                result.add(books);
            }
        }

        if (result.isEmpty()) {
            System.out.println("No book found.");
        } else {
            System.out.println("Search results:");
            System.out.println("__________________________________");
            for (Books books : result) {
                System.out.println("");
                System.out.println("Title: " + books.getTitle());
                System.out.println("Author: " + books.getAuthorFirstName() + " " + books.getAuthorLastName());
                System.out.println("Genre: " + books.getGenre());
                System.out.println("__________________________________");
            }
        }
    }

}
