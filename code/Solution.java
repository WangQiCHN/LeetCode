package code;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 5};
        int n = 20;
        System.out.println(solution.minPatches(nums, n));
    }
    public int minPatches(int[] nums, int n) {
        int ans = 0, i = 0;
        long s = 1;
        while (s <= n) {
            System.out.println(s);
            if (i < nums.length && nums[i] <= s) {
                s += nums[i++];
            } else {
                s *= 2; // 必须添加 s
                ans++;
            }
        }
        return ans;
    }
}