import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class WeightedActivitySelection {
    public static int[][] readActivitiesFromFile(String filename) throws FileNotFoundException {
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
    public static void writeOutputToFile(String inputFilePath, String output, String id) throws IOException {
        File inputFile = new File(inputFilePath);
        File parentDirectory = inputFile.getParentFile();
        String inputFileName = inputFile.getName();

        String baseName = inputFileName.contains(".")
                ? inputFileName.split("\\.")[0]
                : inputFileName;

        String outputFileName = baseName + "_" + id + ".out"+".txt";
        File outputFile = new File(parentDirectory, outputFileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(output);
        }
    }
    public int  selectActivities(int[][] activities)
    {
        sortActivities(activities);
        int[]dp = new int[activities.length+1];
        for(int i=0; i<activities.length; i++)
        {
            int LatestNonConflictActivity =LatestNonConflictActivity(activities,i);
            dp[i+1] = Math.max(dp[i],dp[LatestNonConflictActivity+1]+activities[i][2]);
        }
        return dp[activities.length];
    }

    private void sortActivities(int[][] activities)
    {
        Arrays.sort(activities, Comparator.comparingInt(activity -> activity[1]));
    }

    private int LatestNonConflictActivity(int[][] activities,int indexOfJob)
    {
        //use binary search to make its complexity O(nlgn) rather than lg(n^2)
        int start =0;
        int end = indexOfJob;
        int mid = 0;
        int startOfJob = activities[indexOfJob][0];
        while(start<=end)
        {
            mid = (start+end)/2;
            if(activities[mid][1]<= startOfJob) {
                if (mid == indexOfJob - 1 || activities[mid + 1][1] > startOfJob)
                    return mid;
                start = mid + 1;
            }
            else if(activities[mid][1]> startOfJob)
                end=mid-1;
        }
        return -1;
    }

}
