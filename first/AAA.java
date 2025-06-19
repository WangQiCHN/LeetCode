package first;
import java.util.List;
import java.util.ArrayList;

class AAA {
    public static void main() {
        List<List<Integer>> result = generate(5);
        System.out.println(result);
    }
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for (int i = 0; i < numRows; i++) {
            if (i == 0) {
                List<Integer> row = new ArrayList<Integer>();
                row.add(1);
                result.add(row);
            } else {
                List<Integer> row = generateOneRow(i, result.get(i - 1));
                result.add(row);
            }
        }

        return result;
    }

    public static List<Integer> generateOneRow(int num, List<Integer> last) {
        List<Integer> row = new ArrayList<Integer>();
        row.add(last.get(0));
        for (int i = 1; i <= num - 1; i++) {
            row.add(last.get(i - 1) + last.get(i));
        }
        row.add(last.get(last.size() - 1));

        return row;
    }
}