package demo;

public class Dynamic {
    public static void main(String[] args) {
        Dynamic dynamic = new Dynamic();
        // String s = "()(())";
        int[] s = {2, 3, 1, 1, 4};
        System.out.println(dynamic.canJump(s)); // Output: 6
    }
    
    public boolean canJump(int[] nums) {
        int n = nums.length;
        boolean[] reach = new boolean[n];
        reach[0] = true;
        for (int i = 0; i < n; i++) {
            int c = nums[i];
            if (reach[i]) {
                int sz = c + i + 1 < n ? c + i - 1 : n;
                for (int j = i + 1; j < sz; j++) {
                    reach[j] = true;
                }
            }
        }

        return reach[n - 1];
    }
}
