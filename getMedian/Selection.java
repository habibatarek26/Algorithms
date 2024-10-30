import java.lang.reflect.Array;
import java.util.*;

public class Selection {
    public long[][] get_median(int[] A) {
        int median=0;
        int median_rand=0;
        long avg =0;
        long start_time;
        long end_time;
        long [][] timeAndMed = new long[3][2];

         start_time = System.nanoTime();
         median_rand = randomized_select(A.clone(), 0, A.length - 1, A.length / 2);
         end_time = System.nanoTime();
         timeAndMed[0][0]=end_time-start_time;
         timeAndMed[0][1]=median_rand;

       start_time = System.nanoTime();
       median = select(A.clone(),0,A.length-1,A.length/2);
       end_time = System.nanoTime();
        timeAndMed[1][0]=end_time-start_time;
        timeAndMed[1][1]=median;


        start_time = System.nanoTime();
        Arrays.sort(A);
        end_time = System.nanoTime();
        timeAndMed[2][0]=end_time-start_time;
        timeAndMed[2][1]=A[A.length/2];

        return timeAndMed;
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
        return partition(arr,low,high,arr[pivotIndex]);
    }
    private int partition(int[]arr,int low,int high,int pivot)
    {
        int pivotIndex=0;
        for (int i = low; i <= high; i++) {
            if (arr[i] == pivot) {
                pivotIndex = i;
                break;
            }
        }
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
    private int select(int[] A, int p, int r, int i) {
        if (r - p < 5) {
            Arrays.sort(A, p, r + 1);
            return A[p + i];
        }

        int g = (int) Math.ceil((double) (r - p + 1) / 5);
        int[] medians = new int[g];

        for (int j = 0; j < g; j++) {
            int start = p + j * 5;
            int end = Math.min(start + 4, r);
            Arrays.sort(A, start, end + 1);
            medians[j] = A[start + (end - start) / 2];
        }

        int x = select(medians, 0, medians.length - 1, medians.length / 2);
        int q = partition(A, p, r, x);
        int k = q - p;

        if (i == k) return A[q];
        else if (i < k) return select(A, p, q - 1, i);
        else return select(A, q + 1, r, i - k - 1);
    }

}
