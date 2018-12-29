import java.util.Scanner;

public class Bases2 {
  public static void main (String [] args) {
    Scanner keyboard = new Scanner(System.in);

    System.out.println("Currently supports inputs and outputs from Base 2 to Base 62.");

    System.out.println("Enter a base followed by a number in said base.");
    String start = keyboard.nextLine();

    System.out.println("Enter a base that the number will be converted into.");
    int convBase = keyboard.nextInt();

    String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    String newBaseNum = "";
    //Sets apart the base from the number
    int space = start.indexOf(" ");
    int baseOld = Integer.parseInt(start.substring(0, space));//Old Base
    String numOld = start.substring(space+1,start.length());//Old Base Number
    int num = baseOldToDec(baseOld, numOld, alpha);

    if (convBase < 10) {
      while (num != 0) {
        newBaseNum = (String.valueOf(num%convBase) + newBaseNum);
        num = num/convBase;
      }
      System.out.println("The number "+ numOld +" in base "+baseOld+" is");
      System.out.println("the number "+ newBaseNum + " in base "+convBase);
    } else if (convBase >= 10) {
      while(num !=0) {
        if (num%convBase>=10) {
          newBaseNum = newBaseNum + alpha.substring(num%convBase-10, num%convBase-9);
        } else if (num%convBase<=10){
          newBaseNum = (String.valueOf(num%convBase) + newBaseNum);
        }
        num = num/convBase;
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
    int power = b.length()-1;
    String chara = b.substring(z,z+1);

    if (a >= 10) {
      for (int x = power;x>=0;x--) {
        if(isNumeric(chara)) {
          dec = (int)Math.pow(a, power)*Integer.parseInt(chara) + dec;
        } else if (!isNumeric(chara)) {
          cas = c.indexOf(chara)+10;
          dec = (int)Math.pow(a, power)*cas + dec;
        }
        power--;
        z++;
        if (power == -1) {
          chara = chara;
        } else {
          chara = b.substring(z,z+1);
        }
      }
    } else if (a<10){
      while (power != -1) {
        dec = (int)Math.pow(a, power)*Integer.parseInt(chara) + dec;
        power--;
        z++;
        if (power == -1) {
          chara = chara;
        } else {
          chara = b.substring(z,z+1);
        }
      }
    }
    return dec;
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
