package demo;

public class Demo {
    public static void main() {
        Demo d = new Demo();
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        int result = d.findKthLargest(nums, k);
        System.out.println("The " + k + "th largest element is: " + result);
    }

    private int answer;
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        int realK = n - k;
        traverse(nums, 0, n - 1, realK);
        return answer;
    }

    private void traverse(int[] nums, int left, int right, int k) {
        int j = partition(nums, left, right);
        if (j == k) {
            answer=nums[k];
        } else if (j < k) {
            traverse(nums, j + 1, right, k);
        } else {
            traverse(nums, left, j - 1, k);
        }
    }

    private int partition(int[] nums, int left, int right) {
        int p = nums[left];
        int i = left + 1, j = right;
        while (i <= j) {
            while (i <= right && nums[i] <= p) {
                i++;
            }
            while (j > left && nums[j] > p) {
                j--;
            }

            if (i > j) {
                break;
            }
            swap(nums, i, j);
        }
        swap(nums, left, j);

        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}