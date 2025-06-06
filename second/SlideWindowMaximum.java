import java.util.LinkedList;

public class SlideWindowMaximum {
    public static void main() {
        SlideWindowMaximum s = new SlideWindowMaximum();
        int[] nums = {7,2,4};
        int k = 2;
        s.maxSlidingWindow(nums, k);
    }
    LinkedList<Integer> window = new LinkedList<>();
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 1) {
            return nums;
        }
        int n = nums.length;
        int[] result = new int[n - k + 1];

        for (int i = 0; i < n; i++) {
            if (i < k - 1) {
                if (i == 0) {
                    window.addLast(nums[i]);
                } else {
                    calculate(nums[i]);
                }
            } else {
                calculate(nums[i]);
                result[i - k + 1] = window.getFirst();
                if (window.getFirst() == nums[i - k + 1]) {
                    window.pollFirst();
                }
            }
        }

        return result;
    }

    private void calculate(int current) {
        while (!window.isEmpty()) {
            int v = window.getLast();
            if (v < current) {
                window.removeLast();
            } else {
                break;
            }
        }
        window.addLast(current);
    }
}
