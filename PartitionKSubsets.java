import java.util.HashMap;

public class PartitionKSubsets {
    public static void main() {
        int[] nums = {1,2,2,3,3,4,5};
        int k = 4;
        PartitionKSubsets p = new PartitionKSubsets();
        boolean result = p.canPartitionKSubsets(nums, k);
        System.out.println(result);
    }
    private HashMap<Integer, Boolean> memo = new HashMap<>();
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (k > nums.length) return false;
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % k != 0) return false;
        int target = sum / k;
        int used = 0;

        return backtrace(k, 0, nums, 0, used, target);
    }

    private boolean backtrace(int k, int bucket, int[] nums, int start, int used, int target) {
        if (k == 0) {
            return true;
        }

        if (bucket == target) {
            boolean res = backtrace(k - 1, 0, nums, 0, used, target);
            // 
            memo.put(used, res);
            return res;
        }
        if (memo.containsKey(used)) {
            return memo.get(used);
        }

        for (int i = start; i < nums.length; i++) {
            if (((used >> i) & 1) == 1) {
                continue;
            }
            if (nums[i] + bucket > target) {
                continue;
            }
            used |= 1 << i;
            String binaryString = Integer.toBinaryString(used);
            System.out.println(binaryString);
            bucket += nums[i];
            if (backtrace(k, bucket, nums, i + 1, used, target)) {
                return true;
            }
            used ^= 1 << i;
            bucket -= nums[i];
        }
        return false;
    }
}