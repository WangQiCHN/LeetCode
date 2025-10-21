package code;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = {25,75,49};
        int k = 13;
        int numOperations = 1;
        System.out.println(s.maxFrequency(nums, k, numOperations));
    }
    public int maxFrequency(int[] nums, int k, int numOperations) {
        Arrays.sort(nums);
        int len = nums.length;
        // TreeMap<Integer, Integer> map = new TreeMap<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.merge(n, 1, Integer::sum);
        }
        // int start = map.firstKey(), end = map.lastKey();
        int start = nums[0], end = nums[len - 1];
        int max = 0;
        for (int i = start; i <= end; i++) {
            int left = leftBound(nums, 0, len - 1, i - k);
            int right = rightBound(nums, 0, len - 1, i + k);
            int count = map.containsKey(i) ? map.get(i) : 0;
            max = Math.max(max, Math.min(right - left + 1 - count, numOperations) + count);
        }

        return max;
    }

    private int leftBound(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int m = (left + right) >> 1;
            if (nums[m] >= target) {
                right = m - 1;
            } else {
                left = m + 1;
            }
        }

        return left;
    }
    private int rightBound(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int m = (left + right) >> 1;
            if (nums[m] > target) {
                right = m - 1;
            } else {
                left = m + 1;
            }
        }

        return right;
    }
}