import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner s= new Scanner(System.in);
        String cases = s.next();
        Reader r= new Reader();
        System.out.println(r.readTestCases(cases));



    }
}