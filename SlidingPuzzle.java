import java.util.*;

public class SlidingPuzzle {
    public static void main() {
        // int[][] board = {{1,2,3},{5,4,0}};
        int[][] board = {{4,1,2},{5,0,3}};
        SlidingPuzzle sp = new SlidingPuzzle();
        int result = sp.slidingPuzzle(board);
        System.out.println(result);
    }
    private HashMap<Integer, List<Pair>> solutions = new HashMap<>();
    private HashSet<String> visited = new HashSet<String>();
    public int slidingPuzzle(int[][] board) {
        List<Pair> s = new LinkedList<Pair>();
        s.add(new Pair(0, 1));
        s.add(new Pair(1, 0));
        solutions.put(0, s);

        List<Pair> s2 = new LinkedList<Pair>();
        s2.add(new Pair(0, 1));
        s2.add(new Pair(1, 0));
        s2.add(new Pair(0, -1));
        solutions.put(1, s2);

        List<Pair> s3 = new LinkedList<Pair>();
        s3.add(new Pair(0, -1));
        s3.add(new Pair(1, 0));
        solutions.put(2, s3);

        List<Pair> s4 = new LinkedList<Pair>();
        s4.add(new Pair(0, 1));
        s4.add(new Pair(-1, 0));
        solutions.put(10, s4);

        List<Pair> s5 = new LinkedList<Pair>();
        s5.add(new Pair(0, 1));
        s5.add(new Pair(-1, 0));
        s5.add(new Pair(0, -1));
        solutions.put(11, s5);

        List<Pair> s6 = new LinkedList<Pair>();
        s6.add(new Pair(0, -1));
        s6.add(new Pair(-1, 0));
        solutions.put(12, s6);

        LinkedList<Board> queue = new LinkedList<>();

        // add initial steps
        queue.addLast(new Board(board, 0));

        while (!queue.isEmpty()) {
            Board b = queue.poll();
            int[][] cBoard = b.getBoard();
            if (satisfy(cBoard)) {
                return b.getStep();
            }
            if (visited.contains(getSituation(cBoard))) {
                continue;
            }
            visited.add(getSituation(cBoard));
            Pair zero = getZeroLocation(cBoard);
            List<Pair> solution = solutions.get(zero.x * 10 + zero.y);
            for (Pair s1 : solution) {
                int[][] copyBoard = copySelf(cBoard);
                int tmp = copyBoard[zero.x][zero.y];
                copyBoard[zero.x][zero.y] = copyBoard[s1.x + zero.x][s1.y + zero.y];
                copyBoard[s1.x + zero.x][s1.y + zero.y] = tmp;
                queue.addLast(new Board(copyBoard, b.getStep() + 1));
            }
        }

        return -1;
    }

    private boolean satisfy(int[][] board) {
        return getSituation(board).equals("123450");
    }

    private int[][] copySelf(int[][] board) {
        int[][] copyBoard = new int[2][];
        for (int i = 0; i < 2; i++) {
            copyBoard[i] = new int[3];
            for (int j = 0; j < 3; j++) {
                copyBoard[i][j] = board[i][j];
            }
        }

        return copyBoard;
    }

    private String getSituation(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                int v = board[i][j];
                sb.append(v);
            }
        }

        return sb.toString();
    }

    private Pair getZeroLocation(int[][] board) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    return new Pair(i, j);
                }
            }
        }

        return null;
    }
}

class Pair {
    public int x;
    public int y;
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Board {
    private int[][] board;
    private int step;
    public Board(int[][] board, int step) {
        this.board = new int[2][];
        for (int i = 0; i < 2; i++) {
            this.board[i] = new int[3];
            for (int j = 0; j < 3; j++) {
                this.board[i][j] = board[i][j];
            }
        }
        this.step = step;
    }

    public int[][] getBoard() {
        return this.board;
    }
    public int getStep() {
        return this.step;
    }
}