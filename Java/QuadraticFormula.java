//Danilo Montes (is bored and has nothing to do)

import java.util.Scanner;
import java.lang.Math;

public class QuadraticFormula {
    public static void main (String [] args) {
        Scanner keyboard = new Scanner (System.in);

        System.out.println("Enter A");
        double a = keyboard.nextDouble();

        System.out.println("Enter B");
        double b = keyboard.nextDouble();

        System.out.println("Enter C");
        double c = keyboard.nextDouble();

        double root1 = (-b + (Math.sqrt(b*b - 4*a*c)))/(2*a);
        double root2 = (-b - (Math.sqrt(b*b - 4*a*c)))/(2*a);

        double discriminant = (b*b) - 4*a*c;
        System.out.println("Discriminant = "+discriminant);

        double rational = discriminant % Math.sqrt(discriminant);

        if (discriminant < 0) {
            System.out.println("2 Complex Roots");
        } else if (discriminant == 0) {
            System.out.println("1 Real Rational Root");
            System.out.println("x="+root1);
        } else if (rational == 0.0) {
            System.out.println("2 Real Rational Roots");
            System.out.println("x="+root1);
            System.out.println("x="+root2);
        } else if (rational != 0.0) {
            System.out.println("2 Real Irrational Roots");
            System.out.println("x="+root1);
            System.out.println("x="+root2);
        }
    }
}