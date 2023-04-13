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
		System.out.println("");
		System.out.println("1) Books");
		System.out.println("3) Students");
		System.out.println("5) Borrows list");
		System.out.println("7) Waiting list");
		System.out.println("");
		System.out.println("Q) Exit");
		System.out.println("");
		System.out.print("-> ");
		return Window.read();
	}

	/**
	 * Main method used to start the application
	 * @param args
	 */
	public static void main(String[] args) {
		
		BookWindow bookWindow = new BookWindow();
		BorrowWindow borrowWindow = new BorrowWindow();
		WaitingWindow waitWindow = new WaitingWindow();
		ReaderWindow readerWindow = new ReaderWindow();
		
		try {
			String mainResponse = null;
			do {
		        mainResponse = printMainScreen();
		        switch(mainResponse.toUpperCase()) {
		        case "1":
		        	bookWindow.show();
		        	break;
		        case "3":
		        	readerWindow.show();
		        	break;
		        case "5":
		        	borrowWindow.show();
		        	break;
		        case "7":
		        	waitWindow.show();
		        	break;
		        }
			} while (mainResponse != null && !mainResponse.equalsIgnoreCase("Q"));
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
                System.out.println("teste");
	}

}
