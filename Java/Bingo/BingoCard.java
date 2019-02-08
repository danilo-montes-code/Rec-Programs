package Rec.Bingo;

import java.util.Random;
import java.util.Collections;
import java.util.ArrayList;

public class BingoCard {
    String[][] card;
    ArrayList<String> numbers = new ArrayList<>();
    Random rng = new Random();
    public BingoCard() {
        // 75 Card Bingo, 5x5 board, each column contains 5 of 15 nums, each column in increasing order
        makeCard();
    }

    public void makeNumbers(int start, int finish) {
        numbers.clear();
        for (int x = start; x < finish; x++) {
            if (x < 10)
                numbers.add(" 0"+Integer.toString(x)+" ");
            else
                numbers.add(" "+Integer.toString(x)+" ");
        }
        Collections.shuffle(numbers);
    }

    public void makeCard() {
        card = new String[5][5];
        makeNumbers(1, 16); // B column
        for (int x = 0; x < card[0].length; x++)
            card[0][x] = numbers.remove(0);
        makeNumbers(16, 31); // I column
        for (int x = 0; x < card[1].length; x++)
            card[1][x] = numbers.remove(0);
        makeNumbers(31, 46); // N column
        for (int x = 0; x < card[2].length; x++)
            card[2][x] = numbers.remove(0);
        makeNumbers(46, 61); // G column
        for (int x = 0; x < card[3].length; x++)
            card[3][x] = numbers.remove(0);
        makeNumbers(61, 76); // O column
        for (int x = 0; x < card[4].length; x++)
            card[4][x] = numbers.remove(0);
    }

    public String[][] getCard() {
        return card;
    }

    public String toString() {
        // First [] is for column, second [] is for row
        return " B    I    N    G    O \n"+card[0][0]+" "+card[1][0]+" "+card[2][0]+" "+card[3][0]+" "
                +card[4][0]+"\n"+card[0][1]+" "+card[1][1]+" "+card[2][1]+" "+card[3][1]+" "+card[4][1]+"\n"+card[0][2]
                +" "+card[1][2]+" "+card[2][2]+" "+card[3][2]+" "+card[4][2]+"\n"+card[0][3]+" "+card[1][3]+" "
                +card[2][3]+" "+card[3][3]+" "+card[4][3]+"\n"+card[0][4]+" "+card[1][4]+" "+card[2][4]+" "+card[3][4]
                +" "+card[4][4];
    }
}
