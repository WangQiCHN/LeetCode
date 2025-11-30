package code;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] grid = { -1, -2, -3, -4, -5 };
        int k = 4;
        // int[] grid = { 1, 2 };
        // int k = 1;
        long result = sol.maxSubarraySum(grid, k);
        System.out.println(result);
    }

    public long maxSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long[] remains = new long[k];
        long max = Long.MIN_VALUE;
        long total = 0;
        Arrays.fill(remains, Long.MAX_VALUE / 2);
        remains[k - 1] = 0;

        for (int i = 0; i < n; i++) {
            total += nums[i];
            max = Math.max(max, total - remains[i % k]);
            remains[i % k] = Math.min(remains[i % k], total);
        }

        return max;
    }
}