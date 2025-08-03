public class Demo {
    public static void main(String[] args) {
        Demo demo = new Demo();
        // int ax1 = -3, ay1 = 0, ax2 = 3, ay2 = 4;
        // int bx1 = 0, by1 = -1, bx2 = 9, by2 = 2;
        int ax1 = -2, ay1 = -2, ax2 = 2, ay2 = 2;
        int bx1 = -1, by1 = -1, bx2 = 1, by2 = 1;
        // int ax1 = 0, ay1 = 0, ax2 = 0, ay2 = 0;
        // int bx1 = -1000000000, by1 = -1000000000, bx2 = 1000000000, by2 = 1000000000;
        int result = demo.computeArea(ax1, ay1, ax2, ay2, bx1, by1, bx2, by2);
        System.out.println("Result: " + result); // Expected output: true
    }
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int s1 = (ax2 - ax1) * (ay2 - ay1);
        int s2 = (bx2 - bx1) * (by2 - by1);
        int s3 = getOverLap(ax1, ay1, ax2, ay2, bx1, by1, bx2, by2);

        return s1 + s2 - s3;
    }

    int getOverLap(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int ax = getV(ax1, ax2, bx1, bx2, false);
        int bx = getV(bx1, bx2, ax1, ax2, true);
        int ay = getV(ay1, ay2, by1, by2, false);
        int by = getV(by1, by2, ay1, ay2, true);

        if (ax == -100000 || ay == -100000 || bx == -100000 || by == -100000) return 0;
        return (Math.abs(ax - bx) * Math.abs(ay - by));
    }

    int getV(int x1, int x2, int x3, int x4, boolean isLarge) {
        int result = -100000;
        if (x3 > x4) {
            int tmp = x3;
            x3 = x4;
            x4 = tmp;
        }
        if (x1 >= x3 && x1 <= x4) {
            result = x1;
            if (!isLarge) return result;
        }
        if (x2 >= x3 && x2 <= x4) {
            return x2;
        }
        return result;
    }
}

// TrieNode class to represent each node in the Trie
    