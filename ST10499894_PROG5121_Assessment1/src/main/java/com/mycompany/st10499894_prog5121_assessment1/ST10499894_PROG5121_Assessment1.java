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
    private static Login loginSystem = new Login();
    private static Message messageSystem = new Message();
    private static Scanner scanner = new Scanner(System.in);
    private static boolean isLoggedIn = false;
    private static String currentUser = "";
    
    public static void main(String[] args) {
        // Creates a little welcome message when the program starts
        System.out.println("====================================");
        System.out.println("Welcome to Chat App :)");
        System.out.println("====================================");
        
        // Main program loop
        while(true) {
            if(!isLoggedIn) {
                // Show login/register menu if not logged in
                showLoginMenu();
            } else {
                // Show message menu if logged in
                showMessageMenu();
            }
        }
    }
    
    // menu for login, registration or to exit prohram using JOptionPane, this replaced my console menu
    private static void showLoginMenu() {
        String[] options = {"Register new user", "Login existing user", "Exit program"};
        int choice = javax.swing.JOptionPane.showOptionDialog(
            null,
            "Welcome to Chat App :)\n\nPlease choose an option:",
            "Chat App - Main Menu",
            javax.swing.JOptionPane.DEFAULT_OPTION,
            javax.swing.JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[0]
        );
        
        
        // the switch works with the choice to run the correct method
        switch(choice) {
            case 0:
                registerNewUser();
                break;
            case 1:
                loginExistingUser();
                break;
            case 2:
            case javax.swing.JOptionPane.CLOSED_OPTION:
                System.out.println("Thank you for using Chat App! Goodbye!");
                System.exit(0);
            default:
                javax.swing.JOptionPane.showMessageDialog(null, 
                    "Invalid option. Please choose 1, 2, or 3.",
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // menu for messaging that appears after being logged in
    private static void showMessageMenu() {
        System.out.println("\n====================================");
        System.out.println(" MESSAGING MENU ");
        System.out.println("====================================");
        System.out.println("1. Send new message");
        System.out.println("2. View all sent messages");
        System.out.println("3. View total messages sent");
        System.out.println("4. Logout");
        System.out.println("5. Exit program");
        System.out.print("Enter your choice: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Clear memory? buffer? not sure what to call it
        
        switch(choice) {
            case 1:
                sendNewMessage();
                break;
            case 2:
                viewAllMessages();
                break;
            case 3:
                viewTotalMessages();
                break;
            case 4:
                logout();
                break;
            case 5:
                System.out.println("Thank you for using Chat App! Goodbye!");
                System.exit(0);
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
    
    //handle user registration
    private static void registerNewUser() {
        System.out.println("\n--- USER REGISTRATION ---");
        
        System.out.print("Enter username (must contain _ and be max 5 chars): ");
        String username = scanner.nextLine();
        
        System.out.print("Enter password (min 8 chars, need capital, number, special): ");
        String password = scanner.nextLine();
        
        System.out.print("Enter cell phone number (format: +27xxxxxxxxx): ");
        String cellPhone = scanner.nextLine();
        
        // Try to register the user
        String result = loginSystem.registerUser(username, password, cellPhone);
        System.out.println("\nRegistration Result:");
        System.out.println(result);
    }
    
    //login by getting username and password
    private static void loginExistingUser() {
        System.out.println("\n--- USER LOGIN ---");
        
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        
        // try login
        boolean loginSuccess = loginSystem.loginUser(username, password);
        String loginMessage = loginSystem.returnLoginStatus(loginSuccess, username);
        
        System.out.println("\nLogin Result:");
        System.out.println(loginMessage);
        
        // set logged in if seccuessful
        if(loginSuccess) {
            isLoggedIn = true;
            currentUser = username;
        }
    }
    
    // Method for sending new message
    private static void sendNewMessage() {
        System.out.println("\n--- SEND NEW MESSAGE ---");
        
        // amount of message sthat needs to be sent
        System.out.print("How many messages do you want to send? ");
        int numberOfMessages = scanner.nextInt();
        scanner.nextLine();
        
        // Loop through and create each message
        for(int i = 0; i < numberOfMessages; i++) { // dynamic messag for total messagas
            System.out.println("\n--- Message " + (i + 1) + " of " + numberOfMessages + " ---");
            
            System.out.print("Enter recipient cell number (format: +27xxxxxxxxx): ");
            String recipient = scanner.nextLine();
            
            System.out.print("Enter your message (max 250 characters): ");
            String message = scanner.nextLine();
            
            // Validate the message
            String validationResult = messageSystem.sendMessage(recipient, message);
            System.out.println("\n" + validationResult);
            
            // If validation succeeded ask user what to do
            if(validationResult.equals("Message ready to send.")) {
                System.out.println("\nChoose an option:");
                System.out.println("1. Send Message");
                System.out.println("2. Disregard Message");
                System.out.println("3. Store Message to send later");
                System.out.print("Enter choice (1-3): ");
                
                int choice = scanner.nextInt();
                scanner.nextLine(); // Clear buffer
                
                String result = messageSystem.processMessage(recipient, message, choice);
                System.out.println("\n" + result);
            }
        }
        
        // Show total messages after sending all
        System.out.println("\nTotal messages sent: " + messageSystem.returnTotalMessages());
    }
    
    // Method to view all sent messages
    private static void viewAllMessages() {
        System.out.println(messageSystem.printMessages());
    }
    
    // Method to view total messages count
    private static void viewTotalMessages() {
        System.out.println("\n--- MESSAGE STATISTICS ---");
        System.out.println("Total number of messages sent: " + messageSystem.returnTotalMessages());
    }
    
    // Method to logout
    private static void logout() {
        System.out.println("\nLogging out " + currentUser + "...");
        isLoggedIn = false;
        currentUser = "";
        System.out.println("Successfully logged out!");
    }
}