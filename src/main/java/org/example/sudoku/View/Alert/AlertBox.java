package org.example.sudoku.View.Alert;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * AlertBox class provides methods to show alerts in the application.
 * It implements the AlertBoxInterface.
 */
public class AlertBox implements AlertBoxInterface {

    /**
     * Displays an information alert with the specified title, header, and message.
     *
     * @param title   The title of the alert.
     * @param header  The header text of the alert.
     * @param message The content message of the alert.
     */
    @Override
    public void InformationAlert(String title, String header, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION); // Create an information alert
        alert.setTitle(title); // Set the title of the alert
        alert.setHeaderText(header); // Set the header of the alert
        alert.setContentText(message); // Set the content of the alert
        alert.showAndWait(); // Show the alert and wait for the user to close it
    }

    /**
     * Displays an error alert with the specified title, header, and message.
     *
     * @param title   The title of the alert.
     * @param header  The header text of the alert.
     * @param message The content message of the alert.
     */
    public void errorAlert(String title, String header, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR); // Create an error alert
        alert.setTitle(title); // Set the title of the alert
        alert.setHeaderText(header); // Set the header of the alert
        alert.setContentText(message); // Set the content of the alert
        alert.showAndWait(); // Show the alert and wait for the user to close it
    }
    public boolean confirmationAlert(String title, String header, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION); // Create a confirmation alert
        alert.setTitle(title); // Set the title of the alert
        alert.setHeaderText(header); // Set the header of the alert
        alert.setContentText(message); // Set the content of the alert
        // Show the alert and wait for the user response
        return alert.showAndWait().map(response -> response == ButtonType.OK).orElse(false);
    }
}
