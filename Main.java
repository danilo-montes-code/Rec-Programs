import java.util.Random;
import java.util.Scanner;

public class Main {

    static String[] myOptions = {
        "Combine two strings",
        "Checks if a given number is within 10 of 100",
        "Returns the sum of two numbers",
        "Calculates the sum of the numbers from 1 to the given number",
        "Calculates a person\'s BMI given their height and weight.",
        "Converts Celsius to Fahrenheit",
        "Flips a coin",
        "Returns the sum of the digits in the given number",
        "Exits the program"
    };

    static Scanner input = new Scanner(System.in);

    public static void Menu(){
        System.out.println("Please select from the following:");
        for (int option=0;option<9;option+=1){
            System.out.println(option+1 + " - " + myOptions[option]);
        }
        System.out.println("Input a number from 1-9!");
    };

    public static void main(String[] args) {
        int choice = 0;
        while (choice != 9){
            Menu();
            choice = input.nextInt();
            if (choice == 1){
                System.out.println("Input a string.");
                String word1 = input.next();
                System.out.println("Input another string.");
                String word2 = input.next();
                comboString(word1, word2);
            } else if (choice == 2){
                System.out.println("Input a number.");
                int number = input.nextInt();
                nearHundred(number);
            } else if (choice == 3){
                System.out.println("Input a number.");
                int number1 = input.nextInt();
                System.out.println("Input another number.");
                int number2 = input.nextInt();
                sumDouble(number1, number2);
            } else if (choice == 4){
                System.out.println("Input a number.");
                int number = input.nextInt();
                sumNumbers(number);
            } else if (choice == 5){
                System.out.println("Input the height.");
                double height = input.nextDouble();
                System.out.println("Input the weight.");
                double weight = input.nextDouble();
                getBMI(height, weight);
            } else if (choice == 6){
                System.out.println("Input the Celsius temperature .");
                double Celsius = input.nextDouble();
                Fahrenheit(Celsius);
            } else if (choice == 7){
                for (int x = 0; x < 20; x++){
                    flipCoin();
                    try{Thread.sleep(200);}catch(InterruptedException e){};
                }
            } else if (choice == 8){
                System.out.println("Input a number.");
                int number = input.nextInt();
                sumDigits(number);
            } else if (choice > 9 || choice < 1){
                System.out.println("Invalid choice... please enter an integer from 1 to 9");
            }
            try{Thread.sleep(1000);}catch(InterruptedException e){};
        }
        System.out.println("Thank you for playing!");
    }

    public static String comboString(String a, String b){
        String combined;
        if (a.length() > b.length()){
            String temp = b;
            b = a;
            a = temp;
        };
        combined = a+b+a;
        System.out.println("Your combined string is "+combined);
        return combined;
    };

    public static boolean nearHundred(int n){
        boolean isNear = (Math.abs(100 - n) < 10);
        String word;
        if (isNear){
            word = "near";
        } else {
            word = "not near";
        }
        System.out.println("The given number is "+word+" 100");
        return isNear;
    };

    public static int sumDouble(int a, int b){
        int sum;
        sum = a+b;
        if (a == b)
            sum *= 2;
        System.out.println("The sum is "+sum);
        return sum;
    };

    public static int sumNumbers(int a){
        int sum = 0;
        String ints = "[";
        for (int x = 1; x<=a; x+=1){
            sum = sum + x;
            ints = ints + x + "+";
        }
        System.out.println(sum + " " + ints.substring(0,ints.length()-1) + "]");
        return sum;
    };

    public static double getBMI (double height, double weight){
        double BMI = weight * 703.0 / (height * height);
        System.out.println("The BMI calculated from the given two values is "+BMI);
        return BMI;
    };

    public static double Fahrenheit(double a){
        double ftemp = 0;
        ftemp = (1.8 * a) + 32;
        System.out.println(a+"°C is "+ftemp+"°F.");
        return ftemp;
    }

    public static void flipCoin(){
        Random gen = new Random();
        boolean heads = (gen.nextBoolean());
        if (heads)
            System.out.println("Heads");
        else
            System.out.println("Tails");

    }

    public static int sumDigits(int a){
        int sum = 0;
        String numString = Integer.toString(a);
        for (int x = 0; x < numString.length(); x ++){
            int digit = numString.charAt(x) - '0';
            sum = sum +digit;
        }
        System.out.println("The sum of the digits in "+a+" is "+sum);
        return sum;
    };
}
