import java.util.Scanner;
import java.util.Random;
//TODO Add blackjack ai somehow
//TODO Maybe add playing again function
public class Blackjack {
  static Random rng = new Random(); //Rng
  static Scanner keyboard = new Scanner(System.in); //Input
  static int card = rng.nextInt(13)+1;
  static int playerTotal = 0;
  static String playing = ""; //Used for in game check to see if the user stands
  static String[] Suits = {
    "♠",
    "♥",
    "♦",
    "♣",
  };
  static String[] Cards = {
    "A",
    "2",
    "3",
    "4",
    "5",
    "6",
    "7",
    "8",
    "9",
    "10",
    "J",
    "Q",
    "K"
  };

  public static void playTheGame() {
    System.out.println("Enter 'draw' to draw a card and 'stand' to stop receiving cards.");
    playing = keyboard.nextLine();
    switch (playing){
      //If you draw
      case "draw":
      case "Draw":

      break;
      //If you quit
      case "stand":
      case "Stand":
      break;
    }
  } //End of playing the game

  public static void main (String [] args) {
    System.out.println("Welcome to Blackjack");
    System.out.println("The goal is to get as close as you can to the number 21 without going over.");
    System.out.println("Face cards are worth 10, and aces are worth either 1 or 11.");
    boolean game = true;
    boolean win = true;
    int numOfCards = 1;

    while (game == true) {
      playTheGame();
      if (playerTotal > 21) {
        win = false;
        game = false;
        System.out.println("Your final total is "+playerTotal" with "+numOfCards+" cards drawn.");
      }
      if (playing.equalsIgnoreCase("stand")) { //Once Ai is added, check if playerTotal > aiTotal and give win accordingly
        game = false;
        System.out.println("Your final total is "+playerTotal" with "+numOfCards+" cards drawn.");
      }
      if (game == true) {
        System.out.println("Your current total is "+playerTotal+" with "+numOfCards+" cards drawn.");
        cards++;
      }
    } //End of playing check
    System.out.println("Thank you for playing!");
  } //End of main
} //End of class

/*TODO Nested if if the player gets an ace so that the 1 value can be counted
if the 11 puts the player over 11. Something like

if (11+playerTotal > 21)
playerTotal = playerTotal-10;
*/
//https://github.com/TomFanella4/Blackjack-Card-Game-with-AI/blob/master/Blackjack.java (If I need a guide)
