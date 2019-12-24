/*By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.

What is the 10 001st prime number? */
//104743
import java.util.ArrayList;
public class ProblemSeven {
  public static void main(String[] args) {
    ArrayList<Boolean> counter = new ArrayList<>();
    boolean prime = true;
    for (long x = 2L; x < 20000000000L; x++) {
      for (long y = 2L; y < x; y++) {
        if (prime) {
          if (x%y!=0 || x==3)
          prime = true;
          else
          prime = false;
        }
      } //End of y loop
      if (prime)
      counter.add(true);
      prime = true;
      if (counter.size() == 10001)
      System.out.println(x);
    } //End of x loop
  }
}
