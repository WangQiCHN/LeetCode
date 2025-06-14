public class CapacityShip {
    public static void main(String[] args) {
        CapacityShip cs = new CapacityShip();
        int[] weights = {1,2,3,1,1};
        int days = 4;
        int result = cs.shipWithinDays(weights, days);
        System.out.println(result); // Output: 15
    }
    public int shipWithinDays(int[] weights, int days) {
        int total = 0;
        int left = Integer.MIN_VALUE;
        for (int w : weights) {
            total += w;
            left = Math.max(left, w);
        }

        int right = total;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            int d = calculate(weights, mid);
            if (d <= days) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private int calculate(int[] weights, int c) {
        int capacity = c;
        int days = 0;
        for (int w : weights) {
            capacity -= w;
            if (capacity > 0) {
                continue;
            } else if (capacity == 0) {
                capacity = c;
                days++;
            } else {
                capacity = c;
                days++;
                capacity -= w;
            }
        }
        if (capacity < c) {
            days++;
        }

        return days;
    }
}
