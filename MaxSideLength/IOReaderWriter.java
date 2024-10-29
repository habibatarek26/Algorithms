import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOReaderWriter {
    public static List<List<long[]>> readTestCases(String fileName) throws IOException {
        List<List<long[]>> testCases = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                long numberOfPoints = Long.parseLong(line.trim());
                List<long[]> points = new ArrayList<>();

                for (int i = 0; i < numberOfPoints; i++) {
                    line = br.readLine();
                    String[] coords = line.trim().split(" ");
                    long x = Long.parseLong(coords[0]);
                    long y = Long.parseLong(coords[1]);
                    points.add(new long[]{x, y});
                }

                testCases.add(points);
            }
        }
        return testCases;
    }
    public static void writeLinesToFile(String fileName, long output) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) { // 'true' enables append mode
            writer.write(String.format("%d", output));
            if(output==Long.MAX_VALUE) writer.write("  INFINITY");
            writer.newLine();
            System.out.println("File written successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }


}
