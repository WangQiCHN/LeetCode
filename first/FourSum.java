package first;
import java.util.*;

import FourSum;

public class FourSum {
    public static void main() {
        int[] nums = {1000000000,1000000000,1000000000,1000000000};
        int target = -294967296;

        FourSum f = new FourSum();
        List<List<Integer>> result = f.fourSum(nums, target);
        System.out.println(result);
    }
    private List<List<Integer>> result = new ArrayList<>();
    private List<Integer> tmp = new LinkedList<>();
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);

        int sz = nums.length;

        for (int i = 0; i < sz;) {
            int v = nums[i];
            tmp.addLast(v);
            boolean hasResult = threeSum(nums, i + 1, target - v);
            tmp.removeLast();
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

    private boolean threeSum(int[] nums, int index, long total) {
        boolean hasResult = false;
        int sz = nums.length;

        for (int i = index; i < sz;) {
            int v = nums[i];
            tmp.addLast(v);
            boolean twoResult = calculate(nums, i + 1, sz - 1, total - v);
            if (!hasResult && twoResult) {
                hasResult = true;
            }
            tmp.removeLast();
            if (twoResult) {
                i++;
                while (i < sz && nums[i] == v) {
                    i++;
                }
            } else {
                i++;
            }
        }

        return hasResult;
    }

    private boolean calculate(int[] nums, int left, int right, long total) {
        boolean hasResult = false;
        while (left < right) {
            int n1 = nums[left];
            int n2 = nums[right];
            if (n1 + n2 == total) {
                List<Integer> res = new ArrayList<>();
                res.addAll(tmp);
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