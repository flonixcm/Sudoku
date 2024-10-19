package org.example.sudoku.View.Alert;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * The AlertBox class provides methods to show various alert dialogs in the application.
 * It implements the AlertBoxInterface to standardize alert functionality.
 */
public class AlertBox implements AlertBoxInterface {

    /**
     * Displays an information alert with the specified title, header, and message.
     *
     * @param title   The title of the alert dialog.
     * @param header  The header text of the alert dialog.
     * @param message The content message of the alert dialog.
     */
    @Override
    public void InformationAlert(String title, String header, String message) {
        // Create an information alert of type INFORMATION
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title); // Set the title of the alert dialog
        alert.setHeaderText(header); // Set the header of the alert dialog
        alert.setContentText(message); // Set the content message of the alert dialog
        alert.showAndWait(); // Show the alert dialog and wait for the user to close it
    }

    /**
     * Displays a confirmation alert with the specified title, header, and message.
     * This alert prompts the user for confirmation and returns their choice.
     *
     * @param title   The title of the confirmation alert.
     * @param header  The header text of the confirmation alert.
     * @param message The content message of the confirmation alert.
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean confirmationAlert(String title, String header, String message) {
        // Create a confirmation alert of type CONFIRMATION
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title); // Set the title of the alert dialog
        alert.setHeaderText(header); // Set the header of the alert dialog
        alert.setContentText(message); // Set the content message of the alert dialog

        // Show the alert dialog and wait for the user response
        // Return true if OK is clicked, false if canceled or closed
        return alert.showAndWait().map(response -> response == ButtonType.OK).orElse(false);
    }
}
