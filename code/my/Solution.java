package code.my;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public static void main(String[] args) {
        int[][] grid = { { 10, 5, 4, 5 } };
        boolean result = new Solution().canPartitionGrid(grid);
        System.out.println(result);
    }

    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        Map<Long, List<long[]>> locations = new HashMap<>();
        long[] preRowSums = new long[m];
        long[] preColSums = new long[n];
        long[][] top2Bottom = new long[m][2];
        long[][] left2Right = new long[n][2];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                long v = (long)grid[i][j];
                preRowSums[i] += v;
                preColSums[j] += v;
                if (!locations.containsKey(v)) {
                    locations.put(v, new ArrayList<>());
                }
                locations.get(v).add(new long[] { i, j });
            }
        }

        top2Bottom[0][0] = preRowSums[0];
        top2Bottom[m - 1][1] = preRowSums[m - 1];
        for (int i = 1; i < m; i++) {
            top2Bottom[i][0] = top2Bottom[i - 1][0] + preRowSums[i];
            top2Bottom[m - i - 1][1] = top2Bottom[m - i][1] + preRowSums[m - i - 1];
        }

        left2Right[0][0] = preColSums[0];
        left2Right[n - 1][1] = preColSums[n - 1];
        for (int j = 1; j < n; j++) {
            left2Right[j][0] = left2Right[j - 1][0] + preColSums[j];
            left2Right[n - j - 1][1] = left2Right[n - j][1] + preColSums[n - j - 1];
        }

        for (int i = 0; i < m - 1; i++) {
            if (top2Bottom[i][0] == top2Bottom[i + 1][1]) return true;
            else if (top2Bottom[i][0] > top2Bottom[i + 1][1]) {
                long v = top2Bottom[i][0] - top2Bottom[i + 1][1];
                if (locations.containsKey(v)) {
                    List<long[]> items = locations.get(v);
                    for (long[] point : items) {
                        long x = point[0]; 
                        long y = point[1];
                        if (x <= i) {
                            if (isGood(0, i, 0, n - 1, x, y)) return true;
                        }
                    }
                }
            } else {
                long v = top2Bottom[i + 1][1] - top2Bottom[i][0];
                if (locations.containsKey(v)) {
                    List<long[]> items = locations.get(v);
                    for (long[] point : items) {
                        long x = point[0]; 
                        long y = point[1];
                        if (x >= i + 1) {
                            if (isGood(i + 1, m - 1, 0, n - 1, x, y)) return true;
                        }
                    }
                }
            }
        }

        for (int j = 0; j < n - 1; j++) {
            if (left2Right[j][0] == left2Right[j + 1][1]) return true;
            else if (left2Right[j][0] > left2Right[j + 1][1]) {
                long v = left2Right[j][0] - left2Right[j + 1][1];
                if (locations.containsKey(v)) {
                    List<long[]> items = locations.get(v);
                    for (long[] point : items) {
                        long x = point[0]; 
                        long y = point[1]; 
                        if (y <= j) {
                            if (isGood(0, m - 1, 0, j, x, y)) return true;
                        }
                    }
                }
            } else {
                long v = left2Right[j + 1][1] - left2Right[j][0];
                if (locations.containsKey(v)) {
                    List<long[]> items = locations.get(v);
                    for (long[] point : items) {
                        long x = point[0]; 
                        long y = point[1];
                        if (y >= j + 1) {
                            if (isGood(0, m - 1, j + 1, n - 1, x, y)) return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    private boolean isGood(long top, long bottom, long left, long right, long x, long y) {
        if (top == bottom) {
            if (y > left && y < right) return false;
        } else if (left == right) {
            if (x > top && x < bottom) return false;
        }
        return true;
    }
}