import java.util.HashMap;
import java.util.Iterator;

public class MinimumWindow {
    public static void main() {
        MinimumWindow m = new MinimumWindow();
        String s = "cabwefgewcwaefgcf";
        String t = "cae";
        String result = m.minWindow(s, t);
        System.out.println(result);
    }
    private HashMap<Character, Integer> valid = new HashMap<>();
    public String minWindow(String s, String t) {
        for (Character c : t.toCharArray()) {
            if (valid.containsKey(c)) {
                valid.put(c, valid.get(c) + 1);
            } else {
                valid.put(c, 1);
            }
        }

        int left = 0, right = 0, size = s.length();
        int answerLeft = -1, answerRight = size;
        while (right < size) {
            char c = s.charAt(right);
            right++;
            if (!isValid(c)) {
                continue;
            } else {
                if (answerLeft == -1 || answerRight - answerLeft > right - left) {
                    answerLeft = left;
                    answerRight = right;
                }
                while (left <= right) {
                    if (moveLeft(s, left)) {
                        left++;
                        if (answerRight - answerLeft > right - left) {
                            answerLeft = left;
                            answerRight = right;
                        }
                        continue;
                    } else {
                        left++;
                        break;
                    }
                }
            }
        }

        if (answerLeft != -1) {
            return s.substring(answerLeft, answerRight);
        } else {
            return "";
        }
    }

    private boolean isValid(char c) {
        if (valid.containsKey(c)) {
            Integer v  = valid.get(c);
            valid.put(c, v - 1);
            Iterator<Character> keys = valid.keySet().iterator();
            while (keys.hasNext()) {
                Character k = keys.next();
                Integer kv = valid.get(k);
                if (kv > 0) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean moveLeft(String s, int left) {
        char c = s.charAt(left);
        if (!valid.containsKey(c)) {
            return true;
        } else {
            Integer v  = valid.get(c);
            valid.put(c, v + 1);
            Iterator<Character> keys = valid.keySet().iterator();
            while (keys.hasNext()) {
                Character k = keys.next();
                Integer kv = valid.get(k);
                if (kv > 0) {
                    return false;
                }
            }
            return true;
        }
    }
}