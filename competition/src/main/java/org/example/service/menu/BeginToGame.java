package org.example.service.menu;

import org.example.service.inter.BeginToGameInter;

import java.util.Scanner;

public class BeginToGame implements BeginToGameInter {
    @Override
    public void processLogic() {

    }

    @Override
    public void process() {
    }

    @Override
    public void beginToGame() {

            int randomNumber= (int)(Math.random()*5+1);
            System.out.println("Guess random number between 1 and 5");
            Scanner scanner=new Scanner(System.in);
            int userGuess=scanner.nextInt();
            if(userGuess>=1&&userGuess<=5){
                if (userGuess==randomNumber){
                    System.out.println("You have selected right number");
                }else if (!(userGuess==randomNumber)){
                    System.out.println("You failed");
                }else{
                    System.out.println("Invalid input");
                }
                scanner.close();
            }
        }
        public static void beginWithMe(){
            int randomNumber= (int)(Math.random()*5+1);
            System.out.println("Guess random number between 1 and 5");
            Scanner scanner=new Scanner(System.in);
            int userGuess=scanner.nextInt();
            if(userGuess>=1&&userGuess<=5){
                if (userGuess==randomNumber){
                    System.out.println("You have selected right number");
                }else if (!(userGuess==randomNumber)){
                    System.out.println("You failed");
                }else{
                    System.out.println("Invalid input");
                }
                scanner.close();
            }
        }
        }


