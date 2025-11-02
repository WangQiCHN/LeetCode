package code;

import java.util.List;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        // String[] words = { "abcd", "dcba", "lls", "s", "sssll" };
        String[] words = { "aa", "abc", "ba", "cba", "baa", "" };
        List<List<Integer>> result = s.palindromePairs(words);
        for (List<Integer> item : result) {
            System.out.println("{" + item.get(0) + "," + item.get(1) + "}");
        }
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        int n = words.length;
        boolean[][] pref = new boolean[n][];
        boolean[][] suf = new boolean[n][];

        for (int i = 0; i < n; i++) {
            String s = words[i];
            // 这个函数的作用是获得在第N位是不是一个回文串
            boolean[] p = prefixPalFlags(s);
            String rs = new StringBuilder(s).reverse().toString();
            boolean[] pr = prefixPalFlags(rs);
            int m = s.length();
            // 反转顺序的内容
            boolean[] sf = new boolean[m + 1];
            for (int ppos = 0; ppos <= m; ppos++) {
                sf[ppos] = pr[m - ppos];
            }
            pref[i] = p;
            suf[i] = sf;
        }

        Node root = new Node();
        for (int i = 0; i < n; i++) {
            String w = words[i];
            int m = w.length();
            Node u = root;
            for (int d = 0; d < m; d++) {
                int L = m - d;
                if (pref[i][L])
                    u.pal.add(i);
                char c = w.charAt(m - 1 - d);
                int idx = c - 'a';
                if (u.ch[idx] == null)
                    u.ch[idx] = new Node();
                u = u.ch[idx];
            }
            u.pal.add(i);
            u.wordIndex = i;
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String w = words[i];
            int m = w.length();
            Node u = root;
            boolean broken = false;
            for (int p = 0; p < m; p++) {
                if (u.wordIndex != -1 && u.wordIndex != i && suf[i][p]) {
                    ArrayList<Integer> pair = new ArrayList<>(2);
                    pair.add(i);
                    pair.add(u.wordIndex);
                    ans.add(pair);
                }
                int idx = w.charAt(p) - 'a';
                if (u.ch[idx] == null) {
                    broken = true;
                    break;
                }
                u = u.ch[idx];
            }
            if (!broken) {
                for (int j : u.pal) {
                    if (j != i) {
                        ArrayList<Integer> pair = new ArrayList<>(2);
                        pair.add(i);
                        pair.add(j);
                        ans.add(pair);
                    }
                }
            }
        }

        return ans;
    }

    private boolean[] prefixPalFlags(String s) {
        int n = s.length();
        boolean[] pref = new boolean[n + 1];
        pref[0] = true;
        if (n == 0)
            return pref;

        int[] d1 = new int[n];
        int l = 0, r = -1;
        for (int i = 0; i < n; i++) {
            int k = (i > r) ? 1 : Math.min(d1[l + r - i], r - i + 1);
            while (i - k >= 0 && i + k < n && s.charAt(i - k) == s.charAt(i + k))
                k++;
            d1[i] = k;
            if (i + k - 1 > r) {
                l = i - k + 1;
                r = i + k - 1;
            }
        }

        int[] d2 = new int[n];
        l = 0;
        r = -1;
        for (int i = 0; i < n; i++) {
            int k = (i > r) ? 0 : Math.min(d2[l + r - i + 1], r - i + 1);
            while (i - k - 1 >= 0 && i + k < n && s.charAt(i - k - 1) == s.charAt(i + k))
                k++;
            d2[i] = k;
            if (i + k - 1 > r) {
                l = i - k;
                r = i + k - 1;
            }
        }

        for (int i = 0; i < n; i++) {
            if (d1[i] >= i + 1) {
                int L = 2 * i + 1;
                pref[L] = true;
            }
            if (d2[i] >= i) {
                int L = 2 * i;
                pref[L] = true;
            }
        }
        return pref;
    }
}

class Node {
    Node[] ch = new Node[3]; // 这个节点下面的子节点
    int wordIndex = -1; // 在这个节点，第一个单词结束了
    ArrayList<Integer> pal = new ArrayList<>(); // 是和这个词符合的一个顺序
}