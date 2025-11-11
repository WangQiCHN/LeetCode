package code;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = { 1, 2, 0 };
        int result = sol.minOperations(nums);
        System.out.println(result);
    }

    public int minOperations(int[] nums) {
        List<Integer> s = new ArrayList<>();
        int res = 0;
        for (int a : nums) {
            while (!s.isEmpty() && s.get(s.size() - 1) > a) {
                s.remove(s.size() - 1);
            }
            if (a == 0) continue;
            if (s.isEmpty() || s.get(s.size() - 1) < a) {
                res++;
                s.add(a);
            }
        }
        return res;
    }
}