/* The sum of the squares of the first ten natural numbers is,

1^2 + 2^2 + ... + 10^2 = 385
The square of the sum of the first ten natural numbers is,

(1 + 2 + ... + 10)^2 = 55^2 = 3025
Hence the difference between the sum of the squares of the first ten natural numbers and the square of the sum is 3025 − 385 = 2640.

Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum. */
//25164150
public class ProblemSix {
  public static void main(String[] args) {
    int sumsqu = 0;
    int squsum = 0;
    for (int x = 0; x<=100; x++) {
      sumsqu += Math.pow(x,2);
    }
    for (int y = 0; y <=100; y++) {
      squsum += y;
    }
    System.out.println(String.format("%.0f", Math.pow(squsum,2)-sumsqu));
  }
}
