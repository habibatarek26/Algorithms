import java.util.Arrays;

public class Main {
    public static void main(String[] args)
    {
       int size = 15;
        int[]  randomArray={1,400,6,99,3,2,102,22,141,102,87,0,-1030,11,14};
        // Random random = new Random();

        // for (int i = 0; i < size; i++) {
        //     randomArray[i] = random.nextInt(0,100);
        // }
        Selection s =new Selection();
        System.out.println("random array is "+Arrays.toString(randomArray));

        s.get_median(randomArray);
        Arrays.sort(randomArray);
        System.out.print("median by sorting = ");
         System.out.println(randomArray[randomArray.length/2]);
    }
}