package demo;

import java.util.Arrays;

public class Demo {
    public static void main() {
        Demo d = new Demo();
        int[] nums = {1,2,5};
        int amount = 5;
        System.out.println(d.change(amount, nums));
    }

    private int answer = 0;
    public int change(int amount, int[] coins) {
        if (amount == 0) return 1;
        Arrays.sort(coins);
        int n = coins.length;
        for (int i = 0; i < n; i++) {
            int v = coins[i];
            if (v > amount) {
                continue;
            }
            calculate(coins, amount - v, i);
        }
        return answer;
    }

    private void calculate(int[] coins, int amount, int start) {
        if (amount == 0) {
            answer++;
        }
        int n = coins.length;
        for (int i = start; i < n; i++) {
            int v = coins[i];
            if (v > amount) {
                continue;
            }
            calculate(coins, amount - v, i);
        }
    }
}