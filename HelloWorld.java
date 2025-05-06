public class HelloWorld {
    public static void main() {
        int m = 5, n = 10;
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = i + m;
        }

        System.out.println(nums[n - 1]);
    }
}