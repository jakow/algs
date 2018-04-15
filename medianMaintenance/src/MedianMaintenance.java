import java.io.File;
import java.io.IOException;
import java.util.*;

public class MedianMaintenance {
    public static void main(String[] args) {
        List<Integer> input = readInput();
        System.out.println(medianMaintenance(input));
        System.out.println(recomputeMediansStupid(input));
        input = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        System.out.println(medianMaintenance(input));
        System.out.println(recomputeMediansStupid(input));
    }

    public static List<Integer> readInput() {
        List<Integer> input = new ArrayList<>();
        Scanner s;
        try {
            s = new Scanner(new File("data/numbers.txt"));
        } catch (IOException e) {
            throw new RuntimeException("Not found file");
        }
        while (s.hasNextInt()) {
            input.add(s.nextInt());
        }
        return input;
    }

    public static long medianMaintenance(List<Integer> nums) {

        // max heap
        PriorityQueue<Integer> low = new PriorityQueue<>(Comparator.comparingInt(o -> -o));
        // min heap
        PriorityQueue<Integer> high = new PriorityQueue<>(Comparator.comparingInt(o -> o));
        int medianSum = 0;
        for (int i = 0; i < nums.size(); ++i) {
            int runningMedian = recomputeMedian(nums.get(i), low, high);
//            System.out.println(String.format("median is %d", runningMedian));
            medianSum += runningMedian;
        }
        return medianSum % 10000;
    }

    private static int recomputeMediansStupid(List<Integer> nums) {
        int[] arr = new int[nums.size()];
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = nums.get(i);
        }
        int sum = 0;
        for (int len = 1; len <= arr.length; ++len) {
            Arrays.sort(arr, 0, len);
            int median = arr[len % 2 == 0 ? len / 2 - 1 : len / 2];
//            System.out.println(String.format("median is %d", median));
            sum += median;
        }
        return sum % 10000;
    }

    private static int recomputeMedian(int num, PriorityQueue<Integer> low, PriorityQueue<Integer> high) {
//        System.out.println(low);
//        System.out.println(high);
        if (low.isEmpty()) {
            low.add(num);
        } else if (low.size() > high.size()) {
            // i.e. low.sz = 4, hi.sz = 3 - want to re-balance to have 4 and 4 respectively
            // take max element from low
            int maxLeft = low.poll();
            low.add(Math.min(maxLeft, num));
            high.add(Math.max(maxLeft, num));
        } else {
            // we want to expand low to make it equal size or larger
            int maxHigh = high.poll();
            low.add(Math.min(maxHigh, num));
            high.add(Math.max(maxHigh, num));
        }
//        System.out.println(String.format("lengths: low %d, hi %d", low.size(), high.size()));
        return low.peek();
    }
}
