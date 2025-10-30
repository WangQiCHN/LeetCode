package code;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = {1,5,0,4,1,3};
        boolean isTrue = s.increasingTriplet(nums);
        System.out.println(isTrue);
    }
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];

        left[0] = nums[0];
        for (int i = 1; i < n - 1; i++) {
            left[i] = Math.min(left[i - 1], nums[i]);
        }
        right[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], nums[i]);
        }

        for (int i = 1; i < n - 1; i++) {
            if (nums[i] > left[i - 1] && nums[i] < right[i + 1]) return true;
        }

        return false;
    }
}