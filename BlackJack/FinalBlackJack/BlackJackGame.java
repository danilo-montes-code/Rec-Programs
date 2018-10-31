public class BlackJackGame {
    public BlackJackGame() {
        Decks deck = new Decks();
        deck.deckMaker();

    }
    public static void main(String[] args) {
        BlackJackGame game = new BlackJackGame();
    }
}
