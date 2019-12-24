import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack {
	private Deck mainDeck, dealerDeck; // Main draw deck and deck for dealer
	private ArrayList<Deck> playerDecks; // Decks for individual players
	private ArrayList<Integer> totals; // Totals for each player
	
	public BlackJack() {
		mainDeck = new Deck();
		dealerDeck = new Deck();
		playerDecks = new ArrayList<Deck>();
		totals = new ArrayList<Integer>();
		
		// Creates main deck, shuffles deck, gets num players and adds num decks, deals 2 cards to dealer & 2 to each player
		setGameUp();
		
		// Has each player take a turn
		playGame();
		
		// 
		String winner = determineWinner();
		endGame(winner, false);
	}
	
	public void setGameUp() {
		mainDeck.createDeck();
		mainDeck.shuffleDeck();
		Scanner kbInput = new Scanner(System.in);
		System.out.println("How Many Players? Ex: '2'");
		int numDecks = kbInput.nextInt();
		for (int i = 0; i < numDecks; i++) {
			playerDecks.add(new Deck());
		}
		dealCard(dealerDeck);
		dealCard(dealerDeck);
		for (Deck d: playerDecks) {
			dealCard(d);
			dealCard(d);
		}
	}
	
	public void playGame() {
		for (int i = 0; i < playerDecks.size(); i++) {
			Deck playerDeck = playerDecks.get(i);
			takeTurn(playerDeck, i + 1);
		}
		takeTurn(dealerDeck, 0);
	}
	
	public void takeTurn(Deck currentPlayerDeck, int playerNumber) {
		Scanner kbInput = new Scanner(System.in);
		String userChoice = "";
		if (playerNumber == 0) {
			System.out.println("Dealer's Turn");
		}
		else {
			System.out.println("Player " + playerNumber + "'s hand:");
			for(int i = 0; i < currentPlayerDeck.sizeOfDeck(); i++) {
				System.out.println(currentPlayerDeck.getCard(i));
			}
		}
		int total = 0;
		for (int i = 0; i < currentPlayerDeck.sizeOfDeck(); i++) {
			Card tempCard = currentPlayerDeck.getCard(i);
			int tempValue = tempCard.getValue();
			if (tempValue == 1 || tempValue == 11) {
				Card tempCard2 = currentPlayerDeck.getCard(1);
				int tempValue2 = tempCard2.getValue();
				if ((tempValue == 1 && tempValue2 == 11) || (tempValue == 11 && tempValue2 == 1)) {
					if (playerNumber == 0) {
						endGame("Dealer", true);
					}
					else {
						endGame("Player " + playerNumber, true);
					}
					break;
				}
			}
			if (tempValue > 10) {
				tempValue = 10;
			}
			if (tempValue == 1 && (11 + total <= 21)) {
				tempValue = 11;
			}
			total += tempValue;
			if (playerNumber == 0) {
				if (total < 17) {
					Card dealtCard = dealCard(currentPlayerDeck);
					total += dealtCard.getValue();
					System.out.println("Dealer Hit");
				}
				else {
					System.out.println("Dealer Stood");
					totals.add(total);
					break;
				}
			}
			else {
				System.out.println("Hit or Stand?");
				userChoice = kbInput.nextLine();
				userChoice = userChoice.toLowerCase();
				if(userChoice.equals("hit") || userChoice.equals("h")){
					Card dealtCard = dealCard(currentPlayerDeck);
					System.out.println("Player " + playerNumber + " got: " + dealtCard);
					total += dealtCard.getValue();
				}
				else {
					totals.add(total);
					break;
				}
			}
			if (total > 21) {
				for (int k = 0; k < currentPlayerDeck.sizeOfDeck(); k++) {
					if (tempCard.getValue() == 1 && total > 21) {
						total -= 10;
					}
				}
				if (playerNumber == 0) {
					System.out.println("Dealer" + " Busted!");
				}
				else {
					System.out.println("Player " + playerNumber + " Busted!");
				}
				totals.add(total);
				break;
			}
		}
	}
	
	public Card dealCard(Deck d) {
		Card dealtCard = mainDeck.removeTopCard();
		d.addCard(dealtCard);
		return dealtCard;
	}
	
	public String determineWinner() {
		String currentWinner = "";
		int winningTotal = 0;
		int tempWinningTotal = 0;
		int dealerTotal = totals.get(playerDecks.size());
		for (int i = 0; i < playerDecks.size(); i++) {
			tempWinningTotal = totals.get(i);
			if (tempWinningTotal > winningTotal && tempWinningTotal < 22) {
				winningTotal = tempWinningTotal;
				currentWinner = "Player " + (i + 1);
			}
		}
		if (dealerTotal > winningTotal && dealerTotal < 22) {
			currentWinner = "The Dealer";
		}
		return currentWinner;
	}
	
	public void endGame(String winner, Boolean blackJack) {
		if (blackJack) {
			System.out.println(winner + " got Blackjack!!!");
		}
		else {
			System.out.println(winner + " Won!");
		}
		for (int i = 1; i <= totals.size(); i++) {
			if (i == totals.size()) {
				System.out.println("The Dealer had " + totals.get(i - 1) + " points");
			}
			else {
				System.out.println("Player " + i + " had " + totals.get(i - 1) + " points");
			}
		}
	}
	
	public static void main(String[] args) {
		BlackJack game = new BlackJack();
	}
}
