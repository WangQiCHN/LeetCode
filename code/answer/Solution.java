package code.answer;

/*
class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 13, k = 6;
        int ans = sol.findKthNumber(n, k);
        System.out.println(ans);
    }
    public int findKthNumber(int n, int k) {
        int curr = 1;
        k--;
        while (k > 0) {
            int steps = getSteps(curr, n);
            if (steps <= k) {
                k -= steps;
                curr++;
            } else {
                curr = curr * 10;
                k--;
            }
        }
        return curr;
    }

    public int getSteps(int curr, long n) {
        int steps = 0;
        long first = curr;
        long last = curr;
        while (first <= n) {
            steps += Math.min(last, n) - first + 1;
            first = first * 10;
            last = last * 10 + 9;
        }
        return steps;
    }
}
    */
import java.util.*;
class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int maxChoosableInteger = 18;
        int desiredTotal = 300;
        boolean ans = sol.canIWin(maxChoosableInteger, desiredTotal);
        System.out.println(ans);
    }
    private boolean[] visited;
    private Map<String, Boolean> memo = new HashMap<>();
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int total = (maxChoosableInteger + 1) * maxChoosableInteger / 2;
        if (total < desiredTotal) return false;

        visited = new boolean[maxChoosableInteger];

        return dfs(maxChoosableInteger, desiredTotal, 0);
    }

    private boolean dfs(int maxChoosableInteger, int desiredTotal, int currentTotal) {
        String key = getKey();
        boolean res = false;
        if (!memo.containsKey(key)) {
            for (int i = 0; i < maxChoosableInteger; i++) {
                if (!visited[i] && i + 1 + currentTotal >= desiredTotal) {
                    res = true;
                    break;
                }
            }
            if (!res) {
                for (int i = 0; i < maxChoosableInteger; i++) {
                    if (visited[i]) continue;
                    else {
                        visited[i] = true;
                        if (!dfs(maxChoosableInteger, desiredTotal, currentTotal + i + 1)) {
                            res = true;
                        }
                        visited[i] = false;
                        if (res) {
                            break;
                        }
                    }
                }
            }
            memo.put(key, res);
        }
        return memo.get(key);
    }

    private String getKey() {
        StringBuilder sb = new StringBuilder();
        for (boolean v : visited) {
            if (v) {
                sb.append(1);
            } else {
                sb.append(0);
            }
        }

        return sb.toString();
    }
}