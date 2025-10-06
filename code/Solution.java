package code;

class Solution {
    public static void main(String[] args) {
        int[][] grid = {{0,2},{1,3}};
        System.out.println(new Solution().swimInWater(grid));
    }
    private boolean[][] visited;
    public int swimInWater(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];
        visited = new boolean[m][n];
        visited[0][0] = true;
        calculate(grid, dp, 1, 0, m, n);
        calculate(grid, dp, 0, 1, m, n);
        

        return dp[m - 1][n - 1];
    }

    private int calculate(int[][] grid, int[][] dp, int i, int j, int m, int n) {
        if (i == m || j == n || i < 0 || j < 0) return Integer.MAX_VALUE;
        else if (visited[i][j]) return Integer.MAX_VALUE;
        else if (i == m - 1 && j == n - 1) {
            dp[i][j] = grid[i][j];
            return dp[i][j];
        }
        else {
            if (dp[i][j] != 0) return dp[i][j];
            visited[i][j] = true;
            int v1 = calculate(grid, dp, i - 1, j, m, n);
            int v2 = calculate(grid, dp, i + 1, j, m, n);
            int v3 = calculate(grid, dp, i, j - 1, m, n);
            int v4 = calculate(grid, dp, i, j + 1, m, n);
            int v5 = grid[i][j];
            visited[i][j] = false;
            dp[i][j] = Math.min(v1, Math.min(v2, Math.min(v3, Math.min(v4, v5))));

            return dp[i][j];
        }
        // else {
        //     if (dp[i][j] == 0) {
        //         int v1 = calculate(grid, dp, i + 1, j, m, n);
        //         int v2 = calculate(grid, dp, i, j + 1, m, n);
        //         int v3 = grid[i][j];
        //         dp[i][j] = Math.max(v1, Math.max(v2, v3));
        //     }
        //     return dp[i][j];
        // }

    }
}