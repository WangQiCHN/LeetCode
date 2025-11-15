package code;

public class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "00011";
        int result = sol.numberOfSubstrings(s);
        System.out.println(result);
    }
    public int numberOfSubstrings(String s) {
        int len = s.length();
        int total = 0;
        for (int i = 0; i < len; i++) {
            int one = 0, zero = 0;
            for (int j = i; j < len - 1; j++) {
                char c = s.charAt(j);
                if (c == '0') zero++;
                else one++;
                if (one >= (zero * zero)) {
                    total++;
                }
            }
        }
        return total;
    }
}