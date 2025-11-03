package code;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int count = s.countNumbersWithUniqueDigits(1);

        System.out.println(count);
    }

    public int countNumbersWithUniqueDigits(int n) {
        int total = 0;
        for (int i = 0; i <= n; i++) {
            int cnt = calculate(i);
            total += cnt;
        }
        return total;
    }

    private int calculate(int n) {
        switch (n) {
            case 0: return 1;
            case 1: return 9;
            case 2: return 9 * 9;
            case 3: return 9 * 9 * 8;
            case 4: return 9 * 9 * 8 * 7;
            case 5: return 9 * 9 * 8 * 7 * 6;
            case 6: return 9 * 9 * 8 * 7 * 6 * 5;
            case 7: return 9 * 9 * 8 * 7 * 6 * 5 * 4;
            case 8: return 9 * 9 * 8 * 7 * 6 * 5 * 4 * 3;
            default: return 0;
        }
    }
}