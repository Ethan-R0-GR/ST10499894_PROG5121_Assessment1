/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.st10499894_prog5121_assessment1;

import java.util.Scanner;

/**
 *
 * @author RC_Student_lab
 */
public class ST10499894_PROG5121_Assessment1 {

     // Create objects we'll use later in the program
    private static Login loginSystem = new Login(); // Calls the login class we created.
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        //Creates a little welcome message when the program starts
        System.out.println("====================================");
        System.out.println("Welcome to Chat App :)");
        System.out.println("====================================");
        
        // Main program loop
        while(true) {
            // Show 3 menu options
            System.out.println("\nChoose an option :"); // \n is for newline
            System.out.println("1. Register new user");
            System.out.println("2. Login existing user");
            System.out.println("3. Exit program");
            System.out.print("Enter your choice (1, 2, or 3): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // This clears the enter key press from memory
            
            // the switch works with the choice to run the correct method
            switch(choice) {
                case 1:
                    registerNewUser(); // Call our registration method
                    break;
                case 2:
                    loginExistingUser(); // Call our login method
                    break;
                case 3:
                    System.out.println("Thank you for using Chat App! Goodbye!");
                    return; // Exit the program
                default:
                    System.out.println("Invalid option. Please choose 1, 2, or 3.");
            }
        }
    }
    
    // This  method handles user registration and calling the method for invalid inputs
    private static void registerNewUser() {
        System.out.println("\n--- USER REGISTRATION ---");
        
        System.out.print("Enter username (must contain _ and be max 5 chars): ");
        String username = scanner.nextLine(); //get the username
        
        System.out.print("Enter password (min 8 chars, need capital, number, special): ");
        String password = scanner.nextLine(); //get the password
        
        System.out.print("Enter cell phone number (format: +27xxxxxxxxx): ");
        String cellPhone = scanner.nextLine(); //get the phone number
        
        // Try to register the user
        String result = loginSystem.registerUser(username, password, cellPhone);//this method is on the login class, registeruser returns a string that is then used in the print below
        System.out.println("\nRegistration Result:");
        System.out.println(result);
    }
    
    // Method to handle existing user login
    private static void loginExistingUser() {
        System.out.println("\n--- USER LOGIN ---");
        
        System.out.print("Enter your username: ");
        String username = scanner.nextLine(); // get the username
        
        System.out.print("Enter your password: ");
        String password = scanner.nextLine(); //get the password
        
        // Try to login the user
        boolean loginSuccess = loginSystem.loginUser(username, password); //this method is on the login class
        String loginMessage = loginSystem.returnLoginStatus(loginSuccess, username);//this method is on the login class
        
        System.out.println("\nLogin Result:");
        System.out.println(loginMessage);
    }
}
