import java.io.FileInputStream;
import java.io.IOException;
import java.security.Key;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main {


    public static void main(String[] args) {
        HuffmanCode huffman = new HuffmanCode();
        huffman.readNbyNBytes("C:/Users/matrix/Downloads/Assignment 2.pdf", 100);  // Example of reading one byte at a time
        huffman.buildTree();
    }
}

