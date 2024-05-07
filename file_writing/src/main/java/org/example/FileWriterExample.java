package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterExample {
    public static void main(String[] args) {
        String fileName = "nazgozeli";


        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
            bufferedWriter.write("ask boceyi");
            bufferedWriter.close();
            System.out.println("ask bocuvgu");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}