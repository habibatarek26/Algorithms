import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOReaderWriter {
    public static List<List<int[]>> readTestCases(String fileName) throws IOException {
        List<List<int[]>> testCases = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                int numberOfPoints = Integer.parseInt(line.trim());
                List<int[]> points = new ArrayList<>();

                for (int i = 0; i < numberOfPoints; i++) {
                    line = br.readLine();
                    String[] coords = line.trim().split(" ");
                    int x = Integer.parseInt(coords[0]);
                    int y = Integer.parseInt(coords[1]);
                    points.add(new int[]{x, y});
                }

                testCases.add(points);
            }
        }
        return testCases;
    }
    public static void writeLinesToFile(String fileName, int output) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                writer.write(output);
                writer.newLine();
            System.out.println("File written successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

}
