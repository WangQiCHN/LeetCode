import java.util.Arrays;

class Solution {
    public static final int INIT_VALUE = -666;
    public static void main() {
        int[] coins = {1,2,5};
        int amount = 100;
        int num = coinChange(coins, amount);
        System.out.println(num);
    }
    public static int[] memo;
    public static int coinChange(int[] coins, int amount) {
        memo = new int[amount + 1];
        Arrays.fill(memo, INIT_VALUE);
        return dp(coins, amount);
    }
    public static int dp(int[] coins, int amount) {
        if (amount == 0) return 0;
        else if (amount < 0) return -1;
        if (memo[amount] != INIT_VALUE) return memo[amount];

        int minDepth = -1;
        int size = coins.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            int num = dp(coins, amount - coins[i]);
            if (num == -1) {
                continue;
            } else {
                if (num < min) {
                    min = num;
                }
            }
        }
        if (min != Integer.MAX_VALUE) {
            minDepth = min + 1;
        }
        memo[amount] = minDepth;
        return minDepth;
    }
}