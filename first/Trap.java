package first;
public class Trap {
    public static void main() {
        int[] height = {4,4,4,7,1,0};
        Trap t = new Trap();
        int result = t.trap(height);
        System.out.println(result);
    }
    public int trap(int[] height) {
        int sz = height.length;
        int total = 0;
        int left = 0, right = 0;
        int rightIndex = 0;
        for (int i = 0; i < sz; i++) {
            int h = height[i];
            if (i >= rightIndex) {
                rightIndex = findRight(height, i + 1);
                if (rightIndex == height.length) {
                    right = 0;
                } else {
                    right = height[rightIndex];
                }
            }
            int v = Math.min(left, right) - h;
            if (v < 0) {
                v = 0;
            }
            total += v;
            if (left < h) {
                left = h;
            }
        }

        return total;
    }

    private int findRight(int[] height, int index) {
        if (index >= height.length) {
            return height.length;
        }
        int right = -1, rightIndex = 0;
        for (int i = index; i < height.length; i++) {
            if (height[i] > right) {
                rightIndex = i;
                right = height[i];
            }
        }

        return rightIndex;
    }
}