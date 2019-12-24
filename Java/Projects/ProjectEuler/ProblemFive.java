/*2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.

What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20? */
//232792560
public class ProblemFive {
  public static void main(String[] args) {
    for (int x = 0; x <= 1000000000; x++) {
      if (x%11==0 && x%12==0 && x%13==0 && x%14==0 && x%15==0 && x%16==0 && x%17==0 && x%18==0 && x%19==0 && x%20==0 && x!=0)
      System.out.println(x);
    }
  }
}
