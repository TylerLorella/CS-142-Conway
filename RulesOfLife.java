public class RulesOfLife {

	static int gridWidth = 60;
	static int gridHeight = 65;
	static int generation = 0;
	static int numberOfGenerations = 10;
	static boolean[][] currentGrid = new boolean[gridHeight][gridWidth];

		//This program is suppose to replicate Conway's Game of Life with several different methods of generating a starting grid
		//there are two types of grids that get defined; a "currentGrid" that serves as the current generation and gets output directly to the user
		//and a temporary grid called "nextGrid" that gets generated during the beginning of each scan
		//and at the end of the scan the pointer for "currentGrid" is changed to the old "nextGrid"

	public static String converter(boolean state) {
		//converts the boolean state of the cell passed through into an easier to read format; X is alive, 0 is dead
		String toReturn = "";
		if (state == true) toReturn = "X";
		else if (state == false) toReturn = "-";
		return toReturn;
	}

	public static void printGrid(int generation) {
		//prints the currentGrid to the console
		for (int i = 0; i <= currentGrid.length-1; i++) {
			for (int j = 0; j <= currentGrid[0].length-1; j++) {
				System.out.print(converter(currentGrid[i][j]) + " ");
			}
			System.out.println(converter(currentGrid[i][0]) + " ");
		}
		System.out.println("Generation: " + generation + "\n");
	}

	public static void randomGrid(int gridHeight, int gridWidth) {
		//generations a random grid of given size with a 25% probability that a cell will be alive.
		boolean[][] randoGrid = new boolean[gridHeight][gridWidth];
		for (int i = 0; i <= randoGrid.length-1; i++) {
			for (int j = 0; j <= randoGrid[0].length-1; j++) {
				if (Math.random() <= 0.25) randoGrid[i][j] = true;
			}
		}
		currentGrid = randoGrid;
	}

	public static void randomGrid(int gridHeight, int gridWidth, double chance) {
		//overload of randomGrid() with a user defined size, and probability that each cell will be alive. 

		boolean[][] randoGrid = new boolean[gridHeight][gridWidth];
		for (int i = 0; i <= randoGrid.length-1; i++) {
			for (int j = 0; j <= randoGrid[0].length-1; j++) {
				if (Math.random() <= chance) randoGrid[i][j] = true;
			}
		}
		currentGrid = randoGrid;
	}

	public static boolean isAlive(int y, int x) {
		//counts the neighbors of the living cell that passed through and determines if it will die or continue on
		//try catch for scanning the boundaries of the grid where there will be outofbound exceptions
		boolean toReturn = true;
		int livingNeighbor = -1;
		
		for (int i = y-1; i <= y+1; i++) {
			for (int j = x-1; j <= x+1; j++) {
				try {
					if (currentGrid[i][j] == true) {
						livingNeighbor++;
					}
				} catch (Exception e) {
				}
			}

		}

		if (livingNeighbor < 2) toReturn = false;
		//killed through under population
		else if (livingNeighbor > 3) toReturn = false;
		//killed through over population
		return toReturn;
	}

	public static boolean isDead(int y, int x) {
		//when a dead cell is passed through this method checks to see if there is sufficient living neirghbors to bring the cell to life
		//try catch for scanning the boundaries of the grid where there will be outofbound exceptions
		boolean toReturn = false;
		int livingNeighbor = 0;

		for (int i = y-1; i <= y+1; i++) {
			for (int j = x-1; j <= x+1; j++) {
				try {
					if (currentGrid[i][j] == true) {
						livingNeighbor++;
					}
				} catch (Exception e) {

				}
			}
		}
		if (livingNeighbor == 3) toReturn = true;
		//raised by means of NECROMANCY or reproduction
		return toReturn;
	}

	public static void gridScan() {
		//method to scan the current grid and calls isAlive or isDead to determine what the next grid state will be and then copies the temp grid into the current grid
		boolean[][] nextGrid = new boolean[gridHeight][gridWidth];
		for (int i = 0; i <= currentGrid.length-1; i++) {
			for (int j = 0; j <= currentGrid[0].length-1; j++) {

				if (currentGrid[i][j] == true) {
					nextGrid[i][j] = isAlive(i,j);
				}
				else if (currentGrid[i][j] == false) {
					nextGrid[i][j] = isDead(i,j);
				}

			}
		}
		currentGrid = nextGrid;
	}
	
}
