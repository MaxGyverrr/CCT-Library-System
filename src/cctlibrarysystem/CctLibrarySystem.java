/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cctlibrarysystem;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import pages.BookPage;
import pages.BorrowPage;
import pages.StudentPage;

/**
 * 
 * @author Maxwell
 * @author Renan
 */
public class BibliotecaNova {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("");
            System.out.println("########## CCT LIBRARY SYSTEM ##########");
            System.out.println("");
            System.out.println("PLEASE, CHOOSE AN OPTION: ");
            System.out.println("1. Books");
            System.out.println("2. Students");
            System.out.println("3. Borrowing / Wait List");
            System.out.println("");
            System.out.println("0. Exit");
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
                    BookPage bookPage = new BookPage();
                    bookPage.run();
                    break;
                case 2:
                    StudentPage studentsPage = new StudentPage();
                    studentsPage.run();
                    break;
                case 3:
                    BorrowPage borrowPage = new BorrowPage();
                    borrowPage.run();
                    break;
                case 0:
                    System.out.println("--------");
                    System.out.println("Goodbye!");
                    System.out.println("--------");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    System.out.println("---------------------------------");
                    break;
            }

            System.out.println(); // add a blank line after the menu
        }

        scanner.close();
    }
}
