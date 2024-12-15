import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Key;
import java.time.Duration;
import java.time.LocalTime;
import java.time.Period;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
public class Main {

    private static String getOutputFileName(String inputFilePath, String op) throws IOException {
        File inputFile = new File(inputFilePath);
        File parentDirectory = inputFile.getParentFile();
        String inputFileName = inputFile.getName();

        String outputFileName;
        if (op.equals("d")) {
            if (inputFileName.endsWith(".hc")) {
                inputFileName = inputFileName.substring(0, inputFileName.lastIndexOf(".hc")); // Remove ".hc" extension
            }
            outputFileName = "extracted." + inputFileName;
        } else {
            outputFileName = inputFileName + ".hc";
        }

        // If no parent directory, use current directory
        String parentPath = (parentDirectory != null) ? parentDirectory.getAbsolutePath() : ".";
        return new File(parentPath, outputFileName).getAbsolutePath();
    }


    public static void main(String[] args) throws IOException {
//
//        HuffmanCode huffman = new HuffmanCode();
//        huffman.readNbyNBytes("C:/Users/matrix/Desktop/Assign1Net/progassign.pdf", 3);  // Example of reading one byte at a time
//        System.out.println("finish reading");
//        huffman.buildTree();
//        System.out.println("tree has been built");
//        FileCompressor fileCompressor =new FileCompressor(huffman.code ,huffman.dCode);
//        fileCompressor.compressFile("C:/Users/matrix/Desktop/Assign1Net/progassign.pdf", getOutputFileName("C:/Users/matrix/Desktop/Assign1Net/progassign.pdf",""),3);
//        System.out.println("finish compressing");
//        FileDecompressor fileDecompressor=new FileDecompressor();
//        fileDecompressor.decompressFile(getOutputFileName("C:/Users/matrix/Desktop/Assign1Net/progassign.pdf",""),getOutputFileName("C:/Users/matrix/Desktop/Assign1Net/progassign.pdf","d") ,3);

//        HuffmanCode huffman = new HuffmanCode();
//        huffman.readNbyNBytes("C:/Users/matrix/Desktop/ennnnv.txt", 2);  // Example of reading one byte at a time
//        huffman.buildTree();
//        huffman.compressFile("C:/Users/matrix/Desktop/ennnnv.txt", "C:/Users/matrix/Desktop/outy.txt",2);
//        System.out.println("dcodr"+huffman.dCode);

      //  huffman.decompressFile("C:/Users/matrix/Desktop/outy.txt","C:/Users/matrix/Desktop/outyd.txt",2 );
        HuffmanCode huffman = new HuffmanCode();
        LocalTime start = LocalTime.now();
        huffman.readNbyNBytes("C:\\Users\\matrix\\Downloads\\gbbct10.seq", 1);  // Example of reading one byte at a time
        huffman.buildTree();
        LocalTime end1 = LocalTime.now();
        System.out.println("tree wn 2raya = "+(Duration.between(start,end1)));
        FileCompressor fileCompressor =new FileCompressor(huffman.code ,huffman.dCode);
        FileDecompressor fileDecompressor = new FileDecompressor();
        fileCompressor.compressFile("C:\\Users\\matrix\\Downloads\\gbbct10.seq", "C:\\Users\\matrix\\Downloads\\gbbct10.seq.hc",1);
        LocalTime end = LocalTime.now();
        System.out.println("compress "+(Duration.between(start,end)));
         start = LocalTime.now();
        fileDecompressor.decompressFile("C:\\Users\\matrix\\Downloads\\gbbct10.seq.hc","C:\\Users\\matrix\\Downloads\\extracted.gbbct10.seq" ,1);
         end = LocalTime.now();
        System.out.println("decompress "+(Duration.between(start,end)));




//        if(args[2].equals("d"))
//        {
//            FileDecompressor fileDecompressor = new FileDecompressor();
//            fileDecompressor.decompressFile(args[0],getOutputFileName(args[0],"d") ,Integer.parseInt(args[1]));
//
//        }
//        else {
//            HuffmanCode huffmanCode =new HuffmanCode();
//            huffmanCode.readNbyNBytes(args[0],Integer.parseInt(args[1]));
//            huffmanCode.buildTree();
//            FileCompressor fileCompressor =new FileCompressor(huffman.code ,huffman.dCode);
//            fileCompressor.compressFile(args[0], getOutputFileName(args[0],""),Integer.parseInt(args[1]));
//        }
//    }
}}
//certutil -hashfile "C:\Users\matrix\Downloads\gbbct10.seq" SHA256
//certutil -hashfile "C:\Users\matrix\Downloads\extracted.gbbct10.seq" SHA256
//certutil -hashfile "C:/Users/matrix/Desktop/Assign1Net/progassign.pdf" SHA256
//certutil -hashfile "C:/Users/matrix/Desktop/mineDD.pdf" SHA256
