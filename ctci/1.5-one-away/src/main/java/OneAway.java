/**
 * Created by jakub on 19/01/2018.
 */
public class OneAway {
    public static boolean oneAway(String s1, String s2) {
        String longer;
        String shorter;
        if (s1.length() > s2.length()) {
            longer = s1;
            shorter = s2;
        } else {
            longer = s2;
            shorter = s1;
        }
        if (longer.length() - shorter.length() > 1) {
            return false;
        }
        boolean insert = longer.length() != shorter.length();
        boolean wasApplied = false;
        int i = 0;
        int j = 0;
        while (i < longer.length() && j < shorter.length()) {
            if (longer.charAt(i) != shorter.charAt(j)) {
                if (wasApplied) {
                    return false;
                }
                wasApplied = true;
                if (insert) {
                    i++;
                }
            }
            i++;
            j++;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(oneAway("pale", "ple"));
        System.out.println(oneAway("pale", "plea"));
        System.out.println(oneAway("pale", "pole"));
        System.out.println(oneAway("pale", "pales"));
        System.out.println(oneAway("bake", "pale"));
        System.out.println(oneAway("bake", "palette"));
    }
}
