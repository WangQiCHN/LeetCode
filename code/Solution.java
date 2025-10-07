package code;

import java.util.*;

class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        // int[][] grid = {{0,1,2,3,4},{24,23,22,21,5},{12,13,14,15,18},{11,17,18,19,20},{10,9,8,7,6}};
        int[][] grid = {{0,5},{5,4}};
        System.out.println(solution.swimInWater(grid)); // Output: 4
    }
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int[][] D = new int[][]{{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        PriorityQueue<int[]> PQ = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        PQ.add(new int[]{0, 0, grid[0][0]});
        grid[0][0] = -1;
        while (true) {
            int[] item = PQ.poll();
            int i = item[0];
            int j = item[1];
            int time = item[2];
            if (i == n - 1 && j == n - 1) return time;
            for (int[] d: D) {
                int x = i + d[0];
                int y = j + d[1];
                if (x == -1 || x == n) continue;
                if (y == -1 || y == n) continue;
                if (grid[x][y] == -1) continue;
                PQ.add(new int[]{x, y, Math.max(grid[x][y], time)});
                grid[x][y] = -1;
            }
        }
    }
}