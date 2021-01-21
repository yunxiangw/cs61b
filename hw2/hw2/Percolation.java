package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF checkFull;
    private WeightedQuickUnionUF checkPercolate;
    private boolean[][] records;
    private int N;
    private int count;

    /* Create N-by-N grid (plus two virtual sites), with all sites initially blocked */
    public Percolation(int N) {
        checkFull = new WeightedQuickUnionUF(N * N + 1);
        checkPercolate = new WeightedQuickUnionUF(N * N + 2);
        records = new boolean[N][N];
        count = 0;
        this.N = N;
        // checkPercolate has two virtual sites that are connected to top and bottom row respectively
        // checkFull has one virtual site that is connected to top row
        for (int i = 1; i < N + 1; i++) {
            checkPercolate.union(0, i);
            checkFull.union(0, i);
        }
        for (int i = N * (N - 1) + 1; i < N * N + 1; i++) {
            checkPercolate.union(N * N + 1, i);
        }
    }

    /* Open the site (row, col) if it is not open already */
    public void open(int row, int col) {
        if (isOpen(row, col)) return;
        records[row][col] = true;
        if (row - 1 >= 0 && isOpen(row - 1, col)) {
            checkPercolate.union(xyToIndex(row, col), xyToIndex(row - 1, col));
            checkFull.union(xyToIndex(row, col), xyToIndex(row - 1, col));
        }
        if (row + 1 <= N - 1 && isOpen(row + 1, col)) {
            checkPercolate.union(xyToIndex(row, col), xyToIndex(row + 1, col));
            checkFull.union(xyToIndex(row, col), xyToIndex(row + 1, col));
        }
        if (col - 1 >= 0 && isOpen(row, col - 1)) {
            checkPercolate.union(xyToIndex(row, col), xyToIndex(row, col - 1));
            checkFull.union(xyToIndex(row, col), xyToIndex(row, col - 1));
        }
        if (col + 1 <= N - 1 && isOpen(row, col + 1)) {
            checkPercolate.union(xyToIndex(row, col), xyToIndex(row, col + 1));
            checkFull.union(xyToIndex(row, col), xyToIndex(row, col + 1));
        }
        count++;
    }

    /* Check the site (row, col) is open or not */
    public boolean isOpen(int row, int col) {
        return records[row][col];
    }

    /* Check the site (row, col) is full or not */
    public boolean isFull(int row, int col) {
        return isOpen(row, col) && checkFull.connected(0, xyToIndex(row, col));
    }

    /* Number of open sites */
    public int numberOfOpenSites() {
        return count;
    }

    /* Check the system is percolate or not */
    public boolean percolates() {
        return checkPercolate.connected(0, checkPercolate.count() - 1);
    }

    /* Calculate index given the row and col */
    private int xyToIndex(int row, int col) {
        return row * N + col + 1;
    }

    public static void main(String[] args) {
        Percolation system = new Percolation(5);
    }
}
