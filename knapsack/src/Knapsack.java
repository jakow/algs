import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Knapsack {
    public static void main(String[] args) {
        int[] weights = new int[] {
                4, 2, 1, 1, 5, 4, 1, 2, 2, 34, 4, 123, 3, 12, 5, 124, 1, 124
        };
        int[] values = new int[] {
                3, 2, 5, 4, 1, 2, 3, 4, 1, 100, 3, 500, 20, 4, 6, 10, 20, 400
        };
        if (weights.length != values.length) throw new RuntimeException("oops");
        Set<Integer> solution = knapsackProblem(weights, values, 100000);
        System.out.println(solution);
        System.out.println(
            String.format("The total sum is %d",
            solution.stream().reduce(0, (acc, curr) -> acc + values[curr])
        ));
    }

    static Set<Integer> knapsackProblem(int[] weights, int values[], int maxWeight) {
        Set<Integer> items = new HashSet<>();
        int[][] totalValues = new int[weights.length + 1][maxWeight + 1];
        for (int i = 0; i <= weights.length; ++i) {
            totalValues[i][0] = 0;
        }
        for (int i = 1; i <= weights.length; ++i) {
            for (int x = 1; x <= maxWeight; ++x) {
                int index = i - 1;
                int ifSkipped = totalValues[i - 1][x];
                int ifIncluded = 0;
                if (weights[index] <= x) {
                    ifIncluded = values[index] + totalValues[i - 1][x - weights[index]];
                }
                totalValues[i][x] = Math.max(ifSkipped, ifIncluded);
            }
        }
        int i = weights.length;
        int x = maxWeight;
        while (i >= 1 && x > 0) {
            int index = i - 1;
            // is this value used ?
            if (totalValues[i][x] > totalValues[i - 1][x]) {
                items.add(index);
                x -= weights[index];
            }
            i -= 1;
        }
        return items;
    }
}
