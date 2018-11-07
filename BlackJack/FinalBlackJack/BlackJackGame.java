import java.util.ArrayList;
import java.util.Scanner;

public class BlackJackGame {
  private Decks gameDeck;
  private ArrayList<String> userDeck, compDeck;
  private int userTotal, compTotal, turn;
  private Scanner keys = new Scanner(System.in);

  private BlackJackGame() {
    gameDeck = new Decks();
    userDeck = new ArrayList<>();
    compDeck = new ArrayList<>();
    gameDeck.makeDeck();
    System.out.println("-------------------------------------------------------" +
            "------------------------------------------------------------");
    System.out.println("Welcome to Blackjack");
    System.out.println("The goal is to get as close as you can to the number 21 without going over.");
    System.out.println("Face cards are worth 10, and aces are worth either 1 or 11.");
    playTheGame(); //Plays the game
    determineWinner(); //Determines winner
  }

  private void playTheGame() {
    boolean game = true;
    while (game) {
      turn++;
      System.out.println("------");
      System.out.println("Turn "+turn);
      System.out.println("------");
      System.out.println("Hit or Stand?");
      String choose = keys.nextLine();
      while (!choose.equalsIgnoreCase("Hit") && !choose.equalsIgnoreCase("Stand")) {
        System.out.println("Please enter a valid move");
        choose = keys.nextLine();
      }
      choose = choose.toLowerCase();
      switch (choose) {
        case "hit":
          userTotal = takeTurn(userTotal,0); //Player Turn
          compTotal = takeTurn(compTotal,1); //Computer Turn
          if (userTotal >= 21 || compTotal >= 21)
            game = false;
          else
            System.out.println("\nYour hand: "+userDeck+"\nYour current total is "+userTotal);
          break;
        case "stand":
          game = false;
          break;
      }
    }
  } //End of playTheGame

  private int takeTurn (int score, int player) {
    boolean blackjack;
    score += drawCard(player);
    if (turn == 1) {
      score += drawCard(player);
      blackjack = blackJackHand(score);
      if (blackjack) {
        if (player == 0)
          System.out.println("You got blackjack!");
        else
          System.out.println("The computer got blackjack!");
      }
    }
    if (score > 21)
      score = gameDeck.aceStuff(score); //Makes 1 if it can, stays 11 if it can't
    return score;
  } //End of takeTurn

  private int drawCard(int player) {
    String card = gameDeck.drawCard();
    int cardVal;
    String value = card.substring(0,card.length()-1);
    if (player == 0) {
      userDeck.add(card);
    } else if (player == 1) {
      compDeck.add(card);
    }
    if (!isNumeric(value)) {
      if (value.equals("A")) {
        cardVal = 11;
      } else {
        cardVal = 10;
      }
    } else {
      cardVal = Integer.parseInt(value);
    }
    return cardVal;
  } //End of drawCard

  private void determineWinner() {
    if (userTotal > 21) {
      System.out.println("Sorry, you busted with a total of "+userTotal+" and a hand of "+userDeck);
      System.out.println("The computer won with "+compTotal+" total with a hand of "+compDeck);
    } else if (compTotal > 21) {
      System.out.println("The computer busted with a total of "+compTotal+" and a hand of "+compDeck);
      System.out.println("You won with "+userTotal+" total with a hand of "+userDeck);
    } else {
      if (userTotal > compTotal)
        System.out.println("Congratulations, you won! You had "+userTotal+" total with a hand of "+userDeck+
                ", and the computer had "+compTotal+" total with a hand of "+compDeck+".");
      else if (userTotal < compTotal) {
        System.out.println("Sorry, you lost. You had "+userTotal+" total with a hand of "+userDeck+
                ", and the computer had "+compTotal+" total with a hand of "+compDeck+".");
      }
      else if (userTotal == compTotal) {
        System.out.println("You tied with the computer! You had "+userTotal+" total with a hand of "+userDeck+
                ", and the computer had "+compTotal+" total with a hand of "+compDeck+".");
      }
    }
    System.out.println("Do you want to play again?"); //Works
    String playAgain = keys.nextLine();
    if (playAgain.equalsIgnoreCase("Yes"))
      new BlackJackGame();
  } //End of determineWinner

  private boolean blackJackHand(int score) {
    boolean blackjack = false;
    if (score == 21)
      blackjack = true;
    return blackjack;
  } //End of blackJackHand

  private static boolean isNumeric(String strNum) { //Done
    try {
      int d = Integer.parseInt(strNum);
    } catch (NumberFormatException | NullPointerException nfe) {
      return false;
    }
    return true;
  } //End of isNumeric

  public static void main(String[] args) { //Done
    BlackJackGame game = new BlackJackGame();
  }
}