class LongestPalindromeSubseq {
    public static void main() {
        LongestPalindromeSubseq c = new LongestPalindromeSubseq();
        String s = "cbbdcedfc";
        c.longestPalindromeSubseq(s);
    }
    private int[][] memo;
    public int longestPalindromeSubseq(String s) {
        int sz = s.length();
        memo = new int[sz][sz];
        
        for (int i = 0; i < sz; i++) {
            memo[i][i] = 1;
        }

        for (int i = sz - 1; i >= 0; i--) {
            for (int j = i + 1; j < sz; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    memo[i][j] = memo[i + 1][j - 1] + 2;
                } else {
                    memo[i][j] = Math.max(
                        memo[i][j - 1],
                        memo[i + 1][j]
                    );
                }
            }
        }

        return memo[0][sz - 1];
    }
}