/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.st10499894_prog5121_assessment1;

import java.util.Random;
import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author RC_Student_lab
 */
public class Message {
    // Arrays to store message data
    private final String[] messageIDs = new String[100];
    private final String[] messageHashes = new String[100];
    private final String[] recipients = new String[100];
    private final String[] messages = new String[100];
    private static int messageCount = 0;
    
    // check that message is less than 250 characters
    public boolean checkMessageLength(String message) {
        return message.length() <= 250;
    }
    
    // Check if messageID is valid
    public boolean checkMessageID(String messageID) {
        return messageID.length() <= 10;
    }
    
    // Check if recipient cell number is valid (max 10 characters and has international code)
    public int checkRecipientCell(String cellNumber) {
        // Using the same regex from login class
        // TODO: add a retry for invalid numbers
        String regex = "^\\+27\\d{9}$";
        if(cellNumber.matches(regex)) {
            return 1; // Success
        }
        return 0; // failed
    }
    
    // Generate a random 10-digit message ID
    private String generateMessageID() {
        Random rand = new Random();
        StringBuilder id = new StringBuilder();
        for(int i = 0; i < 10; i++) {
            id.append(rand.nextInt(10)); // up to 10 characters long
        }
        return id.toString();
    }
    
    // Create message hash
    public String createMessageHash(String messageID, int messageNum, String message) {
        // first 2 digits of messageID
        String firstTwo = messageID.substring(0, 2);
        
        // Get first and last words of message
        String[] words = message.trim().split("\\s+"); // trim removes extra white space and split splist the string where it finds teh character for a space
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        
        // Format for the hash ---------> FirstTwo:MessageNum:FirstWord LastWord
        return (firstTwo + ":" + messageNum + ":" + firstWord + lastWord).toUpperCase();
    }
    
    // Send message with user choice
    public String sendMessage(String recipient, String message) {
        // validate message length here
        if(!checkMessageLength(message)) {
            int excess = message.length() - 250;
            return "Message exceeds 250 characters by " + excess + ", please reduce size.";
        }
        
        // Validate number
        if(checkRecipientCell(recipient) == 0) {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
        
        // Message is valid and can b sent
        return "Message ready to send.";
    }
    
    // Process the message based on user's choice
    public String processMessage(String recipient, String message, int choice) {
        String messageID = generateMessageID();
        String messageHash = createMessageHash(messageID, messageCount, message);
        
        switch(choice) {
            case 1: // Send the message
                messageIDs[messageCount] = messageID;
                messageHashes[messageCount] = messageHash;
                recipients[messageCount] = recipient;
                messages[messageCount] = message;
                messageCount++;
                
                // message details in JOptionPane
                String details = "Message ID: " + messageID + "\n" +
                               "Message Hash: " + messageHash + "\n" +
                               "Recipient: " + recipient + "\n" +
                               "Message: " + message;
                JOptionPane.showMessageDialog(null, details, "Message Sent", JOptionPane.INFORMATION_MESSAGE);
                
                return "Message successfully sent.";
                
            case 2: // delete message
                return "Press 0 to delete message.";
                
             case 3: // Store Message to JSON made by claude. Tt made a .json file, wasn't sure if that is correct or if it should be a text with json in it.
            try {
                
                StringBuilder json = new StringBuilder();
                json.append("{\n");
                json.append("  \"messageID\": \"").append(messageID).append("\",\n");
                json.append("  \"messageHash\": \"").append(messageHash).append("\",\n");
                json.append("  \"recipient\": \"").append(recipient).append("\",\n");
                json.append("  \"message\": \"").append(escapeJson(message)).append("\"\n");
                json.append("}\n");
                
                java.io.FileWriter writer = new java.io.FileWriter("messages.json", true);
                writer.write(json.toString());
                writer.close();
                
                return "Message successfully stored.";
            } catch (java.io.IOException e) {
                return "Error storing message: " + e.getMessage();
            }
                
            default:
                return "Invalid choice.";
        }
    }
    
    // Print all messages sent so far
    public String printMessages() {
        if(messageCount == 0) {
            return "No messages have been sent yet.";
        }
        
        StringBuilder allMessages = new StringBuilder();
        allMessages.append("\n=== ALL SENT MESSAGES ===\n\n");
        
        for(int i = 0; i < messageCount; i++) {
            allMessages.append("Message ").append(i + 1).append(":\n");
            allMessages.append("Message ID: ").append(messageIDs[i]).append("\n");
            allMessages.append("Message Hash: ").append(messageHashes[i]).append("\n");
            allMessages.append("Recipient: ").append(recipients[i]).append("\n");
            allMessages.append("Message: ").append(messages[i]).append("\n");
            allMessages.append("-----------------------------------\n");
        }
        
        return allMessages.toString();
    }
    
    // Return total number of messages sent
    public int returnTotalMessages() {
        return messageCount;
    }
    
    private String escapeJson(String text) {
    return text.replace("\\", "\\\\")
               .replace("\"", "\\\"")
               .replace("\n", "\\n")
               .replace("\r", "\\r")
               .replace("\t", "\\t");
}
}