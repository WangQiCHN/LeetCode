
public class Demo {
    void main(String[] args) {
        int[] nums = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int result = new Demo().rob(nums);
        System.out.println(result);
    }
    int[] memo;
    public int rob(int[] nums) {
        memo = new int[nums.length];
        return traverse(nums, 0);
    }

    private int traverse(int[] nums, int i) {
        if (i >= nums.length) return 0;
        if (memo[i] != 0) return memo[i];
        int result = Math.max(
            traverse(nums, i + 1),
            traverse(nums, i + 2) + nums[i]
        );
        memo[i] = result;
        return result;
    }
}