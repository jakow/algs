import java.util.ArrayDeque;
import java.util.Deque;

public class SequenceAlignment {
    public static int GAP_PENALTY = 1;
    public static int SUBSTITUTE_PENALTY = 3;

    public Result compute(String s1, String s2) {

        char[] seq1 = s1.toCharArray();
        char[] seq2 = s2.toCharArray();

        long[][] penalty = new long[seq1.length + 1][seq2.length + 1];
        for (int i = 0; i <= seq1.length; ++i) {
            penalty[i][0] = i * GAP_PENALTY;
        }

        for (int j = 0; j <= seq2.length; ++j) {
            penalty[0][j] = j * GAP_PENALTY;
        }

        for (int i = 1; i <= seq1.length; ++i) {
            for (int j = 1; j <= seq2.length; ++j) {
                penalty[i][j] = min(
                        penalty[i - 1][j - 1] + (seq1[i - 1] == seq2[j - 1] ? 0 : SUBSTITUTE_PENALTY), // equal or substitute
                        penalty[i - 1][j] + GAP_PENALTY, // induced alignment of Xi-1 and Yj, add gap to Y
                        penalty[i][j - 1] + GAP_PENALTY // induced alignment of Xi and Yj-1, add gap to X
                );
            }
        }

        // reconstruction
        Deque<Character> stack1 = new ArrayDeque<>();
        Deque<Character> stack2 = new ArrayDeque<>();
        int i = seq1.length;
        int j = seq2.length;
        while (i > 0 || j > 0) {
            if ((i > 0 && j > 0) && (
                    (seq1[i - 1] == seq2[j - 1]) ||
                    (penalty[i][j] ==  penalty[i - 1][j - 1] + SUBSTITUTE_PENALTY)
                )
            ) {
                i -= 1;
                j -= 1;
                stack2.addLast(seq1[i]);
                stack1.addLast(seq1[i]);
            } else if (i > 0 && penalty[i][j] == penalty[i - 1][j] + GAP_PENALTY) {
                // inserted a gap into seq2
                stack2.addLast('-');
                i -= 1;
                stack1.addLast(seq1[i]);

            } else { // penalty[i][j] == penalty[i][j - 1] + GAP_PENALTY
                stack1.addLast('-');
                j -= 1;
                stack2.addLast(seq2[j]);
            }
        }
        String s1Aligned = buildString(stack1);
        String s2Aligned = buildString(stack2);
        return new Result(s1Aligned, s2Aligned, penalty[seq1.length][seq2.length]);
    }

    private static String buildString(Deque<Character> stack1) {
        StringBuilder sb = new StringBuilder(stack1.size());
        while (!stack1.isEmpty()) {
            sb.append((stack1.removeLast()));
        }
        return sb.toString();
    }

    public static class Result {
        final long penalty;
        final String s1;
        final String s2;

        private Result(String s1, String s2, long penalty) {
            this.penalty = penalty;
            this.s1 = s1;
            this.s2 = s2;
        }

        @Override
        public String toString() {
            return String.format("Result: penalty %d, %s <-> %s", penalty, s1, s2);
        }
    }

    public static long min(long... values) {
        long currMin = values[0];
        for (int i = 1; i < values.length; ++i) {
            currMin = Math.min(currMin, values[i]);
        }
        return currMin;
    }
}
