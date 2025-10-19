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
            for (int j = 0; j < 10; j++) {
                int[] item = addN(newNums, j * a, len);
                if (b % 2 == 1) {
                    for (int k = 0; k < 10; k++) {
                        int[] newItem = addN2(item, k * a, len);
                        if (isSmaller(newItem, min, len)) {
                            min = newItem;
                        }
                    }
                } else {
                    if (isSmaller(item, min, len)) {
                        min = item;
                    }
                }
            }
        }

        return generateS(min);
    }

    private void print(int[] nums) {
        for (int n : nums) {
            System.out.print(n);
        }
        System.out.println();
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

    private int[] addN(int[] nums, int N, int n) {
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                result[i] = nums[i];
            } else {
                result[i] = (nums[i] + N) % 10;
            }
        }

        return result;
    }

    private int[] addN2(int[] nums, int N, int n) {
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            if (i % 2 != 0) {
                result[i] = nums[i];
            } else {
                result[i] = (nums[i] + N) % 10;
            }
        }

        return result;
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