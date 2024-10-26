import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        String file = "C:\\Users\\matrix\\Documents\\GitHub\\Algorithms\\nSquares\\cases.txt";
        IOReaderWriter rw = new IOReaderWriter();
        List<List<int[]>> cases = rw.readTestCases(file);
        for (int i = 0; i < cases.size(); i++) {
            List<int[]>x=deepCopy(cases.get(i));
            Collections.sort(x, Comparator.comparingInt(point -> point[0]));
            float MaxSquareSide = new getSquareSide().get_Square_Side(x);
           // rw.writeLinesToFile(file,MaxSquareSide);
            System.out.println(MaxSquareSide);
        }
    }
    public static List<int[]> deepCopy(List<int[]> original) {
        List<int[]> copiedList = null;
        try {
            // Serialize the original list
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(original);
            out.flush();

            // Deserialize to create a deep copy
            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteIn);
            copiedList = (List<int[]>) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return copiedList;
    }
}