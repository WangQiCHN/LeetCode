package first;
import java.util.LinkedList;

import SurroundedRegions;

import java.util.HashSet;

public class SurroundedRegions {
    public static void main() {
        SurroundedRegions s = new SurroundedRegions();
        // char[][] board = new char[4][];
        // board[0] = new char[] { 'X', 'X', 'X', 'X' };
        // board[1] = new char[] { 'X', 'O', 'O', 'X' };
        // board[2] = new char[] { 'X', 'X', 'O', 'X' };
        // board[3] = new char[] { 'X', 'O', 'X', 'X' };
        // char[][] board = new char[3][];
        // board[0] = new char[] { 'O', 'O', 'O' };
        // board[1] = new char[] { 'O', 'O', 'O' };
        // board[2] = new char[] { 'O', 'O', 'O' };
        char[][] board = {
                { 'X', 'O', 'O', 'X', 'X', 'X', 'O', 'X', 'O', 'O' },
                { 'X', 'O', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
                { 'X', 'X', 'X', 'X', 'O', 'X', 'X', 'X', 'X', 'X' },
                { 'X', 'O', 'X', 'X', 'X', 'O', 'X', 'X', 'X', 'O' },
                { 'O', 'X', 'X', 'X', 'O', 'X', 'O', 'X', 'O', 'X' },
                { 'X', 'X', 'O', 'X', 'X', 'O', 'O', 'X', 'X', 'X' },
                { 'O', 'X', 'X', 'O', 'O', 'X', 'O', 'X', 'X', 'O' },
                { 'O', 'X', 'X', 'X', 'X', 'X', 'O', 'X', 'X', 'X' },
                { 'X', 'O', 'O', 'X', 'X', 'O', 'X', 'X', 'O', 'O' },
                { 'X', 'X', 'X', 'O', 'O', 'X', 'O', 'X', 'X', 'O' }
            };

        s.solve(board);
    }

    private class Pair {
        public int x;
        public int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        LinkedList<Pair> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        int row = 0;
        for (int i = 0; i < n; i++) {
            if (board[row][i] == 'O') {
                queue.add(new Pair(row, i));
                visited.add(row * 1000 + i);
            }
        }
        row = m - 1;
        for (int i = 0; i < n; i++) {
            if (board[row][i] == 'O') {
                queue.add(new Pair(row, i));
                visited.add(row * 1000 + i);
            }
        }
        row = 0;
        for (int j = 1; j < m - 1; j++) {
            if (board[j][row] == 'O') {
                queue.add(new Pair(j, row));
                visited.add(j * 1000 + row);
            }
        }
        row = n - 1;
        for (int j = 1; j < m - 1; j++) {
            if (board[j][row] == 'O') {
                queue.add(new Pair(j, row));
                visited.add(j * 1000 + row);
            }
        }

        while (!queue.isEmpty()) {
            Pair first = queue.removeFirst();
            int x = first.x;
            int y = first.y;

            if (x - 1 >= 0) {
                char v = board[x - 1][y];
                if (v == 'O' && !visited.contains((x - 1) * 1000 + y)) {
                    queue.add(new Pair(x - 1, y));
                    visited.add((x - 1) * 1000 + y);
                }
            }
            if (x + 1 < m && board[x + 1][y] == 'O') {
                if (!visited.contains((x + 1) * 1000 + y)) {
                    queue.add(new Pair(x + 1, y));
                    visited.add((x + 1) * 1000 + y);
                }
            }
            if (y - 1 >= 0 && board[x][y - 1] == 'O') {
                if (!visited.contains(x * 1000 + y - 1)) {
                    queue.add(new Pair(x, y - 1));
                    visited.add(x * 1000 + y - 1);
                }
            }
            if (y + 1 < n && board[x][y + 1] == 'O') {
                if (!visited.contains(x * 1000 + y + 1)) {
                    queue.add(new Pair(x, y + 1));
                    visited.add(x * 1000 + y + 1);
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int key = i * 1000 + j;
                if (!visited.contains(key)) {
                    board[i][j] = 'X';
                }
            }
        }
        System.out.println(board);
    }
}