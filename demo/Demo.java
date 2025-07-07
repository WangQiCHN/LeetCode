package demo;

public class Demo {
    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        Demo demo = new Demo();
        int result = demo.findKthLargest(nums, k);
        System.out.println("The " + k + "th largest element is: " + result);
    }

    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        int realK = n - k;
        return quick_sort(nums, 0, n - 1, realK);
    }

    private int quick_sort(int[] nums, int left, int right, int k) {
        if (left == right) return nums[k];
        int p = nums[left];
        int lo = left - 1;
        int hi = right + 1;
        while (lo < hi) {
            do lo++; while(nums[lo] < p);
            do hi--; while(nums[hi] > p);

            if (lo < hi) {
                int temp=nums[lo];
                nums[lo]=nums[hi];
                nums[hi]=temp;
            }
        }
        if(k <= hi) return quick_sort(nums, left, hi, k);
        else return quick_sort(nums, hi + 1, right, k);
    }

    // private int quick_sort(int[] nums, int left, int right, int k) {
    //     if (left == right) return nums[k];
    //     int p = nums[left];
    //     int lo = left - 1;
    //     int hi = right + 1;
    //     while (lo < hi) {
    //         do lo++; while(nums[lo] < p);
    //         do hi--; while(nums[hi] > p);

    //         if (lo < hi) {
    //             int temp=nums[lo];
    //             nums[lo]=nums[hi];
    //             nums[hi]=temp;
    //         }
    //     }
    //     if(k <= hi) return quick_sort(nums, left, hi, k);
    //     else return quick_sort(nums, hi + 1, right, k);
    // }
}