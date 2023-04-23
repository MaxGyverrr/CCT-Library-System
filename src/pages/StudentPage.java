/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pages;

import entities.Students;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import search.Search;

/**
 *
 * @author Maxwell
 * @author Renan
 */
public class StudentPage {

    Search search = new Search();
    List<Students> studentList = search.readStudentsFromCSV("students.csv");

    public void run() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("");
            System.out.println("########## STUDENT WINDOW ##########");
            System.out.println("");
            System.out.println("PLEASE, CHOOSE AN OPTION: ");
            System.out.println("1. List all Students");
            System.out.println("2. Search for a Student");
            System.out.println("3. Register a new Student");
            System.out.println("");
            System.out.println("0. Go back to the main menu");
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
                    listStudents();
                    break;
                case 2:
                    searchStudents();
                    break;
                case 3:
                    addStudent();
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

    public void addStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("****Adding a Student***");
        System.out.println("Enter student name:");
        System.out.print("--> ");
        String name = scanner.nextLine();

        System.out.println("Enter student address:");
        System.out.print("--> ");
        String address = scanner.nextLine();

        // Determine the ID for the new student
        int lastId = 0;
        if (!studentList.isEmpty()) {
            Students lastStudent = studentList.get(studentList.size() - 1);
            lastId = Integer.parseInt(lastStudent.getId());
        }
        int newId = lastId + 1;

        // Create the new student object
        Students student = new Students(Integer.toString(newId), name, address);

        // Add the new student to the list
        studentList.add(student);

        // Write the updated list back to the CSV file
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("students.csv", false));
            for (Students s : studentList) {
                writer.write(s.getId() + "," + s.getName() + "," + s.getAddress());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
        System.out.println("---------------------------");
        System.out.println("Student added successfully.");
        System.out.println("---------------------------");
    }

    public void listStudents() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------------");
        System.out.println("Sort by (1)Name or (2)ID:");
        System.out.println("-------------------------");
        System.out.print("--> ");
        int sortOption = 0;
        try {
            sortOption = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            System.out.println("-------------------------------------");
            scanner.next(); // consume the invalid input
            return;
        }

        if (sortOption == 1) {
            sortStudentByName(studentList);
        } else if (sortOption == 2) {

            List<Students> sortid = new ArrayList<>();

            Comparator<Students> byID = Comparator.comparing(Students::getId);
            Collections.sort(sortid, byID);

        } else {
            System.out.println("---------------");
            System.out.println("Invalid option.");
            System.out.println("---------------");
            return;
        }

        if (studentList.isEmpty()) {
            System.out.println("------------------");
            System.out.println("No students found.");
            System.out.println("------------------");
        } else {
            System.out.println("");
            System.out.println("########## LIST OF STUDENTS ##########");
            System.out.println("__________________________________");
            for (Students student : studentList) {
                System.out.println("");
                System.out.println("Student ID: " + student.getId());
                System.out.println("Name: " + student.getName());
                System.out.println("Address: " + student.getAddress());
                System.out.println("__________________________________");

            }
        }
    }

    public void searchStudents() throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter student ID: (If you don't know, just press Enter to continue!)");
        System.out.print("--> ");
        String idStr = scanner.nextLine();

        System.out.println("Enter student Name: (If you don't know, just press Enter to continue!)");
        System.out.print("--> ");
        String name = scanner.nextLine().trim();

        List<Students> result = new ArrayList<>();

        for (Students student : studentList) {
            if ((idStr.isEmpty() || student.getId().equals(idStr))
                    && (name.isEmpty() || student.getName().toLowerCase().contains(name.toLowerCase()))) {
                result.add(student);
            }
        }

        if (result.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("Search results:");
            System.out.println("__________________________________");
            for (Students student : result) {
                System.out.println("");
                System.out.println("Student ID: " + student.getId());
                System.out.println("Name: " + student.getName());
                System.out.println("Address: " + student.getAddress());
                System.out.println("__________________________________");
            }
        }
    }

    private List<Students> sortStudentByName(List<Students> students) {
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = 0; j < students.size() - i - 1; j++) {
                // Compare names case-insensitively
                if (students.get(j + 1).getName().compareToIgnoreCase(students.get(j).getName()) < 0) {
                    // Swap students in-place
                    Students temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
        return students;
    }

}
