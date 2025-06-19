package first;
public class MaxArea {
    public static void main() {
        MaxArea m = new MaxArea();
        int[] height = {1,8,6,2,5,4,8,3,7};
        int result = m.maxArea(height);
        System.out.println(result);
    }
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int max = 0;
        while (left < right) {
            int current = Math.min(height[left], height[right]) * (right - left);
            if (current > max) {
                max = current;
            }
            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }

        return max;
    }
}