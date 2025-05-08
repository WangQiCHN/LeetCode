import java.util.HashSet;
public class LengthOfLongestSubstring {
    public static void main() {
        String s = "aabaab!bb";
        int result = lengthOfLongestSubstring(s);
        System.out.println(result);
    }
    public static int lengthOfLongestSubstring(String s) {
        HashSet<Character> window = new HashSet<>();
        int left = 0, right = 0, max = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            if (window.contains(c)) {
                if (window.size() > max) {
                    max = window.size();
                }
                while (left < right) {
                    char lc = s.charAt(left);
                    left++;
                    if (lc == c) {
                        break;
                    } else {
                        window.remove(lc);
                    }
                }
            } else {
                window.add(c);
            }
        }
        if (window.size() > max) max = window.size();

        return max;
    }
}
