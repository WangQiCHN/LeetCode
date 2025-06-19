package first;
public class MinEatingSpeed {
    public static void main() {
        int[] piles = {3,6,7,11};
        int h = 8;

        MinEatingSpeed m = new MinEatingSpeed();
        int result = m.minEatingSpeed(piles, h);
        System.out.println(result);
    }
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1, right = 1000000000 + 1;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            int hour = getHours(piles, mid);
            if (hour > h) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private int getHours(int[] nums, int speed) {
        int sum = 0;
        for (int n : nums) {
            if (n % speed == 0) {
                sum += (n / speed);
            } else {
                sum += (n / speed + 1);
            }
        }

        return sum;
    }
}