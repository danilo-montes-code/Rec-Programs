/* 2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.

What is the sum of the digits of the number 2^1000? */
//1366
import java.math.BigInteger;
public class ProblemSixteen {
  public static void main(String[] args) {
    BigInteger bruh = new BigInteger("2");
    int power = 1000;
    Long sum = 0L;
    for(int x = 1; x < power; x++) {
      bruh = bruh.multiply(new BigInteger("2"));
    }
    String bigggggie = bruh.toString();
    for (int marker = 0; marker <= bigggggie.length()-1; marker++) {
      sum+=Long.valueOf(bigggggie.substring(marker, marker+1));
    }
    System.out.format("%d%n", sum);
  }
}
