package code;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] power = {1,1,3,4};
        System.out.println(sol.maximumTotalDamage(power));
    }
    public long maximumTotalDamage(int[] power) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : power) {
            cnt.merge(x, 1, Integer::sum); // cnt[x]++
        }

        int n = cnt.size();
        int[] a = new int[n];
        int k = 0;
        for (int x : cnt.keySet()) {
            a[k++] = x;
        }
        Arrays.sort(a);
        
        long[] f = new long[n + 1];
        int j = 0;
        for (int i = 0; i < n; i++) {
            int x = a[i];
            while (a[j] < x - 2) {
                j++;
            }
            System.out.println("i=" + i + ", j=" + j + ", x=" + x + ", cnt=" + x * cnt.get(x));
            f[i + 1] = Math.max(f[i], f[j] + (long) x * cnt.get(x));
        }
        return f[n];
    }
}