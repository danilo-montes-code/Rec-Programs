/* The following iterative sequence is defined for the set of positive integers:

n → n/2 (n is even)
n → 3n + 1 (n is odd)

Using the rule above and starting with 13, we generate the following sequence:

13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms.
Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.

Which starting number, under one million, produces the longest chain?

NOTE: Once the chain starts the terms are allowed to go above one million.*/
//837799
import java.util.ArrayList;
public class ProblemFourteen {
  static ArrayList<Long> chain = new ArrayList<>();
  static ArrayList<Long> chainMaybe = new ArrayList<>();
  static Long lastNum = 1L;
  public static void main(String[] args) {
    int highestTestNum = 1000000;
    chain.add(1L);  // Adding the first chain, which is for one
    for (long x = 2L; x < highestTestNum; x++) {
      chainMaybe.add(x);
      lastNum = chainMaybe.get(chainMaybe.size()-1);
      while (hasNext(lastNum)) {
        chainMaybe.add(new Long(nextInChain(lastNum)));  // Puts in the next num in the sequence
        lastNum = chainMaybe.get(chainMaybe.size()-1);
      }
      compareChains();
    }
    System.out.format("%d%n",chain.get(0));
  }
  public static boolean hasNext(Long x) {
    return x!=1;
  }
  public static void compareChains(){
    if (chainMaybe.size() > chain.size()) {
      chain.clear();
      chain.addAll(chainMaybe);
    }
    chainMaybe.clear();
  }
  public static Long nextInChain(Long x){
    if (x%2==0)
    return x/Long.valueOf(2);
    else
    return Long.valueOf(3)*x+Long.valueOf(1);
  }
}
