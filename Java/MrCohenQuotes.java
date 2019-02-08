/**
* @author Students
* @version 30
*/
import java.util.ArrayList;
public class MrCohenQuotes{
  public static void main(String args[]){
    ArrayList <String> Quotes = new ArrayList<String>();

    /*
    Add new quotes underneath using the same way the other ones are using
    */
    Quotes.add("Crippling regulations");
    Quotes.add("I have 3 thermometers, they all say it's too hot");
    Quotes.add("I want a sign on my front law saying there’s a landmine");
    Quotes.add("Don’t yell at me, Nixon said it");
    Quotes.add("If you hear a lie enough, it’s the truth");
    Quotes.add("Someone open up a window");
    Quotes.add("I can’t read, stop trying. One time someone gifted me a barnes and noble gift card");
    Quotes.add("It’s a social construct");
    Quotes.add("Hashtag no body cares");
    Quotes.add("Stealing is bad");
    Quotes.add("That’s a you problem");
    Quotes.add("DID YOU JUST SAY WIFI IS BETTER THAN ETHERNET?!");
    Quotes.add("Hard questions are hard, okay?");

    for(int i = 0; i < Quotes.size(); i++){
      System.out.println(Quotes.get(i));
    }
  }
}
