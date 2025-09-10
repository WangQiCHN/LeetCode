package code;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    void main() {
        String s =  "aabb";
        int result = minCut(s);
        System.out.println(result);
    }

    public int minCut(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = i - 1;
        }

        boolean[][] isValid = new boolean[n][n];
        for (int t = 0; t < n; t++) {
            for (int f = 0; f <= t; f++) {
                if (s.charAt(t) == s.charAt(f)) {
                    if (t - f <= 2 || isValid[f + 1][t - 1]) {
                        isValid[f][t] = true;
                    }
                }
            }
        }

        for (int t = 1; t <= n; t++) {
            for (int f = 0; f < t; f++) {
                if (isValid[f][t - 1]) {
                    dp[t] = Math.min(dp[t], dp[f] + 1);
                }
            }
        }

        return dp[n];
    }
}