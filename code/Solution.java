package code;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "84688926";
        int a = 2;
        int b = 5;
        String res = solution.findLexSmallestString(s, a, b);
        System.out.println(res);
    }

    public String findLexSmallestString(String s, int a, int b) {
        int len = s.length();
        int rotate = len / gcd(len, b);
        int[] nums = getArray(s, len);
        int[] min = getArray(s, len);
        for (int i = 0; i < rotate; i++) {
            int[] newNums = rotateN(nums, b * i, len);
            addN(newNums, a, len, 1);
            if (b % 2 == 1) {
                addN(newNums, a, len, 0);
            }
            if (isSmaller(newNums, min, len)) {
                min = newNums;
            }
        }

        return generateS(min);
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        int tmp = b;
        b = a % b;
        return gcd(tmp, b);
    }

    private int[] getArray(String s, int n) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = (int) (s.charAt(i) - '0');
        }
        return nums;
    }

    private int[] rotateN(int[] nums, int step, int n) {
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[(i + step) % n] = nums[i];
        }

        return result;
    }

    private void addN(int[] nums, int N, int n, int start) {
        int times = 0, min = 9;
        for (int i = 0; i <= 9; i++) {
            int tmp = (nums[start] + N * i) % 10;
            if (tmp < min) {
                min = tmp;
                times = i;
            }
        }

        for (int i = start; i < n; i += 2) {
            nums[i] = (nums[i] + N * times) % 10;
        }
    }

    private boolean isSmaller(int[] nums1, int[] nums2, int n) {
        for (int i = 0; i < n; i++) {
            if (nums1[i] < nums2[i])
                return true;
            else if (nums1[i] > nums2[i])
                return false;
        }
        return false;
    }

    private String generateS(int[] nums) {
        StringBuilder sb = new StringBuilder();
        for (int n : nums) {
            sb.append(n);
        }

        return sb.toString();
    }
}