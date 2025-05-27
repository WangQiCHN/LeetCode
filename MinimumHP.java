import java.util.Arrays;

public class MinimumHP {
    public static void main() {
        MinimumHP m = new MinimumHP();
        // int[][] dungeon = {
        //     {-2,-3,3},
        //     {-5,-10,1},
        //     {10,30,-5}
        // };

        int[][] dungeon = {{1,-3,3},{0,-2,0},{-3,-3,-3}};
        int result = m.calculateMinimumHP(dungeon);
        System.out.println(result);
    }
    // public int calculateMinimumHP(int[][] dungeon) {
    //     int m = dungeon.length;
    //     int n = dungeon[0].length;

    //     NodeStep[][] cost = new NodeStep[m][n];

    //     for (int i = 0; i < m; i++) {
    //         for (int j = 0; j < n; j++) {
    //             int v = dungeon[i][j];
    //             if (i == 0 && j == 0) {
    //                 if (v >= 0) {
    //                     cost[i][j] = new NodeStep(1 + v, 1);
    //                 } else {
    //                     cost[i][j] = new NodeStep(1, 1 - v);
    //                 }
    //             } else if (i == 0 && j != 0) {
    //                 NodeStep pre = cost[i][j - 1];
    //                 cost[i][j] = generateNode(pre, v);
    //             } else if (i != 0 && j == 0) {
    //                 NodeStep pre = cost[i - 1][j];
    //                 cost[i][j] = generateNode(pre, v);
    //             } else {
    //                 NodeStep pre1 = cost[i - 1][j];
    //                 NodeStep pre2 = cost[i][j - 1];
                    
    //                 NodeStep current1 = generateNode(pre1, v);
    //                 NodeStep current2 = generateNode(pre2, v);

    //                 if (current1.least < current2.least) {
    //                     cost[i][j] = current1;
    //                 } else {
    //                     cost[i][j] = current2;
    //                 }
    //             }
    //         }
    //     }

    //     return cost[m - 1][n - 1].least;
    // }

    // private NodeStep generateNode(NodeStep pre, int v) {
    //     if (v >= 0) {
    //         return new NodeStep(pre.current + v, pre.least);
    //     } else {
    //         if (pre.current + v > 0) {
    //             return new NodeStep(pre.current + v, pre.least);
    //         } else {
    //             return new NodeStep(1, pre.least - pre.current - v + 1);
    //         }
    //     }
    // }

    private int[][] memo;
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;

        memo = new int[m][n];

        for(int [] row : memo) {
            Arrays.fill(row, -1);
        }

        return dp(dungeon, 0, 0);
    }

    private int dp(int[][] grid, int i, int j) {
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
            dp(grid, i, j + 1),
            dp(grid, i + 1, j)
        ) - grid[i][j];

        memo[i][j] = res <= 0 ? 1 : res;

        return memo[i][j];
    }
}

// private class NodeStep {
//     public int current;
//     public int least;

//     public NodeStep(int current, int least) {
//         this.current = current;
//         this.least = least;
//     }
// }