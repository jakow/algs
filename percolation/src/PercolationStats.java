/**
 * Created by jakub on 24/01/2017.
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private int t; // store for later
    private int n; // store for later
    private double[] threshold;
    private double m; // mean, calculated once
    private double sd; // stddev, calculated once


    public PercolationStats(int size, int trials) {

        if (size <= 0) throw new IllegalArgumentException("n of 0 or less");
        if (trials <= 0) throw new IllegalArgumentException("non-positive trials");

        t = trials;
        n = size;
        int row, col;
        Percolation p;
        threshold = new double[trials];
        for (int i = 0; i < trials; ++i) {
            p = new Percolation(n);
            do {
                row = StdRandom.uniform(1, n + 1); // StdRandom.uniform range end is exclusive
                col = StdRandom.uniform(1, n + 1);
                p.open(row, col);
            } while (!p.percolates());
            threshold[i] = (double) p.numberOfOpenSites() / (n * n);
        }
        m = StdStats.mean(threshold);
        sd = StdStats.stddev(threshold);
    }

    public double mean() {
        return m;
    }

    public double stddev() {
        return sd;
    }

    public double confidenceLo() {
        return m - 1.96 * sd / Math.sqrt((double) t);
    }

    public double confidenceHi() {
        return m + 1.96 * sd / Math.sqrt((double) t);
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Command line parameters missing. Pass N and T to the main routine");
        }
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);
        StdOut.println("mean\t\t\t= " + stats.mean());
        StdOut.println("stddev\t\t\t= " + stats.stddev());
        StdOut.println("95% confidence interval\t= " + stats.confidenceLo() + ", " + stats.confidenceHi());

    }
}
