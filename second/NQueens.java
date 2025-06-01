import java.util.*;

class NQueens {
    private LinkedList<XY> tmp = new LinkedList<>();
    private List<List<String>> result = new ArrayList<>();

    public static void main() {
        NQueens n = new NQueens();
        n.solveNQueens(4);
    }
    public List<List<String>> solveNQueens(int n) {
        calculate(n, 0);

        return result;
    }

    private void calculate(int n, int row) {
        if (row == n) {
            List<String> solution1 = generateResult();
            result.add(solution1);
            return;
        }
        for (int i = 0; i < n; i++) {
            tmp.addLast(new XY(row, i));
            if (isValid(n)) {
                calculate(n, row + 1);
            }
            tmp.removeLast();
        }
    }

    private boolean isValid(int n) {
        Iterator<XY> iter = tmp.iterator();
        boolean[] checked = new boolean[n];
        while (iter.hasNext()) {
            XY item = iter.next();
            if (checked[item.y]) {
                return false;
            } else {
                checked[item.y] = true;
            }
        }

        for (int i = 0; i < tmp.size() - 1; i++) {
            XY first = tmp.get(i);
            for (int j = i + 1; j < tmp.size(); j++) {
                XY second = tmp.get(j);
                if (Math.abs(first.x - second.x) == Math.abs(first.y - second.y)) {
                    return false;
                }
            }
        }

        return true;
    }

    private List<String> generateResult() {
        List<String> result = new ArrayList<>();
        Iterator<XY> iter = tmp.iterator();
        int sz = tmp.size();
        while (iter.hasNext()) {
            XY item = iter.next();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < sz; i++) {
                if (item.y == i) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
            result.add(sb.toString());
        }

        return result;
    }
}

class XY {
    public int x;
    public int y;

    public XY(int x, int y) {
        this.x = x;
        this.y = y;
    }
}