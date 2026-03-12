package code.my;

public class Solution {
    public static void main(String[] args) {
        // int[][] nums = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        int[][] nums = { { 3 }, { 2 } };
        System.out.println(new Solution().findDiagonalOrder(nums));
    }

    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] result = new int[m * n];
        int index = 0;
        boolean flag = true; // true - 从左下向右上；false - 从右上向左下
        for (int total = 0; total < m + n - 1; total++) {
            if (flag) {
                int bottom = total < m ? total : m - 1;
                int left = total - bottom;
                for (; left < n && bottom >= 0; left++, bottom--) {
                    result[index] = mat[bottom][left];
                    index++;
                }
            } else {
                int right = total < n ? total : n - 1;
                int top = total - right;
                for (; right >= 0 && top < m; right--, top++) {
                    result[index] = mat[top][right];
                    index++;
                }
            }
            flag = !flag;
        }
        return result;
    }
}