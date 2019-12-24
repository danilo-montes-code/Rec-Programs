package Rec.Bingo;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class BingoGame {
    ArrayList<Player> players = new ArrayList<>();
    ArrayList<String> balls = new ArrayList<>();
    static boolean firstTime = true;
    public BingoGame() {
        if (firstTime)
        System.out.println("Welcome to Bingo!\nEach player gets a board with random numbers ranging from 1 to 75.\n" +
                "When a number is called, it is crossed off on your board.\nA player wins when they get five crossed " +
                "off numbers in a row, diagonally, horizontally, or vertically.");
        firstTime = false;
        setUpGame();
        playGame();
    }

    public void playGame() {
        // Draw numbers from balls and cross off any numbers that appear on any one's board. If anyone one wins, end the game and declare said person the winnner
        boolean game = true;
        while (game) {
            System.out.println("\n");
            String draw = "";
            if (balls.size() > 0) {
                draw = balls.remove(0);
                System.out.println("The number "+draw+" was drawn");
                System.out.println();
            } else
                game = false;
            for (Player player : players) {
                player.crossOff(draw);
                System.out.println(player.showCard());
                System.out.println();
            }
            for (Player player : players) {
                if (player.hasWon()) {
                    System.out.println(player.showName() + " has Bingo!");
                    System.out.println(player.showCard());
                    game = false;
                }
            }
        }
        playAgain();
    }

    public void setUpGame() {
        // Asks for number of players and makes cards for all of them
        Scanner keys = new Scanner(System.in);
        System.out.println("How many players?");
        int numberOfPlayers = keys.nextInt();
        keys.nextLine();
        for (int x = 1; x <= numberOfPlayers; x++) {
            System.out.println("Who is Player "+x+"?");
            String name = keys.nextLine();
            players.add(new Player(name));
        }
        // Makes the balls
        for (int x = 1; x < 76; x++) {
            if (x < 10)
                balls.add("0"+Integer.toString(x));
            else
                balls.add(Integer.toString(x));
        }
        Collections.shuffle(balls);
    }

    public void playAgain() {
        System.out.println("Would you like to play again with different boards? (Yes/No)");
        Scanner keys = new Scanner(System.in);
        String answer = keys.nextLine();
        while (!answer.equalsIgnoreCase("Yes") && !answer.equalsIgnoreCase("No")) {
            System.out.println("Would you like to play again with different boards? (Yes/No)");
            answer = keys.nextLine();
        }
        if (answer.equalsIgnoreCase("Yes")) {
            System.out.println("\n");
            System.out.println("\n");
            new BingoGame();
        } else
            System.out.println("Thank you for playing!");
    }
    public static void main(String[] args) {
        new BingoGame();
    }
}
