package org.example.model;

public class Admin extends Entity implements User {
    @Override
    public void printRole() {
        System.out.println("Role: Admin");
    }

    public void checkTicket(){
        System.out.println("Admin is checking the ticket....");
    }
}
