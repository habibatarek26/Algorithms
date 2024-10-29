import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String []arg) throws IOException {
        Scanner s = new Scanner(System.in);
        String file = "test.txt";
        IOReaderWriter rw = new IOReaderWriter();
        List<List<long[]>> cases = rw.readTestCases(file);
        for (int i = 0; i < cases.size(); i++) {
            List<long[]>x=deepCopy(cases.get(i));
            Collections.sort(x, Comparator.comparing(point -> point[0]));
            long MaxSquareSide =(long) new MaxSideLength().get_Square_Side(x);
            rw.writeLinesToFile(file.replace("test","output"),MaxSquareSide);
            System.out.println((long)MaxSquareSide);
        }
    }
    public static List<long[]> deepCopy(List<long[]> original) {
        List<long[]> copiedList = null;
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(original);
            out.flush();

            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteIn);
            copiedList = (List<long[]>) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return copiedList;
    }
}