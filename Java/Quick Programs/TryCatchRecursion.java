public class TryCatchRecursion {
  public static void main(String[] args) {
    int input = 5; // Input in the program
    int total = rec(input);
    System.out.println(total);
  }
  static int rec(int x) {
    int total = 0;
	System.out.println(x);
    while (true) {
      try {
        Math.sqrt(x);
      } catch (Exception e) {
        break;
      }
      total *= x;
      x--;
    }
    return total;
  }
}
