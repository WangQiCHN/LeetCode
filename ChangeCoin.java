public class ChangeCoin {
    public static void main() {
        ChangeCoin c = new ChangeCoin();
        int size = c.change(5, new int[] {1,2,5});
        System.out.println(size);
    }
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] dps = new int[n + 1][amount + 1];

        for (int i = 0; i <= n; i++) {
            dps[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j - coins[i - 1] >= 0) {
                    dps[i][j] = dps[i - 1][j] + dps[i][j - coins[i - 1]];
                } else {
                    dps[i][j] = dps[i - 1][j];
                }
            }
        }

        print(dps, n + 1, amount + 1);
        return dps[n][amount];
    }

    private void print(int[][] array, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(array[i][j] + ",");
            }
            System.out.println();
        }
        // dp[i][j]的意思是在i种银币时，要达到j个值，需要多少种可能
    }
}
