public class SearchRange {
    public static void main() {
        int[] coins = {5,7,7,8,8,10};
        SearchRange s = new SearchRange();
        int[] result = s.searchRange(coins, 6);
        System.out.println(result[0] + "," + result[1]);
    }
    public int[] searchRange(int[] nums, int target) {
        int index = searchSelf(nums, target);
        if (index == -1) {
            return new int[] {-1, -1};
        }
        int left = searchLeft(nums, target);
        int right = searchRight(nums, target);

        return new int[] {left, right};
    }

    private int searchSelf(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int n = nums[mid];
            if (n == target) {
                return mid;
            } else if (n > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
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

        return left - 1;
    }
}