import java.io.File;
import java.io.IOException;
import java.util.*;

public class TwoSum {
    static int twoSum(long[] nums, long target) {
        Set<Long> memo = new HashSet<>();
        int sum = 0;
        for (long num : nums) {
            if (memo.contains(target - num)) {
                return 1;
            }
            memo.add(num);
        }
        return 0;
    }

    public static long[] readNumbers() {
        List<Long> numbers = new ArrayList<>();
        Scanner s;
        try {
            s = new Scanner(new File("data/2sum.txt"));
        } catch (IOException e) {
            throw new RuntimeException("File not found");
        }
        while (s.hasNextLong()) {
            numbers.add(s.nextLong());
        }

        long[] nums = new long[numbers.size()];
        for (int i = 0; i < numbers.size(); ++i) {
            nums[i] = numbers.get(i);
        }
        return nums;
    }

    public static void main(String[] args) {
        long[] numbers = readNumbers();
        long ts = 0;
        for (int target = -10000; target <= 10000; ++target) {
            System.out.println(String.format("Calculating 2-SUM for %d", target));
            ts += twoSum(numbers, target);
        }
        System.out.println(ts);
    }
}
