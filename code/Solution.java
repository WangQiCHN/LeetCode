// package code;

class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int numBootles = 13;
        int numExchange = 6;
        int res = sol.maxBottlesDrunk(numBootles, numExchange);
        System.out.println(res);
    }

    public int maxBottlesDrunk(int numBottles, int numExchange) {
        int full = numBottles;
        int empty = 0;
        int total = 0;

        while (full + empty >= numExchange) {
            if (empty >= numExchange) {
                empty -= numExchange;
                full++;
                numExchange++;
            } else {
                total += full;
                empty += full;
                full = 0;
            }
        }
        if (full > 0) {
            total += full;
        }

        return total;
    }
}