package code.my;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int poured = 2, query_row = 1, query_glass = 1;
        System.out.println(solution.champagneTower(poured, query_row, query_glass));
    }
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[] row = new double[] { poured };
        for (int i = 1; i <= query_row; i++) {
            double[] next = new double[i + 1];
            for (int j = 0; j < i; j++) {
                double volumn = row[j];
                if (row[j] > 1) {
                    next[j] += (volumn - 1) / 2;
                    next[j + 1] += (volumn - 1) / 2;
                }
            }
            row = next;
        }
        return Math.min(1, row[query_glass]);

    }
}