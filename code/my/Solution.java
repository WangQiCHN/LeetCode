package code.my;

import java.util.Arrays;

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
    public long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {
        int m = source.length();
        int n = original.length;
        int[][] dict = new int[n * 2][n * 2];
        for (int[] d : dict) {
            Arrays.fill(d, INF);
        }

        Tie root = new Tie();
        for (int i = 0; i < n; i++) {
            int x = addNode(original[i], root);
            int y = addNode(changed[i], root);
            dict[x][y] = Math.min(dict[x][y], cost[i]);
        }

        int size = globalIndex + 1;
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (i == j) {
                        dict[i][j] =0 ;
                    } else {
                        dict[i][j] = Math.min(dict[i][j], dict[i][k] + dict[k][j]);
                    }
                }
            }
        }

        long[] dp = new long[m];
        Arrays.fill(dp, -1);

        for (int i = 0; i < m; i++) {
            if (i > 0 && dp[i - 1] == -1) {
                continue;
            }

            long base = (i == 0 ? 0 : dp[i - 1]);
            int indexS = source.charAt(i) - 'a';
            int indexT = target.charAt(i) - 'a';
            if (indexS == indexT) {
                dp[i] = (dp[i] == -1 ? base : Math.min(dp[i], base));
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

                if (u.id != -1 && v.id != -1 && dict[u.id][v.id] != INF) {
                    long newVal = base + dict[u.id][v.id];
                    if (dp[j] == -1) dp[j] = newVal;
                    else {
                        dp[j] =  Math.min(dp[j], newVal);
                    }
                }
            }
        }
        return dp[m - 1];
    }

    private int addNode(String value, Tie t) {
        for (char c : value.toCharArray()) {
            int index = c - 'a';
            if (t.children[index] == null) {
                t.children[index] = new Tie();
            }
            t = t.children[index];
        }
        if (t.id == -1) {
            t.id = ++globalIndex;
        }
        return t.id;
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
