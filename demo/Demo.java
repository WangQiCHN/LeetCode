package demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Demo {
    public static void main() {
        int[] nums = {1000000000,1000000000,1000000000,1000000000};
        Demo d = new Demo();
        int target = -294967296;
        System.out.println(d.fourSum(nums, target));
    }

    private List<List<Integer>> result = new ArrayList<>();
    private List<Integer> item = new LinkedList<>();
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                long v = target - nums[i] - nums[j];
                item.addLast(nums[i]);
                item.addLast(nums[j]);
                calculate2(nums, v, j);
                item.removeLast();
                item.removeLast();
            }
        }

        return result;
    }

    private void calculate2(int[] nums, long total, int start) {
        int left = start + 1, right = nums.length - 1;
        while (left < right) {
            int l = nums[left];
            int r = nums[right];
            long sum = l + r;
            if (sum > total) {
                right--;
            } else if (sum < total) {
                left++;
            } else {
                item.addLast(l);
                item.addLast(r);
                result.add(new ArrayList<>(item));
                item.removeLast();
                item.removeLast();
                left++;
                right--;

                while (left < right && nums[left] == l) {
                    left++;
                }

                while (right > left && nums[right] == r) {
                    right--;
                }

                if (left >= right) break;
            }
        }
    }
}