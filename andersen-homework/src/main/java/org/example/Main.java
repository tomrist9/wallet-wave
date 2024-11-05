package org.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

   Ticket ticket1=new Ticket();
   ticket1.share("555-50-09");

   Ticket ticket2=new Ticket();
   ticket2.share("555-50-09", "tteymurlu9@list.ru");
    }
}