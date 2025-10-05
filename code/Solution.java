package code;

class Solution {
    public static void main(String[] args) {
        int[][] matrix = {{7,7,5},{2,4,6},{8,2,0}};
        System.out.println(new Solution().longestIncreasingPath(matrix));
    }
    private int[][] memo;
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        memo = new int[m][n];
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int route = calculate(matrix, i, j, 0);
                max = Math.max(max, route);
            }
        }

        return max;
    }

    private int calculate(int[][] matrix, int i, int j, int preV) {
        if (i < 0 || j < 0 || i == matrix.length || j == matrix[0].length) {
            return 0;
        } else {
            int cnt = matrix[i][j];
            if (cnt > preV) {
                if (memo[i][j] != 0) return memo[i][j];
                else {
                    int v1 = calculate(matrix, i + 1, j, cnt);
                    int v2 = calculate(matrix, i - 1, j, cnt);
                    int v3 = calculate(matrix, i, j + 1, cnt);
                    int v4 = calculate(matrix, i, j - 1, cnt);
                    int v = Math.max(v1, Math.max(v2, Math.max(v3, v4)));
                    memo[i][j] = 1 + v;
                    return memo[i][j];
                }
            } else {
                return 0;
            }
        }
    }
}