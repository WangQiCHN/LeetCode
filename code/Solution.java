package code;

class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int a = 2;
        int[] b = { 1, 3 };
        int result = sol.superPow(a, b);
        System.out.println(result);
    }

    // private static final int MOD = 1337;

    public int superPow(int a, int[] b) {
        int ans = 1;
        for (int e : b) {
            ans = (int) ((long) pow(ans, 10) * pow(a, e));
        }
        return ans;
    }

    public int pow(int x, int n) {
        int res = 1;
        while (n != 0) {
            if (n % 2 != 0) {
                res = (int) ((long) res * x);
            }
            x = (int) ((long) x * x);
            n /= 2;
        }
        return res;
    }
}