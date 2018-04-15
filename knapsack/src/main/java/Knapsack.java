import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Table;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Knapsack {

    public static void main(String[] args) {
//        int[] weights = new int[] {
//                4, 2, 1, 1, 5, 4, 1, 2, 2, 34, 4, 123, 3, 12, 5, 124, 1, 124
//        };
//        int[] values = new int[] {
//                3, 2, 5, 4, 1, 2, 3, 4, 1, 100, 3, 500, 20, 4, 6, 10, 20, 400
//        };
//        if (weights.length != values.length) throw new RuntimeException("oops");
//        Set<Integer> solution = knapsackDP(weights, values, 100000);
//        System.out.println(solution);
//        System.out.println(
//            String.format("The total value is %d",
//            solution.stream().reduce(0, (acc, curr) -> acc + values[curr])
//        ));

        Scanner s;
        try {
            s = new Scanner(new File("data/knapsack_big.txt"));
        } catch (IOException e) {
            return;
        }
        int knapsackSize = s.nextInt();
        int numberOfItems = s.nextInt();
        int[] weights = new int[numberOfItems];
        int[] values = new int[numberOfItems];
        int i = 0;
        while (i < numberOfItems) {
            values[i] = s.nextInt();
            weights[i] = s.nextInt();
            i++;
        }

        Set<Integer> solution = knapsackDP(weights, values, knapsackSize);
        System.out.println(solution);
        System.out.println(
            String.format("The total value is %d",
            solution.stream().reduce(0, (acc, curr) -> acc + values[curr])
        ));

        try {
            s = new Scanner(new File("data/knapsack_bigger.txt"));
        } catch (IOException e) {
            return;
        }

        knapsackSize = s.nextInt();
        numberOfItems = s.nextInt();
        int[] weights2 = new int[numberOfItems];
        int[] values2 = new int[numberOfItems];
        i = 0;
        while (i < numberOfItems) {
            values2[i] = s.nextInt();
            weights2[i] = s.nextInt();
            i++;
        }

        Knapsack recursive = new Knapsack(weights2, values2);
        KnapsackSolution knapsackSolution = recursive.solution(knapsackSize);
        System.out.println(knapsackSolution.value);
        System.out.println(knapsackSolution.items);
    }

    static Set<Integer> knapsackDP(int[] weights, int values[], int maxWeight) {
        Set<Integer> items = new HashSet<>();
        int[][] totalValues = new int[weights.length + 1][maxWeight + 1];
        for (int i = 0; i <= weights.length; ++i) {
            totalValues[i][0] = 0;
        }
        for (int currentWeight = 1; currentWeight <= maxWeight; ++currentWeight) {
            for (int item = 1; item <= weights.length; ++item) {
                int ifSkipped = totalValues[item - 1][currentWeight];
                int itemIndex = item - 1;
                int ifIncluded = 0;
                if (weights[itemIndex] <= currentWeight) {
                    ifIncluded = values[itemIndex] + totalValues[item - 1][currentWeight - weights[itemIndex]];
                }
                totalValues[item][currentWeight] = Math.max(ifSkipped, ifIncluded);
            }
        }
        // path reconstruction
        int i = weights.length;
        int weight = maxWeight;
        while (i >= 1 && weight > 0) {
            int index = i - 1;
            // is this value used?
            if (totalValues[i][weight] > totalValues[i - 1][weight]) {
                items.add(index);
                weight -= weights[index];
            }
            i -= 1;
        }
        return items;
    }


    private final KnapsackSolution EMPTY_SOLUTION = new KnapsackSolution();
    private int[] weights;
    private int[] values;
    private Table<Integer, Integer, KnapsackSolution> memo;

    Knapsack(int[] weights, int[] values) {
        this.weights = weights;
        this.values = values;
        this.memo = HashBasedTable.create();
    }

    public KnapsackSolution solution(int capacity) {
        return knapsackMemo(weights.length, capacity);
    }

    private KnapsackSolution knapsackMemo(
            int itemsSoFar,
            int capacity) {
        KnapsackSolution ifIncluded, ifSkipped;
        if (itemsSoFar == 0 || capacity == 0) {
            return EMPTY_SOLUTION;
        }

        if (memo.contains(itemsSoFar, capacity)) {
            return memo.get(itemsSoFar, capacity);
        }

        int currentItem = itemsSoFar - 1;
        int weight = weights[currentItem];

        ifSkipped = knapsackMemo(itemsSoFar - 1, capacity);

        if (weight <= capacity) {
            KnapsackSolution withSpaceForCurrent = knapsackMemo(itemsSoFar - 1, capacity - weight);
            ifIncluded = withSpaceForCurrent.withAddedItem(currentItem);
        } else {
            ifIncluded = null;
        }

        KnapsackSolution solution;

        if (ifIncluded != null && ifIncluded.value > ifSkipped.value) {
            solution = ifIncluded;
        } else {
            solution = ifSkipped;
        }
        memo.put(itemsSoFar, capacity, solution);
        return solution;
    }


    public class KnapsackSolution {
        public final ImmutableSet<Integer> items;
        public final long value;

        private KnapsackSolution() {
            this.items = ImmutableSet.of();
            this.value = 0;
        }

        private KnapsackSolution(ImmutableSet<Integer> items, long value) {
            this.items = items;
            this.value = value;
        }

        private KnapsackSolution withAddedItem(int index) {
            ImmutableSet<Integer> newItems = new ImmutableSet.Builder<Integer>()
                    .addAll(items).add(index).build();
            return new KnapsackSolution(newItems, value + values[index]);
        }
    }
}
