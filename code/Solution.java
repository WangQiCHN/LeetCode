package code;

import java.util.List;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "aabca";
        int result = sol.countPalindromicSubsequence(s);
        System.out.println(result);
    }
    public int countPalindromicSubsequence(String s) {
        List<List<Integer>> dict = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            dict.add(new ArrayList<>());
        }
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            dict.get(index).add(i);
        }

        int count = 0;
        for (int i = 0; i < 26; i++) {
            if (dict.get(i).size() >= 3) count++;
        }
        for (int i = 0; i < 25; i++) {
            List<Integer> is = dict.get(i);
            if (is.size() == 0) continue;
            for (int j = i + 1; j < 26; j++) {
                List<Integer> js = dict.get(j);
                count += calculate(is, js);
            }
        }

        return count;
    }

    private int calculate(List<Integer> first, List<Integer> second) {
        if (second.size() == 0) return 0;
        if (second.size() == 1 && first.size() == 1) return 0;
        
        int total = 0;
        int current = 0; // 1 means first, -1 means second
        int i = 0, j = 0;
        for (; i < first.size() && j < second.size();) {
            if (first.get(i) > second.get(j)) {
                if (current == 0 || current == 1) {
                    current = -1;
                    total++;
                }
                j++;
            } else {
                if (current == 0 || current == -1) {
                    current = 1;
                    total++;
                }
                i++;
            }
            if (total == 4) return 2;
        }

        if (i < first.size() && current == -1) {
            total++;
        } else if (j < second.size() && current == 1) {
            total++;
        }

        if (total == 3) return 1;
        else if (total == 4) return 2;
        else return 0;
    }
}