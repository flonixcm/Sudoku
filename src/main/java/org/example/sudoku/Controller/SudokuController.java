package org.example.sudoku.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.example.sudoku.Model.Sudoku;
import org.example.sudoku.View.Alert.AlertBox;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SudokuController {

    @FXML
    private GridPane SudokuGridpane; // The GridPane that holds the Sudoku cells in the UI.

    private Sudoku sudoku; // The Sudoku model that manages the logic of the game.

    // Constructor for SudokuController
    public SudokuController() {
        // Initialize the Sudoku model.
        sudoku = new Sudoku();
    }

    @FXML
        /*
         * Method invoked when the user clicks the "Help" button.
         * Reveals a random empty cell by updating it with the correct value from the full grid.
         */
    void OnActionHelpButton(ActionEvent event) {
        // Get the list of empty cells (cells with a value of 0).
        List<int[]> emptyCells = new ArrayList<>();
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                if (sudoku.getGrid()[row][col] == 0) {
                    emptyCells.add(new int[]{row, col});
                }
            }
        }

        // Check if there are any empty cells left.
        if (!emptyCells.isEmpty()) {
            // Select a random empty cell from the list.
            Random random = new Random();
            int[] cell = emptyCells.get(random.nextInt(emptyCells.size()));

            int row = cell[0];
            int col = cell[1];

            // Get the correct value for this cell from the completed grid.
            int value = sudoku.getFullGridValue(row, col);

            // Update the model with the revealed value.
            sudoku.setValue(row, col, value);

            // Refresh the view to reflect the updated model.
            updateGrid();
        } else {
            // If no empty cells remain, show an alert.
            new AlertBox().InformationAlert("Sudoku", "No empty cells", "There are no more empty cells to reveal.");
        }
    }

    @FXML
        /*
         * Method invoked when the user clicks the "How to Play" button.
         * Displays information about how to play the Sudoku game.
         */
    void OnActionHowButtom(ActionEvent actionEvent) {
        // Display an informational alert with instructions on how to play Sudoku.
        new AlertBox().InformationAlert("Sudoku", "How to play Sudoku?",
                "Objective: Fill a 6x6 board with the numbers 1 to 6 without repeating them in rows, columns, or 2x3 subgrids.\n" +
                        "\n" +
                        "Starting the Game:\n" +
                        "Open the application and press \"Play\" to generate a new board with some pre-filled numbers (2 per subgrid).\n" +
                        "Playing:\n" +
                        "Click on an empty cell.\n" +
                        "Enter a number from 1 to 6 and it will be confirmed automatically.\n" +
                        "If valid, the border turns green.\n" +
                        "If invalid, the cell clears, and the border turns red.\n" +
                        "Tips:\n" +
                        "Use logic to complete the board.\n" +
                        "You can get help by pressing \"Help\" if you get stuck.\n" +
                        "Winning: Complete the board correctly with unique numbers in rows, columns, and subgrids.\n" +
                        "\n" +
                        "Enjoy the game!\n");
    }

    @FXML
        /*
         * Method invoked when the user clicks the "Play" button.
         * Starts a new Sudoku game by generating a new board and updating the view.
         */
    void OnActionPlayButton(ActionEvent event) {
        AlertBox alertBox = new AlertBox();
        // Ask the user for confirmation to start a new game, which will erase the current progress.
        boolean result = alertBox.confirmationAlert("Sudoku", "Are you sure you want to start a new game?", "This will erase your current progress.");

        // If the user confirms, generate a new Sudoku puzzle using the backtracking algorithm.
        if (result) {
            sudoku.generateSudoku();  // Generate a new Sudoku puzzle.
            updateGrid();  // Refresh the view with the newly generated puzzle.
        }
    }

    /*
     * Method to update the GridPane in the view with the current state of the Sudoku model.
     * It reflects changes in the model by updating the visual representation of the grid.
     */
    private void updateGrid() {
        SudokuGridpane.getChildren().clear();  // Clear the previous grid.

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                // Create a TextField for each cell.
                TextField textField = new TextField();
                textField.setPrefWidth(50);  // Set cell width.
                textField.setPrefHeight(100);  // Set cell height.
                textField.setStyle("-fx-alignment: center; -fx-font-size: 24px;");  // Center text and set font size.

                int value = sudoku.getGrid()[row][col];  // Get the value for this cell from the Sudoku model.

                if (value != 0) {
                    // If the cell has a value, display it and make the cell non-editable.
                    textField.setText(String.valueOf(value));
                    textField.setEditable(false);
                    textField.setStyle(textField.getStyle() + "-fx-border-color: green; -fx-border-width: 2px;");  // Set green border for preset values.
                } else {
                    // If the cell is empty, make it editable by the user.
                    textField.setEditable(true);
                    int finalRow = row;
                    int finalCol = col;

                    // Add a ChangeListener to handle user input.
                    textField.textProperty().addListener((observable, oldValue, newValue) -> {
                        if (!newValue.isEmpty() && newValue.length() == 1) {
                            try {
                                // Convert the text input to a number.
                                int newValueInt = Integer.parseInt(newValue);
                                // Check if the number is valid (between 1 and 6) and follows Sudoku rules.
                                if (newValueInt >= 1 && newValueInt <= 6 && sudoku.isValid(finalRow, finalCol, newValueInt)) {
                                    // If valid, update the model.
                                    sudoku.setValue(finalRow, finalCol, newValueInt);
                                    textField.setStyle(textField.getStyle() + "-fx-border-color: green; -fx-border-width: 2px;");  // Set green border for valid inputs.
                                } else {
                                    textField.setStyle(textField.getStyle() + "-fx-border-color: red; -fx-border-width: 2px;");  // Set red border for invalid inputs.
                                }
                            } catch (NumberFormatException ex) {
                                textField.setText("");  // Clear the input if it's not a valid number.
                                textField.setStyle(textField.getStyle() + "-fx-border-color: red; -fx-border-width: 2px;");  // Set red border for non-numeric inputs.
                            }
                        }
                    });
                }
                // Add the TextField to the GridPane at the corresponding row and column.
                SudokuGridpane.add(textField, col, row);
            }
        }
    }
}
