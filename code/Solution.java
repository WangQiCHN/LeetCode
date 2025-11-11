package code;

public class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = { 4, 4, 4, 4 };
        int r = 0;
        int k = 3;
        long result = sol.maxPower(nums, r, k);
        System.out.println(result);
    }

    public long maxPower(int[] stations, int r, int k) {
        int n = stations.length;
        long[] preSums = new long[n + 1];
        long lo = -1L, hi = 0;
        for (int i = 0; i < n; i++) {
            long s = (long) stations[i];
            if (lo == -1L || lo > s) {
                lo = s;
            }
            hi += s;
            int left = Math.max(0, i - r);
            int right = Math.min(n, i + r + 1);
            preSums[left] += s;
            preSums[right] -= s;
        }
        hi += k;

        long result = 0;
        while (lo <= hi) {
            long m = (lo + hi) >> 1;
            if (check(preSums, m, r, k)) {
                result = m;
                lo = m + 1;
            } else {
                hi = m - 1;
            }
        }

        return result;
    }

    private boolean check(long[] preSums, long v, int r, int k) {
        long[] diff = preSums.clone();
        int n = diff.length - 1;
        long sum = 0;
        long remain = (long) k;
        for (int i = 0; i < n; i++) {
            long d = diff[i];
            sum += d;
            if (sum < v) {
                if (sum + remain >= v) {
                    long sub = v - sum;
                    sum = v;
                    remain -= sub;
                    int end = Math.min(n, i + 2 * r + 1);
                    diff[end] -= sub;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}