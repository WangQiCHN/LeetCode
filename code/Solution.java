package code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        List<List<String>> equations = new ArrayList<>();
        equations.add(Arrays.asList("dd", "ff"));
        equations.add(Arrays.asList("aa", "dd"));
        equations.add(Arrays.asList("a", "aa"));
        // equations.add(Arrays.asList("bc", "cd"));
        // double[] values = { 1.5, 2.5, 5.0 };
        double[] values = { 3.0, 2.0, 2.0 };
        List<List<String>> queries = new ArrayList<>();
        // [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
        queries.add(Arrays.asList("ff", "a"));
        // queries.add(Arrays.asList("bc", "cd"));
        // queries.add(Arrays.asList("cd", "bc"));
        double[] result = sol.calcEquation(equations, values, queries);
        System.out.println(result);
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int sz = equations.size();
        Union uf = new Union(sz * 2);
        Map<String, Integer> dict = new HashMap<>();
        int id = 0;
        for (int i = 0; i < sz; i++) {
            List<String> equation = equations.get(i);
            double v = values[i];
            String p = equation.get(0);
            String q = equation.get(1);
            if (!dict.containsKey(p)) {
                dict.put(p, id);
                id++;
            }
            if (!dict.containsKey(q)) {
                dict.put(q, id);
                id++;
            }
            int pi = dict.get(p);
            int qi = dict.get(q);
            uf.connect(pi, qi, v);
        }

        double[] result = new double[queries.size()];
        int index = 0;
        for (List<String> query : queries) {
            String p = query.get(0);
            String q = query.get(1);
            int pi = -1;
            int qi = -1;
            if (dict.containsKey(p)) {
                pi = dict.get(p);
            }
            if (dict.containsKey(q)) {
                qi = dict.get(q);
            }
            if (pi == -1 || qi == -1) {
                result[index] = -1.0;
            } else {
                result[index] = uf.isConnect(pi, qi);
            }
            index++;
        }

        return result;
    }
}

class Union {
    int[] parents;
    double[] weights;

    public Union(int n) {
        parents = new int[n];
        weights = new double[n];
        Arrays.fill(weights, 1.0d);
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
    }

    public void connect(int p, int q, double v) {
        int pp = find(p);
        int qp = find(q);
        if (pp != qp) {
            parents[pp] = qp;
            weights[pp] = weights[q] * v / weights[p];
        }
    }

    public double isConnect(int p, int q) {
        int pp = find(p);
        int qp = find(q);
        if (pp != qp) {
            return -1.0;
        }
        else return weights[p] / weights[q];
    }

    private int find(int x) {
        if (x != parents[x]) {
            int origin = parents[x];
            parents[x] = find(origin);
            weights[x] *= weights[origin];
        }

        return parents[x];
    }
}