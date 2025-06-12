import java.util.Arrays;
import java.util.Stack;

public class PartitionK {
    public static void main() {
        PartitionK p = new PartitionK();
        int[] nums = {4,3,2,3,5,2,1};
        boolean result = p.canPartitionKSubsets(nums, 4);
        System.out.println(result);
    }
    private boolean[] used;
    private Stack<Integer> stack = new Stack<>();
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int total = 0, target;
        for (int n : nums) {
            total += n;
        }
        if (total % k != 0) return false;
        target = total / k;
        
        Arrays.sort(nums);
        used = new boolean[nums.length];
        boolean result = traverse(nums, target);
        return result;
    }

    private boolean traverse(int[] nums, int target) {
        int sum = 0;
        int index = 0;
        int n = nums.length;
        while (index < n) {
            if (used[index]) {
                index++;
                continue;
            }
            sum += nums[index];
            stack.push(index);
            used[index] = true;
            if (sum == target) {
                if (stack.size() == n) {
                    return true;
                }
                index = 0;
                sum = 0;
            } else if (sum > target) {
                sum -= nums[index];
                used[index] = false;
                index = stack.pop();
                used[index] = false;
                index++;
            } else {
                index++;
            }
        }

        return false;
    }
}