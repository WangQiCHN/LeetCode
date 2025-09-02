package code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solution {
    void main(String[] args) {
        int[][] ps = {{4,4},{2,6},{6,2}};
        int result = numberOfPairs(ps);
        System.out.println(result);
    }
    public int numberOfPairs(int[][] ps) {
        List<Point> points = new ArrayList<>();
        for (int[] p : ps) {
            int x = p[0];
            int y = p[1];

            points.add(new Point(x, y));
        }
        Comparator<Point> comparator = (p1, p2) -> {
            if (p1.y == p2.y) return p1.x - p2.x;
            else return p2.y - p1.y;
        };
        Collections.sort(points, comparator);

        int result = 0;
        int size = points.size();
        for (int i = 0; i < size - 1; i++) {
            Point pointI = points.get(i);
            for (int j = i + 1; j < size; j++) {
                Point pointJ = points.get(j);
                if (pointJ.x >= pointI.x) {
                    boolean isValid = checkRestPoints(points, i, j);
                    if (isValid) result++;
                }
            }
        }

        return result;
    }

    private boolean checkRestPoints(List<Point> points, int s, int e) {
        Point pointS = points.get(s);
        Point pointE = points.get(e);
        for (int i = s + 1; i < e; i++) {
            Point pointI = points.get(i);
            if (pointI.x >= pointS.x && pointI.x <= pointE.x && pointI.y <= pointS.y && pointI.y >= pointE.y) return false;
        }

        return true;
    }
}

class Point {
    int x;
    int y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}