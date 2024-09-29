package org.example.util;

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




    String getLabel() {
        return label;
    }








}





