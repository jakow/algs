/**
 * Created by jakub on 27/01/2018.
 */
public class MagicIndex {
    static int magicIndex(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        return magicIndex(nums, start, end);
    }

    private static int magicIndex(int[] nums, int start, int end) {
        if (end < start) {
            return -1;
        }
        int mid = start + (end - start) / 2;
        if (nums[mid] == mid) {
            return mid;
        }
        int leftIdx = Math.min(nums[mid], mid - 1);
        int left = magicIndex(nums, start, leftIdx);
        if (left >= 0) return left;

        int rightIdx = Math.max(nums[mid], mid + 1);
        return magicIndex(nums, rightIdx, end);
    }

    public static void main(String[] args) {
        System.out.println(magicIndex(new int[] {-10,-5,2,2,3,4,7,9,12,13}));
    }
}
