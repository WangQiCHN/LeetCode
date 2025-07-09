package demo;

class Demo {
    public static void main(String[] args) {
        Demo demo = new Demo();
        int k = 2;
        int n = 6;
        int result = demo.superEggDrop(k, n);
        System.out.println(result); // Output: 2
    }

    public int superEggDrop(int k, int n) {
        int[][] dps = new int[k + 1][n + 1];

        for (int i = 1; i <= k; i++) {
            dps[i][1] = 1; // 1 floor requires 1 attempt
        }
        for (int j = 1; j <= n; j++) {
            dps[1][j] = j; // 1 egg requires j attempts for
        }

        for (int i = 2; i <= k; i++) {
            for (int j = 2; j <= n; j++) {
                for (int x = 1; x <= j; x++) {
                    int temp = 1 + Math.max(dps[i - 1][x - 1], dps[i][j - x]);
                    if (dps[i][j] == 0 || temp < dps[i][j]) {
                        dps[i][j] = temp;
                    }
                }
            }
        }

        return dps[k][n];
    }
}
