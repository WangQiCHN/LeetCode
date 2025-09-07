package code;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    void main() {
        // int ax1 = -2, ay1 = -2, ax2 = 2, ay2 = 2, bx1 = -1, by1 = 4, bx2 = 1, by2 = 6;
        int result = calculate("(1+(4+5+2)-3)+(6+8)");
        System.out.println(result);
    }
    private int lastIndex = -1;
    public int calculate(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(s);
        sb.append(")");

        String news = sb.toString();

        char[] cs = news.toCharArray();

        return calculate(cs, 0);   
    }

    private int calculate(char[] array, int index) {
        int cnt = 0;
        List<Integer> nums = new ArrayList<>();
        List<Character> operators = new ArrayList<>();
        for (int i = index; i < array.length; i++) {
            char c = array[i];
            if (c == '(') {
                cnt = calculate(array, index + 1);
                i = lastIndex;
            } else if (c == ')') {
                lastIndex = i;
                break;
            } else if (c == ' ') {
                continue;
            } else if (c == '+' || c == '-') {
                nums.add(cnt);
                operators.add(c);
                cnt = 0;
            } else {
                cnt = cnt * 10 + (c - '0');
            }
        }
        nums.add(cnt);

        if (nums.size() == 1) return nums.get(0);
        else {
            cnt = nums.get(0);
            for (int i = 0; i < operators.size(); i++) {
                char c = operators.get(i);
                if (c == '+') {
                    cnt += nums.get(i + 1);
                } else {
                    cnt -= nums.get(i + 1);
                }
            }

            return cnt;
        }
    }
}
