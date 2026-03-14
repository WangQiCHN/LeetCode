package code.my;

import java.util.*;

class Solution {
    public static void main(String[] args) {
        int n = 2;
        System.out.println(new Solution().largestPalindrome(n));
    }
    public int largestPalindrome(int n) {
        if (n == 1) return 9;
        int[] array = new int[n];
        Arrays.fill(array, 9);
        array[n - 1] = 8;
        while (true) {
            long next = getNext(array);
            long sqrt = (long)Math.sqrt(next);
            long max = getMin(n);
            for (long i = sqrt; ; i--) {
                if (next / i > max) break;
                if (next % i == 0) {
                    return (int)(next % 1337);
                }
            }
        }
    }

    private long getNext(int[] array) {
        long result = 0;
        for (int i = 0; i < array.length; i++) {
            result = result * 10 + array[i];
        }
        for (int i = array.length - 1; i >= 0; i--) {
            result = result * 10 + array[i];
        }
        subArray(array, array.length - 1);

        return result;
    }

    private void subArray(int[] array, int n) {
        if (array[n] != 0) {
            array[n]--;
        } else {
            array[n] = 9;
            subArray(array, n - 1);
        }
    }

    private long getMin(int n) {
        long r = 1;
        while (n > 0) {
            r = r * 10;
            n--;
        }

        return r;
    }
}