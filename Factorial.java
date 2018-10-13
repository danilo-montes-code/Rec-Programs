import java.util.Scanner;

public class Factorial {
  public static void main (String [] args) {
    Scanner keyboard = new Scanner(System.in);
    
    System.out.println("Enter Number");
    int number = keyboard.nextInt();
    int ognum = number;
    for(int x = number-1; x>=1; x--) {
      number = x*number;
    }
    System.out.println(ognum+"! is "+number);
  }
}
