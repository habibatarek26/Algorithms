import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class Selection {
    public void get_median(int[] A) {
        int median=0;
        int median_rand=0;
        long avg =0;
        long start_time;
        long end_time;
        for(int i=0; i<10; i++) {
             start_time = System.currentTimeMillis();
            median_rand = randomized_select(A, 0, A.length - 1, A.length / 2);
             end_time = System.currentTimeMillis();
             avg+=(end_time-start_time);
        }
        avg/=10;
        //print median of the array
        System.out.print("Median = ");
        System.out.println(median_rand);

        //time elapsed by the randomized algorithm
        System.out.print("time elapsed by randomized method in ms = ");
        System.out.println(avg);


       avg=0;
       for(int i=0; i<10; i++) {
           start_time = System.currentTimeMillis();
           median = select(A, 0, A.length - 1, A.length / 2);
           end_time = System.currentTimeMillis();
           avg+=(end_time-start_time);
       }
       avg/=10;
       //print median of the array
       System.out.print("Median = ");
       System.out.println(median);


       System.out.print("time elapsed by linear method in ms = ");
       System.out.println(avg);
    }

    private int randomized_select(int[] A, int p, int r, int i) {
        if (p == r) {
            return A[p];
        }

        int q = randomized_partition(A, p, r);
        int k = q - p;

        if (i == k) {
            return A[q];
        } else if (i < k) {
            return randomized_select(A, p, q - 1, i);
        } else {
            return randomized_select(A, q + 1, r, i - k - 1);
        }
    }

    private int randomized_partition(int[] arr, int low, int high) {
        int pivotIndex = new Random().nextInt(high - low + 1) + low;
        return partition(arr,low,high,pivotIndex);
    }
    private int partition(int[]arr,int low,int high,int pivotIndex)
    {
        int pivot = arr[pivotIndex];
        swap(arr, pivotIndex, high);

        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    private void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
    private  int select(int[] A, int p, int r, int i) {
        if (r == p) {
            return A[p];
        }

        if (r - p + 1 <= 5) {
            Arrays.sort(A, p, r + 1);
            return A[(r - p + 1) / 2];
        }

        int g = (int) Math.ceil((double) (r - p + 1) / 5);
        for(int j=0; j<g; j++)
        {
            Arrays.sort(A,p+g*j,Math.min(p+(j+1)*g,r));
        }

        int x = select(A, p+2*g, p+3*g-1,(int) Math.ceil(g/2));
        int q = partition(A, p, r, x);

        int k = q - p +1;
        if (i == k) {
            return A[q];
        } else if (i < k) {
            return select(A, p, q - 1, i);
        } else {
            return select(A, q + 1, r, i - k);
        }
    }


}
