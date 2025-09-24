package code;
import java.util.Arrays;

class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] primes = {2,7,13,19};
        System.out.println(s.nthSuperUglyNumber(12, primes)); 
    }
    public int nthSuperUglyNumber(int n, int[] primes) {
        int len = primes.length;
        int[] count = new int[len];
        // Arrays.fill(count, 1);
        int[] result = new int[n];
        result[0] = 1;
        for (int i = 1; i < n; i++) {
            int[] tmp = new int[len];
            for (int j = 0; j < len; j++) {
                tmp[j] = result[count[j]] * primes[j];
            }

            result[i] = min(tmp);
            updateCount(result, count, tmp, i);
        }

        return result[n - 1];
    }

    private int min(int[] array) {
        int result = Integer.MAX_VALUE;
        for (int a : array) {
            result = Math.min(a, result);
        }

        return result;
    }

    private void updateCount(int[] result, int[] count, int[] tmp, int index) {
        for (int i = 0; i < count.length; i++) {
            if (tmp[i] == result[index]) {
                count[i]++;
            }
        }
    }
}