package hw4.puzzle;
import edu.princeton.cs.algs4.Queue;

import java.util.Arrays;

public class Board implements WorldState{

    private int[][] tiles;
    private int[][] goal;
    private int N;

    public Board(int[][] tiles) {
        this.tiles = copy(tiles);
        N = tiles.length;
        goal = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                goal[i][j] = i * N + j + 1;
            }
        }
        goal[N - 1][N - 1] = 0;
    }

    /* Returns value of tile at row i, column j (or 0 if blank) */
    public int tileAt(int i, int j) {
        if (!inBounds(i, j)) {
            throw new IndexOutOfBoundsException("i and j should be between 0 and N-1");
        }
        return tiles[i][j];
    }

    /* Returns the board size N */
    public int size() {
        return N;
    }

    /**
     * Returns neighbors of this board.
     * copied from: http://joshh.ug/neighbors.html
     */
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == 0) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = 0;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = 0;
                }
            }
        }
        return neighbors;
    }

    /* Return Hamming distance */
    public int hamming() {
        int dist = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] != 0 && tiles[i][j] != goal[i][j]) {
                    dist++;
                }
            }
        }
        return dist;
    }

    /* Return Manhattan distance */
    public int manhattan() {
        int dist = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] != 0) {
                    int row = (tiles[i][j] - 1) / N;
                    int col = (tiles[i][j] - 1) % N;
                    dist += Math.abs(row - i) + Math.abs(col - j);
                }
            }
        }
        return dist;
    }

    /**
     * Estimated distance to goal
     * This method should simply return the results of manhattan() when submitted to Gradescope
     */
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }
        if (y == null || getClass() != y.getClass()) {
            return false;
        }

        Board b = (Board) y;
        return Arrays.deepEquals(tiles, b.tiles);

    }

    /** Returns the string representation of the board. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

    private boolean inBounds(int i, int j) {
        return i >= 0 && i < N && j >= 0 && j < N;
    }

    private int[][] copy(int[][] arr) {
        int N = arr.length;
        int[][] newArr = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newArr[i][j] = arr[i][j];
            }
        }
        return newArr;
    }
}
