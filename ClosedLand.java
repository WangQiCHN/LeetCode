public class ClosedLand {
    public static void main() {
        int[][] grid = {
                { 0, 0, 1, 1, 0, 1, 0, 0, 1, 0 },
                { 1, 1, 0, 1, 1, 0, 1, 1, 1, 0 },
                { 1, 0, 1, 1, 1, 0, 0, 1, 1, 0 },
                { 0, 1, 1, 0, 0, 0, 0, 1, 0, 1 },
                { 0, 0, 0, 0, 0, 0, 1, 1, 1, 0 },
                { 0, 1, 0, 1, 0, 1, 0, 1, 1, 1 },
                { 1, 0, 1, 0, 1, 1, 0, 0, 0, 1 },
                { 1, 1, 1, 1, 1, 1, 0, 0, 0, 0 },
                { 1, 1, 1, 0, 0, 1, 0, 1, 0, 1 },
                { 1, 1, 1, 0, 1, 1, 0, 1, 1, 0 }
        };
        ClosedLand cl = new ClosedLand();
        int num = cl.closedIsland(grid);
        System.out.println(num);
    }

    public int closedIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int num = 0;
        for (int j = 0; j < n; j++) {
            if (grid[0][j] == 0) {
                dfs(grid, 0, j, m, n);
            }
        }
        for (int j = 0; j < n; j++) {
            if (grid[m - 1][j] == 0) {
                dfs(grid, m - 1, j, m, n);
            }
        }
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 0) {
                dfs(grid, i, 0, m, n);
            }
        }
        for (int i = 0; i < m; i++) {
            if (grid[i][n - 1] == 0) {
                dfs(grid, i, n - 1, m, n);
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    num++;
                    dfs(grid, i, j, m, n);
                }
            }
        }

        return num;
    }

    private void dfs(int[][] grid, int i, int j, int m, int n) {
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return;
        }
        if (grid[i][j] == 1) {
            return;
        }

        grid[i][j] = 1;
        dfs(grid, i - 1, j, m, n);
        dfs(grid, i + 1, j, m, n);
        dfs(grid, i, j - 1, m, n);
        dfs(grid, i, j + 1, m, n);
    }
}
