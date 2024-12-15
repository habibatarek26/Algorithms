import java.io.*;
import java.util.List;
import java.util.Map;

public class FileDecompressor {

    public void decompressFile(String inputFilePath, String outputFilePath, int n) throws IOException {

        try (FileInputStream fis = new FileInputStream(inputFilePath);
             ObjectInputStream ois = new ObjectInputStream(fis);
             FileOutputStream fos = new FileOutputStream(outputFilePath)) {

            int padding = ois.readInt();

            Map<String, List<Byte>> dCode = (Map<String, List<Byte>>) ois.readObject();

            byte[] buffer = new byte[n * 1024 * 1024];
            int bytesRead;
            StringBuilder s = new StringBuilder();
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            int turn = 0;

            while ((bytesRead = fis.read(buffer)) != -1) {
                turn++;
                boolean isLastChunk = fis.available() <= 0;
                for (int i = 0; i < bytesRead - 1; i++) {
                    s.append(String.format("%8s", Integer.toBinaryString(buffer[i] & 0xFF)).replace(' ', '0'));
                }
                if (isLastChunk) {
                    s.append(String.format("%8s", Integer.toBinaryString(buffer[bytesRead - 1] & 0xFF))
                            .replace(' ', '0')
                            .substring(padding));
                    System.out.println(String.format("%8s", Integer.toBinaryString(buffer[bytesRead - 1] & 0xFF))
                            .replace(' ', '0')
                            .substring(padding)+padding);
                } else {
                    s.append(String.format("%8s", Integer.toBinaryString(buffer[bytesRead - 1] & 0xFF)).replace(' ', '0'));
                }

                int start = 0;
                while (start < s.length()) {
                    boolean matched = false;
                    for (int i = start + 1; i <= s.length(); i++) {
                        String sub = s.substring(start, i);
                        if (dCode.containsKey(sub)) {
                            List<Byte> chunk = dCode.get(sub);
                            for (byte b : chunk) {
                                byteStream.write(b);
                            }
                            start = i;
                            matched = true;
                            break;
                        }
                    }
                    if (!matched) {
                        break;
                    }
                }
                if (turn == 100) {
                    byteStream.writeTo(fos);
                    byteStream.reset();
                    turn = 0;
                }
                s.delete(0, start);
            }
            byteStream.writeTo(fos);
        } catch (ClassNotFoundException e) {
            throw new IOException("Error reading the dCode map from the file", e);
        }
    }

}
