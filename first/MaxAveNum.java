package first;
public class MaxAveNum {
    public static void main() {
        // int[] nums = {1,12,-5,-4,50,3};
        int[] nums = {1,12,-5,-4,50,4,100,200,-8};
        // int k = 4;
        int k = 3;
        double result = maxAveNum(nums, k);
        System.out.println(result);
    }

    public static double maxAveNum(int[] nums, int k) {
        double max = Integer.MIN_VALUE;
        int left = 0, right = 0;
        double total = 0;
        while (right < nums.length) {
            total += nums[right];
            right++;
            if (right - left == k) {
                if (total * 1.0 / k > max) {
                    max = total * 1.0 / k;
                }
                total -= nums[left];
                left++;
            }
        }

        return max;
    }
}
