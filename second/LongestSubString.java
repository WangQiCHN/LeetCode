import java.util.HashMap;

public class LongestSubString {
    public static void main() {
        LongestSubString m = new LongestSubString();
        String s = "abcabcbb";
        int result = m.lengthOfLongestSubstring(s);
        System.out.println(result);
    }

    public int lengthOfLongestSubstring(String s) {
        int left = 0, right = 0, sz = s.length();
        int max = 0, current = 0;
        HashMap<Character, Integer> visited = new HashMap<>();

        while (right < sz) {
            char c = s.charAt(right);
            right++;
            if (!visited.containsKey(c)) {
                visited.put(c, 1);
                current++;
                if (max < current) {
                    max = current;
                }
            } else {
                int v = visited.get(c);
                if (v == 0) {
                    current++;
                    if (max < current) {
                        max = current;
                    }
                }
                visited.put(c, v + 1);
                if (v == 1) {
                    while (left < right) {
                        char l = s.charAt(left);
                        left++;
                        if (l != c) {
                            visited.put(l, 0);
                            current--;
                            continue;
                        } else {
                            visited.put(l, 1);
                            break;
                        }
                    }
                }
            }
        }

        return max;
    }
}
