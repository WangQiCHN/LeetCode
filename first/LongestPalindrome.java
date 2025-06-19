package first;
public class LongestPalindrome {
    public static void main() {
        String s = "babad";
        System.out.println(longestPalindrome(s));
    }
    public static String longestPalindrome(String s) {
        int size = s.length();
        String result = "";

        for (int i = 0; i < size; i++) {
            String odd = palindrome(s, i);
            String even = palindrome(s, i, i + 1);
            int oLen = odd.length();
            int eLen = even.length();
            if (result.length() < oLen) {
                result = odd;
            }
            if (result.length() < eLen) {
                result = even;
            }
        }

        return result;
    }

    public static String palindrome(String s, int center) {
        int from = center - 1;
        int to = center + 1;
        while (from != -1 && to != s.length()) {
            if (s.charAt(from) == s.charAt(to)) {
                from--;
                to++;
            } else {
                break;
            }
        }
        from++;
        return s.substring(from, to);
    }

    public static String palindrome(String s, int center1, int center2) {
        int from = center1;
        int to = center2;
        if (to == s.length() || s.charAt(from) != s.charAt(to)) return "";
        while (from != -1 && to != s.length()) {
            if (s.charAt(from) == s.charAt(to)) {
                from--;
                to++;
            } else {
                break;
            }
        }
        from++;
        return s.substring(from, to);
    }
}
