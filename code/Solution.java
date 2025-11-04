package code;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] matrix = {{1,3,5},{6,7,12},{11,14,14}};
        int k = 6;
        int count = s.kthSmallest(matrix, k);

        System.out.println(count);
    }
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        PriorityQueue<Item> queue = new PriorityQueue<>((a, b) -> {
            if (a.v == b.v) return a.x - b.x;
            else return a.v - b.v;
        });
        queue.offer(new Item(0, 0, matrix[0][0]));
        visited[0][0] = true;

        while (k >= 1) {
            Item item = queue.poll();
            if (k == 1) return item.v;
            else {
                int x = item.x;
                int y = item.y;
                if (x < m - 1 && !visited[x + 1][y]) {
                    queue.offer(new Item(x + 1, y, matrix[x + 1][y]));
                    visited[x + 1][y] = true;
                }
                if (y < n - 1 && !visited[x][y + 1]) {
                    queue.offer(new Item(x, y + 1, matrix[x][y + 1]));
                    visited[x][y + 1] = true;
                }
            }
            k--;
        }

        return 0;
    }
}

class Item {
    int x;
    int y;
    int v;

    public Item(int x, int y, int v) {
        this.x = x;
        this.y = y;
        this.v = v;
    }
}