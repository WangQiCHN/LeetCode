public class SearchRange {
    public static void main() {
        int[] coins = {5,7,7,8,8,10};
        SearchRange s = new SearchRange();
        int[] result = s.searchRange(coins, 6);
        System.out.println(result[0] + "," + result[1]);
    }
    public int[] searchRange(int[] nums, int target) {
        int left = searchLeft(nums, target);
        int right = searchRight(nums, target);

        return new int[] {left, right};
    }

    private int searchLeft(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int n = nums[mid];
            if (n == target) {
                right = mid - 1;
            } else if (n > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        if (left == nums.length || nums[left] != target) return -1;
        return left;
    }

    private int searchRight(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int n = nums[mid];
            if (n == target) {
                left = mid + 1;
            } else if (n > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        if (left - 1 < 0 || nums[left - 1] != target) return -1;
        return left - 1;
    }
}