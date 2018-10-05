import java.util.Scanner;
import java.util.Random;
//Add blackjack ai somehow
//Add date for randomness
public class Blackjack {
  static int Random = new Random();
  static Scanner keyboard = new Scanner(System.in);
  
  public void playTheGame() {
    int card = rng.nextInt(13)+1;
    String faces = " JQK";
    String cardFace = "";
    int playerTotal = 0;
    
    System.out.println("Welcome to Blackjack");
    System.out.println("The goal is to get as close as you can to the number 21 without going over.");
    System.out.println("Face cards are worth 10, and aces are worth either 1 or 11.");
    System.out.println("Enter the word 'Draw' to begin, and the word 'Quit' to quit (you can quit at any time).");
    String move = keyboard.nextLine();
    
    if (move.equalsIgnoreCase("draw")) {
      while(playerTotal <= 21) {
        if (card > 10){ //Face cards
          cardFace = faces.substring(card, card+1);
          System.out.println("Your card is a "+cardFace);
          playerTotal = playerTotal + 10;
          System.out.println("Your current total is "+playerTotal);
          System.out.println("Would you like to draw another card?");
          move = keyboard.nextLine();
        } else if (card > 1 && card <= 10) {
          System.out.println("Your card is "+card);
          playerTotal = playerTotal + card;
          System.out.println("Your current total is "+playerTotal);
          System.out.println("Would you like to draw another card?");
          move = keyboard.nextLine();
        } else if (card == 1) {
          System.out.println("Your card is an ace.");
          if (playerTotal+11 > 21 && playerTotal+1 > 21) {
            playerTotal = playerTotal + card;
            System.out.println("You busted! Enter 'yes' to try again and 'quit' to stop.");
            move = keyboard.nextLine();
          } else if (playerTotal + 1 <+ 21) {
            playerTotal = playerTotal + card;
            System.out.println("Your current total is "+playerTotal);
            System.out.println("Would you like to draw another card?");
            move = keyboard.nextLine();
          }
        }
        card = rng.nextInt(13)+1;
        System.out.println("You won! You card total is "+playerTotal);
        System.out.println("Enter 'draw' to play again and 'quit' to quit.");
        move = keyboard.nextLine();
        /* 
         Make it so that numbers 1 to 13 are possibilities, 11 means J, 12 means Q, 13 means K, and 1 nets you an ace.
         
         Also, nested if if the player gets an ace so that the 1 value can be counted
         if the 11 puts the player over 11. Something like
         
         if (11+playerTotal > 21)
         playerTotal = playerTotal-10;
         }
         */
        
        
      }
    } else if (move.equalsIgnoreCase("quit")) {
      System.out.println("Thank you for playing.");
      playerTotal = 100;
    } else {
      System.out.println("Please enter 'Draw' to begin");
      move = keyboard.nextLine();
    }
  } //End of playing the game
  
  
  
  public static void main (String [] args) {
    Scanner keyboard = new Scanner(System.in); // delete when static scanner is made
    Random rng = new Random(); // add under static scanner
    String answer;
    playTheGame();
    System.out.println("Would you like to play again?");
    answer = keyboard.nextLine();
    while (!answer.equalsIgnoreCase("yes") || !answer.equalsIgnoreCase("no")) {
      if (answer.equalsIgnoreCase("yes")) {
        playTheGame();
      } else if (answer.equalsIgnoreCase("no")) {
        System.out.println("Thanks for playing!");
      } else {
        System.out.println("Please enter either 'yes' or 'no'.");
        answer = keyboard.nextLine();
      }
    }
  } //End of main
} //End of class

//https://github.com/TomFanella4/Blackjack-Card-Game-with-AI/blob/master/Blackjack.java
