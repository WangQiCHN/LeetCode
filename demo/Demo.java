package demo;
import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main() {
        Demo d = new Demo();
        char[][] board = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        d.solve(board);
    }

    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        UF uf = new UF(m * n + 1);
        int dummy = m*n;

        for (int i = 0; i < n; i++) {
            if (board[0][i] == 'O') {
                uf.connect(dummy, i);
            }
            if (board[m - 1][i] == 'O') {
                uf.connect(dummy,(m - 1) * n + i);
            }
        }
        for (int j = 0; j < m; j++) {
            if (board[j][0] == 'O') {
                uf.connect(dummy, j * n);
            }
            if (board[j][n - 1] == 'O') {
                uf.connect(dummy, j * n + n - 1);
            }
        }

        for (int i = 1; i < m -1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (board[i][j] == '0') {
                    if (board[i - 1][j] == 'O') {
                        uf.connect(i * n + j, (i - 1) * n + j);
                    }
                    if (board[i + 1][j] == 'O') {
                        uf.connect(i * n + j, (i + 1) * n + j);
                    }
                    if (board[i][j - 1] == 'O') {
                        uf.connect(i * n + j, i * n + j - 1);
                    }
                    if (board[i][j + 1] == 'O') {
                        uf.connect(i * n + j, i * n + j + 1);
                    }
                }
            }
        }

        for (int i = 1; i < m -1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (board[i][j] == 'O') {
                    if (!uf.isConnect(i * n + j, dummy)) {
                        board[i][j] = 'X';
                    }
                }
            }
        }
    }

}


class UF {
    int count;
    int[] parents;

    public UF(int size) {
        this.count = size;
        parents = new int[size];
        for (int i = 0; i < count; i++) {
            parents[i] = i;
        }
    }

    public void connect(int p, int q) {
        int parentP = find(p);
        int parentQ = find(q);

        if (parentP == parentQ) {
            return;
        }

        parents[q] = parentP;
        parents[parentQ] = parentP;
        count--;
    }

    private int find(int p) {
        List<Integer> buffer = new ArrayList<>();
        while (parents[p] != p) {
            buffer.add(p);
            p = parents[p];
        }

        for (Integer b : buffer) {
            parents[b] = p;
        }

        return p;
    }

    public int getCount() {
        return count;
    }

    public boolean isConnect(int p, int q) {
        int parentP = find(p);
        int parentQ = find(q);

        return (parentP == parentQ);
    }
}