/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cctlibrarysystem;

import java.io.IOException;

/**
 *
 * @author Max William
 */
public class CctLibrarySystem {

    /**
	 * Prints the main menu
	 * @return the user response based on the options
	 * @throws IOException
	 */
	public static String printMainScreen() throws IOException {
		System.out.println("#### Welcome to CCT Library System ####");
		System.out.println("---------------------------------------");
		System.out.println("1) Books");
		System.out.println("2) Students");
		System.out.println("3) Borrows List");
		System.out.println("4) Waiting List");
		System.out.println("-------");
		System.out.println("Q) Exit");
		System.out.println("-------");
		System.out.print("--> ");
		return Page.read();
	}

	/**
	 * Main method used to start the application
	 * @param args
	 */
	public static void main(String[] args) {
		
		BookPage bookPage = new BookPege();
		BorrowPage borrowPage = new BorrowPage();
		WaitingPage waitPage = new WaitingPage();
		StudentPage studentPage = new StudentPage();
		
		try {
			String mainResponse = null;
			do {
		        mainResponse = printMainScreen();
		        switch(mainResponse.toUpperCase()) {
		        case "1":
		        	bookPage.show();
		        	break;
		        case "2":
		        	studentPage.show();
		        	break;
		        case "3":
		        	borrowPage.show();
		        	break;
		        case "4":
		        	waitPage.show();
		        	break;
		        }
			} while (mainResponse != null && !mainResponse.equalsIgnoreCase("Q"));
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

}
