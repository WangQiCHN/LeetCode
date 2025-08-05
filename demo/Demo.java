class Demo {
    void main() {
        Demo d = new Demo();
        // String s = "abcd";
        String s = "ababc";
        // String s = "aacecaa";
        // String result = d.shortestPalindrome(s);
        // System.out.println(result);
        int[] result = d.computeLPS(s);
        for (int r : result) {
            System.out.print(r);
            System.out.print(",");
        }
    }
    public String shortestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        
        // Step 1: Create the concatenated string s + "#" + reverse(s)
        String rev = new StringBuilder(s).reverse().toString();
        String combined = s + "#" + rev;
        
        // Step 2: Compute the LPS array for the combined string
        int[] lps = computeLPS(combined);
        
        // Step 3: The last value in LPS gives the length of the longest palindromic prefix
        int longestPalindromicPrefixLength = lps[combined.length() - 1];
        
        // Step 4: Take the suffix after the palindromic prefix, reverse it, and prepend to s
        String suffix = s.substring(longestPalindromicPrefixLength);
        String prefix = new StringBuilder(suffix).reverse().toString();
        
        return prefix + s;
    }
    
    // Compute the LPS (Longest Prefix Suffix) array using KMP
    public int[] computeLPS(String s) {
        int n = s.length();
        int[] lps = new int[n];
        int length = 0; // Length of the previous longest prefix & suffix
        int i = 1;
        
        while (i < n) {
            char first = s.charAt(i);
            char second = s.charAt(length);
            if (first == second) {
                length++;
                lps[i] = length;
                i++;
            } else {
                if (length != 0) {
                    length = lps[length - 1]; // Fall back to the previous prefix
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        
        return lps;
    }
}
