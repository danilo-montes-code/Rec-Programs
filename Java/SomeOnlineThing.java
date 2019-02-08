public class SomeOnlineThing {
  public static void main(String[] args) {
    String s1 = "boaty";
    String s2 = "otybo";
    String finalString = "";
    for (int x = 0; x < s1.length(); x++) {
      finalString = longestSub(s1, s2, finalString, x);
    }
    System.out.println(finalString);
    // oty
    finalString = "";
    String s3 = "ABBA";
    String s4 = "ABA";
    for (int y = 0; y < s3.length(); y++) {
      finalString = longestSub(s3,s4,finalString,y);
    }
    System.out.println(finalString);
    // ABA
    finalString = "";
    String s5 = "";
    String s6 = "";
    for (int z = 0; z < s5.length(); z++) {
      finalString = longestSub(s5,s6,finalString,z);
    }
    System.out.println(finalString);
    // [Empty String]
  }
  public static String longestSub (String s1, String s2, String biggie, int s1num) {
    String longest = "";
    int marker = -1;
    for (int x = s1num; x < s1.length(); x++) {
      for (int y = marker+1; y < s2.length(); y++) {
        if (s1.substring(x,x+1).equals(s2.substring(y,y+1))) {
          marker = y;
          longest += s1.substring(x,x+1);
          if (x!=s1.length()-1)
          x++;
        }
      }
    }
    if (biggie.length() < longest.length())
    return longest;
    else
    return biggie;
  }
}
