import java.util.Arrays;

public class PartitionEqual {
    public static void main(String[] args) {
        PartitionEqual pe = new PartitionEqual();
        int[] nums = {1, 5, 11, 5};
        System.out.println(pe.canPartition(nums)); // Output: true
    }
    public boolean canPartition(int[] nums) {
        Arrays.sort(nums);

        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % 2 == 1) return false;
        int m = nums.length;
        sum /= 2;

        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int i = 0; i < m; i++) {
            for (int j = sum; j >= 0; j--) {
                if (j - nums[i] >= 0) {
                    dp[j] = dp[j] || dp[j - nums[i]];
                    System.out.println("i: " + i + ", j: " + j + ", nums[i]: " + nums[i] + ", dp[j]: " + dp[j]);
                }
            }
        }
        return dp[sum];
    }
}