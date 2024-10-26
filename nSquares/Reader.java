import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {
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
}
