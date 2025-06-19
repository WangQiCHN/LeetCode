package first;
import java.util.*;

import ThreeSum;

class ThreeSum {
    public static void main() {
        int[] nums = {-1,0,1,2,-1,-4};
        ThreeSum t = new ThreeSum();
        t.threeSum(nums);
    }
    private List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int sz = nums.length;

        for (int i = 0; i < sz;) {
            int v = nums[i];
            boolean hasResult = calculate(nums, i + 1, sz - 1, -v);
            if (hasResult) {
                i++;
                while (i < sz && nums[i] == v) {
                    i++;
                }
            } else {
                i++;
            }
        }

        return result;
    }

    private boolean calculate(int[] nums, int left, int right, int total) {
        boolean hasResult = false;
        while (left < right) {
            int n1 = nums[left];
            int n2 = nums[right];
            if (n1 + n2 == total) {
                List<Integer> res = new ArrayList<>();
                res.add(-total);
                res.add(n1);
                res.add(n2);
                result.add(res);
                hasResult = true;
                left++;
                while (left < right && nums[left] == n1) {
                    left++;
                }
                right--;
                while (left < right && nums[right] == n2) {
                    right--;
                }
                continue;
            } else if (n1 + n2 > total) {
                right--;
            } else {
                left++;
            }
        }

        return hasResult;
    }
}