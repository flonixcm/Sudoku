package org.example.sudoku.View;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class represents the view for the Sudoku game, extending the Stage in JavaFX.
 * It is responsible for loading the user interface from the FXML file and displaying the window.
 */
public class SudokuView extends Stage {

    /**
     * Constructor to initialize the SudokuView.
     * Loads the FXML file for the view, sets the window's title and icon, and displays the view.
     *
     * @throws IOException If there is an issue loading the FXML resource.
     */
    public SudokuView() throws IOException {
        // Load the FXML layout for the Sudoku view.
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/org/example/sudoku/sudoku-view.fxml")
        );
        Parent root = loader.load();  // Load the UI elements from the FXML.

        // Set the title of the Sudoku window.
        this.setTitle("Sudoku");

        // Create and set the scene with the loaded FXML.
        Scene scene = new Scene(root);

        // Set the window's icon.
        this.getIcons().add(new Image(getClass().getResourceAsStream("/org/example/sudoku/icons/favicon.png")));

        // Set the scene on this stage (window) and display it.
        this.setScene(scene);
        this.show();
    }

    /**
     * Singleton pattern to ensure only one instance of the SudokuView exists.
     * If no instance exists, it creates one; otherwise, it returns the existing instance.
     *
     * @return The single instance of SudokuView.
     * @throws IOException If there is an issue creating the instance.
     */
    public static SudokuView getInstance() throws IOException {
        // Check if an instance of SudokuView already exists.
        if (SudokuViewHolder.INSTANCE == null) {
            return SudokuViewHolder.INSTANCE = new SudokuView();  // Create a new instance if none exists.
        } else {
            return SudokuViewHolder.INSTANCE;  // Return the existing instance.
        }
    }

    /**
     * Static inner class to hold the singleton instance of SudokuView.
     */
    public static class SudokuViewHolder {
        // Static variable to hold the single instance of SudokuView.
        private static SudokuView INSTANCE;
    }
}
