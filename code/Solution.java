package code;

public class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] grid = { { 5, 2, 4 }, { 3, 0, 5 }, { 0, 7, 2 } };
        int k = 3;
        int result = sol.numberOfPaths(grid, k);
        System.out.println(result);
    }

    private static final int MOD = 1000000007;

    public int numberOfPaths(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        long[][][] dp = new long[m + 1][n + 1][k];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 && j == 1) {
                    dp[i][j][grid[0][0] % k] = 1;
                    continue;
                }

                int value = grid[i - 1][j - 1] % k;
                for (int r = 0; r < k; r++) {
                    int prevMod = (r - value + k) % k;
                    dp[i][j][r] = (dp[i - 1][j][prevMod] + dp[i][j - 1][prevMod]) % MOD;
                }
            }
        }

        return (int) dp[m][n][0];
    }
}