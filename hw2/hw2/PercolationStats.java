package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {

    private double[] threshold;
    private int T;

    /* Perform T independent experiments on an N-by-N grid */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N and T must be larger than 0");
        }
        threshold = new double[T];
        this.T = T;
        for (int i = 0; i < T; i++) {
            Percolation sys = pf.make(N);
            while (!sys.percolates()) {
                sys.open(StdRandom.uniform(N), StdRandom.uniform(N));
            }
            threshold[i] = (double) sys.numberOfOpenSites() / (N * N);
        }

    }

    /* Calculate mean */
    public double mean() {
        return StdStats.mean(threshold);
    }

    /* Calculate standard deviation */
    public double stddev() {
        return StdStats.stddev(threshold);
    }

    /* Calculate lower bound of 95% confidence interval */
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    /* Calculate upper bound of 95% confidence interval */
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }
}
