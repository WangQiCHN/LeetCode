package demo;

public class Demo {
    public static void main() {
        Demo d = new Demo();
        int[] nums = {5,1,1,2,0,0};
        d.sortArray(nums);
    }

    public int[] sortArray(int[] nums) {
        int m = nums.length, size = 2;
        while (size < m) {
            for (int i = 0; i < m; i += size) {
                mergeSort(nums, i, i + size);
            }
            size *= 2;
        }

        return nums;
    }

    private void mergeSort(int[] nums, int left, int right) {
        if (right > nums.length) {
            right = nums.length;
        }

        int i = left, j = (right - left) / 2 + left;
        int iTop = j, jTop = right;
        int[] tmp = new int[right - left];
        int k = 0;
        while (i < iTop && j < jTop) {
            if (nums[i] < nums[j]) {
                tmp[k] = nums[i];
                i++;
            } else {
                tmp[k] = nums[j];
                j++;
            }
            k++;
        }

        if (i >= iTop) {
            for (; j < jTop; j++, k++) {
                tmp[k] = nums[j];
            }
        }

        if (j >= jTop) {
            for (; i < iTop; i++, k++) {
                tmp[k] = nums[i];
            }
        }
        k = 0;
        for (i = left; i < right; i++, k++) {
            nums[i] = tmp[k];
        }
    }
}