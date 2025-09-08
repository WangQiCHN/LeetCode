package code;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    void main() {
        String s = "232";
        int target = 8;
        List<String> result = addOperators(s, target);
        System.out.println(result);
    }
    private List<String> result = new ArrayList<>();
    private List<Integer> nums = new ArrayList<>();
    private List<Character> ops = new ArrayList<>();
    public List<String> addOperators(String num, int target) {
        char[] cnums = num.toCharArray();
        analyze(cnums, 0, target);

        return result;
    }

    private void analyze(char[] cnums, int s, int target) {
        int cnt = 0;
        int sz = cnums.length;

        for (int i = s; i < sz; i++) {
            char c = cnums[i];
            cnt = cnt * 10 + (int)(c - '0');
            nums.add(cnt);
            
            if (i != sz -1) {
                ops.add('+');
                analyze(cnums, i + 1, target);
                ops.remove(ops.size() - 1);
                ops.add('-');
                analyze(cnums, i + 1, target);
                ops.remove(ops.size() - 1);
                ops.add('*');
                analyze(cnums, i + 1, target);
                ops.remove(ops.size() - 1);
            } else {
                calculate(target);
            }

            nums.remove(nums.size() - 1);
            

            if (i == s && c == '0') break;
        }
    }

    private void calculate(int target) {
        int v = calculate();
        if (v == target) {
            result.add(genExpr());
        }
    }

    private int calculate() {
        int sz = nums.size();
        int cnt = nums.get(0);
        if (sz == 1) return cnt;
        List<Integer> tnums = new ArrayList<>();
        List<Character> tops = new ArrayList<>();
        for (int i = 0; i < sz - 1; i++) {
            char c = ops.get(i);
            if (c == '+' || c == '-') {
                tnums.add(cnt);
                tops.add(c);
                cnt = nums.get(i + 1);
            } else {
                cnt *= nums.get(i + 1);
            }
        }
        tnums.add(cnt);
        sz = tnums.size();
        cnt = tnums.get(0);
        if (sz == 1) return cnt;

        for (int i = 0; i < sz - 1; i++) {
            char c = tops.get(i);
            if (c == '+') {
                cnt += tnums.get(i + 1);
            } else {
                cnt -= tnums.get(i + 1);
            }
        }

        return cnt;
    }

    private String genExpr() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.size(); i++) {
            sb.append(nums.get(i));
            if (i != nums.size() - 1) {
                sb.append(ops.get(i));
            }
        }

        return sb.toString();
    }
}
