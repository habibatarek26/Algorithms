import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args)
    {
        for (int i=1; i<=8; i++) {
            int size =(int) Math.pow(10,i);
            System.out.println("Array size = "+size);
            int[] randomArray = new int[size];
            Random random = new Random();

            for (int j = 0; j < size; j++)
                randomArray[j] = random.nextInt();

            Selection s = new Selection();

            s.get_median(randomArray);
            System.out.println();
        }
    }
}