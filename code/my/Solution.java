package code.my;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        // String source = "abcd", target = "acbe";
        // String[] original = { "a", "b", "c", "c", "e", "d" }, changed = { "b", "c", "b", "e", "b", "e" };
        // int[] cost = { 2, 5, 5, 1, 2, 20 };
        // String source = "abcdefgh", target = "acdeeghh";
        // String[] original = { "bcd","fgh","thh" }, changed = { "cde","thh","ghh" };
        // int[] cost = { 1, 3, 5 };
        String source = "fjybg", target = "apyyt";
        String[] original = { "bg","xr","cc","ip","vq","po","ym","rh","vw","lf","lo","ee","qv","yr","f","w","i","u","g","a","e","f","s","r","p","j","o","g","i","u" }, changed = { "xr","cc","ip","vq","po","ym","rh","vw","lf","lo","ee","qv","yr","yt","w","i","u","g","a","e","f","s","r","p","a","o","g","i","u","p" };
        int[] cost = { 97733,90086,87125,85361,75644,46301,21616,79538,52507,95884,79353,61127,58665,96031,95035,12116,41158,91096,47819,88522,25493,80186,66981,87597,56691,86820,89031,99954,41271,39699 };
        long answer = sol.minimumCost(source, target, original, changed, cost);
        System.out.println(answer);
    }

    private static final int INF = Integer.MAX_VALUE / 2;
    private int globalIndex = -1;
    public long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {
        int m = source.length();
        int n = original.length;
        int[][] dp = new int[n * 2][n * 2];
        for (int [] array : dp) {
            Arrays.fill(array, INF);
        }

        Tie root = new Tie();
        for (int i = 0; i < n; i++) {
            int x = addNode(original[i], root);
            int y = addNode(changed[i], root);
            dp[x][y] = Math.min(dp[x][y], cost[i]);
        }

        int size = globalIndex + 1;
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (i == j) {
                        dp[i][j] = 0;
                    } else {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                    }
                }
            }
        }

        long[] f = new long[m];
        Arrays.fill(f, -1);
        for (int i = 0; i < m; i++) {
            if (i > 0 && f[i - 1] == -1) {
                continue;
            }

            long base = (i == 0 ? 0 : f[i - 1]);
            int indexS = source.charAt(i) - 'a';
            int indexT = target.charAt(i) - 'a';
            if (indexS == indexT) {
                f[i] = (f[i] == -1 ? base : Math.min(f[i], base));
            }

            Tie u = root;
            Tie v = root;
            for (int j = i; j < m; j++) {
                indexS = source.charAt(j) - 'a';
                indexT = target.charAt(j) - 'a';
                u = u.children[indexS];
                v = v.children[indexT];
                if (u == null || v == null) {
                    break;
                }

                if (u.id != -1 && v.id != -1 && dp[u.id][v.id] != INF) {
                    long newValue = base + dp[u.id][v.id];
                    if (f[j] == -1 || f[j] > newValue) {
                        f[j] = newValue;
                    }
                }
            }
        }

        return f[m - 1];
    }

    private int addNode(String value, Tie tie) {
        for (char c : value.toCharArray()) {
            int index = c - 'a';
            if (tie.children[index] == null) {
                tie.children[index] = new Tie();
            }
            tie = tie.children[index];
        }
        if (tie.id == -1) {
            tie.id = ++globalIndex;
        }
        return tie.id;
    }
}

class Tie {
    Tie[] children;
    int id;
    public Tie() {
        children = new Tie[26];
        id = -1;
    }
}
