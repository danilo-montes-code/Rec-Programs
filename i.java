//Danilo Montes

import java.util.Scanner;
import java.lang.Math;

public class i {
  public static void main (String [] args) {
    Scanner keyboard = new Scanner(System.in);
    
     System.out.println("Enter exponenet of i");
     double expo = keyboard.nextDouble();
     double expon = expo%4;
       
     if (expon==0.0) {
       System.out.println("i = i");
     } else if (expon==.25) {
       System.out.println("i = -1");
     } else if (expon==.5) {
       System.out.println("i = -i");
     } else if (expon==.75) {
       System.out.println("i = 1");
     }
  }
}