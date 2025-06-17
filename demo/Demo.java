package demo;
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        Demo d = new Demo();
        int[] nums = {4,3,2,3,5,2,1};
        int k = 4;
        boolean result = d.canPartitionKSubsets(nums, k);
        System.out.println("Can partition into " + k + " subsets: " + result);
    }
    private int used = 0;
    private HashMap<Integer, Boolean> memo = new HashMap<>();
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (sum % k != 0) return false;
        int size = sum / k;

        return calculate(nums, size, 0, 0, k);
    }

    private boolean calculate(int[] nums, int size, int start, int current, int total) {
        if (total == 0) {
            return true;
        }

        if (size == current) {
            boolean result = calculate(nums, size, 0, 0, total - 1);
            memo.put(used, result);
            return result;
        }

        if (memo.containsKey(used)) {
            return memo.get(used);
        }

        for (int i = start; i < nums.length; i++) {
            if (((used >> i) & 1) == 1) {
                continue;
            }

            if (nums[i] + current > size) {
                continue;
            } else {
                used |= (1 << i);
                current += nums[i];
                if (calculate(nums, size, i + 1, current, total)) {
                    return true;
                }
                current -= nums[i];
                used ^= (1 << i);
            }
        }

        return false;
    }
}

