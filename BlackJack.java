import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
//TODO Maybe add playing again function
//TODO shuffle deck instead of make it
//TODO add computer player
//TODO remove cards from deck
//TODO 2 cards on first draw
public class Blackjack {
  static Random rng = new Random(); //Rng
  static int cardPick = rng.nextInt(52); //Rng
  static Scanner keyboard = new Scanner(System.in); //Input
  static int playerTotal = 0; //User's in game total
  static String playing; //Used for in game check to see if the user stands
  static int turn = 1; //Turn counter
  static int numOfCards = 0; //Draw counter
  //Deck stuff
  static String[] suitsArray = {"â™ ","â™¥","â™¦","â™£",};
  static String[] cardsArray = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
  static ArrayList<String> deck = new ArrayList<String>(); //Deck
  static ArrayList<String> suits = new ArrayList<String>(); //Suits
  static ArrayList<String> cards = new ArrayList<String>(); //Cards
  static ArrayList<String> playerDraw = new ArrayList<String>(); //Drawn Cards
  static ArrayList<Boolean> aceCheck = new ArrayList<Boolean>(); //Checks for drawn aces
  static ArrayList<Boolean> usedAceCheck = new ArrayList<Boolean>(); //Checks for aces accounted for
  static boolean aceS = false;
  static boolean aceH = false;
  static boolean aceD = false;
  static boolean aceC = false;

  public static void shuffleDeck() { //Creates deck TODO make it shuffle instead of just create the deck
    Collections.addAll(suits,suitsArray);
    Collections.addAll(cards,cardsArray);
    for (int suit = 0; suit<= 3; suit++) {
      for (int card = 0; card <=12;card++) {
        deck.add(cards.get(card)+suits.get(suit));
      }
    }
  } //End of shuffleDeck

  public static void main (String [] args) {
    shuffleDeck(); //Currently only creates the deck in the same order everytime
    System.out.println("-------------------------------------------------------------------------------------------------------------------");
    System.out.println("Welcome to Blackjack");
    System.out.println("The goal is to get as close as you can to the number 21 without going over.");
    System.out.println("Face cards are worth 10, and aces are worth either 1 or 11.");
    boolean game = true;
    boolean win = true;

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
      }
    } //End of playing check
    if (win == true) {
      System.out.println("You won!");
    } else if (win == false) {
      System.out.println("You lost, better luck next time.");
    }
    System.out.println("Thank you for playing!");
  } //End of main

  public static void playTheGame() {
    System.out.println("Enter 'draw' to draw a card, 'stand' to stop receiving cards, and 'view' to see all the cards that you have drawn.");
    playing = keyboard.nextLine();
    while (!playing.equalsIgnoreCase("draw") && !playing.equalsIgnoreCase("stand") && !playing.equalsIgnoreCase("view")) {
      System.out.println("Please enter a valid move.");
      playing = keyboard.nextLine();
    }
    switch(playing) {
      //If you draw
      case "draw":
      case "Draw":
        System.out.println("------");
        System.out.println("Turn "+turn);
        System.out.println("------");
        int cardVal = 0;
        cardVal = cardPicker();
        turn++;
        numOfCards++;
        if (cardVal == 1) { //Makes ace 11 if doesn't bust because of it
          if (playerTotal + 11 < 21) {
            cardVal = 11;
          }
        }
        //If the drawn deck contains an ace
        if (playerDraw.contains("Aâ™ ")) {
          if (aceS == false) {
            aceCheck.add(true);
          }
          aceS = true;
        } if (playerDraw.contains("Aâ™¥")) {
          if (aceH == false) {
            aceCheck.add(true);
          }
          aceH = true;
        } if (playerDraw.contains("Aâ™¦")) {
          if (aceD == false) {
            aceCheck.add(true);
          }
          aceD = true;
        } if (playerDraw.contains("Aâ™£")) {
          if (aceC == false) {
            aceCheck.add(true);
          }
          aceC = true;
        }
        if (playerTotal + cardVal > 21) {
          if(aceCheck.size() != usedAceCheck.size()) { //Trying to prevent loss of score with the -10 if the ace should be 1 instead of 11
            for(int x = 0; x<=usedAceCheck.size();x++) {
              playerTotal = playerTotal - 10;
            }
            usedAceCheck.add(false);
          }
        }
        playerTotal = playerTotal + cardVal;
        cardPick = rng.nextInt(52);
        break;
        //If you quit
      case "stand":
      case "Stand":
        break;
        //if you view your cards
      case "view":
      case "View":
        if (turn == 1) {
        System.out.println("You have not drawn any cards yet.");
      } else {
        System.out.println("So far, you have drawn: "+playerDraw);
      }
      break;
    }
  } //End of playTheGame

  public static int cardPicker() {
    String valString = deck.get(cardPick);
    System.out.println("You drew the "+valString);
    playerDraw.add(valString); //Adds drawn card to player draw pile TODO remove the used card from the deck
    String cardStringVal = valString.substring(0,valString.length()-3);
    int cardVal = 0;
    if (isNumeric(cardStringVal) == false) {
      if (cardStringVal.equals("A")) {
        cardVal = 1;
      } else {
        cardVal = 10;
      }
    } else {
      cardVal = Integer.parseInt(cardStringVal);
    }
    return cardVal;
  } //End of cardPicker

  public static boolean isNumeric(String strNum) {
    try {
      int d = Integer.parseInt(strNum);
    } catch (NumberFormatException | NullPointerException nfe) {
      return false;
    }
    return true;
  } //End of isNumeric
} //End of class
//https://github.com/TomFanella4/Blackjack-Card-Game-with-AI/blob/master/Blackjack.java (If I need a guide)
