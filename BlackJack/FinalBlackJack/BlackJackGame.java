import java.util.ArrayList;

public class BlackJackGame {
  private Decks gameDeck;
  private ArrayList<String> userDeck, compDeck;
  private int userTotal, compTotal;

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
    determineWinner(); //Determines winner and gives win message
  }

  public void playTheGame() {
    Scanner keys = new Scanner(System.in);
    boolean game = true;
    while (game) {
      System.out.println("------");
      System.out.println("Turn "+turn);
      System.out.println("------");
      System.out.println("Hit or Stand?");
      choose = keys.nextLine();
      while (!choose.equalsIgnoreCase("Hit") && !choose.equalsIgnoreCase("Stand")) {
        System.out.println("Please enter a valid move");
        choose = keys.nextLine();
      }
      choose = choose.toLowerCase();
      switch (choose) {
        case "hit":
        takeTurn(0, playerTotal, turn); //Player Turn
        takeTurn(1, compTotal, turn); //Computer Turn
        case "stand":
        break;
      }
    }
  }
  public void takeTurn (int player, int score, int turn) {
    int card;
    if (turn == 1) {
      
    } else {
      if (player == 0) {
        card = drawCard();
        if (score + card > 21) {
          score += card;

        }
      } else {
        card = drawCard();

      }
    }
  }

  public int drawCard() {
    //String card = gameDeck.drawCard();
    int cardVal = 0;
    String value = gameDeck.drawCard().substring(0,drawnCard.length()-1);
    if (!isNumeric(value)) {
      if (value.equals("A")) {
        cardVal = 1;
      } else {
        cardVal = 10;
      }
    } else {
      cardVal = Integer.parseInt(value);
    }
    return cardVal;
  }

  public void determineWinner() {

  }

  private static boolean isNumeric(String strNum) {
    try {
      int d = Integer.parseInt(strNum);
    } catch (NumberFormatException | NullPointerException nfe) {
      return false;
    }
    return true;
  } //End of isNumeric

  public static void main(String[] args) {
    BlackJackGame game = new BlackJackGame();
  }
}
