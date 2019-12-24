import java.util.ArrayList;

public class BlackjackTester {
    /*private Decks test;
    private ArrayList<String> userDeck, compDeck;
    private int userTotal, compTotal;

    public BlackjackTester() {
        userDeck = new ArrayList<>();
        compDeck = new ArrayList<>();
        test = new Decks();
        test.makeDeck();

        System.out.println("Game Deck " + test.showDeck()+"\n"); //Shows full deck

        System.out.println("Turn 1");
        compTotal = takeTurn(compTotal,1);
        userTotal = takeTurn(userTotal,0);
        determineWinner();
    }

    private int takeTurn (int score, int player) {
        boolean blackjack;
        score += drawCard(player);
            test.removeTop();
            test.removeTop();
            test.removeTop();
            test.removeTop();
            test.removeTop();
            test.removeTop();
            test.removeTop();
            test.removeTop();
            test.removeTop();
            test.removeTop();
            test.removeTop();
        score += drawCard(player);
        blackjack = blackJackHand(score);
        if (blackjack) {
            if (player == 0)
                System.out.println("You got blackjack!");
            else
                System.out.println("The computer got blackjack!");
        }
        if (score > 21)
            score = test.aceStuff(score); //Makes 1 if it can, stays 11 if it can't
        return score;
    }

    public int drawCard(int player) {
        String card = test.drawCard();
        int cardVal;
        String value = card.substring(0,1);
        if (player == 0) {
            userDeck.add(card);
        } else if (player == 1) {
            compDeck.add(card);
        }
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

    public boolean blackJackHand(int score) {
        boolean blackjack = false;
        if (score == 21)
            blackjack = true;
        return blackjack;
    }

    private void determineWinner() {
        if (userTotal > 21) {
            System.out.println("Sorry, you busted with a total of "+userTotal+" and a hand of "+userDeck);
            System.out.println("The computer had "+compTotal+" total with a hand of "+compDeck);
        } else if (compTotal > 21) {
            System.out.println("The computer busted with a total of "+compTotal+" and a hand of "+compDeck);
            System.out.println("You had "+userTotal+" total with a hand of "+userDeck);
        } else {
            if (userTotal > compTotal)
                System.out.println("Congratulations, you won! You had "+userTotal+" total with a hand of "+userDeck+
                        ", and the computer had "+compTotal+" total with a hand of "+compDeck+".");
            else if (userTotal < compTotal) {
                System.out.println("Sorry, you lost. You had "+userTotal+" total with a hand of "+userDeck+
                        ", and the computer had "+compTotal+" total with a hand of "+compDeck+".");
            }
            else if (userTotal == compTotal) {
                System.out.println("You tied with the computer! You had "+userTotal+" total with a hand of "+userDeck+
                        ", and the computer had "+compTotal+" total with a hand of "+compDeck+".");
            }
        }
    }

    private static boolean isNumeric(String strNum) { //Done
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    } //End of isNumeric

    public static void main(String[] args) {
        BlackjackTester test = new BlackjackTester();
    } */
}
