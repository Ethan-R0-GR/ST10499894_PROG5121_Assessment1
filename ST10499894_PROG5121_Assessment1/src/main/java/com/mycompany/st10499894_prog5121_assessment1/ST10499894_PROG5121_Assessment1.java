/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.st10499894_prog5121_assessment1;
import javax.swing.JOptionPane;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author RC_Student_lab
 */
public class ST10499894_PROG5121_Assessment1 {
    // Create objects we'll use later in the program
    private static Login loginSystem = new Login();
    private static Message messageSystem = new Message();
    private static boolean isLoggedIn = false;
    private static String currentUser = "";
    
    public static void main(String[] args) {
        // Welcome message in console (just for startup confirmation)
        System.out.println("====================================");
        System.out.println("Welcome to Chat App :)");
        System.out.println("====================================");
        
        // Load any stored messages from JSON file
        String loadResult = messageSystem.loadStoredMessages();
        System.out.println(loadResult); // Console feedback for debugging
        
        // Show welcome dialog
        JOptionPane.showMessageDialog(null, 
            "Welcome to Chat App! :)\n\n" + loadResult,
            "Chat App - Startup",
            JOptionPane.INFORMATION_MESSAGE);
        
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
    
    // Menu for login, registration or to exit program using JOptionPane
    private static void showLoginMenu() {
        String[] options = {"Register new user", "Login existing user", "Exit program"};
        int choice = JOptionPane.showOptionDialog(
            null,
            "Welcome to Chat App :)\n\nPlease choose an option:",
            "Chat App - Main Menu",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
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
            case JOptionPane.CLOSED_OPTION:
                JOptionPane.showMessageDialog(null,
                    "Thank you for using Chat App! Goodbye!",
                    "Goodbye",
                    JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            default:
                JOptionPane.showMessageDialog(null, 
                    "Invalid option. Please try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Menu for messaging, made a vertical layout with JList
    private static void showMessageMenu() {
        String[] options = {
            "1. Send new message",
            "2. View all sent messages",
            "3. View stored messages",
            "4. View total messages sent",
            "5. Display longest message",
            "6. Search message by ID",
            "7. Search messages by recipient",
            "8. Delete message by hash",
            "9. Display full report",
            "10. Logout",
            "11. Exit program"
        };
        
        // Create JList for vertical display
        JList<String> list = new JList<>(options);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(11);
        
        JScrollPane scrollPane = new JScrollPane(list);
        
        int result = JOptionPane.showConfirmDialog(
            null,
            scrollPane,
            "Welcome " + currentUser + "! - Select an option:",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        if(result == JOptionPane.OK_OPTION) {
            int choice = list.getSelectedIndex();
            
            switch(choice) {
                case 0: // Send new message
                    sendNewMessage();
                    break;
                case 1: // View all sent messages
                    viewAllMessages();
                    break;
                case 2: // View stored messages
                    viewStoredMessages();
                    break;
                case 3: // View total messages sent
                    viewTotalMessages();
                    break;
                case 4: // Display longest message
                    displayLongestMessage();
                    break;
                case 5: // Search message by ID
                    searchByMessageID();
                    break;
                case 6: // Search messages by recipient
                    searchByRecipient();
                    break;
                case 7: // Delete message by hash
                    deleteByHash();
                    break;
                case 8: // Display full report
                    displayReport();
                    break;
                case 9: // Logout
                    logout();
                    break;
                case 10: // Exit program
                    JOptionPane.showMessageDialog(null,
                        "Thank you for using Chat App! Goodbye!",
                        "Goodbye",
                        JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                    break;
            }
        } else if(result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
            // User cancelled,return to menu
            return;
        }
    }
    
    // Handle user registration
    private static void registerNewUser() {
        // Get username
        String username = JOptionPane.showInputDialog(
            null,
            "Enter username:\n(must contain _ and be max 5 characters)",
            "User Registration - Username",
            JOptionPane.QUESTION_MESSAGE
        );
        
        if(username == null) return; // User cancelled
        
        // Get password
        String password = JOptionPane.showInputDialog(
            null,
            "Enter password:\n(min 8 chars, need capital, number, special character)",
            "User Registration - Password",
            JOptionPane.QUESTION_MESSAGE
        );
        
        if(password == null) return;
        
        // Get cell phone
        String cellPhone = JOptionPane.showInputDialog(
            null,
            "Enter cell phone number:\n(format: +27xxxxxxxxx)",
            "User Registration - Cell Phone",
            JOptionPane.QUESTION_MESSAGE
        );
        
        if(cellPhone == null) return;
        
        // Try to register the user
        String result = loginSystem.registerUser(username, password, cellPhone);
        
        // Show result
        if(result.contains("successfully")) {
            JOptionPane.showMessageDialog(null,
                result,
                "Registration Successful",
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                result,
                "Registration Failed",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Login by getting username and password
    private static void loginExistingUser() {
        // Get username
        String username = JOptionPane.showInputDialog(
            null,
            "Enter your username:",
            "User Login - Username",
            JOptionPane.QUESTION_MESSAGE
        );
        
        if(username == null) return;
        
        // Get password
        String password = JOptionPane.showInputDialog(
            null,
            "Enter your password:",
            "User Login - Password",
            JOptionPane.QUESTION_MESSAGE
        );
        
        if(password == null) return;
        
        // Try login
        boolean loginSuccess = loginSystem.loginUser(username, password);
        String loginMessage = loginSystem.returnLoginStatus(loginSuccess, username);
        
        // Show result
        if(loginSuccess) {
            isLoggedIn = true;
            currentUser = username;
            JOptionPane.showMessageDialog(null,
                loginMessage,
                "Login Successful",
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                loginMessage,
                "Login Failed",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Method for sending new message
    private static void sendNewMessage() {
        // Get number of messages
        String numStr = JOptionPane.showInputDialog(
            null,
            "How many messages do you want to send?",
            "Send Message - Count",
            JOptionPane.QUESTION_MESSAGE
        );
        
        if(numStr == null) return;
        
        int numberOfMessages;
        try {
            numberOfMessages = Integer.parseInt(numStr);
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                "Invalid number! Please enter a valid number.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Loop through and create each message
        for(int i = 0; i < numberOfMessages; i++) {
            // Show progress
            String recipient = JOptionPane.showInputDialog(
                null,
                "Message " + (i + 1) + " of " + numberOfMessages + "\n\n" +
                "Enter recipient cell number:\n(format: +27xxxxxxxxx)",
                "Send Message - Recipient",
                JOptionPane.QUESTION_MESSAGE
            );
            
            if(recipient == null) continue; // Skip this message
            
            // Get message text
            String message = JOptionPane.showInputDialog(
                null,
                "Message " + (i + 1) + " of " + numberOfMessages + "\n\n" +
                "Enter your message:\n(max 250 characters)",
                "Send Message - Content",
                JOptionPane.QUESTION_MESSAGE
            );
            
            if(message == null) continue; // Skip this message
            
            // Validate the message
            String validationResult = messageSystem.sendMessage(recipient, message);
            
            // If validation succeeded ask user what to do
            if(validationResult.equals("Message ready to send.")) {
                String[] messageOptions = {"Send Message", "Disregard Message", "Store Message to send later"};
                int choice = JOptionPane.showOptionDialog(
                    null,
                    "Message is valid!\n\nRecipient: " + recipient + "\n" +
                    "Message: " + message + "\n\nWhat would you like to do?",
                    "Send Message - Action",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    messageOptions,
                    messageOptions[0]
                );
                
                if(choice >= 0 && choice <= 2) {
                    String result = messageSystem.processMessage(recipient, message, choice + 1);
                    // processMessage shows JOptionPane for sent messages
                    if(choice != 0) { // Don't show duplicate
                        JOptionPane.showMessageDialog(null,
                            result,
                            "Message Processed",
                            JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } else {
                // Show validation error
                JOptionPane.showMessageDialog(null,
                    validationResult,
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        // Show total messages after sending all
        JOptionPane.showMessageDialog(null,
            "Total messages sent: " + messageSystem.returnTotalMessages(),
            "Send Message - Complete",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Method to view all sent messages
    private static void viewAllMessages() {
        String messages = messageSystem.printMessages();
        
        // Create scrollable text area
        javax.swing.JTextArea textArea = new javax.swing.JTextArea(messages);
        textArea.setEditable(false);
        textArea.setRows(20);
        textArea.setColumns(60);
        textArea.setCaretPosition(0); // Scroll to top
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        JOptionPane.showMessageDialog(null,
            scrollPane,
            "All Sent Messages",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Method to view stored messages
    private static void viewStoredMessages() {
        String messages = messageSystem.displayStoredMessages();
        
        // Create scrollable text area
        javax.swing.JTextArea textArea = new javax.swing.JTextArea(messages);
        textArea.setEditable(false);
        textArea.setRows(20);
        textArea.setColumns(60);
        textArea.setCaretPosition(0); // Scroll to top
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        JOptionPane.showMessageDialog(null,
            scrollPane,
            "Stored Messages",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Method to view total messages count
    private static void viewTotalMessages() {
        JOptionPane.showMessageDialog(null,
            "Total number of messages sent: " + messageSystem.returnTotalMessages(),
            "Message Statistics",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Method to display longest message
    private static void displayLongestMessage() {
        String result = messageSystem.displayLongestMessage();
        
        // Create scrollable text area
        javax.swing.JTextArea textArea = new javax.swing.JTextArea(result);
        textArea.setEditable(false);
        textArea.setRows(15);
        textArea.setColumns(50);
        textArea.setCaretPosition(0); // Scroll to top
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        JOptionPane.showMessageDialog(null,
            scrollPane,
            "Longest Message",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Method to search by message ID
    private static void searchByMessageID() {
        String messageID = JOptionPane.showInputDialog(
            null,
            "Enter message ID to search:",
            "Search by Message ID",
            JOptionPane.QUESTION_MESSAGE
        );
        
        if(messageID == null) return;
        
        String result = messageSystem.searchMessageByID(messageID);
        
        // Create scrollable text area
        javax.swing.JTextArea textArea = new javax.swing.JTextArea(result);
        textArea.setEditable(false);
        textArea.setRows(10);
        textArea.setColumns(50);
        textArea.setCaretPosition(0); // Scroll to top
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        JOptionPane.showMessageDialog(null,
            scrollPane,
            "Search Results",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Method to search by recipient
    private static void searchByRecipient() {
        String recipient = JOptionPane.showInputDialog(
            null,
            "Enter recipient cell number:\n(format: +27xxxxxxxxx)",
            "Search by Recipient",
            JOptionPane.QUESTION_MESSAGE
        );
        
        if(recipient == null) return; // User cancelled
        
        String result = messageSystem.searchMessagesByRecipient(recipient);
        
        // Create scrollable text area
        javax.swing.JTextArea textArea = new javax.swing.JTextArea(result);
        textArea.setEditable(false);
        textArea.setRows(15);
        textArea.setColumns(50);
        textArea.setCaretPosition(0); // Scroll to top
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        JOptionPane.showMessageDialog(null,
            scrollPane,
            "Search Results - " + recipient,
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Method to delete by hash
    private static void deleteByHash() {
        String hash = JOptionPane.showInputDialog(
            null,
            "Enter message hash to delete:\n(format: 12:0:DIDCAKE?)",
            "Delete Message by Hash",
            JOptionPane.QUESTION_MESSAGE
        );
        
        if(hash == null) return;
        
        // Confirm deletion
        int confirm = JOptionPane.showConfirmDialog(
            null,
            "Are you sure you want to delete the message with hash:\n" + hash + "?",
            "Confirm Deletion",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );
        
        if(confirm == JOptionPane.YES_OPTION) {
            String result = messageSystem.deleteMessageByHash(hash);
            
            if(result.contains("successfully")) {
                JOptionPane.showMessageDialog(null,
                    result,
                    "Deletion Successful",
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                    result,
                    "Deletion Failed",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // Method to display full report
    private static void displayReport() {
        String report = messageSystem.displayMessageReport();
        
        // Create scrollable text area
        javax.swing.JTextArea textArea = new javax.swing.JTextArea(report);
        textArea.setEditable(false);
        textArea.setRows(20);
        textArea.setColumns(60);
        textArea.setCaretPosition(0); 
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        JOptionPane.showMessageDialog(null,
            scrollPane,
            "Complete Message Report",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Method to logout
    private static void logout() {
        int confirm = JOptionPane.showConfirmDialog(
            null,
            "Are you sure you want to logout?",
            "Confirm Logout",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        if(confirm == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null,
                "Goodbye " + currentUser + "!\nYou have been logged out successfully.",
                "Logged Out",
                JOptionPane.INFORMATION_MESSAGE);
            isLoggedIn = false;
            currentUser = "";
        }
    }
}