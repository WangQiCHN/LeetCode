public class NumberConnectedComponents {
    public static void main() {
        NumberConnectedComponents n = new NumberConnectedComponents();
        int[][] edges = {
            {0,1}, {1,2},{0,2},{3,4}
        };
        n.countComponents(5, edges);
    }
    public int countComponents(int n, int[][] edges) {
        UF uf = new UF(n);
        for (int[] e : edges) {
            int p = e[0];
            int q = e[1];
            uf.union(p, q);
        }

        return uf.getCount();
    }
}

class UF {
    private int count;
    private int[] parents;

    public UF(int n) {
        this.count = n;
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
    }

    public void union(int p, int q) {
        int parentP = findParent(p);
        int parentQ = findParent(q);

        if (parentP == parentQ) return;
        parents[parentP] = parentQ;
        count--;
    }

    public int getCount() {
        return count;
    }

    private int findParent(int x) {
        while (parents[x] != x) {
            parents[x] = parents[parents[x]];
            x = parents[x];
        }

        return x;
    }
}
