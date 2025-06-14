import java.util.Arrays;

public class DungeonGame {
    public static void main(String[] args) {
        DungeonGame game = new DungeonGame();
        int[][] dungeon = {
            {-2, -3, 3},
            {-5, -10, 1},
            {10, 30, -5}
        };
        int result = game.calculateMinimumHP(dungeon);
        System.out.println(result); // Output: 7
    }
    private int[][] memo;
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;

        memo = new int[m][n];
        for (int[] me : memo) {
            Arrays.fill(me, -1);
        }

        return calculate(dungeon, 0, 0);
    }

    private int calculate(int[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;

        if (i == m - 1 && j == n - 1) {
            return grid[i][j] >= 0 ? 1 : -grid[i][j] + 1;
        }

        if (i == m || j == n) {
            return Integer.MAX_VALUE;
        }

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        int res = Math.min(
            calculate(grid, i + 1, j),
            calculate(grid, i, j + 1)
        ) - grid[i][j];

        memo[i][j] = (res <= 0 ? 1 : res);

        return memo[i][j];
    }
}