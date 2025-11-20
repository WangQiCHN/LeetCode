package code;

public class Solution {
    private int lastIndex = -1;
    public static void main(String[] args) {
        Solution sol = new Solution();
        String n = "3[a2[c]]";
        String result = sol.decodeString(n);
        System.out.println(result);
    }
    public String decodeString(String s) {
        return decodeString(s, 0);
    }

    private String decodeString(String s, int start) {
        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        int l = start;
        int num = 0;
        while (l < s.length()) {
            char c = s.charAt(l);
            if (c >= '0' && c <= '9') {
                num = num * 10 + (c - '0');
                if (temp.length() != 0) {
                    sb.append(temp.toString());
                    temp = new StringBuilder();
                }
            } else if (c >= 'a' && c <= 'z') {
                temp.append(c);
            } else if (c == '[') {
                String v = decodeString(s, l + 1);
                for (int i = 0; i < num; i++) {
                    sb.append(v);
                }
                num = 0;
                l = lastIndex;
            } else if (c == ']') {
                lastIndex = l;
                return temp.toString();
            }
            l++;
        }
        return sb.toString();
    }
}