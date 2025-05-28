import java.util.Arrays;

public class SuperEggDrop {
    public static void main() {
        SuperEggDrop s = new SuperEggDrop();
        int k = 2;
        int n = 6;
        int result = s.superEggDrop(k, n);
        System.out.println(result);
    }
    // public int superEggDrop(int k, int n) {
    //     int[][] memo = new int[k + 1][n + 1];
    //     for (int[] m : memo) {
    //         Arrays.fill(m, -1000);
    //     }

    //     for (int i = 0; i < k + 1; i++) {
    //         memo[i][0] = 0;
    //     }

    //     for (int j = 0; j < n + 1; j++) {
    //         memo[0][j] = 0;
    //     }
        
    //     int m = 0;
    //     while (memo[k][m] < n) {
    //         m++;
    //         for (int K = 1; K <= k; K++) {
    //             memo[K][m] = memo[K][m - 1] + memo[K - 1][m - 1] + 1;
    //         }
    //     }

    //     return m;
    // }
    private int[][] memo;
    public int superEggDrop(int k, int n) {
        memo = new int[k + 1][n + 1];
        for (int[] m : memo) {
            Arrays.fill(m, -1000);
        }
        
        return calculate(k, n);
    }

    private int calculate(int K, int N) {
        if (K == 1) return N;
        if (N == 0) return 0;

        if (memo[K][N] != -1000) {
            return memo[K][N];
        }

        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            int ok = calculate(K, N - i);
            int broken = calculate(K - 1, i - 1);
            min = Math.min(
                min,
                Math.max(
                    ok,
                    broken                    
                ) + 1
            );
        }

        memo[K][N] = min;
        return min;
    }
}
