package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class FileWriterExample {
    public static void foo() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url="jdbc:mysql://localhost:3306";
        String username="root";
        String password="postgres";
        Connection connection= DriverManager.getConnection(url, username, password);
        Statement stmt= connection.createStatement();
        stmt.execute("select * from user");
        ResultSet resultSet= stmt.getResultSet();

        while(resultSet.next()){
            int id=resultSet.getInt("id");
            String name=resultSet.getString("name");
            String surname=resultSet.getString("surname");
            String phone=resultSet.getString("phone");
            String email=resultSet.getString("email");
            System.out.println("id"+id);
            System.out.println("name"+name);
            System.out.println("surname"+surname);
            System.out.println("phone"+phone);
            System.out.println("email"+email);

        }
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        foo();
    }
}