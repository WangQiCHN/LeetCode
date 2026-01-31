package code.answer;

import java.util.Arrays;

class Trie {
    Trie[] child = new Trie[26];
    int id = -1;
}

public class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        // String source = "abcd", target = "acbe";
        // String[] original = { "a", "b", "c", "c", "e", "d" }, changed = { "b", "c", "b", "e", "b", "e" };
        // int[] cost = { 2, 5, 5, 1, 2, 20 };
        String source = "abcdefgh", target = "acdeeghh";
        String[] original = { "bcd","fgh","thh" }, changed = { "cde","thh","ghh" };
        int[] cost = { 1, 3, 5 };
        // String source = "fjybg", target = "apyyt";
        // String[] original = { "bg","xr","cc","ip","vq","po","ym","rh","vw","lf","lo","ee","qv","yr","f","w","i","u","g","a","e","f","s","r","p","j","o","g","i","u" }, changed = { "xr","cc","ip","vq","po","ym","rh","vw","lf","lo","ee","qv","yr","yt","w","i","u","g","a","e","f","s","r","p","a","o","g","i","u","p" };
        // int[] cost = { 97733,90086,87125,85361,75644,46301,21616,79538,52507,95884,79353,61127,58665,96031,95035,12116,41158,91096,47819,88522,25493,80186,66981,87597,56691,86820,89031,99954,41271,39699 };
        long answer = sol.minimumCost(source, target, original, changed, cost);
        System.out.println(answer);
    }

    private static final int INF = Integer.MAX_VALUE / 2;
    private int globalIndex = -1;

    private int add(Trie node, String word) {
        for (char ch : word.toCharArray()) {
            int i = ch - 'a';
            if (node.child[i] == null) {
                node.child[i] = new Trie();
            }
            node = node.child[i];
        }
        if (node.id == -1) {
            node.id = ++globalIndex;
        }
        return node.id;
    }

    public long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {
        int n = source.length();
        int m = original.length;
        Trie root = new Trie();

        int[][] G = new int[m * 2][m * 2]; // 因为可能有2m中可能性，original + changed，从globalIndex

        for (int i = 0; i < m * 2; i++) {
            Arrays.fill(G[i], INF);
            G[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            int x = add(root, original[i]); // original[i] 的id
            int y = add(root, changed[i]); // changed[i] 的id
            G[x][y] = Math.min(G[x][y], cost[i]); // 竟然可以同一匹配，cost不同！
        }

        int size = globalIndex + 1;
        // Floyd-Warshall
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    G[i][j] = Math.min(G[i][j], G[i][k] + G[k][j]); // 这也解释了为什么需要INF = Integer.MAX_VALUE / 2
                }
            }
        }

        long[] f = new long[n];
        Arrays.fill(f, -1);
        for (int j = 0; j < n; j++) {
            if (j > 0 && f[j - 1] == -1) { // f[j] = -1 表示没有计算过值，也就是被其他转换代替，比如abc => edf，那么f[0],f[1]均为-1
                continue;
            }
            long base = (j == 0 ? 0 : f[j - 1]);
            if (source.charAt(j) == target.charAt(j)) {
                f[j] = f[j] == -1 ? base : Math.min(f[j], base);
            }

            Trie u = root;
            Trie v = root;
            for (int i = j; i < n; i++) {
                u = u.child[source.charAt(i) - 'a'];
                v = v.child[target.charAt(i) - 'a'];
                if (u == null || v == null) {
                    break;
                }
                if (u.id != -1 && v.id != -1 && G[u.id][v.id] != INF) {
                    long newVal = base + G[u.id][v.id];
                    if (f[i] == -1 || newVal < f[i]) {
                        f[i] = newVal;
                    }
                }
            }
        }

        return f[n - 1];
    }
}
