package first;
import java.util.Stack;

public class RemoveDup {
    public static void main() {
        RemoveDup r = new RemoveDup();
        String s = "cbacdcbc";
        String result = r.removeDuplicateLetters(s);
        System.out.println(result);
    }
    public String removeDuplicateLetters(String s) {
        Stack<Character> stk = new Stack<>();
        int len = s.length();
        int[] chars = new int[26];
        boolean[] inStack = new boolean[26];

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            chars[c - 'a'] += 1;
        }

        for (char c : s.toCharArray()) {
            chars[c - 'a']--;
            if (inStack[c - 'a']) {
                continue;
            }

            while (!stk.isEmpty() && stk.peek() > c) {
                if (chars[stk.peek() - 'a'] == 0) {
                    break;
                }
                inStack[stk.pop() - 'a'] = false;
            }
            stk.push(c);
            inStack[c - 'a'] = true;
        }

        StringBuilder sb = new StringBuilder();
        while (!stk.empty()) {
            sb.append(stk.pop());
        }
        return sb.reverse().toString();
    }
}