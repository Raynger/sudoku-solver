import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * This class represents the board of a Sudoku puzzle.
 * It is responsible for initialising the game grid with initial values.
 * The digit 0 is used for unknown values.
 * @Author Ray Chen
 */

class Sudoku{
    private int[][] grid;

    Sudoku(){
        grid = new int[9][9];
    }
    
    /**
     * Reads in the initial game state from file into a 2-D array,
     * representing the Sudoku grid. 
     */
    public void fillGrid() throws FileNotFoundException{
    	Scanner sc = null;
    	
    	//Checks if there is a file.
    	try{
            sc = new Scanner(new FileReader("game.txt"));
    	} catch (FileNotFoundException e){
    		throw new FileNotFoundException("File not found. Make sure you have a file called 'game.txt' in the directory");
    	}
    	
        for(int i = 0; i < 9; i++){
            String row = sc.nextLine();
            for(int j = 0; j < 9; j++){
                char digit = row.charAt(j);
                if(digit != '_'){
                    //If digit is known
                    grid[i][j] = digit - '0'; //Character to integer conversion
                }
            }
        }
        sc.close();
    }
    
    /**
     * Prints the representation of the Sudoku grid
     */
    public void printGrid(){
    	for(int i = 0; i < 9; i++){
    		for(int j = 0; j < 9; j++){
    			if(grid[i][j] == 0){
    				System.out.print("_");
    			} else{
            		System.out.print(grid[i][j]);
    			}
    		}
    		System.out.println();
    	}
    }
    
    /**
     * @return the number located at the row and column in the grid
     */
    public int getNum(int row, int col){
    	return grid[row][col];
    }
    
    /**
     * Sets a number in the grid given the row and column
     */
    public void setNum(int row, int col, int num){
    	grid[row][col] = num;
    }
}
