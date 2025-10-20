package org.yamendez.game;

import java.util.Scanner;

public class App {
    private static int min = 1;
    private static int max = 100;
    private static int answer = min + (int)(Math.random() * ((max - min) + 1));
    private static String[] level = new String[]{"Easy", "Medium", "Hard"};

    public static void main(String[] args) {

        welcomeMessage();
        selectLevel();


        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();
        int count = 0;
        int chances;
        int attends = 0;

        switch (input) {
            case 1 -> {
                level(level[0]);
                chances = 10;
                guessing(count, chances, input, attends, scan);
            }
            case 2 -> {
                level(level[1]);
                chances = 5;
                guessing(count, chances, input, attends, scan);
            }
            case 3 -> {
                level(level[2]);
                chances = 3;
                guessing(count, chances, input, attends, scan);
            }
        }


    }

    public static void welcomeMessage(){
        System.out.println("""
                Welcome to the Number Guessing Game!
                I'm thinking of a number between 1 and 100.
                """);
    }

    public static void selectLevel() {
        System.out.print("""
                 Please select the difficulty level:
                 1. Easy (10 chances)\s
                 2. Medium (5 chances)\s
                 3. Hard (3 chances)\s
                \s
                 Enter your choice:\s""");
    }

    private static void level(String level) {
        System.out.println("Great! You have selected the "+level+" difficulty level.\n" +
                "Lets start the game!");
    }

    private static void guessing(int count, int chances, int input, int attends, Scanner scan) {
        while (count < chances && input != answer) {
            attends++;
            System.out.print("\nEnter your guess: ");
            input = scan.nextInt();

            verifyInput(input, attends);
            count++;
        }
        if (input != answer) {
            System.out.println("Sorry, you ran out of attends.\n" +
                               "The correct number was "+answer+".");
        }
    }

    private static void verifyInput(int input, int attends) {
        if (input == answer) {
            System.out.println("Congratulations! You guessed the correct number in " + attends + " attempts");
        } else if (input < answer) {
            System.out.println("Incorrect! The number is greater than " + input + ".");
        } else {
            System.out.println("Incorrect! The number is less than " + input + ".");
        }
    }
}
