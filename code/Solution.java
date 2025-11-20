package code;

public class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {4,3,2,6};
        int result = sol.maxRotateFunction(nums);
        System.out.println(result);
    }
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        int total = 0;
        int count = 0;
        for (int i = 0; i < n; i++) {
            total += nums[i];
            count += (i * nums[i]);
        }
        int max = count;

        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i] = new int[]{nums[i], total - nums[i]};
        }

        for (int i = 0; i < n; i++) {
            int[] p = pairs[i];
            count = count + p[0] * (n - 1) - p[1];
            max = Math.max(max, count);
        }

        return max;
    }
}