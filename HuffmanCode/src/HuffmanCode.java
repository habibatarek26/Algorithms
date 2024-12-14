import java.io.*;
import java.util.*;

public class HuffmanCode {
    public Map<List<Byte>, Integer> frequency;
    public Node ht;
    public static Map<List<Byte>, String> code;
    public static Map<String, List<Byte>> dCode;
    public int padding;

    // Node class definition remains unchanged
    public class Node {
        List<Byte> val;
        int freq;
        Node left;
        Node right;

        Node(List<Byte> val, int freq, Node left, Node right) {
            this.val = val;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        Node(List<Byte> val, int freq) {
            this.val = val;
            this.freq = freq;
        }
    }

    public HuffmanCode() {
        frequency = new HashMap<>();
        code = new HashMap<>();
        dCode = new HashMap<>();
        padding = 0;
    }


    public void addWord(ArrayList<Byte> key) {
        frequency.put(key, frequency.getOrDefault(key, 0) + 1);
    }

    public void readNbyNBytes(String filePath, int n) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            byte[] buffer = new byte[n * 1024 *1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                int offset = 0;
                while (offset < bytesRead) {
                    int chunkSize = Math.min(n, bytesRead - offset);
                    ArrayList<Byte> chunk = new ArrayList<>();
                    for (byte b : Arrays.copyOfRange(buffer, offset, offset + chunkSize)) {
                        chunk.add(b);
                    }
                    addWord(chunk);
                    offset += chunkSize;
                }
            }
        } catch (IOException e) {
            System.out.println("Wrong file path?");
        }
    }


    public void buildTree() {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.freq));

        for (Map.Entry<List<Byte>, Integer> entry : frequency.entrySet()) {
            pq.offer(new Node(entry.getKey(), entry.getValue()));
        }

        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            Node z = new Node(null, left.freq + right.freq, left, right);
            pq.offer(z);
        }
        ht = pq.poll();
        getPaths(ht, "");
    }

    private void getPaths(Node node, String path) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            dCode.put(path, node.val);
            code.put(node.val, path);
            return;
        }
        getPaths(node.left, path + "0");
        getPaths(node.right, path + "1");
    }
    public void compressFile(String inputFilePath, String outputFilePath, int n) throws IOException {
        try (FileInputStream fis = new FileInputStream(inputFilePath);
             FileOutputStream fos = new FileOutputStream(outputFilePath)) {

            byte[] buffer = new byte[n * 1024 * 1024];
            int bytesRead;
            StringBuilder leftoverBits = new StringBuilder();
            ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
            while ((bytesRead = fis.read(buffer)) != -1) {
                StringBuilder stringBuilder = new StringBuilder(leftoverBits);
                for (int i = 0; i < bytesRead; i += n) {
                    int chunkSize = Math.min(n, bytesRead - i);     // for the last chunk in the file
                    List<Byte> chunk = new ArrayList<>();           //to handle different types of files not only text
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
                //take every 8 bits in the string to convert them to int and take only first 8*x string the remaining to be considered
                //in the next iteration
                int remainingBitsStart = length - (length % 8);

                for (int i = 0; i < remainingBitsStart; i += 8) {
                    String byteString = binaryString.substring(i, i + 8);
                    int intValue = Integer.parseInt(byteString, 2);

                    outputBuffer.write(intValue);
                }
                leftoverBits.setLength(0);
                leftoverBits.append(binaryString.substring(remainingBitsStart));
            }
            //in the last iteration check for string of length <8 has not been written in the file as padding is required
            if (leftoverBits.length() > 0) {
                while (leftoverBits.length() < 8) {
                    leftoverBits.insert(0,"0");
                    padding++;
                }
                int intValue = Integer.parseInt(leftoverBits.toString(), 2);
                  outputBuffer.write(intValue);

            }
            //write the whole file one at a time  to reduce the time as much as possible
            fos.write(outputBuffer.toByteArray());
        }
    }

    public void decompressFile(String inputFilePath, String outputFilePath, int n) throws IOException {
        System.out.println("Start = " +padding);

        try (FileInputStream fis = new FileInputStream(inputFilePath);
             FileOutputStream fos = new FileOutputStream(outputFilePath)) {

            byte[] buffer = new byte[n * 1024 * 1024];
            int bytesRead;
            StringBuilder s = new StringBuilder();
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            boolean turn =true;
            while ((bytesRead = fis.read(buffer)) != -1) {
                turn=!turn;
                 boolean temp = fis.available()<=0;
                for (int i = 0; i < bytesRead-1; i++) {
                        s.append(String.format("%8s", Integer.toBinaryString(buffer[i] & 0xFF)).replace(' ', '0'));
                }
                if(temp) {
                    s.append(String.format("%8s", Integer.toBinaryString(buffer[bytesRead - 1] & 0xFF))
                            .replace(' ', '0')
                            .substring(padding));
                    System.out.println(String.format("%8s", Integer.toBinaryString(buffer[bytesRead - 1] & 0xFF))
                            .replace(' ', '0')
                            .substring(padding));
                }else
                    s.append(String.format("%8s", Integer.toBinaryString(buffer[bytesRead-1] & 0xFF)).replace(' ', '0'));

                int  start =0;
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
               if(turn) {
                   byteStream.writeTo(fos);
                   byteStream.reset();
               }
                s.delete(0, start);
            }
            byteStream.writeTo(fos);
        }
    }

}
