import edu.princeton.cs.algs4.WeightedQuickUnionUF;


/**
 * 
 * @author fredy mosquera lemus
 * @date 10/07/2018
 */
public class Percolation {

	private int n;
	private boolean [][] grid;
	private int source = 0;
	private int sink;
	private int openSites = 0;
	private WeightedQuickUnionUF weightedQuickUnionUF;
	// create n-by-n grid, with all sites blocked
	public Percolation(int n){
		if(n <= 0){
			throw new IllegalArgumentException("N can't be <= 0");
		}

		this.n = n;
		sink = n * n + 1;
		weightedQuickUnionUF = new WeightedQuickUnionUF(n * n + 2);
		grid = new boolean [n][n];

	}

	// open site (row, col) if it is not open already
	public void open(int row, int col){
		if(row <= 0 || row > n ||col <= 0 || col > n){
			throw new IllegalArgumentException("Verify row or col can't be <= 0 or > n");
		}
		if(!isOpen(row, col)){
			grid[row - 1][col - 1] = true;
			unionVirtualTopSite(row, col);
			unionVirtualBottomSite(row, col);
			unionTopNeighbor(row, col);
			unionBottomNeighbor(row, col);
			unionLeftNeighbor(row, col);
			unionRightNeighbor(row, col);
			openSites++;
		}


	}

	private void unionTopNeighbor(int row, int col) {
		if(col > 1 && isOpen(row , col - 1)){
			weightedQuickUnionUF.union(getNumberRowCol(row, col), getNumberRowCol(row , col - 1));
		}
	}

	private void unionBottomNeighbor(int row, int col) {
		if(col < n && isOpen(row , col + 1)){
			weightedQuickUnionUF.union(getNumberRowCol(row, col), getNumberRowCol(row , col + 1));
		}
	}

	private void unionRightNeighbor(int row, int col) {
		if(row < n && isOpen(row + 1, col)){
			weightedQuickUnionUF.union(getNumberRowCol(row, col), getNumberRowCol(row + 1, col));
		}
	}

	private void unionLeftNeighbor(int row, int col) {
		if(row > 1 && isOpen(row - 1, col)){
			weightedQuickUnionUF.union(getNumberRowCol(row , col), getNumberRowCol(row - 1, col));
		}
	}

	private void unionVirtualBottomSite(int row, int col) {
		if(row == n){
			weightedQuickUnionUF.union(getNumberRowCol(row, col), sink);
		}
	}

	private void unionVirtualTopSite(int row, int col) {
		if(row == 1){
			weightedQuickUnionUF.union(getNumberRowCol(row, col), source);
		}
	}
	// is site (row, col) open?
	public boolean isOpen(int row, int col){
		if(row <= 0 || row > n ||col <= 0 || col > n){
			throw new IllegalArgumentException("Verify row or col can't be <= 0 or > n");
		}
		return grid[row - 1][col - 1];
	}
	// is site (row, col) full?
	public boolean isFull(int row, int col){
		if(row <= 0 || row > n ||col <= 0 || col > n){
			throw new IllegalArgumentException("Verify row or col can't be <= 0 or > n");
		}else{
			return weightedQuickUnionUF.connected(row, col);
		}
	}

	// number of open sites
	public int numberOfOpenSites(){
		return openSites;
	}
	// does the system percolate?
	public boolean percolates(){
		return weightedQuickUnionUF.connected(source, sink);
	}
	public int getNumberRowCol(int row, int col){
		return n  * (row - 1) + col;
	}

	/*public static void main(String[] args) {

	}*/

}
