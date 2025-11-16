package code;

public class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "101101";
        // String s = "00011";
        // String s = "1";
        int result = sol.numberOfSubstrings(s);
        System.out.println(result);
    }
    public int numberOfSubstrings(String s) {
        int n = s.length();
        int[] preZero = new int[n + 1];
        preZero[0] = -1;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                preZero[i + 1] = i;
            } else {
                preZero[i + 1] = preZero[i];
            }
        }
        

        int res = 0;
        for (int i = 0; i < n; i++) {
            int cnt0 = (s.charAt(i) == '0' ? 1 : 0);
            int j = i;
            int mul = cnt0 * cnt0;
            while (j != -1 && mul <= i) {
                int cnt1 = i - preZero[j] - cnt0;
                if (mul <= cnt1) {
                    res += Math.min(j - preZero[j], cnt1 - mul + 1);
                }
                j = preZero[j];
                cnt0++;
                mul = cnt0 * cnt0;
            }
        }
        return res;
    }
}