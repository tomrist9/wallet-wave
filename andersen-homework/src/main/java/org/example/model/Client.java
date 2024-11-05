package org.example.model;

public class Client extends Entity implements User {
    @Override
    public void printRole() {
        System.out.println("Role: Client");
    }

    public void getTicket(){
        System.out.println("Client is getting the ticket");
    }
}
