package org.example.sudoku;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.sudoku.View.SudokuView;

import java.io.IOException;

/**
 * The main entry point of the Sudoku application.
 * This class extends the JavaFX Application class to launch the GUI.
 */
public class Main extends Application {

    /**
     * This method is called when the application starts.
     * It initializes the SudokuView, which sets up the main window.
     *
     * @param stage The primary stage for this application, onto which
     *              the application scene can be set.
     * @throws IOException If there is an issue initializing the SudokuView.
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Initialize and display the Sudoku game view
        SudokuView.getInstance();
    }

    /**
     * The main method that launches the JavaFX application.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Launch the JavaFX application, which triggers the start() method
        launch(args);
    }
}
