import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import first.FourSum;

public class FourSum {
    public static void main(String[] args) {
        FourSum m = new FourSum();
        // int[] nums = {-1, 0, 1, 2, -1, -4};
        int[] nums = {1,0,-1,0,-2,2};
        int target = 0;
        List<List<Integer>> result = m.fourSum(nums, target);
        System.out.println(result);
    }
    private List<List<Integer>> result = new ArrayList<>();
    private List<Integer> item = new LinkedList<>();
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int v = nums[i];
            item.addLast(v);
            calculateThree(nums, i + 1, target - v);
            item.removeLast();
        }

        return result;
    }

    private void calculateThree(int[] nums, int start, int target) {
        for (int i = start; i < nums.length - 1; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue;
            int v = nums[i];
            item.addLast(v);
            calculate(nums, i + 1, target - v);
            item.removeLast();
        }
    }

    private void calculate(int[] nums, int start, int target) {
        int left = start, right = nums.length - 1;
        while (left <= right) {
            int total = nums[left] + nums[right];
            if (total < target) {
                left++;
            } else if (total > target) {
                right--;
            } else {
                item.addLast(nums[left]);
                item.addLast(nums[right]);
                result.add(new ArrayList<>(item));
                item.removeLast();
                item.removeLast();
                left++;
                while (left < right && nums[left] == nums[left - 1]) {
                    left++;
                }
                right--;
                while (left < right && nums[right] == nums[right + 1]) {
                    right--;
                }
            }
        }
    }
}
