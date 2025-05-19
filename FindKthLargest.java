public class FindKthLargest {
    public static void main() {
        FindKthLargest c = new FindKthLargest();
        // int[] nums = new int[] {2, 1};
        // int[] nums = new int[] {0,1,2,3,4,5,9999};
        int[] nums = new int[] {7,6,5,4,3,2,1};
        int result = c.findKthLargest(nums, 2);
        System.out.println(result);
    }
    public int findKthLargest(int[] nums, int k) {
        int from = 0, to = nums.length - 1;
        k = nums.length - k;
        while (from <= to) {
            int p = quick_sort(nums, from, to);
            if (p < k) {
                from = p + 1;
            } else if (p > k) {
                to = p - 1;
            } else {
                return nums[p];
            }
        }
        return -1;
    }

    private int quick_sort(int[] nums, int from, int to) {
        int p = nums[from];
        int i = from + 1, j = to;
        while (i <= j) {
            while (nums[i] <= p && i < to) {
                i++;
            }
            while (nums[j] > p && j > from) {
                j--;
            }
            if (i >= j) {
                break;
            }
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
        int tmp = nums[from];
        nums[from] = nums[j];
        nums[j] = tmp;
        return j;
    }
}
