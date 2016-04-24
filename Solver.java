import java.util.ArrayList;

/**
 * This class contains the backtracking algorithm and helper function
 * used to solve a given Sudoku puzzle.
 * @author Ray Chen
 */
public class Solver {
	
	private Sudoku game;
	
	Solver(Sudoku game){
		this.game = game;
	}
	
	/**
	 * Calls the backtracking function used the solve the puzzle.
	 * Prints the solution if found. Else gives error message.
	 */
	public void solve(){
		if(backtrack(0, 0)){
			game.printGrid();
		} else{
			System.out.println("No solution found.");
		}
	}
	
	/**
	 * The recursive backtracking algorithm.
	 */
	public boolean backtrack(int row, int col){
		//Checks if the indexes are going off the grid.
		if(col == 9){
			//Check if we are at the final row.
			if(row == 8){
				//We reached the end of the grid and thus must have found a solution.
				return true;
			} else{
				//Go to next row
				return backtrack(row+1, 0);
			}
		}
		
		//If the current square is already filled in.
		if(game.getNum(row, col) != 0){
			return backtrack(row, col+1);
		}
		
		//Generate and try each valid number
		ArrayList<Integer> candidates = findCandidates(row, col);
		for(int n : candidates){
			game.setNum(row, col, n);
			boolean foundSoln = backtrack(row, col+1);
			if(foundSoln){
				return true;
			}
		}
		
		//No number was possible. Must have done something wrong earlier. Backtrack.
		game.setNum(row, col, 0);
		return false;
	}
	
	/**
	 * Given the row and column an empty square is located at,
	 * returns an ArrayList containing all the valid numbers
	 * that can be put into the square.
	 */
	public ArrayList<Integer> findCandidates(int row, int col){
		
		//Initialises the numbers 1-9 to be a possible candidate at the start
		boolean[] isValid = new boolean[10];
		for(int i = 1; i <= 9; i++){
			isValid[i] = true;
		}
		
		//Check the row for used numbers
		for(int i = 0; i < 9; i++){
			int n = game.getNum(row, i);
			isValid[n] = false;
		}
		
		//Check the column for used numbers
		for(int i = 0; i < 9; i++){
			int n = game.getNum(i, col);
			isValid[n] = false;
		}
				
		//Check the 3x3 square for used numbers
		
		//Calculate the location of the top left corner of the 3x3 square
		int startRow = (row/3) * 3;
		int startCol = (col/3) * 3;
		
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				int n = game.getNum(startRow+i, startCol+j);
				isValid[n] = false;
			}
		}
		
		//Converts the boolean array to an ArrayList with only the valid numbers
		ArrayList<Integer> candidates = new ArrayList<Integer>();
		for(int i = 1; i <= 9; i++){
			if(isValid[i]){
				candidates.add(i);	
			}
		}
		return candidates;
	}
	
    public static void main(String[] args) throws Exception{
    	Sudoku game = new Sudoku();
    	game.fillGrid();
    	
    	Solver solver = new Solver(game);
    	solver.solve();
    }
}
