import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class HuffmanCoding implements HuffmanInterface {
    private class HuffmanNode implements Comparable<HuffmanNode> {
        int count;
        char value;
        HuffmanNode left;
        HuffmanNode right;
        public HuffmanNode(int count, char value) {
            this.count = count;
            this.value = value;
            left = right = null;
        }
        public HuffmanNode(HuffmanNode left, HuffmanNode right) {
            this.left = left;
            this.right = right;
            this.count = left.count + right.count;
            this.value = 0;
        }
        public boolean isLeaf() {
            return left == null && right == null;
        }

        @Override
        public int compareTo(HuffmanNode o) {
            return this.count - o.count;
        }
    }

    class HuffmanCode {
        String code;
        char value;
        HuffmanCode(char value, String code) {
            this.code = code;
            this.value = value;
        }
    }

    void generateTreeFromKey(String key){
        root = new HuffmanNode(-1, '0');
        boolean leaf = false;
        HuffmanNode node = root;
        for (char c : key.toCharArray()) {
            if (leaf){
                leaf = false;
                node.value = c;
                node = root;
                continue;
            }
            if (c == '0'){
                if (node.left == null) {
                    node.left = new HuffmanNode(-1, '0');
                }
                node = node.left;
            }
            if (c == '1'){
                if (node.right == null) {
                    node.right = new HuffmanNode(-1, '0');
                }
                node = node.right;
            }
            if (c == ':') {
                leaf = true;
            }
        }
        System.out.println("");
    }

    String generateKeyFromTree(HuffmanNode node, String key){
        if (node == null) {
            return "";
        }
        if(node.isLeaf()){
            return codes + ":" + node.value;
        }
        String left = generateKeyFromTree(node.left, codes+"0");
        String right = generateKeyFromTree(node.right, codes+"1");
        return left + right;
    }

    public HuffmanCoding(){

    }
    public HuffmanCoding(String key){
        generateTreeFromKey(key);
    }

    public String getKey() {
        return generateKeyFromTree(root, "");
    }

    PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
    HuffmanNode root;
    List<HuffmanCode> codes = new ArrayList<>();



    @Override
    public String decode(String codedMessage) {
        if (codedMessage == null || codedMessage.length() == 0 || root == null) {
            return "";
        }

        String decodedString = "";
        HuffmanNode node = root;
        for (char c : codedMessage.toCharArray()) {
            if (c == '0') {
                node = node.left;
            }
            if (c == '1') {
                node = node.right;
            }
            if (node.isLeaf()) {
                decodedString += node.value;
                node = root;

            }
        }
        return decodedString;
    }

    @Override
    public String encode(String message) {
        int[] counts = new int[256];

        for (char c : message.toCharArray()) {
            counts[c]++;
        }

        for (char c = 0; c < counts.length; c++) {
            if (counts[c] > 0) {
                HuffmanNode huffmanNode = new HuffmanNode(counts[c], c);
                priorityQueue.add(huffmanNode);
            }
        }

        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();
            HuffmanNode composite = new HuffmanNode(left, right);
            priorityQueue.add(composite);
        }

        HuffmanNode root = priorityQueue.poll();


        generateCodes(root, "");

        String encodedMessage = "";
        for (char c : message.toCharArray()) {
            encodedMessage += findCodes(c);
        }

        return encodedMessage;
    }


    String findCodes(char c) {
        for (HuffmanCode cod : codes) {
            if (cod.value == c) {
                return cod.code.toString();
            }
        }
        return "";
    }

    void generateCodes(HuffmanNode node, String code) {
        if (node != null) {
            return;
        }
        if (node.isLeaf()) {
            codes.add(new HuffmanCode(node.value, code));
        }
        generateCodes(node.left, code + "0");
        generateCodes(node.right, code + "1");
    }
}


// Noah Marino
//
// N 2
// o 2
// a 2
// h 1
// m 1
// r 1
// i 1
// _ 1
//