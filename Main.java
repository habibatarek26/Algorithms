import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args)
    {
       int size = 10;
        int[]  randomArray=new int[size];
         Random random = new Random();

         for (int i = 0; i < size; i++) {
             randomArray[i] = random.nextInt();
         }
        Selection s =new Selection();
        System.out.println("random array is "+Arrays.toString(randomArray));

        s.get_median(randomArray);
        Arrays.sort(randomArray);
        System.out.print("median by sorting = ");
         System.out.println(randomArray[randomArray.length/2]);
        System.out.println("random array is "+Arrays.toString(randomArray));

    }
}