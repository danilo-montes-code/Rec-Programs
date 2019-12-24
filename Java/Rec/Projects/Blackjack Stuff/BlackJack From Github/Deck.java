import java.util.ArrayList;

public class Deck {
	public ArrayList<Card> cards; // Deck of cards

	public Deck(){ // Constructor
		cards = new ArrayList<Card>();
	}
	
	public void printCards(){ // Prints all the cards in the deck
		for(Card c:cards)
			System.out.println(c.getName());
	}
	
	public void addCard(Card c){ // Adds a card to the deck
		cards.add(c);
	}
	
	public Card removeTopCard(){ // Removes a card from the top of the deck
		return cards.remove(0);
		
	}
	
	public Card getCard(int index){ // Gets a card at a specific spot within the deck
		return cards.get(index);
	}
	
	public int sizeOfDeck(){ // Returns the size of the deck
		return cards.size();
	}
	
	public void shuffleDeck(){ // Shuffles the cards in the deck
		int size = cards.size();
		for(int i = 0; i < size; i++) {
			int random1 = (int)(Math.random() * size);
			int random2 = (int)(Math.random() * size);
			Card tempC = cards.get(random2);
			cards.set(random2, cards.get(random1));
			cards.set(random1, tempC);
		}
	}
	
	public void createDeck(){ // Generates the deck in default order
		String tempName;
		String suite = "";
		for(int j = 0; j < 4; j++) { // Determines suite of the card
			if(j == 0) {
				suite = "Clubs";
			}
			if(j == 1) {
				suite = "Spades";
			}
			if(j == 2) {
				suite = "Hearts";
			}
			if(j == 3) {
				suite = "Dimonds";
			}
			for(int i = 1; i < 14; i++) { // Determines value of the card
				if(i == 1) {
					tempName = "Ace";
				}
				else if(i == 11) {
					tempName = "Jack";
				}
				else if(i == 12) {
					tempName = "Queen";
				}
				else if(i == 13) {
					tempName = "King";
				}
				else {
					tempName = Integer.toString(i);
				}
				cards.add(new Card(tempName + " of " + suite,i)); // adds the card to the deck
			}
		}
	}
}
