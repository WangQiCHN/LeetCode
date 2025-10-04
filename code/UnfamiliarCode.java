package code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Arrays;

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class UnfamiliarCode {
    public void numberOfPairs(int[][] ps) {
        List<Point> points = new ArrayList<>();
        for (int[] p : ps) {
            int x = p[0];
            int y = p[1];

            points.add(new Point(x, y));
        }
        // 如何进行数组排序不熟悉，不知道如何使用Comparator
        // arrays是数组，collections是集合
        Comparator<Point> comparator = (p1, p2) -> {
            if (p1.y == p2.y)
                return p1.x - p2.x;
            else
                return p2.y - p1.y;
        };
        Collections.sort(points, comparator);
        Collections.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                if (p1.y == p2.y)
                    return p1.x - p2.x;
                else
                    return p2.y - p1.y;
            }
        });

        // 按姓名升序
        Arrays.sort(ps, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });

        // Lambda 简化
        Arrays.sort(ps, (a, b) -> a[0] - b[0]);

        StringBuilder sb = new StringBuilder();
        // 在sb中插入一个字符
        int index = 2;
        sb.insert(index, '(');

        // 在hashmap中如果有直接插入，没有新建在插入
        // destTimStamps.computeIfAbsent(destination, k -> new ArrayList<>()).add(timestamp);
        // 直接计算三个点的面积
        // int area = Math.abs(p1.x * (p2.y - p3.y) + p2.x * (p3.y - p1.y) + p3.x * (p1.y - p2.y));
    }
}
