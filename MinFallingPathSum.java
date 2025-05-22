import java.util.HashMap;

public class MinFallingPathSum {
    private HashMap<Integer, Integer> memo = new HashMap<>();
    public static void main() {
        MinFallingPathSum m = new MinFallingPathSum();
        int[][] matrix = {{2,1,3},{6,5,4},{7,8,9}};
        int result = m.minFallingPathSum(matrix);
        System.out.println(result);
    }
    public int minFallingPathSum(int[][] matrix) {
        int minSum = Integer.MAX_VALUE;
        if (matrix.length == 1) {
            return matrix[0][0];
        }
        for (int i = 0; i < matrix.length; i++) {
            int v = nextStep(matrix, i, 1) + matrix[0][i];
            if (v < minSum) {
                minSum = v;
            }
        }

        return minSum;
    }

    private int nextStep(int[][] matrix, int col, int row) {
        if (memo.containsKey(row * 101 + col)) {
            return memo.get(row * 101 + col);
        }
        int minSum = Integer.MAX_VALUE;
        if (row == matrix.length - 1) {
            if (col - 1 >= 0) {
                int v = matrix[row][col - 1];
                if (minSum > v) {
                    minSum = v;
                }
            }
            if (col + 1 < matrix.length) {
                int v = matrix[row][col + 1];
                if (minSum > v) {
                    minSum = v;
                }
            }
            int v = matrix[row][col];
            if (minSum > v) {
                minSum = v;
            }
            return minSum;
        }

        if (col - 1 >= 0) {
            int v = nextStep(matrix, col - 1, row + 1) + matrix[row][col - 1];
            if (minSum > v) {
                minSum = v;
            }
        }
        if (col + 1 < matrix.length) {
            int v = nextStep(matrix, col + 1, row + 1) + matrix[row][col + 1];
            if (minSum > v) {
                minSum = v;
            }
        }
        int v = nextStep(matrix, col, row + 1) + matrix[row][col];
        if (minSum > v) {
            minSum = v;
        }
        memo.put(row * 101 + col, minSum);
        return minSum;
    }
}
