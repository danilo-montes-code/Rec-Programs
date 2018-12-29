import java.util.Scanner;

public class Bases {
    public static void main (String [] args) {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Enter a non zero postitive integer.");
        int num = keyboard.nextInt();

        System.out.println("Enter a base that the number will be converted into.");
        int base = keyboard.nextInt();

        String bin = " ";
        String let = " ";
        int number = num;


        if (base<10) {
            while (num != 0) {
                bin = (String.valueOf(num%base) + bin );
                num = num/base;
            }

            System.out.println("The number "+ number +" in base "+base+" is");
            System.out.println(bin);

        } else if (base>10) {
            while (num != 0) {
                if (num%base>=10) {
                    if (num%base==10)
                        let = "A";
                    else if (num%base==11)
                        let = "B";
                    else if (num%base==12)
                        let = "C";
                    else if (num%base==13)
                        let = "D";
                    else if (num%base==14)
                        let = "E";
                    else if (num%base==15)
                        let = "F";
                    else if (num%base==16)
                        let = "G";
                    bin = let + bin;
                    num = num/base;
                } else if (num%base<=10){
                    bin = (String.valueOf(num%base) + bin);
                    num = num/base;
                }
            }

            System.out.println("The number "+ number +" in base "+base+" is");
            System.out.println(bin);

        } else if (base == 10) {
            System.out.println("The number "+ number +" in base "+base+" is");
            System.out.println(number);
        }
    }
}