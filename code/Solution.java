package code;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = {1,2,4,5};
        int k = 2;
        int numOperations = 4;
        System.out.println(s.maxFrequency(nums, k, numOperations));
    }

    public int maxFrequency(int[] nums, int k, int numOperations) {
        Arrays.sort(nums);
        int maximumFrequencyInNums = maxFrequencyInNums(nums, k, numOperations);
        if (maximumFrequencyInNums >= numOperations) {
            return maximumFrequencyInNums;
        }
        int maximumFrequencyNotInNums = maxFrequencyNotInNums(nums, k, numOperations);
        return Math.max(maximumFrequencyInNums, maximumFrequencyNotInNums);
    }

    public int maxFrequencyInNums(int[] nums, int k, int numOperations) {
        int maximumFrequency = 0;
        int n = nums.length;
        int start = 0, end = 0;
        int targetIndex = 0;
        int targetFrequency = 0;
        while (targetIndex < n) {
            int target = nums[targetIndex];
            targetFrequency = 1;
            while (targetIndex + 1 < n && nums[targetIndex + 1] == target) {
                targetIndex++;
                targetFrequency++;
            }
            while (nums[start] < target - k) {
                start++;
            }
            while (end + 1 < n && nums[end + 1] <= target + k) {
                end++;
            }
            int cnt = Math.min(end - start + 1, targetFrequency + numOperations);
            maximumFrequency = Math.max(maximumFrequency, cnt);
            targetIndex++;
        }
        return maximumFrequency;
    }

    public int maxFrequencyNotInNums(int[] nums, int k, int numOperations) {
        int maximumFrequency = 0;
        int n = nums.length;
        int start = 0, end = 0;
        while (end < n) {
            while (nums[end] - nums[start] > 2 * k) {
                start++;
            }
            maximumFrequency = Math.max(maximumFrequency, Math.min(end - start + 1, numOperations));
            end++;
        }
        return maximumFrequency;
    }
}