import java.util.Stack;

class Demo {
    void main() {
        Demo d = new Demo();
        // String s = "0-2147483647";
        String s = "1*2-3/4+5*6-7*8+9/10";
        int result = d.calculate(s);
        System.out.println(result);

        // 2-0c
    }
    public int calculate(String s) {
        Stack<String> stack = new Stack<>();
        int n = s.length();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                cnt = cnt * 10 + (c - '0');
            } else if (c == '+' || c == '-') {
                cnt = tryToCal(stack, c, cnt);
                stack.push("" + cnt);
                stack.push("" + c);
                cnt = 0;
            } else if (c == '*' || c == '/') {
                cnt = tryToCal(stack, c, cnt);
                stack.push("" + cnt);
                stack.push("" + c);
                cnt = 0;
            }
        }
        while (!stack.isEmpty()) {
            String opt = stack.pop();
            String num1 = stack.pop();
            cnt = cal(num1, opt, cnt);
        }

        return cnt;
    }

    private int tryToCal(Stack<String> s, char c, int cnt) {
        while (!s.isEmpty()) {
            String opt = s.pop();
            String num1 = s.pop();
            if (c == '*' || c == '/') {
                if (opt.equals("*") || opt.equals("/")) {
                    cnt = cal(num1, opt, cnt);
                } else {
                    s.push(num1);
                    s.push(opt);
                    return cnt;
                }
            } else {
                cnt = cal(num1, opt, cnt);
            }
        }
        return cnt;
    }

    private int cal(String snum, String opt, int num2) {
        int num1 = Integer.parseInt(snum);
        if (opt.equals("+")) {
            return num1 + num2;
        } else if (opt.equals("-")) {
            return num1 - num2;
        } else if (opt.equals("*")) {
            return num1 * num2;
        } else {
            return num1 / num2;
        }
    }
}
