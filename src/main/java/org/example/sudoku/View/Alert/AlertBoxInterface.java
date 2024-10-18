package org.example.sudoku.View.Alert;

/**
 * AlertBoxInterface defines the methods for displaying alerts in the application.
 */
public interface AlertBoxInterface {

    /**
     * Displays an information alert with the specified title, header, and message.
     *
     * @param title   The title of the alert.
     * @param header  The header text of the alert.
     * @param message The content message of the alert.
     */
    void showAlert(String title, String header, String message);

    /**
     * Displays an error alert with the specified title, header, and message.
     *
     * @param title   The title of the alert.
     * @param header  The header text of the alert.
     * @param message The content message of the alert.
     */
    void errorAlert(String title, String header, String message);
}