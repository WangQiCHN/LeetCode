package demo;

public class Demo {
    private int[][] memo;
    public static void main(String[] args) {
        Demo d = new Demo();
        int k = 3; 
        int n = 14;
        System.out.println("Minimum number of moves: " + d.superEggDrop(k, n));
    }

    public int superEggDrop(int k, int n) {
        memo = new int[k + 1][n + 1];

        return calculate(k, n);
    }

    private int calculate(int K, int N) {
        int m = 0;
        while (memo[K][m] < N) {
            m++;
            for (int i = 1; i <= K; i++)
                memo[i][m] = memo[i][m - 1] + memo[i - 1][m - 1] + 1;
        }

        return m;
    }

}