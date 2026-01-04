package code;

import java.util.Map;
import java.util.HashMap;

class Solution {
    static final int MOD = 1000000007;

    public static void main(String[] argvs) {
        Solution sol = new Solution();
        int[] nums = {1,2,3,4,5,6,7,8,9,10};
        int ans = sol.sumFourDivisors(nums);
        System.out.println(ans);
    }

    public int sumFourDivisors(int[] nums) {
        int count = 0;
        Map<Integer, Integer> dict = new HashMap<>();
        for (int n : nums) {
            if (dict.containsKey(n)) {
                count += dict.get(n);
                continue;
            }
            int sqrt = (int)Math.sqrt(n);
            int v = isFour(n, sqrt);
            if (v != 0) {
                count += v;
            }
            dict.put(n, v);
        }

        return count;
    }

    private int isFour(int n , int sqrt) {
        int size = 0;
        int total = n + 1;
        for (int i = 2; i <= sqrt; i++) {
            if (n % i == 0) {
                if (n / i == i) return 0;
                size += 2;
                total += (n / i + i);
                if (size > 2) return 0;
            }
        }

        return size == 2 ? total : 0;
    }
}