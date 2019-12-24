import java.util.ArrayList;
public class TestThing {
  public static void main(String[] args) {
    ArrayList<Integer> test = new ArrayList<>();
    test.add(test.size(),2);
    System.out.println(test.size());
  }
}

class Meme implements Comparable<Meme>{
  Integer y = new Integer(0);

  Meme (Integer x) {
    y = x;
  }
  @Override
  public int compareTo(Meme other){
    // compareTo should return < 0 if this is supposed to be
    // less than other, > 0 if this is supposed to be greater than
    // other and 0 if they are supposed to be equal
    int last = y.compareTo(other.y);
    return last == 0 ? y.compareTo(other.y) : last;
  }
  public boolean equals(Meme a) {
    return compareTo(a) == 0;
  }
}
