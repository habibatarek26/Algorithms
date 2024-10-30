import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args)
    {
        long [][]res = new long[3][2];
        long[] avg = new long[3];
        for (float i=1; i<=7; i+=0.5) {
         //   int avg=0;
            int size =(int) Math.pow(10,i);
            for(int k=0; k<20; k++)
            {
                int[] randomArray = new int[size];
                Random random = new Random();

                for (int j = 0; j < size; j++)
                    randomArray[j] = random.nextInt();
                Selection s = new Selection();
                res = s.get_median(randomArray);
                avg[0]+=res[0][0];
                avg[1]+=res[1][0];
                avg[2]+=res[2][0];
            }
            System.out.println("Array size = "+size);
            System.out.println("Average time in randomized algorithm = "+(avg[0]/20));
            System.out.println("Average time in Median of Medians algorithm = "+(avg[1]/20));
            System.out.println("Average time using naiive sorting = "+(avg[2]/20));
            System.out.println("Median of each algorithm in one case to check the correctness :");
            System.out.println(res[0][1]);
            System.out.println(res[1][1]);
            System.out.println(res[2][1]);
            System.out.println();
        }
    }
}