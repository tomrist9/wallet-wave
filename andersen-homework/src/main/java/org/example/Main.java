package org.example;

import org.example.model.Admin;
import org.example.model.Client;
import org.example.model.Ticket;

public class Main {
    public static void main(String[] args) {

        Ticket ticket1=new Ticket();
        ticket1.share("555-50-09");

        Ticket ticket2=new Ticket();
        ticket2.share("555-50-00", "tteymurlu9@list.ru");

        Client client=new Client();
        client.printRole();

        Admin admin=new Admin();
        admin.printRole();


    }
}