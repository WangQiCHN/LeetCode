package demo;

class Demo {
    public static void main(String[] args) {
        Demo demo = new Demo();
        String answer = demo.getPermutation(3, 3);
        System.out.println(answer);
    }

    private boolean[] visited;
    private StringBuilder sb = new StringBuilder();
    public String getPermutation(int n, int k) {
        if (n == 1) return "1";
        visited = new boolean[n + 1];
        getIndex(n, n, k - 1);

        return sb.toString();
    }

    private void getIndex(int total, int n, int k) {
        if (n == 1) {
            for (int i = 1; i <= total; i++) {
                if (visited[i]) continue;
                else {
                    sb.append(i);
                    visited[i] = true;
                }
            }
            return;
        }
        int f = factorial(n - 1);
        int r = k / f;
        int t = r * f;
        for (int i = 1; i <= total; i++) {
            if (visited[i]) continue;
            else {
                if (r == 0) {
                    sb.append(i);
                    visited[i] = true;
                    getIndex(total, n - 1, k - t);
                } else {
                    r--;
                }
            }
        }
    }

    private int factorial(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
