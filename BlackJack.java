import java.util.Scanner;
import java.util.Random;
//TODO Add blackjack ai somehow
//TODO Maybe add playing again function
public class Blackjack {
  static Random rng = new Random(); //Rng
  static Scanner keyboard = new Scanner(System.in); //Input
  static int card = rng.nextInt(13);
  static int playerTotal = 0;
  static String playing; //Used for in game check to see if the user stands
  static String[] suits = {
    "?",
    "?",
    "?",
    "?",
  };
  static String[] cards = {
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
    while (!playing.equalsIgnoreCase("draw") && !playing.equalsIgnoreCase("stand")) {
      System.out.println("Please enter a valid move.");
      playing = keyboard.nextLine();
    }
    switch(playing) {
      //If you draw
      case "draw":
      case "Draw":
      if (card == 0 || card == 7) {
        System.out.println("You drew an "+ cards[card]);
      } else {
        System.out.println("You drew a "+ cards[card]);
      }
      if (card >=1 && card <=9) {
        playerTotal = playerTotal + (card+1);
      } else if (card >= 10) {
        playerTotal = playerTotal + 10;
      } else if (card == 0) { //TODO if playerTotal doesn't go over 21 when the ace is taken as 1 instead of 11 then do that please
        if (playerTotal+11 > 21) {
          playerTotal = playerTotal + 1;
        } else if (playerTotal+11 <= 21) {
          playerTotal = playerTotal + 11;
        }
      }
      card = rng.nextInt(13); //TODO change rng stuff to make more rng
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
        System.out.println("Your final total is "+playerTotal+" with "+numOfCards+" cards drawn.");
      } else if (playerTotal == 21) {
        game = false;
      }
      if (playing.equalsIgnoreCase("stand")) { //TODO Once Ai is added, check if playerTotal > aiTotal and give win accordingly
        game = false;
        System.out.println("Your final total is "+playerTotal+" with "+numOfCards+" cards drawn.");
      }
      if (game == true) {
        System.out.println("Your current total is "+playerTotal+" with "+numOfCards+" cards drawn.");
        numOfCards++;
      }
    } //End of playing check
    if (win == true) {
      System.out.println("You won!");
    } else if (win == false) {
      System.out.println("You lost, better luck next time.");
    }
    System.out.println("Thank you for playing!");
  } //End of main
} //End of class

/*TODO Nested if if the player gets an ace so that the 1 value can be counted
if the 11 puts the player over 11. Something like
if (11+playerTotal > 21)
playerTotal = playerTotal-10;
*/
//https://github.com/TomFanella4/Blackjack-Card-Game-with-AI/blob/master/Blackjack.java (If I need a guide)
