import java.util.Scanner;
  
public class SoN {
  public static void main (String [] args) {
    Scanner keyboard = new Scanner(System.in);
    
    System.out.println("Enter a non zero positive integer");
    int num = keyboard.nextInt();
    int sum;
    
    for(sum = 0; sum <= num; sum++) {
      sum=sum+1;
      System.out.println(sum);
    }
    System.out.println(sum);
  }
}
      
    
