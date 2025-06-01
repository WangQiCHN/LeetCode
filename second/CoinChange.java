import java.util.Arrays;

class Solution {
    public static void main() {
        int[] coins = {1,2147483647};
        Solution s = new Solution();
        int result = s.coinChange(coins, 2);
        System.out.println(result);
    }
    private int[] dps;
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        dps = new int[amount + 1];
        Arrays.fill(dps, -666);

        return calculate(coins, amount);
    }

    private int calculate(int[] nums, int amount) {
        if (amount == 0) return 0;

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int v = nums[i];
            int result;
            if (amount - v < 0) {
                continue;
            }
            if (dps[amount - v] != -666) {
                result = dps[amount - v];
            } else {
                result = calculate(nums, amount - v);
            }
            if (result != -1) {
                min = Math.min(min, result + 1);
            }
        }

        dps[amount] = (min == Integer.MAX_VALUE ? -1 : min);
        return dps[amount];
    }
}