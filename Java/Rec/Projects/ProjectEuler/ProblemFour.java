/* A palindromic number reads the same both ways.
The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.

Find the largest palindrome made from the product of two 3-digit numbers.*/
//906609
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.Collections;
public class ProblemFour {
  public static void main (String[] args) {
    ArrayList<Integer> pal = new ArrayList<>();
    for (int x = 0;x<=999;x++) {
      for (int y = 0;y<=999;y++) {
        String rev = Integer.toString(x*y);
        if (rev.equals(new StringBuilder(rev).reverse().toString()) && x*y != 0) {
          pal.add(x*y);
        }
      }
    }
    Collections.sort(pal, Collections.reverseOrder());
    System.out.println(pal);
  }
}
