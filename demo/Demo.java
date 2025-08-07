public class Demo {
    void main() {
        Demo d = new Demo();
        // int[][] matrix = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        // int[][] matrix = {{1,4},{2,5}};
        int[][] matrix = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
        // int target = 20;
        int target = 15;
        boolean result = d.searchMatrix(matrix, target);
        System.out.println(result);

    }
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        return search(matrix, target, 0, 0, m - 1, n - 1);
    }

    private boolean search(int[][] matrix, int target, int si, int sj, int ei, int ej) {
        if (si > ei || sj > ej) return false;
        if (si == ei) {
            for (int j = 0; j <= ej; j++) {
                if (matrix[si][j] == target) return true;
            }
            // return false; 
        }
        if (sj == ej) {
            for (int i = 0; i <= ei; i++) {
                if (matrix[i][sj] == target) return true;
            }
            // return false;
        }
        if (sj == ej || si == ei) return false;
        int v = matrix[(si + ei) / 2][(sj + ej) / 2];
        if (v == target) return true;
        else if (v > target) {
            return search(matrix, target, si, sj, (si + ei) / 2, (sj + ej) / 2);
        } else {
            return search(matrix, target, (si + ei) / 2 + 1, (sj + ej) / 2 + 1, ei, ej);
        }
    }
}
