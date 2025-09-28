package code;

class Solution {
    public static void main(String[] args) {
        // Solution s = new Solution();
        // int[] nums1 = {6, 5};
        // int[] nums2 = {9, 5, 8, 3};
        // int k = 5;
        // int[] result = s.maxNumber(nums1, nums2, k);
        // for (int i : result) {
        //     System.out.print(i + " ");
        // }
        Integer a = 200;
        Integer b = 200;
        System.out.println(a.equals(b));
    }
    private int[] temp;
    private int[] result;

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        temp = new int[k];
        result = new int[k];

        calculate(nums1, 0, nums2, 0, k, 0);

        return result;
    }

    private void calculate(int[] nums1, int n1, int[] nums2, int n2, int k, int index) {
        if (index == 1 && n2 == 2) {
            System.out.println();
        }
        if (nums1.length - n1 + nums2.length - n2 < k - index)
            return;
        if (k == index) {
            updateResult(k);
            return;
        }
        if (nums1.length == n1) {
            calculate(nums1, n1, nums2, n2 + 1, k, index);
            temp[index] = nums2[n2];
            calculate(nums1, n1, nums2, n2 + 1, k, index + 1);
            return;
        }
        if (nums2.length == n2) {
            calculate(nums1, n1 + 1, nums2, n2, k, index);
            temp[index] = nums1[n1];
            calculate(nums1, n1 + 1, nums2, n2, k, index + 1);
            return;
        }
        if (nums1[n1] > nums2[n2]) {
            temp[index] = nums1[n1];
            calculate(nums1, n1 + 1, nums2, n2, k, index + 1);
        } else if (nums1[n1] < nums2[n2]) {
            temp[index] = nums2[n2];
            calculate(nums1, n1, nums2, n2 + 1, k, index + 1);
        } else {
            temp[index] = nums1[n1];
            calculate(nums1, n1 + 1, nums2, n2, k, index + 1);
            calculate(nums1, n1, nums2, n2 + 1, k, index + 1);
        }
        calculate(nums1, n1 + 1, nums2, n2, k, index);
        calculate(nums1, n1, nums2, n2 + 1, k, index);
    }

    private void updateResult(int k) {
        for (int i = 0; i < k; i++) {
            if (temp[i] == result[i])
                continue;
            else if (temp[i] > result[i])
                break;
            else
                return;
        }
        for (int i = 0; i < k; i++) {
            result[i] = temp[i];
        }
    }
}