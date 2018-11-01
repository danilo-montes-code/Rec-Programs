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
        System.out.println(gameDeck.showDeck()); //Testing
        playTheGame(); //Plays the game
        determineWinner(); //Determines winner and gives win message
    }
    public void playTheGame() {
        boolean game = true;
        while (game) {
            for (int x = 0; x < 1; x++) {
                turn(x);
            }
        }

    }
    public void turn (int player) {
        if (player == 0) {
            
        } else {

        }

    }
    public void determineWinner() {

    }
    public static void main(String[] args) {
        BlackJackGame game = new BlackJackGame();
    }
}
