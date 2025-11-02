package code;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[] words = { "abcd", "dcba", "lls", "s", "sssll" };
        // String[] words = { "a", "b", "c", "ab", "ac", "aa" };
        List<List<Integer>> result = s.palindromePairs(words);
        for (List<Integer> item : result) {
            System.out.println("{" + item.get(0) + "," + item.get(1) + "}");
        }
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        Map<String, Integer> dict = new HashMap<>();
        List<List<Integer>> result = new ArrayList<>();
        int n = words.length;
        for (int i = 0; i < n; i++) {
            dict.put(words[i], i);
        }

        for (int i = 0; i < n; i++) {
            String word = words[i];
            String rword = new StringBuilder().append(word).reverse().toString();
            for (int j = 0; j <= word.length(); j++) {
                String a = word.substring(0, j);
                String b = word.substring(j, word.length());
                String ra = rword.substring(rword.length() - j, rword.length());
                String rb = rword.substring(0, rword.length() - j);
                if (isPalindrome(a) && dict.containsKey(rb)) {
                    int index = dict.get(rb);
                    if (index != i) {
                        if (word.length() == rb.length()) {
                            if (index > i) {
                                result.add(Arrays.asList(index, i));
                            }
                        } else {
                            result.add(Arrays.asList(index, i));
                        }
                    }
                }
                if (isPalindrome(b) && dict.containsKey(ra)) {
                    int index = dict.get(ra);
                    if (index != i) {
                        if (word.length() == ra.length()) {
                            if (index > i) {
                                result.add(Arrays.asList(i, index));
                            }
                        } else {
                            result.add(Arrays.asList(i, index));
                        }
                    }
                }
            }
        }

        return result;
    }

    private boolean isPalindrome(String s) {
        int len = s.length();
        int l = 0, r = len - 1;
        while (l <= r) {
            if (s.charAt(l) != s.charAt(r))
                return false;
            l++;
            r--;
        }

        return true;
    }
}