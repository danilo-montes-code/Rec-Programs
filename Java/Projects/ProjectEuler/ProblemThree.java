/*The prime factors of 13195 are 5, 7, 13 and 29.

What is the largest prime factor of the number 600851475143 ? */
//6857
public class ProblemThree {
  public static void main (String[] args) {
    long num = 600851475143L;
    for (long x = 2; x < num; x++) {
      while (num%x==0) {
        System.out.println(x+"");
        num = num/x;
      }
    }
    if (num > 2) {
      System.out.println(num);
    }
  }
}
