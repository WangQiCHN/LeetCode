package demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Demo {

    public static void main(String[] args) {
        Demo d = new Demo();
        String s = "cbaebabacd";
        String t = "abc";
        List<Integer> result = d.findAnagrams(s, t);
        System.out.println("Result: " + result);
    }
    private Map<Character, Integer> count = new HashMap<>();
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        for (char c : p.toCharArray()) {
            if (count.containsKey(c)) {
                int v = count.get(c);
                count.put(c, v + 1);
            } else {
                count.put(c, 1);
            }
        }

        int left = 0, right = 0;
        int s1 = s.length(), p1 = p.length();

        while (right < s1) {
            char c = s.charAt(right);
            right++;

            updateCount(c, -1);
            if (right - left == p1) {
                if (isValid()) {
                    result.add(left);
                }
                char d = s.charAt(left);
                updateCount(d, 1);
                left++;
            }
        }

    return result;
    }

    private void updateCount(char c, int v) {
        if (count.containsKey(c)) {
            int v1 = count.get(c);
            count.put(c, v + v1);
        }
    }

    private boolean isValid() {
        Iterator<Character> iter = count.keySet().iterator();

        while (iter.hasNext()) {
            char c = iter.next();
            if (count.get(c) != 0) {
                return false;
            }
        }

        return true;
    }
}