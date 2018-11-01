import java.util.ArrayList;
import java.util.Collections;

public class Decks {
    private ArrayList<String> cards;

    public Decks() { //Constructor
        cards = new ArrayList<>();
    }

    public void makeDeck() { //Makes the deck
        String[] suitsArray = {"♠","♥","♦","♣"};
        String[] cardsArray = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        for (int suit2 = 0; suit2<= 3; suit2++) {
            for (int card2 = 0; card2 <=12; card2++) {
                cards.add(cardsArray[card2]+suitsArray[suit2]);
            }
        }
        Collections.shuffle(cards);
    } //End of makeDeck



    //Testing Methods
    public ArrayList showDeck() {
        return cards;
    }
} //End of class
/*
    //Deck Stuff
    private static String[] suitsArray = {"♠","♥","♦","♣"};
    private static ArrayList<String> suits = new ArrayList<String>(); //Suits

    private static ArrayList<String> cards = new ArrayList<String>(); //Cards
    private static ArrayList<String> dealerDeck = new ArrayList<String>(); //Deck
    //Game Deck Stuff
    private static ArrayList<String> playerHand = new ArrayList<String>(); //Player's Drawn Cards
    private static ArrayList<Boolean> aceCheck = new ArrayList<Boolean>(); //Checks for drawn aces
    private static ArrayList<Boolean> usedAceCheck = new ArrayList<Boolean>(); //Checks for aces accounted for
    //Creating the deck
    private static void createDeck() {
        Collections.addAll(suits,suitsArray);
        Collections.addAll(cards,cardsArray);
        for (int suit = 0; suit<= 3; suit++) {
            for (int card = 0; card <=12;card++) {
                dealerDeck.add(cards.get(card)+suits.get(suit));
            }
        }
        cardPick = rng.nextInt(dealerDeck.size());
    }
    */