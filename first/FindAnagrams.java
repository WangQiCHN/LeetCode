package first;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FindAnagrams {
    public static void main() {
        String s = "cbaebabacd";
        String p = "abc";
        List<Integer> result = findAnagrams(s, p);
        for (Integer i : result) {
            System.out.println(i);
        }
    }
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<Integer>();
        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();
        int valid = 0;
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            window.put(c, window.getOrDefault(c, 0) + 1);
            if (window.get(c).equals(need.get(c))) {
                valid++;
            }
            right++;
            if (right - left == p.length()) {
                if (valid == need.size()) {
                    result.add(left);
                }
                c = s.charAt(left);
                if (need.containsKey(c)) {
                    if (window.get(c).equals(need.get(c))) {
                        valid--;
                    }
                    window.put(c, window.get(c) - 1);
                }
                left++;
            }
        }

        return result;
    }
}
