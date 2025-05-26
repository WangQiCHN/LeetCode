public class MinPathSum {

    public static void main() {
        MinPathSum m = new MinPathSum();
        int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
        int result = m.minPathSum(grid);
        System.out.println(result);
    }
    
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] cost = new int[m][n];

        int sum = 0;
        for (int i = 0; i < m; i++) {
            cost[i][0] = sum + grid[i][0];
            sum = cost[i][0];
        }

        sum = 0;
        for (int j = 0; j < n; j++) {
            cost[0][j] = sum + grid[0][j];
            sum = cost[0][j];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                cost[i][j] = Math.min(
                    cost[i - 1][j],
                    cost[i][j - 1]
                ) + grid[i][j];
            }
        }

        return cost[m - 1][n - 1];
    }
}
