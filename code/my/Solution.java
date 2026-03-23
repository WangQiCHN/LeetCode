package code.my;

class Solution {
    public static void main(String[] args) {
        int zero = 3, one = 3, limit = 2;
        int result = new Solution().numberOfStableArrays(zero, one, limit);
        System.out.println(result);
    }

    private static final int MOD = 1000000007;
    int[][][] memo;
    int limit;
    public int numberOfStableArrays(int zero, int one, int limit) {
        memo = new int[zero + 1][one + 1][2];
        for (int i = 0; i <= zero; i++) {
            for (int j = 0; j <= one; j++) {
                memo[i][j][0] = -1;
                memo[i][j][1] = -1;
            }
        }
        this.limit = limit;

        int result = (dp(zero, one, 0) + dp(zero, one, 1)) % MOD;
        return result;
    }

    private int dp(int zero, int one, int lastBit) {
        if (zero == 0) {
            return (lastBit == 0 || one > limit) ? 0 : 1;
        } else if (one == 0) {
            return (lastBit == 1 || zero > limit) ? 0 : 1;
        }

        if (memo[zero][one][lastBit] == -1) {
            int result;
            if (lastBit == 0) {
                result = (dp(zero - 1, one, 0) + dp(zero - 1, one, 1)) % MOD;
                if (zero > limit) {
                    result = result - dp(zero - 1 - limit, one, 1) % MOD;
                
                }
            } else {
                result = (dp(zero, one - 1, 0) + dp(zero, one - 1, 1)) % MOD;
                if (one > limit) {
                    result = result - dp(zero, one - 1 - limit, 0) % MOD;
                
                }
            }
            memo[zero][one][lastBit] = result;
        }

        return memo[zero][one][lastBit];
    }
}