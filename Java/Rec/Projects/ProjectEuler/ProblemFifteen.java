/* Starting in the top left corner of a 2×2 grid,
and only being able to move to the right and down, there are exactly 6 routes to the bottom right corner.


How many such routes are there through a 20×20 grid? */
//137846528820
import java.math.BigInteger;
public class ProblemFifteen {
  public static void main(String[] args) {
    BigInteger squareSize = new BigInteger("20");
    BigInteger squareSize2 = new BigInteger("40");
    System.out.println(getFact(squareSize2).divide(getFact(squareSize)).divide(getFact(squareSize)));
  }
  public static BigInteger getFact(BigInteger x) {
    BigInteger fact = new BigInteger("1");

    for (BigInteger y = x; y.compareTo(BigInteger.ONE) == 1; y = y.subtract(BigInteger.ONE)) {
      fact = fact.multiply(y);
    }
    return fact;
  }
}
