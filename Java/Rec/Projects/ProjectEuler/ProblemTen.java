/*The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.

Find the sum of all the primes below two million. */
//142913828922
public class ProblemTen {
  public static void main(String[] args) {
    long sum = 2L;
    for (long x = 3L; x < 2000000L; x++) {
      if (isPrime(x))
      sum += x;
    } //End of x loop
    System.out.format("%d%n", sum);
  }

  public static boolean isPrime(long number) {
    boolean prime = true;
    for(long y = 2L; y < (int)Math.sqrt(number)+1; y++) {
      if (prime) {
        if (number%y!=0 || number==3)
        prime = true;
        else
        prime = false;
      }
    } //End of y loop
    return prime;
  }
}
