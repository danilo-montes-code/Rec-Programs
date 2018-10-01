import java.util.Scanner;
import java.util.Random;

public class Blackjack {
  public static void main (String [] args) {
    Scanner keyboard = new Scanner(System.in);
    
    System.out.println("Welcome to Blackjack");
    System.out.println("The goal is to get as close as you can to the number 21 without going over.");
    System.out.println("Face cards are worth 10, and aces are worth either 1 or 11.:);
    System.out.println('Enter the word "Draw" to begin');
    String move = keyboard.nextLine();
                       
    if (move.equalsIgnoreCase("draw") {
      /* Make the card variable, which is random, and if the random number is 10, 
      then randomly choose between the 10 card or a face card. 
      Figure out if you can make the 1 and 11 values for ace the same variable, 
      so that there is an equal chance of getting either one. */
      
      System.out.println("Your first draw is: +card");
    
    } else if (move.equalsIgnoreCase("quit") {
      System.out.println("Thank you for playing.");
      break;
    } else {
      System.out.println("Please enter "Draw" to begin");
      move = keyboard.nextLine();
    }
  }
}
