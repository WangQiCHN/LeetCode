package code;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {-2, 5, -1};
        int lower = -2;
        int upper = 2;
        System.out.println(solution.countRangeSum(nums, lower, upper));
    }
    // public int countRangeSum(int[] nums, int lower, int upper) {
    //     long s = 0;
    //     long[] sum = new long[nums.length + 1];
    //     for (int i = 0; i < nums.length; ++i) {
    //         s += nums[i];
    //         sum[i + 1] = s;
    //     }
    //     return countRangeSumRecursive(sum, lower, upper, 0, sum.length - 1);
    // }

    // public int countRangeSumRecursive(long[] sum, int lower, int upper, int left, int right) {
    //     if (left == right) {
    //         return 0;
    //     } else {
    //         int mid = (left + right) / 2;
    //         int n1 = countRangeSumRecursive(sum, lower, upper, left, mid);
    //         int n2 = countRangeSumRecursive(sum, lower, upper, mid + 1, right);
    //         int ret = n1 + n2;

    //         // 首先统计下标对的数量
    //         int i = left;
    //         int l = mid + 1;
    //         int r = mid + 1;
    //         while (i <= mid) {
    //             while (l <= right && sum[l] - sum[i] < lower) {
    //                 l++;
    //             }
    //             while (r <= right && sum[r] - sum[i] <= upper) {
    //                 r++;
    //             }
    //             ret += r - l;
    //             i++;
    //         }

    //         // 随后合并两个排序数组
    //         long[] sorted = new long[right - left + 1];
    //         int p1 = left, p2 = mid + 1;
    //         int p = 0;
    //         while (p1 <= mid || p2 <= right) {
    //             if (p1 > mid) {
    //                 sorted[p++] = sum[p2++];
    //             } else if (p2 > right) {
    //                 sorted[p++] = sum[p1++];
    //             } else {
    //                 if (sum[p1] < sum[p2]) {
    //                     sorted[p++] = sum[p1++];
    //                 } else {
    //                     sorted[p++] = sum[p2++];
    //                 }
    //             }
    //         }
    //         for (int j = 0; j < sorted.length; j++) {
    //             sum[left + j] = sorted[j];
    //         }
    //         return ret;
    //     }
    // }
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] sums = new long[n + 1];

        for (int i = 1; i < n + 1; i++) {
            sums[i] = (sums[i - 1] + nums[i - 1]);
        }

        return mergeSortandCount(sums, 0, n, lower, upper);
    }

    private int mergeSortandCount(long[] sums, int left, int right, int lower, int upper) {
        if (left == right) {
            return 0;
        }

        int m = (left + right) >> 1;
        int n1 = mergeSortandCount(sums, left, m, lower, upper);
        int n2 = mergeSortandCount(sums, m + 1, right, lower, upper);
        int result = n1 + n2;

        int l = m + 1, r = m + 1, i = left;
        while (i <= m) {
            while (l <= right && sums[l] - sums[i] < lower) {
                l++;
            }
            while (r <= right && sums[r] - sums[i] <= upper) {
                r++;
            }
            result += (r - l);
            i++;
        }

        long[] sorted = new long[right - left + 1];
        int p1 = left, p2 = m + 1, p = 0;
        while (p1 <= m && p2 <= right) {
            if (sums[p1] < sums[p2]) {
                sorted[p++] = sums[p1++];
            } else {
                sorted[p++] = sums[p2++];
            }
        }
        if (p1 > m) {
            while (p2 <= right) {
                sorted[p++] = sums[p2++];
            }
        }
        if (p2 > right) {
            while (p1 <= m) {
                sorted[p++] = sums[p1++];
            }
        }
        p1 = left;
        p = 0;
        for (; p1 <= right; ) {
            sums[p1++] = sorted[p++];
        }


        return result;
    }
}