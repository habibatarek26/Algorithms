import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner s= new Scanner(System.in);
        String cases = "C:\\Users\\matrix\\Documents\\GitHub\\Algorithms\\nSquares\\cases.txt";//s.next();
        Reader r= new Reader();
        System.out.println(r.readTestCases(cases));



    }
}