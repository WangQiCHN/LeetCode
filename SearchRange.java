public class SearchRange {
    public static void main() {
        int[] coins = {0,0,0,1,1,1,2,2,4,4,4,5,5,6,7,7,7,7};
        int[] num = searchRange(coins, 7);
        System.out.println(num[0] + "," + num[1]);
    }
    public static int[] searchRange(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        int index = searchNum(nums, left, right, target);
        if (index == -1) {
            return new int[] {-1, -1};
        } else {
            left = searchLeftBound(nums, left, index, target, index);
            right = searchRightBound(nums, index + 1, right, target, index);
            return new int[] {left, right};
        }
    }
    public static int searchNum(int[] nums, int left, int right, int target) {
        if (left >= right) return -1;
        int mid = (left + right) / 2;
        if (nums[mid] == target) return mid;
        else if (nums[mid] > target) {
            return searchNum(nums, left, mid, target);
        } else {
            return searchNum(nums, mid + 1, right, target);
        }
    }
    public static int searchLeftBound(int[] nums, int left, int right, int target, int current) {
        int newIndex = searchNum(nums, left, right, target);
        if (newIndex == -1) return current;
        else {
            return searchLeftBound(nums, left, current, target, newIndex);
        }
    }
    public static int searchRightBound(int[] nums, int left, int right, int target, int current) {
        int newIndex = searchNum(nums, left, right, target);
        if (newIndex == -1) return current;
        else {
            return searchRightBound(nums, newIndex + 1, right, target, newIndex);
        }
    }
}
