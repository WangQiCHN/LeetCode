package code;

public class Solution {
    void main() {
        int n = 13;
        double result = countDigitOne(n);
        System.out.println(result);
    }

    public int countDigitOne(int n) {
        long count = 0, i = 1;

        while (i <= n) {
            long div = i * 10;
            long first = n / div * i;
            long second = n % div - i + 1;
            long third = Math.max(Math.min(second, 0), i);

            count += (first + third);
            i = div;
        }

        return (int)count;
    }
}