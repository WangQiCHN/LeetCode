package demo;

import java.util.Arrays;

public class Demo {

    public static void main() {
        int[] w = {1,3};
        Demo d = new Demo(w);
        for (int i = 0; i < 4; i++) {
            System.out.println(d.pickIndex(i));
        }
    }


    int[] p;
    public Demo(int[] w) {
        p = new int[w.length + 1];
        p[0] = 0;
        for (int i = 1; i < w.length + 1; i++) {
            p[i] = p[i - 1] + w[i - 1];
        }
    }
    
    public int pickIndex(int i) {
        int sum = p[p.length - 1];
        int index = i;// (int)(Math.random() * sum);
        int left = 0, right = p.length - 1;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (p[mid] > index) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left - 1;
    }
}