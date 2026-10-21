package com.mycompany.loginregister;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class ProgApp {
    
    // --- PART 1 VARIABLES ---
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    // --- PART 1 METHODS (so MainJava is not needed) ---
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        return password.matches(".*[A-Z].*") && 
               password.matches(".*[0-9].*") && 
               password.matches(".*[!@#$%^&*()].*") && 
               password.length() >= 8;
    }

    public String registerUser(String username, String password, String firstName, String lastName) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        return "Username and password successfully captured. User registered.";
    }

    public boolean loginUser(String username, String password) {
        return username.equals(this.username) && password.equals(this.password);
    }

    public String returnLoginStatus(boolean isLoggedIn) {
        if (isLoggedIn) {
            return "Welcome " + firstName + " " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    // --- PART 2 MAIN - QuickChat ---
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProgApp loginObj = new ProgApp();
        Messages messageObj = new Messages();
        List<String[]> sentMessages = new ArrayList<>();

        // Part 1 Login Flow
        JOptionPane.showMessageDialog(null, "Welcome to Registration");
        String fName = JOptionPane.showInputDialog("Enter First Name:");
        String lName = JOptionPane.showInputDialog("Enter Last Name:");
        String user = JOptionPane.showInputDialog("Enter Username (must contain _ and <=5 chars):");
        String pass = JOptionPane.showInputDialog("Enter Password (8 chars, capital, number, special char):");
        
        String regStatus = loginObj.registerUser(user, pass, fName, lName);
        JOptionPane.showMessageDialog(null, regStatus);
        
        if (!regStatus.contains("successfully")) {
            return; // stop if registration failed
        }

        String loginUser = JOptionPane.showInputDialog("LOGIN - Enter Username:");
        String loginPass = JOptionPane.showInputDialog("LOGIN - Enter Password:");
        boolean loggedIn = loginObj.loginUser(loginUser, loginPass);
        JOptionPane.showMessageDialog(null, loginObj.returnLoginStatus(loggedIn));

        if (!loggedIn) return;

        // Part 2 QuickChat Flow
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");
        
        String countInput = JOptionPane.showInputDialog("How many messages do you want to send?");
        int messageLimit = Integer.parseInt(countInput);
        int messagesSent = 0;

        while (true) {
            String menu = JOptionPane.showInputDialog(
                "Choose:\n1) Send Message\n2) Show recently sent messages\n3) Quit");
            
            if (menu == null) break;
            
            switch (menu) {
                case "1":
                    if (messagesSent >= messageLimit) {
                        JOptionPane.showMessageDialog(null, "You have reached the message limit.");
                        break;
                    }
                    String recipient = JOptionPane.showInputDialog("Enter recipient cell number (e.g. +27731234567):");
                    String message = JOptionPane.showInputDialog("Enter message (max 250 chars):");
                    
                    if (!messageObj.isValidCell(recipient)) {
                        JOptionPane.showMessageDialog(null, "Cell phone number is incorrectly formatted or does not contain international code.");
                        break;
                    }
                    if (message.length() > 250) {
                        JOptionPane.showMessageDialog(null, "Please enter a message of less than 250 characters.");
                        break;
                    }
                    
                    String hash = messageObj.createMessageHash(String.valueOf(messagesSent), message);
                    String result = messageObj.storeMessage(recipient, message, hash);
                    JOptionPane.showMessageDialog(null, result);
                    
                    if (result.contains("successfully sent")) {
                        sentMessages.add(new String[]{hash, recipient, message});
                        messagesSent++;
                    }
                    break;
                    
                case "2":
                    JOptionPane.showMessageDialog(null, messageObj.printMessages(sentMessages));
                    break;
                    
                case "3":
                    JOptionPane.showMessageDialog(null, "Total messages sent: " + messagesSent);
                    System.exit(0);
                    break;
                    
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option.");
            }
        }
    }
}
