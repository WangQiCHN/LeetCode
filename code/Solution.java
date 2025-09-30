// package code;

import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] values = {1, 3, 1, 4, 1, 5};
        System.out.println(solution.minScoreTriangulation(values));
    }
    public int minScoreTriangulation(int[] values) {
        Arrays.sort(values);
        int len = values.length, total = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < len - 2; i++) {
            for (int j = i + 1; j < len - 1; j++) {
                for (int k = j + 1; k < len; k++) {
                    int cnt = values[i] * values[j] * values[k];
                    queue.offer(cnt);
                }
            }
        }

        for (int i = 0; i < len - 2; i++) {
            total += queue.poll();
        }

        return total;
    }

}