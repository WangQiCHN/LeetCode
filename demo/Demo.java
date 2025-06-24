package demo;


public class Demo {

    public static void main() {
        Demo d = new Demo();
        int[] nums1 = {4,5,6,8,9};
        int[] nums2 = {};
        // int[] nums1 = {1, 2};
        double result = d.findMedianSortedArrays(nums1, nums2);
        System.out.println(result);
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int total = m + n;
        if (total == 1 || total == 2) {
            int sum = 0;
            for (int i : nums1) {
                sum += i;
            }
            for (int i : nums2) {
                sum += i;
            }
            return sum * 1.0 / total;
        }
        int left, right;
        // 1-> 0, 2 -> 0, 1, 3 -> 1, 4 -> 1,2
        if (total % 2 == 0) {
            left = total / 2;
            right = total / 2 + 1;
        } else {
            left = right = total / 2 + 1;
        }
        if (left == right)
            return getLeft(nums1, nums2, m, n, left);
        else
            return getRight(nums1, nums2, m, n, left, right);
    }

    private double getLeft(int[] nums1, int[] nums2, int m, int n, int left) {
        int i = 0, j = 0;
        int last = 0;
        while (left > 0 && i < m && j < n) {
            if (nums1[i] > nums2[j]) {
                last = nums2[j];
                j++;
            } else {
                last = nums2[i];
                i++;
            }
            left--;
        }
        if (left == 0) {
            return (double)last;
        }
        if (i == m) {
            while (left > 0) {
                last = nums2[j];
                j++;
                left--;
            }
        }
        if (j == n) {
            while (left > 0) {
                last = nums1[i];
                i++;
                left--;
            }
        }

        return last;
    }

    private double getRight(int[] nums1, int[] nums2, int m, int n, int left, int right) {
        int i = 0, j = 0;
        int last0 = 0, last1 = 0;
        while (right > 0 && i < m && j < n) {
            if (nums1[i] > nums2[j]) {
                last1 = last0;
                last0 = nums2[j];
                j++;
            } else {
                last1 = last0;
                last0 = nums1[i];
                i++;
            }
            right--;
        }
        if (right == 0) {
            return (double)((last0 + last1) / 2.0);
        }
        if (i == m) {
            while (right > 0) {
                last1 = last0;
                last0 = nums2[j];
                j++;
                right--;
            }
        }
        if (j == n) {
            while (right > 0) {
                last1 = last0;
                last0 = nums1[i];
                i++;
                right--;
            }
        }

        return (double)((last0 + last1) / 2.0);
    }
}