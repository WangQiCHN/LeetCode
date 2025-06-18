package demo;

public class Demo {
    public static void main(String[] args) {
        Demo d = new Demo();
        int[] nums = {3, 1, 5, 8};
        System.out.println("Maximum coins: " + d.maxCoins(nums));
    }

    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] points = new int[n + 2];
        int[][] dps = new int[n + 2][n + 2];

        points[0] = points[n + 1] = 1;
        for (int i = 1; i <= n; i++) {
            points[i] = nums[i - 1];
        }

        for (int i = n; i >= 0; i--) {
            for (int j = i + 2; j <= n + 1; j++) {
                for (int k = i + 1; k < j; k++) {
                    int temp1 = dps[i][k];
                    int temp2 = dps[k][j];
                    int temp3 = points[i] * points[k] * points[j];
                    int temp = temp1 + temp2 + temp3;
                    dps[i][j] = Math.max(dps[i][j], temp);
                }
            }
        }

        return dps[0][n + 1];
    }

}