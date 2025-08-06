import java.util.ArrayList;
import java.util.List;

class Demo {
    void main() {
        Demo d = new Demo();
        // String s = "0-2147483647";
        String s = "2*3+4";
        int result = d.calculate(s);
        System.out.println(result);

        // 2-0c
    }
    public int calculate(String s) {
        List<Integer> nums = new ArrayList<>();
        List<Character> ops = new ArrayList<>();
        boolean has = false;

        int n = s.length();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                cnt = cnt * 10 + (c - '0');
            } else if (c == '+' || c == '-') {
                nums.add(cnt);
                ops.add(c);
                cnt = 0;
            } else if (c == '*' || c == '/') {
                nums.add(cnt);
                ops.add(c);
                cnt = 0;
                has = true;
            }
        }
        nums.add(cnt);

        if (has) {
            List<Integer> tnums = new ArrayList<>();
            List<Character> tops = new ArrayList<>();
            cnt = 0;
            int index = -1;
            for (int i = 0; i < ops.size(); i++) {
                char c = ops.get(i);
                if (c == '+' || c == '-') {
                    if (index != -1) {
                        tnums.add(cnt);
                        cnt = 0;
                        index = -1;
                    } else {
                        tnums.add(nums.get(i));
                    }
                    tops.add(c);
                } else {
                    if (index == -1) {
                        index = i;
                        cnt = nums.get(i);
                    }
                    if (c == '*') {
                        cnt *= nums.get(i + 1);
                    } else {
                        cnt /= nums.get(i + 1);
                    }
                }
            }
            if (index == -1) {
                tnums.add(nums.get(ops.size()));
            } else {
                tnums.add(cnt);
            }
            nums = tnums;
            ops = tops;
        }

        cnt = nums.get(0);
        for (int i = 0; i < ops.size(); i++) {
            char c = ops.get(i);
            if (c == '+') cnt += nums.get(i + 1);
            else cnt -= nums.get(i + 1);
        }

        return cnt;
    }
}
