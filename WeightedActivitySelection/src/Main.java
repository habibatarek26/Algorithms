import java.io.*;
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
    private static void writeOutputToFile(String inputFilePath, String output, String id) throws IOException {
        File inputFile = new File(inputFilePath);
        File parentDirectory = inputFile.getParentFile();
        String inputFileName = inputFile.getName();

        String baseName = inputFileName.contains("_")
                ? inputFileName.substring(0, inputFileName.lastIndexOf('_'))
                : inputFileName;

        String outputFileName = baseName + "_" + id + "_out"+".txt";
        File outputFile = new File(parentDirectory, outputFileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(output);
        }
    }
    public static void main(String[] args) throws IOException {
        int [][]activities =readActivitiesFromFile(args[0]);
        WeightedActivitySelection a = new WeightedActivitySelection();
        writeOutputToFile(args[0], Integer.toString(a.selectActivities(activities)), "21010445");
    }
}