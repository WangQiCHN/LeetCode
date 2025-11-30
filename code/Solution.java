package code;

import java.util.Map;
import java.util.HashMap;

public class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] grid = { 3, 1, 4, 2 };
        int k = 6;
        // int[] grid = { 1, 2 };
        // int k = 1;
        int result = sol.minSubarray(grid, k);
        System.out.println(result);
    }

    public int minSubarray(int[] nums, int p) {
        long lx = 0;
        for (int n : nums) {
            lx += n;
        }

        int x = (int) (lx % p);
        if (x == 0)
            return 0;

        Map<Integer, Integer> map = new HashMap<>();
        int v = 0, answer = nums.length;
        for (int i = 0; i < nums.length; i++) {
            v = (v + nums[i]) % p;
            map.put(v, i);
            int mod = (v - x + p) % p;
            if (map.containsKey(mod)) {
                answer = Math.min(answer, i + 1 - map.get(mod));
            }
        }

        return answer;
    }
}