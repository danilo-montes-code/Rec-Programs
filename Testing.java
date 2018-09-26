import java.util.Scanner;

public class Testing {
  public static void main (String [] args) {
    Scanner keyboard = new Scanner(System.in);

    System.out.println("Currently supports inputs and outputs from Base 2 to Hexadecimal.");

    System.out.println("Enter a base followed by a number in said base.");
    String start = keyboard.nextLine();

    System.out.println("Enter a base that the number will be converted into.");
    int convBase = keyboard.nextInt();

    String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    String newBaseNum = " ";
    String letter = " "; //If letters need to be used
    //Sets apart the base from the number
    int space = start.indexOf(" ");
    int baseOld = Integer.parseInt(start.substring(0, space));//Old Base
    String numOld = start.substring(space+1,start.length());//Old Base Number
    int num = baseOldToDec(baseOld, numOld, alpha);

    if (convBase<10) { // Displays binary if the base is less than 10
      while (num != 0) {
        newBaseNum = (String.valueOf(num%convBase) + newBaseNum);
        num = num/convBase;
      }
      System.out.println("The number "+ numOld +" in base "+baseOld+" is");
      System.out.println("the number "+ newBaseNum + " in base "+convBase);
    } else if (convBase>10) { // Displays binary if the base is greater than 10, includes letters
      while (num != 0) {
        if (num%convBase>=10) {
          if (num%convBase==10)
            letter = "A";
          else if (num%convBase==11)
            letter = "B";
          else if (num%convBase==12)
            letter = "C";
          else if (num%convBase==13)
            letter = "D";
          else if (num%convBase==14)
            letter = "E";
          else if (num%convBase==15)
            letter = "F";
          newBaseNum = letter + newBaseNum;
          num = num/convBase;
        } else if (num%convBase<=10){
          newBaseNum = (String.valueOf(num%convBase) + newBaseNum);
          num = num/convBase;
        }
      }
      System.out.println("The number "+ numOld +" in base "+baseOld+" is");
      System.out.println("the number "+ newBaseNum + " in base "+convBase);
    } else if (convBase == 10) {
      System.out.println("The number "+ numOld +" in base "+baseOld+" is");
      System.out.println("the number "+ num + " in base "+convBase);
    }
  }
  public static int baseOldToDec (int a, String b, String c) {
    int dec = 0;
    int cas;
    int z = 0;
    int length = b.length()-1;
    String chara = b.substring(z,z+1);

    for (int x = length;x>=0; x--) {
      if (length<0) {
      } else {
        if (isNumeric(chara)) {
          cas = c.indexOf(chara);
          dec = ((int)Math.pow(a,length)*(10+cas)) + dec;
          z++;
          length--;
        } else {
          System.out.println(chara);
          cas = Integer.parseInt(b.substring(z,z+1));
          if (cas!=0) {
            dec = ((int) Math.pow(a,length)*cas) + dec;
            z++;
            length--;
          } else if (cas==0) {
            z++;
            length--;
          }
        }
      }
    }return dec;
  }
  public static boolean isNumeric(String strNum) {
    try {
      int d = Integer.parseInt(strNum);
    } catch (NumberFormatException | NullPointerException nfe) {
      return false;
    }
    return true;
  }
}
