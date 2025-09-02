package code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UnfamiliarCode {
    public void numberOfPairs(int[][] ps) {
        List<Point> points = new ArrayList<>();
        for (int[] p : ps) {
            int x = p[0];
            int y = p[1];

            points.add(new Point(x, y));
        }
        // 如何进行数组排序不熟悉，不知道如何使用Comparator
        Comparator<Point> comparator = (p1, p2) -> {
            if (p1.y == p2.y) return p1.x - p2.x;
            else return p2.y - p1.y;
        };
        Collections.sort(points, comparator);
    }
}
