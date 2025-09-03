package code;

public class Solution {
    void main() {
        int[] nums = {1,1};
        System.out.println(firstMissingPositive(nums));
    }
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] < n && nums[i] != nums[nums[i] - 1]) {
                int j = nums[i] - 1;
                swap(nums, i, j);
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) return i + 1;
        }

        return n + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}