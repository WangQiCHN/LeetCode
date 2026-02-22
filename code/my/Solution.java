package code.my;

class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 13, k = 2;
        int ans = sol.findKthNumber(n, k);
        System.out.println(ans);
    }
    public int findKthNumber(int n, int k) {
        int current = 1;
        k--;
        
        while (k > 0) {
            int steps = getSteps(current, n);
            if (steps <= k) {
                k -= steps;
                current++;
            } else {
                current = current * 10;
                k--;
            }
        }

        return current;
    }
    private int getSteps(int cnt, int n) {
        long first = cnt;
        long last = cnt;
        int steps = 0;

        while (first <= n) {
            steps += Math.min(last, n) - first + 1;
            first *= 10;
            last = last * 10 + 9;
        }

        return steps;
    }
}