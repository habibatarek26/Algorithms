import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args)
    {
       int size = 100_000_000;
        int[]  randomArray=new int[size];
         Random random = new Random();

         for (int i = 0; i < size; i++) {
             randomArray[i] = random.nextInt();
         }
        Selection s =new Selection();

        s.get_median(randomArray);


    }
}