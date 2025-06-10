public class SurroundedRegions {
    public static void main() {
        SurroundedRegions s = new SurroundedRegions();
        char[][] board = {{'O','O','O'},{'O','O','O'},{'O','O','O'}};
        s.solve(board);
    }
    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;

        UF uf = new UF(m * n + 1);
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') {
                uf.union(0*n + j, m*n);
            }
            if (board[m - 1][j] == 'O') {
                uf.union(n*(m-1) + j, m*n);
            }
        }
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                uf.union(i*n, m*n);
            }
            if (board[i][n-1] == 'O') {
                uf.union(i*n + n - 1, m*n);
            }
        }

        for (int i = 1; i < m -1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (board[i][j] == 'O') {
                    if (board[i - 1][j] == 'O') uf.union(i*n + j, (i - 1)*n+j);
                    if (board[i + 1][j] == 'O') uf.union(i*n + j, (i + 1)*n+j);
                    if (board[i][j - 1] == 'O') uf.union(i*n + j, i*n+j - 1);
                    if (board[i][j + 1] == 'O') uf.union(i*n + j, i*n+j + 1);
                }
            }
        }

        for (int i = 1; i < m -1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (board[i][j] == 'O') {
                    if (!uf.isConnect(m*n, i*n+j)) {
                        board[i][j] = 'X';
                    }
                }
            }
        }
    }
}
