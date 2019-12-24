//Danilo Montes

import java.util.Scanner;
import java.lang.Math;

public class Triangles {
  public static void main (String [] args) {
    Scanner keyboard = new Scanner(System.in);
    
     System.out.println("Enter A value");
     double a = keyboard.nextDouble();
     System.out.println("Enter B value");
     double b = keyboard.nextDouble();
     System.out.println("Enter C value");
     double c = keyboard.nextDouble();
     
     
     String sides = " ";
     String angles = " ";
     
     if (a + b > c && a + c > b && b + c > a) {
       if (a*a + b*b == c*c) {
         angles = "Right";
         System.out.println("Pythagorean Triple");
       } else if (a*a + b*b > c*c) {
         angles = "Acute";
       } else if (a*a + b*b < c*c) {
         angles = "Obtuse";
       }
       
       if (a == b && b == c) {
         sides = "Equilateral";
       } else if (a == b || b == c || c == a) {
         sides = "Isosceles";
       } else if (a != b && b != c) {
         sides = "Scalene";
       }
       
       System.out.println(angles+", "+sides+" Triangle");
     } else {
       System.out.println("Lengths can not form a triangle");
     }
  }
}
