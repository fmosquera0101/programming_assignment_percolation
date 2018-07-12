

public class Percolation {
	
	int n;
	// create n-by-n grid, with all sites blocked
	public Percolation(int n){
		this.n = n;
		if(n <= 0){
			throw new IllegalArgumentException("N can't be <= 0");
		}
		
	}
	
	// open site (row, col) if it is not open already
	public void open(int row, int col){
		if(row <= 0 || row > n ||col <= 0 || col > n){
			throw new IllegalArgumentException("N can't be <= 0");
		}
	}
	// is site (row, col) open?
	public boolean isOpen(int row, int col){
		return false;
	}
	 // is site (row, col) full?
	public boolean isFull(int row, int col){
		return false;
	}
	
	// number of open sites
	public int numberOfOpenSites(){
		return 0;
	}
	// does the system percolate?
	public boolean percolates(){
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
