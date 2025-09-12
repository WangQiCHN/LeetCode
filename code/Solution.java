package code;

public class Solution {
    void main() {
        int[] nums1 = {1,2,3,4,5};
        int[] nums2 = {6,7,8,9,10,11,12,13,14,15,16,17};
        double result = findMedianSortedArrays(nums1, nums2);
        System.out.println(result);
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int m1 = (n1 + n2 + 1) / 2;
        int m2 = (n1 + n2 + 2) / 2;

        int first = findMedian(nums1, 0, nums2, 0, m1);
        int second = findMedian(nums1, 0, nums2, 0, m2);

        return (first + second) / 2.0;
    }

    private int findMedian(int[] nums1, int i, int[] nums2, int j, int k) {
        if (nums1.length == i) return nums2[j + k - 1];
        else if (nums2.length == j) return nums1[i + k - 1];
        else if (k == 1) return Math.min(nums1[i], nums2[j]);

        int v1 = (i + k / 2 - 1 < nums1.length ? nums1[i + k / 2 - 1] : Integer.MAX_VALUE);
        int v2 = (j + k / 2 - 1 < nums2.length ? nums2[j + k / 2 - 1] : Integer.MAX_VALUE);

        if (v1 < v2) {
            return findMedian(nums1, i + k / 2, nums2, j, k - k / 2);
        } else {
            return findMedian(nums2, i, nums2, j + k / 2, k - k / 2);
        }
    }
}