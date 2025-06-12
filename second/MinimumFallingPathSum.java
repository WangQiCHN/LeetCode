import java.util.Arrays;

public class MinimumFallingPathSum {
    public static void main() {
        MinimumFallingPathSum m = new MinimumFallingPathSum();
        int[][] matrix = {{2,1,3},{6,5,4},{7,8,9}};
        int result = m.minFallingPathSum(matrix);
        System.out.println(result);
    }

    // private int[][] memo;
    // private static final int MAX = 10001;
    // public int minFallingPathSum(int[][] matrix) {
    //     int min = MAX;
    //     int n = matrix.length;
    //     if (n == 1) {
    //         return matrix[0][0];
    //     }
    //     memo = new int[n][n];
    //     for (int i = 0; i < n; i++) {
    //         Arrays.fill(memo[i], MAX);
    //     }
    //     for (int i = 0; i < n; i++) {
    //         int value = matrix[0][i] + min(
    //             calculate(matrix, n, 1, i - 1),
    //             calculate(matrix, n, 1, i),
    //             calculate(matrix, n, 1, i + 1)
    //         );
    //         if (value < min) {
    //             min = value;
    //         }
    //     }

    //     return min;
    // }

    // private int calculate(int[][] matrix, int n, int i, int j) {
    //     if (i < 0 || i == n) return MAX;
    //     if (j < 0 || j == n) return MAX;

    //     if (memo[i][j] != MAX) {
    //         return memo[i][j];
    //     }
    //     if (i == n - 1) {
    //         return matrix[i][j];
    //     }
    //     int value = matrix[i][j] + min(
    //         calculate(matrix, n, i + 1, j - 1),
    //         calculate(matrix, n, i + 1, j),
    //         calculate(matrix, n, i + 1, j + 1)
    //     );

    //     memo[i][j] = value;
    //     return value;
    // }

    // private int min(int a, int b, int c) {
    //     if (c <= a && c <= b) return c;
    //     else if (a <= b && a <= c) return a;
    //     else return b;
    // }
    private int[][] memo;
    private static final int MAX = 1000001;
    public int minFallingPathSum(int[][] matrix) {
        int min = MAX;
        int n = matrix.length;
        if (n == 1) {
            return matrix[0][0];
        }
        memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], MAX);
        }
        for (int i = 0; i < n; i++) {
            int value = matrix[0][i] + min(
                calculate(matrix, n, 1, i - 1),
                calculate(matrix, n, 1, i),
                calculate(matrix, n, 1, i + 1)
            );
            if (value < min) {
                min = value;
            }
        }

        return min;
    }

    private int calculate(int[][] matrix, int n, int i, int j) {
        if (i < 0 || i == n) return MAX;
        if (j < 0 || j == n) return MAX;

        if (memo[i][j] != MAX) {
            return memo[i][j];
        }
        if (i == n - 1) {
            return matrix[i][j];
        }
        int value = matrix[i][j] + min(
            calculate(matrix, n, i + 1, j - 1),
            calculate(matrix, n, i + 1, j), 
            calculate(matrix, n, i + 1, j + 1)
        );

        memo[i][j] = value;
        return value;
    }

    private int min(int a, int b, int c) {
        // 13, 12, 12, 竟然选择了13，因为12和12相等，所以不满足条件 所以需要等于号！
        // return Math.min(a, Math.min(b, c));
        if (c <= a && c <= b) return c;
        else if (b <= a && b <= c) return b;
        else return a;
    }
}