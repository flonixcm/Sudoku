package org.example.sudoku.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.example.sudoku.Model.Sudoku;
import org.example.sudoku.View.Alert.AlertBox;

public class SudokuController {

    @FXML
    private GridPane SudokuGridpane;

    private Sudoku sudoku;

    public SudokuController() {
        // Inicializar el modelo del Sudoku
        sudoku = new Sudoku();
    }

    @FXML
    void OnActionHelpButton(ActionEvent event) {
        // Lógica de ayuda (opcional)
    }

    @FXML
    void OnActionHowButtom(ActionEvent actionEvent) {
        // Lógica para mostrar cómo jugar (opcional)
        new AlertBox().InformationAlert("Sudoku", "How to play Sudoku?",
                "Objective: Fill a 6x6 board with the numbers 1 to 6 without repeating them in rows, columns, or 2x3 subgrids.\n" +
                        "\n" +
                        "Starting the Game:\n" +
                        "\n" +
                        "Open the application and press \"Play\" to generate a new board with some pre-filled numbers (2 per subgrid).\n" +
                        "Playing:\n" +
                        "\n" +
                        "Click on an empty cell.\n" +
                        "Enter a number from 1 to 6 and it will be confirmed automatically.\n" +
                        "If valid, the border turns green.\n" +
                        "If invalid, the cell clears, and the border turns red.\n" +
                        "Tips:\n" +
                        "Use logic to complete the board.\n" +
                        "You can get help by pressing \"Help\" if you get stuck.\n" +
                        "Winning: Complete the board correctly with unique numbers in rows, columns, and subgrids.\n" +
                        "\n" +
                        "Enjoy the game!\n" +
                        "\n");
    }

    @FXML
    void OnActionPlayButton(ActionEvent event) {
        AlertBox alertBox = new AlertBox();
        boolean result = alertBox.confirmationAlert("Sudoku", "Are you sure you want to start a new game?", "This will erase your current progress.");

        // Si el usuario confirmó, generar un nuevo Sudoku
        if (result) {
            sudoku.generateRandomSudoku();
            updateGrid();  // Actualizar la vista con el nuevo tablero generado
        }
    }

    // Método para actualizar el GridPane con los valores del modelo
    private void updateGrid() {
        SudokuGridpane.getChildren().clear();  // Limpiar la cuadrícula anterior

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                TextField textField = new TextField();
                textField.setPrefWidth(50);
                textField.setPrefHeight(300);
                textField.setStyle("-fx-alignment: center; -fx-font-size: 24px;");
                int value = sudoku.getGrid()[row][col];

                if (value != 0) {
                    // Mostrar los valores generados (no editables)
                    textField.setText(String.valueOf(value));
                    textField.setEditable(false);  // Las celdas prellenadas no deben ser editables
                    textField.setStyle("-fx-alignment: center; -fx-font-size: 24px; -fx-border-color: green; -fx-border-width: 2px;"); // Bordes verdes para los generados
                } else {
                    // Las celdas vacías son editables por el usuario
                    textField.setEditable(true);
                    int finalRow = row;
                    int finalCol = col;

                    // Agregar un ChangeListener para manejar las entradas del usuario
                    textField.textProperty().addListener((observable, oldValue, newValue) -> {
                        if (!newValue.isEmpty()) {
                            try {
                                int newValueInt = Integer.parseInt(newValue);  // Convertir el texto a número
                                if (newValueInt >= 1 && newValueInt <= 6 && sudoku.isValid(finalRow, finalCol, newValueInt)) {
                                    // Si es un valor válido según las reglas, actualizar el modelo
                                    sudoku.setValue(finalRow, finalCol, newValueInt);
                                    textField.setStyle("-fx-alignment: center; -fx-font-size: 24px; -fx-border-color: green; -fx-border-width: 2px;"); // Bordes verdes si es válido
                                } else {
                                      // Limpiar la celda si el valor no es válido
                                    textField.setStyle("-fx-alignment: center; -fx-font-size: 24px; -fx-border-color: red; -fx-border-width: 2px;"); // Bordes rojos si no es válido
                                }
                            } catch (NumberFormatException ex) {
                                textField.setText("");  // Limpiar si no es un número
                                textField.setStyle("-fx-alignment: center; -fx-font-size: 24px; -fx-border-color: red; -fx-border-width: 2px;"); // Bordes rojos para entradas no numéricas
                            }
                        }
                    });
                }

                // Añadir el campo de texto al GridPane
                SudokuGridpane.add(textField, col, row);
            }
        }
    }
}
