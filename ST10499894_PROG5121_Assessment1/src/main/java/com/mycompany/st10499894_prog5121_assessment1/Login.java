/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.st10499894_prog5121_assessment1;

/**
 *
 * @author RC_Student_lab 
 */
public class Login {

    // Arrays to store user data
    private static String[] usernames = new String[100];
    private static String[] passwords = new String[100];
    private static String[] cellNumbers = new String[100];
    private static int userCount = 0; // tracks how many users are registered

    //Check username format
    public boolean checkUserName(String username) {
        // Username must contain underscore AND be 5 characters or less
        if(username.length() <= 5 && username.contains("_")) {
            return true;
        }
        return false;
    }

    // Check password complexity
    public boolean checkPasswordComplexity(String password) {
        // Must be at least 8 characters
        if(password.length() < 8) {
            return false;
        }

        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        // Check each character in password
        for(char c : password.toCharArray()) {
            if(Character.isUpperCase(c)) {
                hasCapital = true;
            }
            if(Character.isDigit(c)) {
                hasNumber = true;
            }
            if(!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }

        // All three conditions must be true
        return hasCapital && hasNumber && hasSpecial;
    }

    // Check cell phone format
    public boolean checkCellPhoneNumber(String cellPhone) {
        // Using regex that i got from Claude
        String regex = "^\\+27\\d{9}$";
        return cellPhone.matches(regex);
    }

    // Register a new user and check that all fields are valid
    public String registerUser(String username, String password, String cellPhone) {
        // Check if user is valid
        if(!checkUserName(username)) {
           return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        // Check if password is valid
        if(!checkPasswordComplexity(password)) {
            
            return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        // Check if cellphone number is valid
        if(!checkCellPhoneNumber(cellPhone)) {
            return "Cell phone number is incorrectly formatted or does not contain international code, please correct the number and try again.";
        }

        // this code will only work if the above three checks pass
        usernames[userCount] = username;
        passwords[userCount] = password;
        cellNumbers[userCount] = cellPhone;
        userCount++; // increase counter for next user

        // Return success messages
        
        return "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully captured.";
    }

    // Check if user login info is correct
    public boolean loginUser(String username, String password) {
        // Look through all registered users for the user trying to log in
        for(int i = 0; i < userCount; i++) {
            //this is the check to see if the user is actually in the array
            if (usernames[i].equals(username) && passwords[i].equals(password)) {
                return true; // Found matching user
            }
        }
        return false; // returns false if no matching user is found
    }

    // Return login message
    public String returnLoginStatus(boolean loginSuccess, String username) {
        if(loginSuccess) {
            //welcome the user back if lofin is a success
            return "Welcome " + username + " it is great to see you again.";
        }else{
            //if user does not exist or entered the wrong username or password show this message
            return "Username or password incorrect, please try again.";
        }
    }
}
