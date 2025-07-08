package demo;

import java.util.Random;

class Demo {
    public static void main(String[] args) {
        int[] w = { 1, 3 };
        Demo demo = new Demo(w);
        for (int i = 0; i < 10; i++) {
            System.out.println(demo.pickIndex(0.26));
        }
    }

    private double[] p;
    private Random random;

    // public Demo(int[] w) {
    //     random = new Random();
    //     int n = w.length;
    //     p = new double[n + 1];
    //     p[0] = 0;
    //     for (int i = 0; i < n; i++) {
    //         p[i + 1] = (p[i] + w[i]);
    //     }
    // }
    public Demo(int[] w) {
        random = new Random();
        int n = w.length;
        p = new double[n + 1];
        p[0] = 0.0;
        for (int i = 0; i < n; i++) {
            p[i + 1] = (p[i] + w[i]);
        }
        for (int i = 0; i < n + 1; i++) {
            p[i] /= p[n];
        }
    }

    // public int pickIndex() {
    // int sum = p[p.length - 1];
    // int v = random.nextInt(sum) + 1;
    // int left = 0, right = p.length - 1;
    // while (left < right) {
    // int mid = (left + right) >> 1;
    // if (p[mid] < v) {
    // left = mid + 1;
    // } else {
    // right = mid;
    // }
    // }

    // return left - 1;
    // }
    public int pickIndex(double v) {
        // double v = random.nextDouble();
        int left = 0, right = p.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (p[mid] < v) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left - 1;
    }
}