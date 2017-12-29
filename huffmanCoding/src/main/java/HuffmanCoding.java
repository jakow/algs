import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HuffmanCoding {
    static HuffmanCoding build(Map<Character, Double> alphabet) {
        if (alphabet.size() < 2) {
            throw new IllegalArgumentException("Huffman alphabet must be of length at least 2");
        }
        NavigableSet<Node> nodes = alphabet.entrySet()
                .stream()
                .map(a -> new Leaf(a.getKey(), a.getValue()))
                .collect(Collectors.toCollection(TreeSet::new));
        Node decodeTree = buildDecodeTree(nodes);
        Map<Character, String> encodeMap = buildEncodeMap(decodeTree);
        return new HuffmanCoding(decodeTree, encodeMap);
    }

    private static Node buildDecodeTree(NavigableSet<Node> nodes) {
        if (nodes.size() == 1) {
            return nodes.pollFirst();
        }
        Node right = nodes.pollFirst();
        Node left = nodes.pollFirst();
        Node combined = new Tree(left, right);
        nodes.add(combined);
        return buildDecodeTree(nodes);
    }

    private static Map<Character, String> buildEncodeMap(Node decodeTree) {
        Map<Character, String> map = new HashMap<>();
        Deque<String> current = new ArrayDeque<>();
        buildEncodeMap(decodeTree, map, current);
        return map;
    }

    private static void buildEncodeMap(Node decodeTree, Map<Character, String> map, Deque<String> curr) {
        if (decodeTree instanceof Leaf) {
            Leaf leaf = (Leaf) decodeTree;
            map.put(leaf.value, curr.stream().collect(Collectors.joining()));
        } else {
            Tree tree = (Tree) decodeTree;
            curr.addLast("0");
            buildEncodeMap(tree.children[0], map, curr);
            curr.removeLast();
            curr.addLast("1");
            buildEncodeMap(tree.children[1], map, curr);
            curr.removeLast();
        }
    }



    private Node decodeTree;
    private Map<Character, String> encodeMap;

    private HuffmanCoding(Node tree, Map<Character, String> map) {
        decodeTree = tree;
        encodeMap = map;
    }

    public String encode(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (!encodeMap.containsKey(c)) {
                throw new IllegalArgumentException(String.format("Character %c not in the dictionary", c));
            }
            sb.append(encodeMap.get(c));
        }
        return sb.toString();
    }

    public String decode(String s) {
        char[] digits = s.toCharArray();
        int idx = 0;
        StringBuilder sb = new StringBuilder();
        while (idx < digits.length) {
            idx = traverseTree(decodeTree, digits, idx, sb);
        }
        return sb.toString();
    }

    private int traverseTree(Node node, char[] digits, int idx, StringBuilder builder) {
        if (node instanceof Tree) {
            Tree tree = (Tree) node;
            if (digits[idx] == '0') {
                return traverseTree(tree.children[0], digits, idx + 1, builder);
            } else if (digits[idx] == '1') {
                return traverseTree(tree.children[1], digits, idx + 1, builder);
            } else {
                throw new IllegalArgumentException("Malformed binary string for decoding");
            }
        } else {
            builder.append(((Leaf) node).value);
            return idx;
        }
    }
}
