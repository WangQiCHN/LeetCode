package demo;

class Demo {
    public static void main(String[] args) {
        Demo demo = new Demo();
        String k = "bbbab";
        int result = demo.longestPalindromeSubseq(k);
        System.out.println(result); // Output: 2
    }

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();
        int[] dps = new int[n];

        for (int i = n - 1; i >=0; i--) {
            dps[i] = 1;
            int prev = 0;
            for (int j = i + 1; j < n; j++) {
                int temp = dps[j];
                dps[j] = (cs[i] == cs[j] ? (prev + 2) : Math.max(dps[j], dps[j - 1]));
                prev = temp;
            }
        }

        return dps[n - 1];
    }
}
