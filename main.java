/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loginregister;

import java.util.Scanner;
/**
 *
 * @author tswelopele
 */
public class MainJava {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Registration and Login");

        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter a username: ");
        String username = scanner.nextLine();

        System.out.print("Enter a password: ");
        String password = scanner.nextLine();

        System.out.print("Enter your South African cellphone number: ");
        String cellPhoneNumber = scanner.nextLine();

        Loginregister login = new Loginregister(
                username,
                password,
                cellPhoneNumber,
                firstName,
                lastName
        );

        String registrationMessage = login.registerUser();

        System.out.println(registrationMessage);

        if (!registrationMessage.equals(
                "Username and password successfully captured.")) {

            System.out.println("Registration unsuccessful.");
            scanner.close();
            return;
        }

        System.out.println("Please log in.");

        System.out.print("Enter your username: ");
        String enteredUsername = scanner.nextLine();

        System.out.print("Enter your password: ");
        String enteredPassword = scanner.nextLine();

        login.loginUser(enteredUsername, enteredPassword);

        System.out.println(login.returnLoginStatus());

        scanner.close();
    }
}
  
