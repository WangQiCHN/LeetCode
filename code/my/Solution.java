package code.my;

class Solution {
  public static void main(String[] args) {
    int[] nums = { 2, 4, 3, 5, 1 };
    System.out.println(new Solution().reversePairs(nums));
  }

  public int reversePairs(int[] nums) {
    // it is a merge sort
    if (nums.length == 0) {
      return 0;
    }
    int result = reverseMergeSort(nums, 0, nums.length - 1);
    return result;
  }

  private int reverseMergeSort(int[] nums, int s, int e) {
    if (s == e)
      return 0;
    int m = (s + e) / 2;
    int n1 = reverseMergeSort(nums, s, m);
    int n2 = reverseMergeSort(nums, m + 1, e);

    int[] sorted = new int[e - s + 1];
    int l = s, r = m + 1, i = 0;
    while (l <= m && r <= e) {
      if (nums[l] < nums[r]) {
        sorted[i] = nums[l];
        l++;
      } else {
        sorted[i] = nums[r];
        r++;
      }
      i++;
    }
    if (l > m) {
      while (r <= e) {
        sorted[i++] = nums[r++];
      }
    }
    if (r > e) {
      while (l <= m) {
        sorted[i++] = nums[l++];
      }
    }

    for (i = s; i <= e; i++) {
      nums[i] = sorted[i - s];
    }

    return n1 + n2;
  }
}
