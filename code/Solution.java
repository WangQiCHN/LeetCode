package code;

class Solution {
    void main() {
        int n = 6, delay = 2, forget = 4;
        System.out.println(peopleAwareOfSecret(n, delay, forget));
    }
    private static final int MOD = 1_000_000_007;

    public int peopleAwareOfSecret(int n, int delay, int forget) {
        // dp[i] represents the number of new people who learn the secret on day i
        long[] dp = new long[n + 1];
        dp[1] = 1; // On day 1, one person knows the secret
        
        // For each day, compute how many new people learn the secret
        for (int i = 1; i <= n; i++) {
            // People who learned the secret on day i share it with others after 'delay' days
            for (int j = i + delay; j < Math.min(n + 1, i + forget); j++) {
                dp[j] = (dp[j] + dp[i]) % MOD;
            }
        }
        
        // Sum the number of people who are aware of the secret on day n
        // People who learned the secret from day (n - forget + 1) to day n are still aware
        long result = 0;
        for (int i = Math.max(1, n - forget + 1); i <= n; i++) {
            result = (result + dp[i]) % MOD;
        }
        
        return (int) result;
    }
}