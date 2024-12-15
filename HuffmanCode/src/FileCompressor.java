import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileCompressor {
    private static Map<List<Byte>, String> code;
    private static Map<String, List<Byte>> dCode;
    private int padding;

    public FileCompressor( Map<List<Byte>, String> code ,Map<String, List<Byte>> dCode) {
        this.code = code;
        this.dCode = dCode;
    }

    public void compressFile(String inputFilePath, String outputFilePath, int n) throws IOException {
        try (FileInputStream fis = new FileInputStream(inputFilePath);
             FileOutputStream fos = new FileOutputStream(outputFilePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            byte[] buffer = new byte[n * 1024 * 1024*2];
            int bytesRead;
            StringBuilder leftoverBits = new StringBuilder();
            ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();

            while ((bytesRead = fis.read(buffer)) != -1) {
                StringBuilder stringBuilder = new StringBuilder(leftoverBits);
                for (int i = 0; i < bytesRead; i += n) {
                    int chunkSize = Math.min(n, bytesRead - i); // for the last chunk in the file
                    List<Byte> chunk = new ArrayList<>();       // to handle different types of files not only text
                    for (int j = 0; j < chunkSize; j++) {
                        chunk.add(buffer[i + j]);
                    }
                    String val = code.get(chunk);
                    if (val != null) {
                        stringBuilder.append(val);
                    }
                }

                String binaryString = stringBuilder.toString();
                int length = binaryString.length();
                // take every 8 bits in the string to convert them to int and take only first 8*x string the remaining to be considered
                // in the next iteration
                int remainingBitsStart = length - (length % 8);

                for (int i = 0; i < remainingBitsStart; i += 8) {
                    String byteString = binaryString.substring(i, i + 8);
                    int intValue = Integer.parseInt(byteString, 2);

                    outputBuffer.write(intValue);
                }

                leftoverBits.setLength(0);
                leftoverBits.append(binaryString.substring(remainingBitsStart));
            }
            // in the last iteration check for string of length <8 has not been written in the file as padding is required
            if (leftoverBits.length() > 0) {
                while (leftoverBits.length() < 8) {
                    leftoverBits.insert(0, "0");
                    padding++;
                }
                int intValue = Integer.parseInt(leftoverBits.toString(), 2);
                outputBuffer.write(intValue);
            }
            // write the whole file one at a time to reduce the time as much as possible
            oos.writeInt(padding);
            oos.writeObject(dCode);
            fos.write(outputBuffer.toByteArray());
        }
    }
}
