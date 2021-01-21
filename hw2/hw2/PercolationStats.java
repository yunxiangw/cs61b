package hw2;
import edu.princeton.cs.introcs.StdRandom;

public class PercolationStats {

    private int[] threshold;
    private int T;

    /* perform T independent experiments on an N-by-N grid */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N and T must be larger than 0");
        }
        threshold = new int[T];
        this.T = T;
        for (int i = 0; i < T; i++) {
            Percolation sys = pf.make(N);
            while (!sys.percolates()) {
                sys.open(StdRandom.uniform(N), StdRandom.uniform(N));
                threshold[i]++;
            }
        }

    }

    public double mean() {
        double sum = 0;
        for (int x: threshold) {
            sum += x;
        }
        return sum / T;
    }

    public double stddev() {
        double sigma = 0;
        double mu = mean();
        for (int x: threshold) {
            sigma += (x - mu) * (x - mu);
        }
        sigma = Math.sqrt(sigma / (T - 1));
        return sigma;
    }

    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }
}
