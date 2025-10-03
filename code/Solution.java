package code;

import java.util.PriorityQueue;

public class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] heightMap = {
            // {1, 4, 3, 1, 3, 2},
            // {3, 2, 1, 3, 2, 4},
            // {2, 3, 3, 2, 3, 1}
            {4, 4, 4, 4, 4, 4},
            {4, 3, 3, 3, 3, 3},
            {4, 4, 4, 4, 4, 4}
        };
        System.out.println(sol.trapRainWater(heightMap)); // Output: 4
    }
    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) {
            return 0;
        }
        int m = heightMap.length;
        int n = heightMap[0].length;
        if (m < 3 || n < 3) {  // Edge case: too small to trap water
            return 0;
        }

        // Min-heap: [height, i, j]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        boolean[][] visited = new boolean[m][n];

        // Add all border cells to heap
        // Top and bottom rows
        for (int j = 0; j < n; j++) {
            pq.offer(new int[]{heightMap[0][j], 0, j});
            pq.offer(new int[]{heightMap[m - 1][j], m - 1, j});
            visited[0][j] = true;
            visited[m - 1][j] = true;
        }
        // Left and right columns (exclude corners already added)
        for (int i = 1; i < m - 1; i++) {
            pq.offer(new int[]{heightMap[i][0], i, 0});
            pq.offer(new int[]{heightMap[i][n - 1], i, n - 1});
            visited[i][0] = true;
            visited[i][n - 1] = true;
        }

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};  // Up, down, left, right
        int water = 0;

        while (!pq.isEmpty()) {
            int[] cell = pq.poll();
            int h = cell[0];
            int x = cell[1];
            int y = cell[2];

            // Check all neighbors
            for (int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if (nx == 1 && ny == 1) {
                    System.out.println("Debug");
                }
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    // Trapped water = max(0, current water level - height)
                    water += Math.max(0, h - heightMap[nx][ny]);
                    // Push with effective height: max(own height, water level)
                    pq.offer(new int[]{Math.max(heightMap[nx][ny], h), nx, ny});
                }
            }
        }

        return water;
    }
}