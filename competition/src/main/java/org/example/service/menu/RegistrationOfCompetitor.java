package org.example.service.menu;

import org.example.service.inter.RegistrationOfCompetitorInter;

import java.util.Scanner;

public class RegistrationOfCompetitor implements RegistrationOfCompetitorInter {

    @Override
    public void processLogic() {
        System.out.println("How many people are going to compete?");
        Scanner scanner=new Scanner(System.in);
        String text=scanner.nextLine();
        System.out.println("Enter their name and surnames");
        String name=scanner.nextLine();
        String surname=scanner.nextLine();
        System.out.println("Competitors have been succesfully saved");
    }

    @Override
    public void registerUser() {
        System.out.println("How many people are going to compete?");
        Scanner scanner=new Scanner(System.in);
        String text=scanner.nextLine();
        System.out.println("Enter their name and surnames");
        String name=scanner.nextLine();
        String surname=scanner.nextLine();
        String name2=scanner.nextLine();
        String surname2=scanner.nextLine();
        String name3=scanner.nextLine();
        String surname3=scanner.nextLine();
        String name4=scanner.nextLine();
        String surname4=scanner.nextLine();
        String name5=scanner.nextLine();
        String surname5=scanner.nextLine();
        System.out.println("Competitors have been succesfully saved");
    }
    public static void REGISTER() {
        System.out.println("How many people are going to compete?");
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        System.out.println("Enter their name and surnames");
        String name = scanner.nextLine();
        String surname = scanner.nextLine();
        System.out.println("Competitors have been succesfully saved");
    }

}
