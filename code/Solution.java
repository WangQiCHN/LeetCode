package code;

public class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] nums = { {1,1,2,2}, {0,0,1,1} };
        int n = 3;
        int[][] result = sol.rangeAddQueries(n, nums);
        System.out.println(result);
    }
    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] preSum = new int[n + 2][n + 2];
        for (int[] q : queries) {
            int row1 = q[0];
            int col1 = q[1];
            int row2 = q[2];
            int col2 = q[3];

            preSum[row1 + 1][col1 + 1] += 1;
            preSum[row2 + 2][col1 + 1] -= 1;
            preSum[row1 + 1][col2 + 2] -= 1;
            preSum[row2 + 2][col2 + 2] += 1;
        }

        return calcuate(preSum, n + 1);
    }

    private int[][] calcuate(int[][] preSum, int N) {
        int[][] result = new int[N - 1][N - 1];
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                preSum[i][j] += (preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1]);
            }
        }

        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N - 1; j++) {
                result[i][j] = preSum[i + 1][j + 1];
            }
        }

        return result;
    }
}