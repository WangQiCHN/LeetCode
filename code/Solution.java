package code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        List<List<String>> equations = new ArrayList<>();
        equations.add(Arrays.asList("dd", "ff"));
        equations.add(Arrays.asList("aa", "dd"));
        equations.add(Arrays.asList("a", "aa"));
        // equations.add(Arrays.asList("bc", "cd"));
        // double[] values = { 1.5, 2.5, 5.0 };
        // double[] values = { 3.0, 2.0, 2.0 };
        List<List<String>> queries = new ArrayList<>();
        // [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
        queries.add(Arrays.asList("ff", "a"));
        // queries.add(Arrays.asList("bc", "cd"));
        // queries.add(Arrays.asList("cd", "bc"));
        String s = "aaabb";
        int k = 3;
        int result = sol.longestSubstring(s, k);
        System.out.println(result);
    }

    public int longestSubstring(String s, int k) {
        int n = s.length();
        if (n < k) return 0;

        int[] win = new int[26];
        for (char c : s.toCharArray()) {
            win[c - 'a']++;
        }

        if (isSatisfy(win, k)) {
            return n;
        }

        int l = 0, r = 0;
        int max = 0;
        while (r < n) {
            char c = s.charAt(r);
            if (win[c - 'a'] >= k) {
                continue;
            } else {
                max = Math.max(max, longestSubstring(s.substring(l, r), k));
                l = r + 1;
            }
            r++;
        }
        max = Math.max(max, longestSubstring(s.substring(l, r), k));

        return max;
    }

    private boolean isSatisfy(int[] win, int k) {
        for (int n : win) {
            if (n == 0 || n >= k) continue;
            else return false;
        }

        return true;
    }
}