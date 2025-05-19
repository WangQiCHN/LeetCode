import java.util.LinkedList;
import java.util.List;

public class AllPathSourceTarget {
    public static void main() {
        AllPathSourceTarget a = new AllPathSourceTarget();
        int[][] graph = new int[4][];
        graph[0] = new int[] {1,2};
        graph[1] = new int[] {3};
        graph[2] = new int[] {3};
        graph[3] = new int[] {};
        List<List<Integer>> result = a.allPathsSourceTarget(graph);
        System.out.println(result);
    }
    List<List<Integer>> result = new LinkedList<>();
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        LinkedList<Integer> path = new LinkedList<>();
        path.add(0);
        traverse(graph, 0, path);

        return result;
    }

    private void traverse(int[][] graph, int start, LinkedList<Integer> path) {
        int last = graph.length - 1;
        if (start == last) {
            LinkedList<Integer> item = new LinkedList<>();
            item.addAll(path);
            result.add(item);
            return;
        }
        int[] current = graph[start];
        for (int i = 0; i < current.length; i++) {
            int newStart = current[i];
            path.add(newStart);
            traverse(graph, newStart, path);
            path.removeLast();
        }
    }
}
