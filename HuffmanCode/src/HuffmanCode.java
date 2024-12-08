import java.io.FileInputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCode {
    public Map<String , Integer> frequency;
    public Node ht;
    public Map<String ,String> code;
    public class Node  {
        String val;
        int freq;
        Node left;
        Node right;
        Node(String val,int freq, Node left,Node right)
        {
            this.val =val;
            this.freq=freq;
            this.left=left;
            this.right=right;
        }
        Node(String val,int freq)
        {
            this.val =val;
            this.freq=freq;
        }
    }

    public HuffmanCode() {
        frequency = new HashMap<>();
        code = new HashMap<>();
    }
    public void addWord(String val) {
        int k=1;
        if (frequency.containsKey(val)) {
            k = frequency.remove(val);
            frequency.put(val, k + 1);
        }
        else
            frequency.put(val, k);
    }

    public void readNbyNBytes(String filePath, int n) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            byte[] buffer = new byte[n];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                addWord(new String(buffer, 0, bytesRead));
            }
        } catch (IOException e) {
            System.out.println("Wrong file path??");
        }
    }

    public void buildTree() {
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                return Integer.compare(node1.freq, node2.freq);
            }
        });

        for (Map.Entry<String, Integer> entry : frequency.entrySet()) {
            pq.offer(new Node(entry.getKey(), entry.getValue()));
        }

        while (pq.size() > 1) {
            Node right = pq.poll();
            Node left = pq.poll();
            Node z = new Node(null, left.freq + right.freq,left,right);
            pq.offer(z);
        }
        ht = pq.poll();
        printPathsRecursive(ht,"");
    }

    private void printPathsRecursive(Node node, String path) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            code.put(node.val,path);
//            System.out.println("Value: " + node.val + ", Path: " + path);
            return;
        }
        printPathsRecursive(node.right, path + "0");
        printPathsRecursive(node.left, path + "1");
    }
    public void compressFile()
    {

    }
    public  void decompressFile()
    {

    }

}
