import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
public class BlackJack {
    //Starting stuff
    static Random rng = new Random(); //RNG
    static int cardPick; //Picks a card
    static Scanner keys = new Scanner(System.in); //Input
    static int playerTotal = 0; //User's in game total
    static int compTotal = 0; //Computer's in game total
    static int turn = 1; //Turn counter
    static String playing = ""; //Hit, Stand check
    static String drawnCard = ""; //Drawn Card
    //Deck stuff
    static String[] suitsArray = {"♠","♥","♦","♣"};
    static ArrayList<String> suits = new ArrayList<String>(); //Suits
    static String[] cardsArray = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    static ArrayList<String> cards = new ArrayList<String>(); //Cards
    static ArrayList<String> dealerDeck = new ArrayList<String>(); //Deck
    //Game Deck stuff
    static ArrayList<String> playerHand = new ArrayList<String>(); //Player's Drawn Cards
    static ArrayList<Boolean> aceCheck = new ArrayList<Boolean>(); //Checks for drawn aces
    static ArrayList<Boolean> usedAceCheck = new ArrayList<Boolean>(); //Checks for aces accounted for
    //Aces stuff - Checks if aces were used
    static boolean aceS = false;
    static boolean aceH = false;
    static boolean aceD = false;
    static boolean aceC = false;

    public static void main(String[] args) { //[PRETTY SURE IT'S DONE]
        createDeck();
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        System.out.println("Welcome to Blackjack");
        System.out.println("The goal is to get as close as you can to the number 21 without going over.");
        System.out.println("Face cards are worth 10, and aces are worth either 1 or 11.");
        boolean game = true;
        boolean win = false;
        while(game) {
            System.out.println("------");
            System.out.println("Turn "+turn);
            System.out.println("------");
            if (turn != 1) {
                System.out.println("Your current total is "+playerTotal+", and you hand is "+playerHand);
            }
            playTheGame();
            if (playing.equalsIgnoreCase("hit"))
                turn++;
            else { //If you stand
                game = false;
                System.out.println("Your final total is "+playerTotal+", and you hand is "+playerHand);
                if (playerTotal > compTotal)
                    win = true;
            }
            if (playerTotal > 21)
                game = false;
        } //End of game loop
        if (playerTotal > compTotal && playerTotal <= 21)
            win = true;
        if (win)
            System.out.println("You won!");
        else
            System.out.println("You lost!");
        if (playerTotal > 21)
            System.out.println("You busted with "+playerTotal+". The computer had a total of "+compTotal);
        else
            System.out.println("You had a total of "+playerTotal+" and the computer had a total of "+compTotal);
        System.out.println("Thanks for playing!");
    } //End of main

    private static void createDeck() { //[DONE]
        Collections.addAll(suits,suitsArray);
        Collections.addAll(cards,cardsArray);
        for (int suit = 0; suit<= 3; suit++) {
            for (int card = 0; card <=12;card++) {
                dealerDeck.add(cards.get(card)+suits.get(suit));
            }
        }
        cardPick = rng.nextInt(dealerDeck.size());
    } //End of createDeck

    private static void playTheGame() { //[MAYBE DONE]
        System.out.println("Enter 'hit' to draw a card and 'stand' to stop receiving cards.");
        playing = keys.nextLine();
        while (!playing.equalsIgnoreCase("stand") && !playing.equalsIgnoreCase("hit")) {
            System.out.println("Please enter a valid move.");
            playing = keys.nextLine();
        }
        switch(playing) {
            case "hit":
            case "Hit":
                playerTurn();
                compTurn();
            case "stand":
            case "Stand":
                break;
        }
    } //End of playTheGame

    private static void playerTurn() { //[MAYBE DONE]
        int cardVal = 0;
        if (turn == 1) {
            playerTotal = cardGet();
            playerHand.add(drawnCard);
            cardRemover();
            playerTotal = playerTotal + cardGet();
            playerHand.add(drawnCard);
            cardRemover();
            System.out.println("Your hand: " + playerHand);
            //Do some if you draw two aces two decks stuff. Also, check to see if the aces are missing from the game deck to account for them
        } else {
            cardVal = cardGet();
            System.out.println("You drew the "+dealerDeck.get(cardPick));
            playerHand.add(drawnCard);
            cardRemover();
            if (cardVal == 1) { //Makes ace 11 if doesn't bust because of it
                if (playerTotal + 11 < 21) {
                    cardVal = 11;
                }
                cardVal = aceStuff(cardVal, playerTotal);
            }
                playerTotal = playerTotal + cardVal;
        }
    } //End of playerTurn

    private static void compTurn() {
        int cardVal = 0;
        if (turn == 1) {
            compTotal = cardGet();
            cardRemover();
            compTotal = compTotal + cardGet();
            cardRemover();
        } else {
            cardVal = cardGet();
            cardRemover();
            if (cardVal == 1) { //Makes ace 11 if doesn't bust because of it
                if (compTotal + 11 < 21) {
                    cardVal = 11;
                }
                cardVal = aceStuff(cardVal, compTotal);
            }
            compTotal = compTotal + cardVal;
        }
        compTotal  = compTotal + cardVal;
    } //End of compTurn

    private static int cardGet() {
        int cardVal = 0;
        drawnCard = dealerDeck.get(cardPick);
        String drawnCardValue = drawnCard.substring(0,drawnCard.length()-1);
        if (!isNumeric(drawnCardValue)) {
            if (drawnCardValue.equals("A")) {
                cardVal = 1;
            } else {
                cardVal = 10;
            }
        } else {
            cardVal = Integer.parseInt(drawnCardValue);
        }
        return cardVal;
    } //End of cardGet

    private static int aceStuff(int aceValue, int total) { //[MAYBE DONE, NEEDS TESTING]
        if (!dealerDeck.contains("A♠")) {
            if (!aceS) //Only adds ace to aceCheck if it wasn't already in there
                aceCheck.add(true);
            aceS = true;
        }if (!dealerDeck.contains("A♥")) {
            if (!aceH)
                aceCheck.add(true);
            aceH = true;
        } if (!dealerDeck.contains("A♦")) {
            if (!aceD)
                aceCheck.add(true);
            aceD = true;
        } if (!dealerDeck.contains("A♣")) {
            if (!aceC)
                aceCheck.add(true);
            aceC = true;
        }
        //If the drawn deck contains an ace
        if (total + aceValue > 21) {
            if(aceCheck.size() != usedAceCheck.size()) { //Trying to prevent loss of score with the -10 if the ace should be 1 instead of 11
                for(int x = usedAceCheck.size(); x<aceCheck.size();x++) {
                    total = total - 10;
                }
                usedAceCheck.add(false);
            }
        }
        return total;
    } //End of aceStuff

    private static void cardRemover() { //[DONE]
        dealerDeck.remove(cardPick);
        cardPick = rng.nextInt(dealerDeck.size());
    } //End of cardRemover

    private static boolean isNumeric(String strNum) { //[DONE]
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    } //End of isNumeric
} //End of class
//https://github.com/TomFanella4/Blackjack-Card-Game-with-AI/blob/master/Blackjack.java (If I need a guide)
