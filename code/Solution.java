package code;

public class Solution {
    void main() {
        // int n = 6;
        for (int i = 100; i <= 109; i++) {
            countDigitOne(i);
        }
        // System.out.println(result);
    }

    public int countDigitOne(int n) {
        long i = 1, count = 0;
        
        while (i <= n) {
            long div = i * 10;
            long first = (n / div * i); // 个位上的1
            long second = (n % div - i + 1); // 当前位上1可能的数字
            long third = (Math.min(Math.max(second, 0), i)); // 最多只有i，位数上吧
            count += (first + third);
            i = div;
            System.out.print(first + "," + second + "," +third);
            System.out.print("    ");
        }
        System.out.println(count);

        return (int)count;
    }
}