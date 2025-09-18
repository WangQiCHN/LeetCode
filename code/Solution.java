package code;

class Solution {
    void main() {
        int[][] q = {{2, 6}};
        System.out.println(minOperations(q));
    }
    public long minOperations(int[][] queries) {
        long ans = 0;
        for (int[] q : queries) {
            int l = q[0], r = q[1];
            long s = f(r) - f(l - 1);
            long mx = f(r) - f(r - 1);
            ans += Math.max((s + 1) / 2, mx);
        }
        return ans;
    }

    public long f(long x) {
        long res = 0;
        long p = 1;
        int i = 1;
        while (p <= x) {
            long cnt = Math.min(p * 4 - 1, x) - p + 1;
            res += cnt * i;
            i++;
            p *= 4;
        }
        return res;
    }
}