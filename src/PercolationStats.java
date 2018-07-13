import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * 
 * @author fredy mosquera lemus
 * @date 10/07/2018
 */
public class PercolationStats {
	private int trials;
	private Percolation percolation;
	private double [] fractionOpenedSites;
	// perform trials independent experiments on an n-by-n grid
	public PercolationStats(int n, int trials){
		if(n <= 0 || trials <= 0){
			throw new IllegalArgumentException("n o trials can't be <= 0");
		}
		this.trials = trials;
		fractionOpenedSites = new double [trials];
		for(int i = 0; i < trials; i++){
			percolation = new Percolation(n);
			while(!percolation.percolates()){
				int row = StdRandom.uniform(1, n + 1);
				int col = StdRandom.uniform(1, n + 1);
				percolation.open(row, col);
			}
			fractionOpenedSites[i] = (double) percolation.numberOfOpenSites() / (n * n);
		}
		
	}
	// sample mean of percolation threshold
	public double mean() {
		return StdStats.mean(fractionOpenedSites);
		
	}
	// sample standard deviation of percolation threshold
	public double stddev(){
		return StdStats.stddev(fractionOpenedSites);
	}
	// low  endpoint of 95% confidence interval
	public double confidenceLo(){
		return mean() + ((1.96 * stddev()) / Math.sqrt(trials));
	}
	// high endpoint of 95% confidence interval
	public double confidenceHi(){
		return mean() - ((1.96 * stddev()) / Math.sqrt(trials));
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = Integer.parseInt(args[0]);
		int trials = Integer.parseInt(args[1]);
		PercolationStats percolationStats = new PercolationStats(n, trials);
		System.out.println("mean:						= "+percolationStats.mean());
		System.out.println("stddev:						= "+percolationStats.stddev());
		System.out.println("95% confidence interval				= "+"["+percolationStats.confidenceLo()+", "+percolationStats.confidenceHi()+"]");
		

	}

}
