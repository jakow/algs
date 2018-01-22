import java.util.HashMap;
import java.util.Map;

public class PalindromePermutation {
    static boolean isPalindromePermutation(String s) {
        char[] chars = s.toLowerCase().toCharArray();
        Map<Character, Integer> counts = new HashMap<>();
        int len = 0;
        for (char c : chars) {
            if (c != ' ') {
                len++;
                counts.put(c, counts.getOrDefault(c, 0) + 1);
            }
        }
        int permittedSingles = 0;
        if (len % 2 == 1) {
            permittedSingles = 1;
        }
        for (int value : counts.values()) {
            if (value % 2 != 0) {
                if (permittedSingles == 0) {
                    return false;
                } else {
                    permittedSingles--;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindromePermutation("Tact coa"));
        System.out.println(isPalindromePermutation("Able was I ere I saw Elba"));
        System.out.println(isPalindromePermutation("Able was I ere I saw Elbe"));
        System.out.println(isPalindromePermutation("Able I was ere I saw Elba"));
        System.out.println(isPalindromePermutation("ykaka"));
        System.out.println(isPalindromePermutation("ykakay"));
        System.out.println(isPalindromePermutation("abc"));
    }
}
