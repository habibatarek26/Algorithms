import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static int[][] readActivitiesFromFile(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        int n = scanner.nextInt(); // Number of activities
        int[][] activities = new int[n][3];

        for (int i = 0; i < n; i++) {
            activities[i][0] = scanner.nextInt(); // Start time
            activities[i][1] = scanner.nextInt(); // End time
            activities[i][2] = scanner.nextInt(); // Weight
        }
        scanner.close();
        return activities;
    }
    public static void main(String[] args) throws FileNotFoundException {
        int [][]arr =readActivitiesFromFile("C:\\Users\\matrix\\Desktop\\test.txt");
        WeightedActivitySelection a = new WeightedActivitySelection();
        System.out.println(a.selectActivities(arr));
    }
}