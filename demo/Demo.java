package demo;

import java.util.Stack;

public class Demo {
    public static void main() {
        int[] nums = {1,3,5};
        Demo d = new Demo(nums);
        for (int i = 0; i < 9; i++) {
            System.out.println(d.pickIndex(i));
        }
    }

    private int[] p;
    private int sum;
    public Demo(int[] w) {
        int n = w.length;
        p = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            sum += w[i - 1];
            p[i] = sum;
        }
    }
    
    public int pickIndex(int v) {
        // int v = (int)(Math.random() * sum);

        int left = 0, right = p.length - 1;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (p[mid] > v) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left - 1;

    }
}