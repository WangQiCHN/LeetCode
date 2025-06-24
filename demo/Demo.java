package demo;

import java.util.Arrays;

public class Demo {

    public static void main() {
        Demo d = new Demo();
        int[] coins = {1, 2, 5};
        int amount = 11;
        // int[] nums1 = {1, 2};
        int result = d.coinChange(coins, amount);
        System.out.println(result);
    }


    public int coinChange(int[] coins, int amount) {
        int[] dps = new int[amount + 1];
        Arrays.fill(dps, amount + 1);
        dps[0] = 0;
        for (int i = 0; i < dps.length; i++) {
            for (int c : coins) {
                if (i - c < 0) {
                    continue;
                }
                dps[i] = Math.min(dps[i], 1 + dps[i - c]);
            }
        }

        return (dps[amount] == amount + 1) ? -1 : dps[amount];
    }
}