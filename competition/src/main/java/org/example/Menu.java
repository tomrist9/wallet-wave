package org.example;

import org.example.service.inter.BeginToGameInter;
import org.example.service.inter.ExitServiceInter;
import org.example.service.menu.*;
import org.example.service.inter.Process;


import java.util.Scanner;

import static org.example.service.menu.ExitService.exitMenu;
import static org.example.service.menu.RegistrationOfCompetitor.REGISTER;


public enum Menu {

    REGISTER(1, "Register", new RegistrationOfCompetitor()),
    BEGIN_GAME(2, "Begin", new BeginToGame()),
    LOGOUT(3, "Logout", new Logout()),
    EXIT(4, "Exit", new ExitService() ),

    UNKNOWN();


    private int number;
    private String label;
    private Process service;

    Menu(int number, String label, Process service) {
        this.number = number;
        this.label = label;
        this.service = service;
    }

    Menu(int number) {

        this.number = number;
    }

    Menu() {

    }


    private static boolean keepRunning = true;

    Menu(int i, String login, MenuLoginService menuLoginService) {
    }

    Menu(int i, String exit) {
    }


    public int getNumber() {
        return number;
    }


    public static Menu finddMenu(int number) {
        Menu[] menus = Menu.values();
        for (int i = 0; i < menus.length; i++) {
            if (menus[i].getNumber() == number) {
                return menus[i];
            }
        }
        return Menu.UNKNOWN;
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

    private static void handleMenuChoice(int choice) {
        switch (choice) {
            case 1:

                REGISTER();
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

    private String getLabel() {
        return label;
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

        // If loop completes without valid credentials, print message
        System.out.println("Maximum login attempts reached.");

        // Return null or handle the case where login fails
        return null;
    }




    private static boolean isValidCredentials(String username, String password) {
        // Check if the entered username and password are valid
        return username.equals("user") && password.equals("11111");
    }

}



//        static void REGISTER() {
//        System.out.println("How many people are going to compete?");
//        Scanner scanner = new Scanner(System.in);
//        String text = scanner.nextLine();
//        System.out.println("Enter their name and surnames");
//        String name = scanner.nextLine();
//        String surname = scanner.nextLine();
//        System.out.println("Competitors have been succesfully saved");
//    }


