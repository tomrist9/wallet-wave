package org.example.util;


import org.example.bean.Config;
import org.example.service.menu.BeginToGame;
import org.example.service.menu.RegistrationOfCompetitor;

import java.util.Scanner;

import static org.example.service.menu.ExitService.exitMenu;
import static org.example.util.Menu.*;

public class MenuUtil {
    private static boolean keepRunning = true;

    private static Menu showAllMenu() {
        Scanner scanner = new Scanner(System.in);
        Menu[] menus = Menu.values();

        System.out.println("Available Options:");
        for (Menu menu : menus) {
            if (menu != Menu.UNKNOWN) {
                System.out.println(menu.getNumber() + ". " + menu.getLabel());
            }
        }

        System.out.println("Enter your choice:");
        int a = scanner.nextInt();
        handleMenuChoice(a);
        return null;
    }
    private static void handleMenuChoice(int choice) {
        switch (choice) {
            case 1:

                RegistrationOfCompetitor.REGISTER();
                keepRunning = false;
                break;
            case 2:
                BeginToGame.beginWithMe();
                keepRunning = false;
                break;
            case 3:
                System.out.println("Exiting program. Goodbye!");
                showAllMenu();
                keepRunning = false; // Set the flag to exit the loop
                break; // Exit the switch statement
            case 4:
                exitMenu();
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
        }
    }
    public static void mainMenu() {


        while (keepRunning) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Option 1");
            System.out.println("2. Option 2");
            System.out.println("3. Exit");

            System.out.println("Enter your choice:");
            Scanner sc = new Scanner(System.in);

            int choice = sc.nextInt();

            // Check if the next token is an integer
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid option.");
                sc.next(); // Consume the invalid input
            }

            choice = sc.nextInt();
            sc.nextLine(); // Consume the newline character

            if (choice >= 1 && choice <= 3) {
                handleMenuChoice(choice);
            } else {
                System.out.println("Invalid choice. Please enter a valid option.");
            }
            sc.close();
        }


    }
    public static Menu loginApp() {
        System.out.println("First, you have to login");

        Scanner sc = new Scanner(System.in);
        String username, password;

        for (int i = 0; i < 3; i++) {
            System.out.println("Enter your username:");
            username = sc.nextLine();
            System.out.println("Enter your password:");
            password = sc.nextLine();

            if (isValidCredentials(username, password)) {
                System.out.println("Well done");
                return showAllMenu(); // Return inside the loop if credentials are valid
            } else {
                System.out.println("Invalid username or password. Attempts remaining: " + (2 - i));
            }
        }

        System.out.println("Maximum login attempts reached.");


        return null;
    }
    private static boolean isValidCredentials(String username, String password) {
        // Check if the entered username and password are valid
        return username.equals("user") && password.equals("11111");
    }




}