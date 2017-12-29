import java.util.HashMap;
import java.util.Map;

/**
 * Created by jakub on 29/12/2017.
 */
public class Main {
    public static void main(String[] args) {
        Map<Character, Double> alphabet = new HashMap<>();
        alphabet.put('a', 0.08167);
        alphabet.put('b', 0.01492);
        alphabet.put('c', 0.02782);
        alphabet.put('d', 0.04253);
        alphabet.put('e', 0.12702);
        alphabet.put('f', 0.02228);
        alphabet.put('g', 0.02015);
        alphabet.put('h', 0.06094);
        alphabet.put('i', 0.06966);
        alphabet.put('j', 0.00153);
        alphabet.put('k', 0.00772);
        alphabet.put('l', 0.04025);
        alphabet.put('m', 0.02406);
        alphabet.put('n', 0.06749);
        alphabet.put('o', 0.07507);
        alphabet.put('p', 0.01929);
        alphabet.put('q', 0.00095);
        alphabet.put('r', 0.05987);
        alphabet.put('s', 0.06327);
        alphabet.put('t', 0.09056);
        alphabet.put('u', 0.02758);
        alphabet.put('v', 0.00978);
        alphabet.put('w', 0.02360);
        alphabet.put('x', 0.00150);
        alphabet.put('y', 0.01974);
        alphabet.put('z', 0.00074);
        HuffmanCoding coding = HuffmanCoding.build(alphabet);
        String input = "quickbrownfoxjumpsoveralazydog";
        String encoded = coding.encode(input);
        System.out.printf("%s -> %s\n", input, encoded);
        System.out.printf("Decodes correctly: %b\n", coding.decode(encoded).equals(input));
        System.out.printf("Compression factor: %s\n", input.length() / (encoded.length() / 8.0));
    }

}
