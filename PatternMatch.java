import java.util.Arrays;

class PatternMatch {
    public static void main() {
        PatternMatch match = new PatternMatch();
        String s = "aabcd";
        String p = "a*bcde*";
        boolean ok = match.isMatch(s, p);
        System.out.println(ok);
    }

    private int[][] memo;
    public boolean isMatch(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();

        memo = new int[sLen][pLen];
        for (int[] m : memo) {
            Arrays.fill(m, -1);
        }

        return calculate(s, 0, p, 0);
    }

    private boolean calculate(String s, int i, String p, int j) {
        int sLen = s.length();
        int pLen = p.length();

        if (j == pLen) {
            return i == sLen;
        }

        if (i == sLen) {
            if ((pLen - j) % 2 == 1) {
                return false;
            }
            for (; j + 1 < pLen; j += 2) {
                if (p.charAt(j + 1) != '*') {
                    return false;
                }
            }
            return true;
        }

        if (memo[i][j] != -1) {
            return memo[i][j] == 1;
        }

        boolean res = false;
        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
            if (j < pLen - 1 && p.charAt(j + 1) == '*') {
                res = calculate(s, i, p, j + 2) || calculate(s, i + 1, p, j);
            } else {
                res = calculate(s, i + 1, p, j + 1);
            }
        } else {
            if (j < pLen - 1 && p.charAt(j + 1) == '*') {
                res = calculate(s, i, p, j + 2);
            } else {
                res = false;
            }
        }

        memo[i][j] = res ? 1 : 0;
        return res;
    }
}