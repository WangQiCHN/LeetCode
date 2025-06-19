package first;
import java.util.Arrays;

class LongestCommonSubsequence {
    public static void main() {
        LongestCommonSubsequence c = new LongestCommonSubsequence();
        String text1 = "abcde";
        String text2 = "ace";
        int result = c.longestCommonSubsequence(text1, text2);

        System.out.println(result);
    }
    private int[][] memo;
    public int longestCommonSubsequence(String text1, String text2) {
        int t1 = text1.length();
        int t2 = text2.length();

        memo = new int[t1][t2];
        for (int[] m : memo) {
            Arrays.fill(m, -1);
        }

        return calculate(text1, 0, text2, 0);
    }

    private int calculate(String s1, int i, String s2, int j) {
        if (i >= s1.length()) return 0;
        if (j >= s2.length()) return 0;

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        if (s1.charAt(i) == s2.charAt(j)) {
            memo[i][j] = calculate(s1, i + 1, s2, j + 1) + 1;
        } else {
            memo[i][j] = max(
                calculate(s1, i + 1, s2, j),
                calculate(s1, i, s2, j + 1)
            );
        }

        return memo[i][j];
    }

    private int max(int a, int b) {
        return (a > b ? a : b);
    }
}