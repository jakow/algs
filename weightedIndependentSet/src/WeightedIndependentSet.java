import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by jakub on 07/12/2017.
 */
public class WeightedIndependentSet {

    public static void main(String[] args) {
        int[] set = new int[] {
          1,4,5,4,20,2,23,3,4,1,5,5,1,33,11,2,3,22,3,4,4,4,4
        };

        Set<Integer> solution = maxWeightedIndependentSet(set);
        int sum = solution.stream().reduce(0, (acc, idx) -> acc + set[idx]);
        System.out.println(sum);
        System.out.println(solution.stream().sorted().collect(Collectors.toList()));

    }

    private static Set<Integer> maxWeightedIndependentSet(int[] set) {
        Set<Integer> solutionSet = new HashSet<>();
        if (set.length == 0) return solutionSet;
        int[] dp = new int[set.length + 1];
        dp[0] = 0;
        dp[1] = set[0];
        for (int i = 2; i < dp.length; ++i) {
            int index = i - 1;
            dp[i] = Math.max(set[index] + dp[i - 2], dp[i - 1]);
        }

        int i = set.length;
        while ( i >= 1) {
            if (i >= 2 && dp[i - 1] > dp[i - 2] + set[i - 1]) {
                i -= 1;
            } else {
                solutionSet.add(i - 1);
                i -= 2;
            }
        }
        return solutionSet;
    }
}
