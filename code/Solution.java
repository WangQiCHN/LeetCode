package code;

public class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "aaaaaaaaabbbcccccddddd";
        int k = 5;
        int result = sol.longestSubstring(s, k);
        System.out.println(result);
    }
    public int longestSubstring(String s, int k) {
        if (k > s.length()) return 0;
        int[] win = new int[26];
        int n = s.length();
        for (char c : s.toCharArray()) {
            win[c - 'a']++;
        }

        if (isValid(win, k) == 1) return n;

        int l = 0, r = 0;
        int result = 0;
        while (r < n) {
            int index = s.charAt(r) - 'a';
            r++;
            if (win[index] >= k) {
                continue;
            } else {
                result = Math.max(result, longestSubstring(s.substring(l, r - 1), k));
                l = r;
            }
        }
        result = Math.max(result, longestSubstring(s.substring(l, r), k));

        return result;
    }

    // -1 means useless, 0 means analyze it, 1 means satisfy
    private int isValid(int[] win, int k) {
        boolean hasValid = false, hasProblem = false;
        for (int i = 0; i < 26; i++) {
            if (win[i] == 0) continue;
            else if (win[i] >= k) hasValid = true;
            else hasProblem = true;
        }

        if (hasValid && !hasProblem) return 1;
        else if (hasValid && hasProblem) return 0;
        else return -1;
    }
}