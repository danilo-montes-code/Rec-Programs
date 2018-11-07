import java.util.ArrayList;
import java.util.Collections;

public class Decks {
  private ArrayList<String> cards;
  private ArrayList<Boolean> aceCheck, usedAceCheck;
  private boolean aceS, aceD, aceH, aceC;

  public Decks() { //Constructor
    cards = new ArrayList<>();
    aceCheck = new ArrayList<>();
    usedAceCheck = new ArrayList<>();
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

  public String drawCard() {
    return cards.remove(0);
  }

  public int aceStuff(int score) {
    if (!cards.contains("A♠")) {
      if (!aceS)
        aceCheck.add(true);
      aceS = true;
    }
    if (!cards.contains("A♥")) {
      if (!aceH)
        aceCheck.add(true);
      aceH = true;
    }
    if (!cards.contains("A♦")) {
      if (!aceD)
        aceCheck.add(true);
      aceD = true;
    }
    if (!cards.contains("A♣")) {
      if (!aceC)
        aceCheck.add(true);
      aceC = true;
    }
    if (aceCheck.size() != usedAceCheck.size()) {
      for (int x = usedAceCheck.size(); x < aceCheck.size(); x++) {
        score -= 10;
        usedAceCheck.add(false);
        if (score + 11 < 21) { //Only runs if there are 2 aces that haven't been made 1
          score += 10;
          usedAceCheck.remove(0);
        }
      }
    }
    return score;
  } //End of aceStuff

  //Testing Methods
  public ArrayList showDeck() {
    return cards;
  }
  public void removeTop() {
    cards.remove(0);
  }
} //End of class