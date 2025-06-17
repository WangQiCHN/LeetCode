package demo;
import java.util.*;

public class Demo {
    public static void main(String[] args) {
        Demo d = new Demo();
        String s = "aab";
        String p = "c*a*b";
        boolean result = d.isMatch(s, p);
        System.out.println("Is match: " + result);
    }
    private int[][] memo;
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        memo = new int[m][n];
        for (int[] me : memo) {
            Arrays.fill(me, -1);
        }
        return calculate(s, p, 0, 0);
    }

    private boolean calculate(String s, String p, int sIndex, int pIndex) {
        if (pIndex == p.length()) {
            return sIndex == s.length();
        }

        if (sIndex == s.length()) {
            if ((p.length() - pIndex) % 2 == 1) {
                return false;
            }

            for (int i = pIndex; i < p.length(); i += 2) {
                if (i + 1 < p.length() && p.charAt(i + 1) != '*') {
                    return false;
                }
            }

            return true;
        }

        if (memo[sIndex][pIndex] != -1) {
            return true;
        }

        boolean result = false;
        if (s.charAt(sIndex) == p.charAt(pIndex) || p.charAt(pIndex) == '.') {
            if (pIndex < p.length() - 1 && p.charAt(pIndex + 1) == '*') {
                result = (calculate(s, p, sIndex + 1, pIndex) || calculate(s, p, sIndex, pIndex + 2));
            } else {
                result = calculate(s, p, sIndex + 1, pIndex + 1);
            }
        } else {
            if (pIndex < p.length() - 1 && p.charAt(pIndex + 1) == '*') {
                result = calculate(s, p, sIndex, pIndex + 2);
            } else {
                result = false;
            }
        }
        memo[pIndex][sIndex] = result ? 1: 0;
        return result;
    }
}

