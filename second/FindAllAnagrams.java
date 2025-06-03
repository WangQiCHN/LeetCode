import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;

public class FindAllAnagrams {
    public static void main() {
        FindAllAnagrams f = new FindAllAnagrams();
        String s = "cbaebabacd";
        String t = "abc";
        List<Integer> result = f.findAnagrams(s, t);
        System.out.println(result);
    }
    private List<Integer> result = new ArrayList<>();
    private HashMap<Character, Integer> visited = new HashMap<>();
    public List<Integer> findAnagrams(String s, String p) {
        for (char c : p.toCharArray()) {
            if (visited.containsKey(c)) {
                int v = visited.get(c);
                visited.put(c, v + 1);
            } else {
                visited.put(c, 1);
            }
        }

        int left = 0, right = 0, sz = s.length(), pz = p.length();
        while (right < sz) {
            char c = s.charAt(right);
            right++;
            if (isValid(c)) {
                if (isSatisfied()) {
                    result.add(left);
                }
            }
            if (right - left >= pz) {
                char r = s.charAt(left);
                left++;
                if (visited.containsKey(r)) {
                    int v = visited.get(r);
                    visited.put(r, v + 1);
                }
            }
        }

        return result;
    }

    private boolean isValid(char c) {
        if (!visited.containsKey(c)) {
            return false;
        } else {
            int v = visited.get(c);
            visited.put(c, v - 1);
            if (v - 1 >= 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    private boolean isSatisfied() {
        Iterator<Character> iter = visited.keySet().iterator();
        while(iter.hasNext()) {
            char c = iter.next();
            int v = visited.get(c);
            if (v != 0) {
                return false;
            }
        }
        return true;
    }
}
