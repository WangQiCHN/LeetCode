package first;
class BurstBalloons {
    public static void main() {
        BurstBalloons b = new BurstBalloons();
        int[] nums = {3,1,5,8};
        int result = b.maxCoins(nums);
        System.out.println(result);
    }
    public int maxCoins(int[] nums) {
        int n = nums.length;

        int[] points = new int[n + 2];
        points[0] = points[n + 1] = 1;
        for(int i = 1; i <= n; i++) {
            points[i] = nums[i - 1];
        }

        int[][] dps = new int[n + 2][n + 2];
        for (int i = n; i >= 0; i--) {
            for (int j = i + 1; j < n + 2; j++) {
                for (int k = i + 1; k < j; k++) {
                    System.out.println(i + "," + j + ":" + points[k]);
                    dps[i][j] = Math.max(
                        dps[i][j],
                        dps[i][k] + dps[k][j] + points[i]*points[j]*points[k]
                    );
                }
            }
        }

        return dps[0][n+1];
    }
}