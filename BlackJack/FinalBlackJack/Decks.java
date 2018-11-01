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

    public void removeTopCard() {
        cards.remove(0);
    }


    //Testing Methods
    public ArrayList showDeck() {
        return cards;
    }
} //End of class
