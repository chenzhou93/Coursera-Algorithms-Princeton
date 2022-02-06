/* *****************************************************************************
 *  Name:              Chen Zhou
 *  Coursera User ID:  c0a3c14e1095421aaa0a72362b56de50
 *  Last modified:     02/06/2022
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int trails;
    private double[] expResults;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n or trails have to be greater than 0");
        }
        this.trails = trials;
        expResults = new double[n];
        for (int i = 0; i < trails; i++) {
            Percolation p = new Percolation(n);
            //Keep randomly open sites until gird percolates
            while (!p.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                p.open(row, col);
            }
            expResults[i] = (double) p.numberOfOpenSites() / (n * n);
        }

    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(expResults);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(expResults);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(trails));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(trails));
    }

    // test client (see below)
    public static void main(String[] args) {

    }
}
