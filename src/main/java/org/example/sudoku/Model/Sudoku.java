package org.example.sudoku.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * This class represents the model for a 6x6 Sudoku game. It includes methods for generating
 * a solvable Sudoku grid, validating number placement, and managing the game state.
 */
public class Sudoku {
    private int[][] grid;  // The current state of the Sudoku grid.
    private int[][] fullGrid;  // Stores the completed grid before removing numbers for the puzzle.
    private Random random;  // Random number generator for shuffling and selecting random values.

    /**
     * Constructor to initialize the Sudoku model.
     * Initializes the Sudoku grid and the completed grid arrays.
     */
    public Sudoku() {
        grid = new int[6][6];  // Initializes the grid to a 6x6 matrix.
        fullGrid = new int[6][6];  // Stores the fully solved grid.
        random = new Random(System.currentTimeMillis());  // Initializes random generator with system time.
    }

    /**
     * Method to generate a new solvable Sudoku puzzle.
     * Fills the grid with valid numbers and removes some to create a playable puzzle.
     */
    public void generateSudoku() {
        grid = new int[6][6];  // Reset the grid for each new generation.
        fillGrid();  // Fill the grid with a complete solution using backtracking.
        copyFullGrid();  // Store the full grid before removing numbers.
        removeNumbers();  // Remove numbers while maintaining 2 numbers per 2x3 block.
    }

    /**
     * Copies the full Sudoku grid to another array before making it into a playable puzzle.
     */
    private void copyFullGrid() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                fullGrid[i][j] = grid[i][j];  // Copies the grid values.
            }
        }
    }

    /**
     * Retrieves the value from the fully solved grid at a specified position.
     *
     * @param row The row of the cell.
     * @param col The column of the cell.
     * @return The value from the full grid at the specified position.
     */
    public int getFullGridValue(int row, int col) {
        return fullGrid[row][col];
    }

    /**
     * Fills the grid with valid numbers using a backtracking algorithm.
     *
     * @return true if the grid is successfully filled; false if backtracking is needed.
     */
    private boolean fillGrid() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                if (grid[row][col] == 0) {  // If the cell is empty
                    List<Integer> numbers = generateShuffledNumbers();  // Generate a random list of numbers 1 to 6.
                    for (int num : numbers) {
                        if (isValid(row, col, num)) {  // Check if the number is valid at the current position.
                            grid[row][col] = num;  // Assign the number to the cell.
                            if (fillGrid()) {  // Recursive call to fill the next empty cell.
                                return true;
                            }
                            grid[row][col] = 0;  // Reset the cell if the placement is invalid (backtrack).
                        }
                    }
                    return false;  // If no valid number is found, backtrack.
                }
            }
        }
        return true;  // Grid is completely filled.
    }

    /**
     * Generates a shuffled list of numbers from 1 to 6.
     *
     * @return A shuffled list of integers from 1 to 6.
     */
    private List<Integer> generateShuffledNumbers() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            numbers.add(i);  // Add numbers 1 to 6 to the list.
        }
        Collections.shuffle(numbers, random);  // Shuffle the list randomly.
        return numbers;
    }

    /**
     * Validates if a number can be placed at a specific position in the grid.
     * Checks the row, column, and 2x3 subgrid for conflicts.
     *
     * @param row The row of the cell.
     * @param col The column of the cell.
     * @param num The number to validate.
     * @return true if the number can be placed, false otherwise.
     */
    public boolean isValid(int row, int col, int num) {
        // Check if the number is already present in the row or column.
        for (int i = 0; i < 6; i++) {
            if (grid[row][i] == num || grid[i][col] == num) {
                return false;  // Number already exists in the row or column.
            }
        }

        // Check if the number is already present in the 2x3 subgrid.
        int startRow = row - row % 2;
        int startCol = col - col % 3;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[startRow + i][startCol + j] == num) {
                    return false;  // Number already exists in the 2x3 subgrid.
                }
            }
        }

        return true;  // The number is valid for this position.
    }

    /**
     * Removes numbers from the grid to create a playable puzzle, keeping 2 numbers per 2x3 subgrid.
     */
    private void removeNumbers() {
        for (int blockRow = 0; blockRow < 6; blockRow += 2) {
            for (int blockCol = 0; blockCol < 6; blockCol += 3) {
                List<int[]> positions = new ArrayList<>();
                // Collect all positions in the current 2x3 subgrid.
                for (int row = blockRow; row < blockRow + 2; row++) {
                    for (int col = blockCol; col < blockCol + 3; col++) {
                        positions.add(new int[]{row, col});
                    }
                }
                Collections.shuffle(positions, random);  // Shuffle positions in the block.

                // Remove all but 2 numbers in the 2x3 subgrid.
                for (int i = 2; i < positions.size(); i++) {
                    int[] pos = positions.get(i);
                    grid[pos[0]][pos[1]] = 0;  // Set the cell to 0 (empty).
                }
            }
        }
    }

    /**
     * Returns the current state of the Sudoku grid.
     *
     * @return The 6x6 Sudoku grid.
     */
    public int[][] getGrid() {
        return grid;
    }

    /**
     * Updates a specific cell in the grid with a given value if valid.
     *
     * @param row The row of the cell.
     * @param col The column of the cell.
     * @param value The value to place in the cell.
     */
    public void setValue(int row, int col, int value) {
        if (isValid(row, col, value)) {  // Validate the value before placing it.
            grid[row][col] = value;  // Update the cell with the value.
        }
    }
    public boolean isCompleted() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                // Check if any cell is empty or the number doesn't match the solution
                if (grid[row][col] == 0 || grid[row][col] != fullGrid[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }

}

