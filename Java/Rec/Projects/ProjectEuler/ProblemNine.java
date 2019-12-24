/* A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,

a^2 + b^2 = c^2
For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.

There exists exactly one Pythagorean triplet for which a + b + c = 1000.
Find the product abc. */
//31875000
import java.util.Scanner;
public class ProblemNine {
  public static void main(String[] args) {
    String tri = "8 15 17";
    Scanner finder = new Scanner(tri);
    int a = finder.nextInt();
    int b = finder.nextInt();
    int c = finder.nextInt();
    for (int x = 0; x<1000000; x++) {
      if (Math.pow(a*x,2) + Math.pow(b*x,2) == Math.pow(c*x,2) && a*x+b*x+c*x == 1000)
      System.out.println(a*x*b*x*c*x);
    }
  }
}
