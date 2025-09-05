package code;

public class Solution {
    void main() {
        int num1 = 700000000, num2 = 2147483641;
        int step = rangeBitwiseAnd(num1, num2);
        System.out.println(step);
    }

    public int rangeBitwiseAnd(int left, int right) {
        int result = left;
        for (int i = left + 1; i <= right && i <= 2 * left; i++) {
            result &= i;
        }

        return result;
    }
}