package code;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] grid = { 8, 32, 31, 18, 34, 20, 21, 13, 1, 27, 23, 22, 11, 15, 30, 4, 2 };
        int p = 148;
        int result = sol.minSubarray(grid, p);
        System.out.print(result);
    }

    public int minSubarray(int[] nums, int p) {
        long lx = 0;
        for (int n : nums) {
            lx += n;
        }

        int x = (int)(lx % p);
        if (x == 0) return 0;

        Map<Integer, Integer> map = new HashMap<>();
        int v = 0, answer = nums.length;
        for (int i = 0; i < nums.length; i++) {
            map.put(v, i);
            v = (v + nums[i]) % p;
            int mod = (v - x + p) % p;
            if (map.containsKey(mod)) {
                answer = Math.min(answer, i + 1 - map.get(mod));
            }
        }

        return answer == nums.length ? - 1 : answer;
    }
}