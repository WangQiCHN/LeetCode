package demo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Demo {
    public static void main() {
        Demo d = new Demo();
        int[] nums = {2,2,2,2,3,4,5};
        int k = 4;
        System.out.println(d.canPartitionKSubsets(nums, k));
    }

    private Map<Integer, Boolean> memo = new HashMap<>();
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }

        if (sum % k != 0) {
            return false;
        }
        sum /= k;

        Arrays.sort(nums);

        int used = 0;

        return calculate(nums, sum, 0, k, used);
    }

    private boolean calculate(int[] nums, int sum, int current, int k, int used) {
        if (k == 0) {
            return true;
        }
        if (sum == current) {
            return calculate(nums, sum, 0, k - 1, used);
        }

        if (memo.containsKey(used)) {
            return memo.get(used);
        }

        boolean result = false;
        for (int i = 0; i < nums.length; i++) {
            if((used & (1 << i)) > 0) {
                continue;
            }
            int v = nums[i];
            if (v + current <= sum) {
                used |= (1 << i);
                if (calculate(nums, sum, current + v, k, used)) {
                    result = true;
                    break;
                }
                used ^= (1 << i);
            } else {
                break;
            }
        }

        memo.put(used, result);
        return result;
    }
}