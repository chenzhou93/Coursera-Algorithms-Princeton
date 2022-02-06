/* *****************************************************************************
 *  Name:              Chen Zhou
 *  Coursera User ID:  c0a3c14e1095421aaa0a72362b56de50
 *  Last modified:     02/06/2022
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] grid;
    private final WeightedQuickUnionUF unionFind;
    private int totalOpenedSites;
    private final int n;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be larger than 0");
        }
        this.n = n;

        /* Initialize grid */
        grid = new int[n + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                grid[i][j] = 0;
            }
        }

        /* Initialize Union Find Object */
        unionFind = new WeightedQuickUnionUF((n + 1) * (n + 1));

        totalOpenedSites = 0;
    }

    /* opens the site (row, col) if it is not open already */
    public void open(int row, int col) {
        // System.out.println("open Site: " + row + ", " + col);
        /* Edge case */
        if (row <= 0 || col <= 0) {
            throw new IllegalArgumentException("row or col must be larger than 0");
        }

        if (!isOpen(row, col)) {
            grid[row][col] = 1;

            /* Up */
            if ((row - 1 > 0) && isOpen(row - 1, col)) {
                // System.out.println("link Up: " + (row - 1) + ", " + col);
                /* Find mapped number(position) in UF array(list) */
                int q = unionFind.find(n * (row - 2) + col);

                unionFind.union(n * (row - 1) + col, q);

            }

            /* Down */
            if ((row + 1 <= n) && isOpen(row + 1, col)) {
                // System.out.println("link Down: " + (row + 1) + ", " + col);
                /* Find mapped number(position) in UF array(list) */
                int q = unionFind.find(n * (row) + col);

                unionFind.union(n * (row - 1) + col, q);

            }

            /* Left */
            if ((col - 1 > 0) && isOpen(row, col - 1)) {
                // System.out.println("link left: " + row + ", " + (col - 1));
                /* Find mapped number(position) in UF array(list) */
                int q = unionFind.find(n * (row - 1) + (col - 1));

                unionFind.union(n * (row - 1) + col, q);

            }

            /* Right */
            if ((col + 1 <= n) && isOpen(row, col + 1)) {
                // System.out.println("link right: " + row + ", " + (col + 1));
                /* Find mapped number(position) in UF array(list) */
                int q = unionFind.find(n * (row - 1) + (col + 1));

                unionFind.union(n * (row - 1) + col, q);

            }

            totalOpenedSites++;
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || col <= 0) {
            throw new IllegalArgumentException("row or col must be larger than 0");
        }
        return (grid[row][col] == 1);
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row <= 0 || col <= 0) {
            throw new IllegalArgumentException("row or col must be larger than 0");
        }
        boolean isFullResult = false;
        int q = unionFind.find(n * (row - 1) + col);
        // System.out.println("q: " + q);
        if (isOpen(row, col)) {

            if (n * (row - 1) + col <= n) {
                isFullResult = true;
            }

            // Using a loop in this method might be a performance bug. [Performance]
            for (int i = 1; i <= n; i++) {
                if (isOpen(1, i) && (unionFind.find(i) == q)) {
                    isFullResult = true;
                }
            }

        }
        // System.out.println("Check Full: " + row + ", " + col + " result: " + isFullResult);
        return isFullResult;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return totalOpenedSites;
    }

    // does the system percolate?
    public boolean percolates() {
        for (int j = 1; j <= n; j++) {
            if (isFull(n, j)) {
                return true;
            }
        }
        return false;
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(10);
        p.open(-1, 5);
    }
}
