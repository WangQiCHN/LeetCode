package demo;


public class Demo {
    public static void main() {
        Demo d = new Demo();
        int[] nums = {3, 1, 5, 8};
        System.out.println(d.maxCoins(nums));
    }

    int[][] memo;
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] points = new int[n + 2];
        points[0] = points[n + 1] = 1;
        for(int i = 1; i < n + 1; i++) {
            points[i] = nums[i - 1];
        }

        memo = new int[n + 2][n + 2];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j <= n + 1; j++) {
                for(int k = i + 1; k < j; k++) {
                    memo[i][j] = Math.max(
                        memo[i][j],
                        memo[i][k] + memo[k][j] + points[i] * points[k] * points[j]
                    );
                }
            }
        }

        return memo[0][n + 1];
    }
}