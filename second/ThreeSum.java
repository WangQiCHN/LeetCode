import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ThreeSum {
    public static void main(String[] args) {
        ThreeSum m = new ThreeSum();
        // int[] nums = {-1, 0, 1, 2, -1, -4};
        int[] nums = {0,0,0,0};
        List<List<Integer>> result = m.threeSum(nums);
        System.out.println(result);
    }
    private List<List<Integer>> result = new ArrayList<>();
    private List<Integer> item = new LinkedList<>();
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            item.addLast(nums[i]);
            calculate(nums, i + 1, -nums[i]);
            item.removeLast();
        }

        return result;
    }

    private void calculate(int[] nums, int start, int target) {
        for (int j = start; j < nums.length - 1; j++) {
            if (j > start && nums[j] == nums[j - 1]) continue;
            int v = nums[j];
            item.addLast(v);
            int newTarget = target - v;
            int left = j + 1, right = nums.length - 1;
            while (left <= right) {
                int mid = (right - left ) / 2 + left;
                if (nums[mid] == newTarget) {
                    item.addLast(nums[mid]);
                    result.add(new ArrayList<>(item));
                    item.removeLast();
                    break;
                } else if (nums[mid] > newTarget) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            item.removeLast();
        }
    }
}
