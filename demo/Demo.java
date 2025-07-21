class Solution {
    private Map<String, Boolean> visited = new HashMap<>();
    public boolean isScramble(String s1, String s2) {
        String key = s1 + "#" + s2;
        if(visited.containsKey(key)) {
            return visited.get(key);
        }

        if(s1.length() != s2.length()) {
            visited.put(key, false);
            return false;
        }

        if (s1.equals(s2)) {
            visited.put(key, true);
            return true;
        }

        if(!areAnagrams(s1, s2)) {
            visited.put(key, false);
            return false;
        }

        int n = s1.length();
        for (int i = 1; i < n; i++) {
            if (
                isScramble(s1.substring(i), s2.substring(i)) &&
                isScramble(s1.substring(0, i), s2.substring(0, i))
            ) {
                visited.put(key, true);
                return true;
            }
            if (
                isScramble(s1.substring(i), s2.substring(0, n - i)) &&
                isScramble(s1.substring(0, i), s2.substring(n - i))
            ) {
                visited.put(key, true);
                return true;
            }
        }

        visited.put(key, false);
        return false;
    }

    private boolean areAnagrams(String s1, String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int n = c1.length;
        int[] window = new int[26];
        for (int i = 0; i < n; i++) {
            window[c1[i] - 'a']++;
        }
        for (int i = 0; i < n; i++) {
            window[c2[i] - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (window[i] != 0) return false;
        }

        return true;
    }
}