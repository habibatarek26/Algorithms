import java.io.*;
import java.util.*;

public class HuffmanCode {
    public Map<List<Byte>, Integer> frequency;
    public Node ht;
    public static Map<List<Byte>, String> code;
    public static Map<String, List<Byte>> dCode;

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
    }


    public void addWord(ArrayList<Byte> key) {
        frequency.put(key, frequency.getOrDefault(key, 0) + 1);
    }

    public void readNbyNBytes(String filePath, int n) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            byte[] buffer = new byte[n * 1024 *1024*2];
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





}
