import java.io.FileInputStream;
import java.io.IOException;
import java.security.Key;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main {


    public static void main(String[] args) {
//         filePath = "C:\\Users\\matrix\\Desktop\\ennnnv.txt";  // Replace with your file path
//        nBytes = 2;
        HuffmanCode h =new HuffmanCode();
        h.readNbyNBytes("C:/Users/matrix/Downloads/Assignment 2.pdf",10);
        h.printState();
       System.out.println(h.getMinKey());
    }
}
