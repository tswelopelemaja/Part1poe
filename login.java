/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.loginregister;

import java.util.Scanner;

/**
 *
 * @author tswelopele
 */


public class Loginregister {

     String username;
     String password;
     String cellPhoneNumber;
     String firstName;
     String lastName;

    private boolean registered;
    private String loginStatus;

    public Loginregister(String username, String password,
                          String cellPhoneNumber, String firstName,
                          String lastName) {

        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;

        registered = false;
        loginStatus = "Username or password incorrect, please try again.";
    }

    public boolean checkUserName() {
        return username != null
                && username.contains("_")
                && username.length() <= 5;
    }

    public boolean checkPasswordComplexity() {

        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasCapitalLetter = false;
        boolean hasNumber = false;
        boolean hasSpecialCharacter = false;

        for (int i = 0; i < password.length(); i++) {

            char character = password.charAt(i);

            if (Character.isUpperCase(character)) {
                hasCapitalLetter = true;
            }

            if (Character.isDigit(character)) {
                hasNumber = true;
            }

            if (!Character.isLetterOrDigit(character)
                    && !Character.isWhitespace(character)) {
                hasSpecialCharacter = true;
            }
        }

        return hasCapitalLetter
                && hasNumber
                && hasSpecialCharacter;
    }

    public boolean checkCellPhoneNumber() {

        if (cellPhoneNumber == null) {
            return false;
        }

        return cellPhoneNumber.matches("^\\+27\\d{9}$");
    }

    public String registerUser() {

        if (!checkUserName()) {
            registered = false;
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity()) {
            registered = false;
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber()) {
            registered = false;
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        registered = true;

        return "Username and password successfully captured.";
    }

    public boolean loginUser(String enteredUsername,
                             String enteredPassword) {

        if (registered
                && username.equals(enteredUsername)
                && password.equals(enteredPassword)) {

            loginStatus = "Welcome " + firstName + ", "
                    + lastName + " it is great to see you.";

            return true;
        }

        loginStatus = "Username or password incorrect, please try again.";

        return false;
    }

    public String returnLoginStatus() {
        return loginStatus;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("===== Registration =====");

        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        System.out.print("Enter your cellphone number: ");
        String cellPhoneNumber = scanner.nextLine();

        Loginregister user = new Loginregister(
                username,
                password,
                cellPhoneNumber,
                firstName,
                lastName
        );

        System.out.println();
        System.out.println("===== Registration Result =====");

        String registrationMessage = user.registerUser();

        System.out.println(registrationMessage);

        if (user.checkUserName()) {
            System.out.println("Username: Valid");
        } else {
            System.out.println("Username: Invalid");
        }

        if (user.checkPasswordComplexity()) {
            System.out.println("Password: Valid");
        } else {
            System.out.println("Password: Invalid");
        }

        if (user.checkCellPhoneNumber()) {
            System.out.println("Cellphone number: Valid");
        } else {
            System.out.println("Cellphone number: Invalid");
        }

        if (registrationMessage.equals(
                "Username and password successfully captured.")) {

            System.out.println();
            System.out.println("===== Login =====");

            System.out.print("Enter your username: ");
            String enteredUsername = scanner.nextLine();

            System.out.print("Enter your password: ");
            String enteredPassword = scanner.nextLine();

            user.loginUser(enteredUsername, enteredPassword);

            System.out.println();
            System.out.println(user.returnLoginStatus());
        }

        scanner.close();
    }
}
