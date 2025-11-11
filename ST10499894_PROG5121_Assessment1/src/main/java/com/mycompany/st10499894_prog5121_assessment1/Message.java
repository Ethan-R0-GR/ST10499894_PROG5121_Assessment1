/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.st10499894_prog5121_assessment1;

import java.util.Random;
import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

/**
 *
 * @author RC_Student_lab
 */
public class Message {
    // Arrays to store message data, separated by status
    private static String[] sentMessageIDs = new String[100];
    private static String[] sentMessageHashes = new String[100];
    private static String[] sentRecipients = new String[100];
    private static String[] sentMessages = new String[100];
    private static int sentCount = 0;
    
    private static String[] disregardedMessageIDs = new String[100];
    private static String[] disregardedMessageHashes = new String[100];
    private static String[] disregardedRecipients = new String[100];
    private static String[] disregardedMessages = new String[100];
    private static int disregardedCount = 0;
    
    private static String[] storedMessageIDs = new String[100];
    private static String[] storedMessageHashes = new String[100];
    private static String[] storedRecipients = new String[100];
    private static String[] storedMessages = new String[100];
    private static int storedCount = 0;
    
    // check that message is less than 250 characters
    public boolean checkMessageLength(String message) {
        return message.length() <= 250;
    }
    
    // Check if messageID is valid
    public boolean checkMessageID(String messageID) {
        return messageID.length() <= 10;
    }
    
    // Check if recipient cell number is valid
    public int checkRecipientCell(String cellNumber) {
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
            id.append(rand.nextInt(10));
        }
        return id.toString();
    }
    
    // Create message hash
    public String createMessageHash(String messageID, int messageNum, String message) {
        // first 2 digits of messageID
        String firstTwo = messageID.substring(0, 2);
        
        // Get first and last words of message
        String[] words = message.trim().split("\\s+");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        
        // Format for the hash
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
        
        switch(choice) {
            case 1: // Send the message
                String messageHash = createMessageHash(messageID, sentCount, message);
                sentMessageIDs[sentCount] = messageID;
                sentMessageHashes[sentCount] = messageHash;
                sentRecipients[sentCount] = recipient;
                sentMessages[sentCount] = message;
                sentCount++;
                
                // Save sent message to JSON automatically
                saveMessageToJSON(messageID, messageHash, recipient, message, "sent");
                
                // message details in JOptionPane
                String details = "Message ID: " + messageID + "\n" +
                               "Message Hash: " + messageHash + "\n" +
                               "Recipient: " + recipient + "\n" +
                               "Message: " + message;
                JOptionPane.showMessageDialog(null, details, "Message Sent", JOptionPane.INFORMATION_MESSAGE);
                
                return "Message successfully sent.";
                
            case 2: // Disregard message
                String disregardHash = createMessageHash(messageID, disregardedCount, message);
                disregardedMessageIDs[disregardedCount] = messageID;
                disregardedMessageHashes[disregardedCount] = disregardHash;
                disregardedRecipients[disregardedCount] = recipient;
                disregardedMessages[disregardedCount] = message;
                disregardedCount++;
                return "Message disregarded.";
                
            case 3: // Store Message to JSON
                String storeHash = createMessageHash(messageID, storedCount, message);
                
                // Save stored message to JSON
                saveMessageToJSON(messageID, storeHash, recipient, message, "stored");
                
                // Also add to stored arrays
                storedMessageIDs[storedCount] = messageID;
                storedMessageHashes[storedCount] = storeHash;
                storedRecipients[storedCount] = recipient;
                storedMessages[storedCount] = message;
                storedCount++;
                
                return "Message successfully stored.";
                
            default:
                return "Invalid choice.";
        }
    }
    
    // Helper method to save message to JSON file
    private void saveMessageToJSON(String messageID, String messageHash, String recipient, String message, String type) {
        try {
            StringBuilder json = new StringBuilder();
            json.append("{\n");
            json.append("  \"type\": \"").append(type).append("\",\n");
            json.append("  \"messageID\": \"").append(messageID).append("\",\n");
            json.append("  \"messageHash\": \"").append(messageHash).append("\",\n");
            json.append("  \"recipient\": \"").append(recipient).append("\",\n");
            json.append("  \"message\": \"").append(escapeJson(message)).append("\"\n");
            json.append("}\n");
            
            FileWriter writer = new FileWriter("messages.json", true);
            writer.write(json.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving to JSON: " + e.getMessage());
        }
    }
    
    // Display sender and recipient of all sent messages
    public String displaySenderRecipient() {
        if(sentCount == 0) {
            return "No messages have been sent yet.";
        }
        
        StringBuilder result = new StringBuilder();
        result.append("\n=== SENDER AND RECIPIENT LIST ===\n\n");
        
        for(int i = 0; i < sentCount; i++) {
            result.append("Message ").append(i + 1).append(":\n");
            result.append("Recipient: ").append(sentRecipients[i]).append("\n");
            result.append("-----------------------------------\n");
        }
        
        return result.toString();
    }
    
    // Display the longest sent message
    public String displayLongestMessage() {
        if(sentCount == 0) {
            return "No messages have been sent yet.";
        }
        
        int longestIndex = 0;
        int maxLength = sentMessages[0].length();
        
        for(int i = 1; i < sentCount; i++) {
            if(sentMessages[i].length() > maxLength) {
                maxLength = sentMessages[i].length();
                longestIndex = i;
            }
        }
        
        StringBuilder result = new StringBuilder();
        result.append("\n=== LONGEST MESSAGE ===\n\n");
        result.append("Message: ").append(sentMessages[longestIndex]).append("\n");
        result.append("Length: ").append(maxLength).append(" characters\n");
        result.append("Recipient: ").append(sentRecipients[longestIndex]).append("\n");
        result.append("Message ID: ").append(sentMessageIDs[longestIndex]).append("\n");
        
        return result.toString();
    }
    
    // Search for a message by ID and display number and message
    public String searchMessageByID(String messageID) {
        // Search in sent messages
        for(int i = 0; i < sentCount; i++) {
            if(sentMessageIDs[i].equals(messageID)) {
                return "\n=== MESSAGE FOUND (SENT) ===\n" +
                       "Message ID: " + messageID + "\n" +
                       "Recipient: " + sentRecipients[i] + "\n" +
                       "Message: " + sentMessages[i] + "\n" +
                       "Message Hash: " + sentMessageHashes[i] + "\n";
            }
        }
        
        // Search in stored messages
        for(int i = 0; i < storedCount; i++) {
            if(storedMessageIDs[i].equals(messageID)) {
                return "\n=== MESSAGE FOUND (STORED) ===\n" +
                       "Message ID: " + messageID + "\n" +
                       "Recipient: " + storedRecipients[i] + "\n" +
                       "Message: " + storedMessages[i] + "\n" +
                       "Message Hash: " + storedMessageHashes[i] + "\n";
            }
        }
        
        return "Message with ID " + messageID + " not found.";
    }
    
    // Search for all messages sent to a specific user
    public String searchMessagesByRecipient(String recipient) {
        StringBuilder result = new StringBuilder();
        result.append("\n=== MESSAGES TO ").append(recipient).append(" ===\n\n");
        int foundCount = 0;
        
        // Search sent messages
        for(int i = 0; i < sentCount; i++) {
            if(sentRecipients[i].equals(recipient)) {
                foundCount++;
                result.append("Message ").append(foundCount).append(" (SENT):\n");
                result.append("Message: ").append(sentMessages[i]).append("\n");
                result.append("Message ID: ").append(sentMessageIDs[i]).append("\n");
                result.append("-----------------------------------\n");
            }
        }
        
        // Search stored messages
        for(int i = 0; i < storedCount; i++) {
            if(storedRecipients[i].equals(recipient)) {
                foundCount++;
                result.append("Message ").append(foundCount).append(" (STORED):\n");
                result.append("Message: ").append(storedMessages[i]).append("\n");
                result.append("Message ID: ").append(storedMessageIDs[i]).append("\n");
                result.append("-----------------------------------\n");
            }
        }
        
        if(foundCount == 0) {
            return "No messages found for recipient: " + recipient;
        }
        
        return result.toString();
    }
    
    // Delete a message using message hash
    public String deleteMessageByHash(String messageHash) {
        // Search in sent messages
        for(int i = 0; i < sentCount; i++) {
            if(sentMessageHashes[i].equals(messageHash)) {
                String deletedMessage = sentMessages[i];
                
                // Shift all elements after this one to the left
                for(int j = i; j < sentCount - 1; j++) {
                    sentMessageIDs[j] = sentMessageIDs[j + 1];
                    sentMessageHashes[j] = sentMessageHashes[j + 1];
                    sentRecipients[j] = sentRecipients[j + 1];
                    sentMessages[j] = sentMessages[j + 1];
                }
                sentCount--;
                
                return "Message \"" + deletedMessage + "\" successfully deleted.";
            }
        }
        
        // Search in stored messages
        for(int i = 0; i < storedCount; i++) {
            if(storedMessageHashes[i].equals(messageHash)) {
                String deletedMessage = storedMessages[i];
                
                // Shift all elements after this one to the left.
                for(int j = i; j < storedCount - 1; j++) {
                    storedMessageIDs[j] = storedMessageIDs[j + 1];
                    storedMessageHashes[j] = storedMessageHashes[j + 1];
                    storedRecipients[j] = storedRecipients[j + 1];
                    storedMessages[j] = storedMessages[j + 1];
                }
                storedCount--;
                
                return "Message \"" + deletedMessage + "\" successfully deleted.";
            }
        }
        
        return "Message with hash " + messageHash + " not found.";
    }
    
    //Display full report of all sent messages
    public String displayMessageReport() {
        if(sentCount == 0 && storedCount == 0) {
            return "No messages have been sent or stored yet.";
        }
        
        StringBuilder report = new StringBuilder();
        report.append("\n╔════════════════════════════════════════════════════════════╗\n");
        report.append("║              COMPLETE MESSAGE REPORT                       ║\n");
        report.append("╚════════════════════════════════════════════════════════════╝\n\n");
        
        // SENT MESSAGES SECTION
        if(sentCount > 0) {
            report.append("┌─────────────────────────────────────────────────────────────┐\n");
            report.append("│                    SENT MESSAGES                            │\n");
            report.append("└─────────────────────────────────────────────────────────────┘\n\n");
            
            for(int i = 0; i < sentCount; i++) {
                report.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
                report.append("Sent Message #").append(i + 1).append("\n");
                report.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
                report.append("Message Hash:   ").append(sentMessageHashes[i]).append("\n");
                report.append("Recipient:      ").append(sentRecipients[i]).append("\n");
                report.append("Message:        ").append(sentMessages[i]).append("\n");
                report.append("Message ID:     ").append(sentMessageIDs[i]).append("\n\n");
            }
        }
        
        // STORED MESSAGES SECTION
        if(storedCount > 0) {
            report.append("┌─────────────────────────────────────────────────────────────┐\n");
            report.append("│                   STORED MESSAGES                           │\n");
            report.append("└─────────────────────────────────────────────────────────────┘\n\n");
            
            for(int i = 0; i < storedCount; i++) {
                report.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
                report.append("Stored Message #").append(i + 1).append("\n");
                report.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
                report.append("Message Hash:   ").append(storedMessageHashes[i]).append("\n");
                report.append("Recipient:      ").append(storedRecipients[i]).append("\n");
                report.append("Message:        ").append(storedMessages[i]).append("\n");
                report.append("Message ID:     ").append(storedMessageIDs[i]).append("\n\n");
            }
        }
        
        report.append("════════════════════════════════════════════════════════════\n");
        report.append("Total Sent Messages:   ").append(sentCount).append("\n");
        report.append("Total Stored Messages: ").append(storedCount).append("\n");
        report.append("════════════════════════════════════════════════════════════\n");
        
        return report.toString();
    }
    
    // Display stored messages only
    public String displayStoredMessages() {
        if(storedCount == 0) {
            return "No messages have been stored yet.";
        }
        
        StringBuilder display = new StringBuilder();
        display.append("\n╔════════════════════════════════════════════════════════════╗\n");
        display.append("║              STORED MESSAGES (Saved for Later)             ║\n");
        display.append("╚════════════════════════════════════════════════════════════╝\n\n");
        
        for(int i = 0; i < storedCount; i++) {
            display.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
            display.append("Stored Message #").append(i + 1).append("\n");
            display.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
            display.append("Message Hash:   ").append(storedMessageHashes[i]).append("\n");
            display.append("Recipient:      ").append(storedRecipients[i]).append("\n");
            display.append("Message:        ").append(storedMessages[i]).append("\n");
            display.append("Message ID:     ").append(storedMessageIDs[i]).append("\n\n");
        }
        
        display.append("════════════════════════════════════════════════════════════\n");
        display.append("Total Stored Messages: ").append(storedCount).append("\n");
        display.append("════════════════════════════════════════════════════════════\n");
        
        return display.toString();
    }
    
    // Load stored messages from JSON file
    public String loadStoredMessages() {
        try {
            File file = new File("messages.json");
            if(!file.exists()) {
                return "No stored messages file found.";
            }
            
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder jsonContent = new StringBuilder();
            String line;
            
            while((line = reader.readLine()) != null) {
                jsonContent.append(line).append("\n");
            }
            reader.close();
            
            // Parse JSON manually
            String content = jsonContent.toString();
            String[] jsonObjects = content.split("\\}\\s*\\{");
            
            // Reset counts
            sentCount = 0;
            storedCount = 0;
            
            for(String obj : jsonObjects) {
                if(obj.trim().isEmpty()) continue;
                
                // Extract values
                String type = extractJsonValue(obj, "type");
                String messageID = extractJsonValue(obj, "messageID");
                String messageHash = extractJsonValue(obj, "messageHash");
                String recipient = extractJsonValue(obj, "recipient");
                String message = extractJsonValue(obj, "message");
                
                if(messageID != null && !messageID.isEmpty()) {
                    // Load into appropriate array based on type
                    if("sent".equals(type)) {
                        sentMessageIDs[sentCount] = messageID;
                        sentMessageHashes[sentCount] = messageHash;
                        sentRecipients[sentCount] = recipient;
                        sentMessages[sentCount] = unescapeJson(message);
                        sentCount++;
                    } else if("stored".equals(type)) {
                        storedMessageIDs[storedCount] = messageID;
                        storedMessageHashes[storedCount] = messageHash;
                        storedRecipients[storedCount] = recipient;
                        storedMessages[storedCount] = unescapeJson(message);
                        storedCount++;
                    }
                }
            }
            
            int totalLoaded = sentCount + storedCount;
            return "Successfully loaded " + totalLoaded + " messages (" + sentCount + " sent, " + storedCount + " stored).";
        } catch(IOException e) {
            return "Error loading stored messages: " + e.getMessage();
        }
    }
    
    // Helper method to extract JSON values
    private String extractJsonValue(String json, String key) {
        String searchFor = "\"" + key + "\":";
        int startIndex = json.indexOf(searchFor);
        if(startIndex == -1) return "";
        
        startIndex = json.indexOf("\"", startIndex + searchFor.length()) + 1;
        int endIndex = json.indexOf("\"", startIndex);
        
        if(startIndex > 0 && endIndex > startIndex) {
            return json.substring(startIndex, endIndex);
        }
        return "";
    }
    
    // Print all messages sent so far
    public String printMessages() {
        return displayMessageReport();
    }
    
    // Return total number of messages sent
    public int returnTotalMessages() {
        return sentCount;
    }
    
    // Get arrays for testing purposes
    public String[] getSentMessages() {
        return sentMessages;
    }
    
    public String[] getDisregardedMessages() {
        return disregardedMessages;
    }
    
    public String[] getStoredMessages() {
        return storedMessages;
    }
    
    public int getSentCount() {
        return sentCount;
    }
    
    public int getDisregardedCount() {
        return disregardedCount;
    }
    
    public int getStoredCount() {
        return storedCount;
    }
    
    private String escapeJson(String text) {
        return text.replace("\\", "\\\\")
                   .replace("\"", "\\\"")
                   .replace("\n", "\\n")
                   .replace("\r", "\\r")
                   .replace("\t", "\\t");
    }
    
    private String unescapeJson(String text) {
        return text.replace("\\\"", "\"")
                   .replace("\\\\", "\\")
                   .replace("\\n", "\n")
                   .replace("\\r", "\r")
                   .replace("\\t", "\t");
    }
}