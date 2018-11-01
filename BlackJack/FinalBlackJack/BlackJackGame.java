import java.util.ArrayList;

public class BlackJackGame {
    private Decks gameDeck;
    private ArrayList<String> playerDeck, compDeck;
    private int playerTotal, compTotal;

    public BlackJackGame() {
        gameDeck = new Decks();
        playerDeck = new ArrayList<>();
        compDeck = new ArrayList<>();
        gameDeck.makeDeck();
        System.out.println(gameDeck.showDeck()); //Testing
        //playGame
        //determineWinner
    }
    public static void main(String[] args) {
        BlackJackGame game = new BlackJackGame();
    }
}
