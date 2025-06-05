class LongestPalindromic {
    public static void main() {
        LongestPalindromic l = new LongestPalindromic();
        String s = "ccc";
        String result = l.longestPalindrome(s);
        System.out.println(result);
    }
    public String longestPalindrome(String s) {
        int max = Integer.MIN_VALUE;
        int sz = s.length();
        int left = 0, right = 0;
        for (int i = 0; i < sz; i++) {
            int odd = getOddSize(s, i);
            int even = getEvenSize(s, i);
            if (odd > max) {
                left = i - odd / 2;
                right = i + odd / 2 + 1;
                max = odd;
            }
            if (even > max) {
                left = i - even / 2 + 1;
                right = i + even / 2 + 1;
                max = even;
            }
        }

        return s.substring(left, right);
    }

    private int getOddSize(String s, int i) {
        int left = i - 1, right = i + 1;
        int size = 1;
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) == s.charAt(right)) {
                size = size + 2;
                left--;
                right++;
            } else {
                break;
            }
        }

        return size;
    }

    private int getEvenSize(String s, int i) {
        int left = i, right = i + 1;
        int size = 0;
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) == s.charAt(right)) {
                size = size + 2;
                left--;
                right++;
            } else {
                break;
            }
        }

        return size;
    }
}