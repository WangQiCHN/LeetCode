package code;

import java.util.List;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = { 0, 2 };
        int result = sol.minOperations(nums);
        System.out.println(result);
    }
    public int minOperations(int[] nums) {
        int n = nums.length;
        return minOperations(nums, 0, n - 1);
    }

    private int minOperations(int[] nums, int s, int e) {
        if (s > e) return 0;
        else if (s == e && nums[s] != 0) return 1;
        else if (s == e && nums[s] == 0) return 0;
        else {
            int min = Integer.MAX_VALUE;
            List<Integer> indexes = new ArrayList<>();
            for (int i = s; i <= e; i++) {
                int v = nums[i];
                if (v < min) {
                    min = v;
                    indexes = new ArrayList<>();
                    indexes.add(i);
                } else if (v == min) {
                    indexes.add(i);
                }
            }
            int total = (min == 0 ? 0 : 1);
            for (int i = 0; i < indexes.size(); i++) {
                if (i == 0) {
                    total += minOperations(nums, s, indexes.get(i) - 1);
                } else {
                    total += minOperations(nums, indexes.get(i - 1) + 1, indexes.get(i) - 1);
                }
            }
            total += minOperations(nums, indexes.get(indexes.size() - 1) + 1, e);
            return total;
        }
    }
}