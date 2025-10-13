package code;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.maximumTotalDamage(new int[]{7,1,6,6}));
        // System.out.println(s.maximumTotalDamage(new int[]{1, 1, 1, 1}));
    }
    public long maximumTotalDamage(int[] power) {
        Map<Integer, Integer> buffer = new HashMap<>();
        for (int p : power) {
            buffer.merge(p, 1, Integer::sum);
        }

        int n = buffer.size();
        int[] sums = new int[n];
        int i = 0;
        for (int b : buffer.keySet()) {
            sums[i++] = b;
        }

        Arrays.sort(sums);

        long[] f = new long[n + 1];
        int j = 0;
        i = 1;
        while (i < n + 1) {
            int x = sums[j];
            if (x < sums[i - 1] - 2) {
                j++;
            }
            f[i] = Math.max(f[j] + sums[i - 1] * buffer.get(sums[i - 1]), f[i - 1]);
            if (x >= sums[i - 1] - 2) {
                i++;
            }
        }

        return f[n];
    }
}