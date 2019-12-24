import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class ProblemTwentyTwo {
    public static void main(String[] args) throws IndexOutOfBoundsException{
        ArrayList<String> names = new ArrayList<>();
        Scanner sc = new Scanner("names.txt");
        while (sc.hasNext()) {
            String temp = sc.next();
            names.add(temp);
            System.out.println(temp);
        }
        sc.close();

        System.out.println("Added names");
        System.out.println(names.size());
    }
}
