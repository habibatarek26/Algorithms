import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HuffmanCode {
    private Map<Integer, String> word;
    private Map<String, Integer> frequency;

    public HuffmanCode() {
        word = new HashMap<>();
        frequency = new HashMap<>();
    }

    public void addWord(String val) {
        if (frequency .containsKey(val)) {
            int existingKey = frequency.get(val);
            frequency.put(val, existingKey + 1);
        } else {
            word.put(1, val);
            frequency.put(val, 1);
        }
    }

    public Integer getKeyForValue(String val) {
        return frequency.get(val);
    }

    public String getValueForKey(int key) {
        return word.get(key);
    }
    public Integer getMinKey() {
        if (word.isEmpty()) {
            return null;
        }
        return Collections.min(word.keySet());
    }
    public  void  readNbyNBytes(String filePath , int n)
    {

        try (FileInputStream fis = new FileInputStream(filePath)) {
            byte[] buffer = new byte[n];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                System.out.println("Read " + bytesRead + " bytes");
                System.out.println(new String(buffer, 0, bytesRead));
                addWord(new String(buffer, 0, bytesRead));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void printState() {
        System.out.println("Word Map (Key-Value): " + word);
        System.out.println("Occurrences (Value-Count): " + frequency);
    }
}



