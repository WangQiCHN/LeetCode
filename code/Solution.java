package code;

class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] power = {1,5,2,4};
        int[] mana = {5,1,4,2};
        System.out.println(sol.minTime(power, mana));
    }
    public long minTime(int[] skill, int[] mana) {
        int n = skill.length;
        int m = mana.length;

        int[] amount = new int[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) {
                    amount[j] += skill[j] * mana[i];
                } else {
                    amount[j] = Math.max(amount[j - 1], amount[j]) + skill[j] * mana[i];
                }
            }

            for (int j = n - 2; j >= 0; j--) {
                amount[j] = amount[j + 1] - skill[j + 1] * mana[i];
            }
        }

        return amount[n - 1];
    }
}