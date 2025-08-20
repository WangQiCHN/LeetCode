import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<String> result = solution.addOperators("1000000009", 9);
        System.out.println(result); // Expected output: ["1+2+3", "1*2*3"]
    }

    public List<String> addOperators(String num, int target) {
        ArrayList<String> result = new ArrayList<>();
        if (target == Integer.MIN_VALUE)
            return result;

        long cur = 0;

        char[] path = new char[num.length() << 1];
        for (int i = 0; i < num.length(); i++) {
            cur = cur * 10 + (num.charAt(i) - '0');

            path[i] = num.charAt(i);
            if (!overFlow(cur))
                process(i + 1, i + 1, num.toCharArray(), path, 0, cur, target, result);
            if (num.charAt(0) == '0')
                break;
        }

        return result;
    }

    private void process(int i, int j, char[] num, char[] path, long left, long right, int target,
            ArrayList<String> result) {
        if (overFlow(left) || overFlow(right))
            return;

        if (i == num.length) {
            if (left + right == target)
                result.add(new String(path, 0, j));
            return;
        }

        int cur = 0;
        int f = j;
        for (int index = i; index < num.length; index++) {

            cur = cur * 10 + (num[index] - '0');
            path[f] = '+';
            path[++j] = num[index];
            process(index + 1, j + 1, num, path, left + right, cur, target, result);

            path[f] = '-';
            process(index + 1, j + 1, num, path, left + right, -cur, target, result);

            path[f] = '*';
            process(index + 1, j + 1, num, path, left, right * cur, target, result);

            if (num[i] == '0')
                break;
        }
    }

    public boolean overFlow(long num) {
        return num < Integer.MIN_VALUE || num > Integer.MAX_VALUE;
    }
}