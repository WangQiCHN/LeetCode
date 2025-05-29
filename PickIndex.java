public class PickIndex {
    public static void main() {
        int[] w = {1};
        PickIndex p = new PickIndex(w);
        int result = p.pickIndex();
        System.out.println(result);

    }
    private int[] possibility;
    private int sum = 0;
    public PickIndex(int[] w) {
        int n = w.length;
        possibility = new int[n + 1];
        possibility[0] = 0;
        for (int i = 1; i < n + 1; i++) {
            sum += w[i - 1];
            possibility[i] = sum;
        }
    }
    
    public int pickIndex() {
        int random = (int)(Math.random() * (sum) + 1);
        return leftBound(possibility, random) - 1;
    }

    private int leftBound(int[] nums, int target) {
        int i = 1;
        while (nums[i] < target && i < nums.length + 1) {
            i++;
        }

        return i;
    }
}