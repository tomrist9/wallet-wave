package org.example.service.menu;

import org.example.service.inter.MenuLoginServiceInter;

import java.util.Scanner;

public class MenuLoginService implements MenuLoginServiceInter {

    public void login() {


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

                    return;
                } else {
                    System.out.println("Invalid username or password. Attempts remaining: " + (2 - i));
                }
            }


            System.out.println("Maximum login attempts reached. Exiting program.");
            System.exit(0);
        }

        private static boolean isValidCredentials(String username, String password) {
            // Check if the entered username and password are valid
            return username.equals("user") && password.equals("11111");
        }

    @Override
    public void processLogic() {

    }
}

