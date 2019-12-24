/*
n! means n × (n − 1) × ... × 3 × 2 × 1

For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800,
and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.

Find the sum of the digits in the number 100!
 */
//
import java.math.BigInteger;
public class ProblemTwenty {
    public static void main(String[] args) {
        long sum = 0L;
        BigInteger fac = new BigInteger("1");
        for (int x = 100; x > 0; x--) {
            fac = fac.multiply(new BigInteger(Integer.toString(x)));
        }
        String num = fac.toString();
        for (int y = 0;  y < num.length(); y++) {
            sum += Integer.parseInt(num.substring(y, y+1));
        }
        System.out.format("%d%n", sum);
    }
}
