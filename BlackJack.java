import java.util.Scanner;
import java.util.Random;
//Add blackjack ai somehow
public class Blackjack {
  static //do scanner here
  static void moving() {
    //Do all the stuff here as to the game, ask if you want to play again or quit in the main
  }
  
  
  
  public static void main (String [] args) {
    Scanner keyboard = new Scanner(System.in); // delete when static scanner is made
    Random rng = new Random(); // add under static scanner
    int card = rng.nextInt(13)+1;
    String faces = " JQK";
    String cardFace = "";
    int playerTotal = 0;
    
    System.out.println("Welcome to Blackjack");
    System.out.println("The goal is to get as close as you can to the number 21 without going over.");
    System.out.println("Face cards are worth 10, and aces are worth either 1 or 11.");
    System.out.println("Enter the word 'Draw' to begin, and the word 'Quit' to quit (you can quit at any time).");
    String move = keyboard.nextLine();
    
    while (playerTotal <= 21) {
      System.out.println(card+" before change"); 
      card = rng.nextInt(13)+1;
      System.out.println(card+" after change");
      if (move.equalsIgnoreCase("draw")) {
        if (card > 10){
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
        System.out.println("You won! You card total is "+playerTotal);
        System.out.println("Enter 'draw' to play again and 'quit' to quit.");
        move = keyboard.nextLine();
        /* Make the card variable, which is random, and if the random number is 10,
         then randomly choose between the 10 card or a face card.
         Figure out if you can make the 1 and 11 values for ace the same variable,
         so that there is an equal chance of getting either one.
         Probably have the randomizer randomize the String of the cards instead of the number
         value, and then see if the String is a number. If so, parse int it, if not,
         either the player's total the 10 for faces or 1 or 11 for an ace.
         
         Make it so that numbers 1 to 13 are possibilities, 11 means J, 12 means Q, 13 means K, and 1 nets you an ace.
         
         Also, nested if if the player gets an ace so that the 1 value can be counted
         if the 11 puts the player over 11. Something like
         
         if (11+playerTotal > 21)
         playerTotal = playerTotal-10;
         }
         */
        
        
        
      } else if (move.equalsIgnoreCase("quit")) {
        System.out.println("Thank you for playing.");
        playerTotal = 100;
      } else {
        System.out.println("Please enter 'Draw' to begin");
        move = keyboard.nextLine();
      }
    } 
    }
  }
}
//https://github.com/TomFanella4/Blackjack-Card-Game-with-AI/blob/master/Blackjack.java
