package Rec.Bingo;

import java.util.ArrayList;

public class Player {
    BingoCard card;
    boolean win;
    String name;
    public Player(String name) {
        card = new BingoCard();
        win = false;
        this.name = name;
    }
    public void crossOff(String ball) {
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                if (card.getCard()[x][y].equals(" "+ball+" "))
                    card.getCard()[x][y] = "|" + card.getCard()[x][y].substring(1,3) + "|";
            }
        }
    }

    public String showName() {
        return name;
    }

    public BingoCard showCard() {
        System.out.println(name+"'s Card");
        return card;
    }
    public boolean hasWon() {
        ArrayList<Boolean> winChecker = new ArrayList<>();
        //Checks columns
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                if (card.getCard()[x][y].substring(0,1).equals("|") && card.getCard()[x][y].substring(3,4).equals("|")) {
                    winChecker.add(true);
                }
            }
            if (winChecker.size() == 5) {
                win = true;
            } else {
                winChecker.clear();
            }
        }
        //Checks rows
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                if (card.getCard()[y][x].substring(0,1).equals("|") && card.getCard()[y][x].substring(3,4).equals("|")) {
                    winChecker.add(true);
                }
            }
            if (winChecker.size() == 5) {
                win = true;
            } else {
                winChecker.clear();
            }
        }
        //Checks diagonals
        if (card.getCard()[0][0].substring(0,1).equals("|") && card.getCard()[0][0].substring(3,4).equals("|"))
            if (card.getCard()[1][1].substring(0,1).equals("|") && card.getCard()[1][1].substring(3,4).equals("|"))
                if (card.getCard()[2][2].substring(0,1).equals("|") && card.getCard()[2][2].substring(3,4).equals("|"))
                    if (card.getCard()[3][3].substring(0,1).equals("|") && card.getCard()[3][3].substring(3,4).equals("|"))
                        if (card.getCard()[4][4].substring(0, 1).equals("|") && card.getCard()[4][4].substring(3, 4).equals("|"))
                            win = true;
        if (card.getCard()[4][0].substring(0,1).equals("|") && card.getCard()[4][0].substring(3,4).equals("|"))
            if (card.getCard()[3][1].substring(0,1).equals("|") && card.getCard()[3][1].substring(3,4).equals("|"))
                if (card.getCard()[2][2].substring(0,1).equals("|") && card.getCard()[2][2].substring(3,4).equals("|"))
                    if (card.getCard()[1][3].substring(0,1).equals("|") && card.getCard()[1][3].substring(3,4).equals("|"))
                        if (card.getCard()[0][4].substring(0, 1).equals("|") && card.getCard()[0][4].substring(3, 4).equals("|"))
                            win = true;
        return win;
    }
}
