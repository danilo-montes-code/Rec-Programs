import java.util.ArrayList;
import java.util.Scanner;

public class BlackJackGame {
  private Decks gameDeck;
  private ArrayList<String> userDeck, compDeck;
  private int userTotal, compTotal, turn;
  private Scanner keys = new Scanner(System.in);

  public BlackJackGame() {
    gameDeck = new Decks();
    userDeck = new ArrayList<>();
    compDeck = new ArrayList<>();
    gameDeck.makeDeck();
    System.out.println("-------------------------------------------------------------------------------------------------------------------");
    System.out.println("Welcome to Blackjack");
    System.out.println("The goal is to get as close as you can to the number 21 without going over.");
    System.out.println("Face cards are worth 10, and aces are worth either 1 or 11.");
    playTheGame(); //Plays the game
    determineWinner(); //Determines winner
  }

  public void playTheGame() {
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
        takeTurn(userTotal); //Player Turn
        takeTurn(compTotal); //Computer Turn
        case "stand":
        game = false;
        break;
      }
    }
  }
  public void takeTurn (int score) {
    int card, card2;
    if (turn == 1) {
      card = drawCard();
      card2 = drawCard();
      if (card == card2)
      card2 = 1;
      score = card + card2;
    } else {
      card = drawCard();
      if (score + card > 21) {
        if (card == 11) {
          card = gameDeck.aceStuff(card, score); //Makes 1 if it can, stays 11 if it can't
        }
      }
      score += card;
    }
  }

  public int drawCard() {
    //String card = gameDeck.drawCard();
    int cardVal = 0;
    String value = gameDeck.drawCard().substring(0,1);
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
  }


  public void determineWinner() { //Seemingly Done
    if (userTotal > 21) {
      System.out.println("Sorry, you busted with a total of "+userTotal);
    } else if (compTotal > 21) {
      System.out.println("The computer busted with a total of "+compTotal);
    } else {
      if (userTotal > compTotal)
      System.out.println("Congratulations, you won! You had "+userTotal+" total, and the computer had "+compTotal+" total.");
      else if (userTotal < compTotal) {
        System.out.println("Sorry, you lost. You had "+userTotal+" total, and the computer had "+compTotal+" total.");
      }
    }
    System.out.println("Do you want to play again?"); //Works
    String playAgain = keys.nextLine();
    if (playAgain.equalsIgnoreCase("Yes"))
    new BlackJackGame();
}

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
