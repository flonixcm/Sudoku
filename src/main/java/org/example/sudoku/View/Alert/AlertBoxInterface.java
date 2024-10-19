package org.example.sudoku.View.Alert;

/**
 * AlertBoxInterface defines the methods for displaying different types of alerts in the application.
 * Implementing classes are expected to provide functionality for showing information and confirmation alerts.
 */
public interface AlertBoxInterface {

    /**
     * Displays an information alert with the specified title, header, and message.
     *
     * @param title   The title of the alert dialog, typically displayed at the top.
     * @param header  The header text of the alert dialog, providing context for the alert.
     * @param message The content message of the alert dialog, conveying the main information to the user.
     */
    void InformationAlert(String title, String header, String message);

    /**
     * Displays a confirmation alert with the specified title, header, and message.
     * This alert prompts the user to confirm an action, returning their choice as a boolean.
     *
     * @param title   The title of the confirmation alert dialog.
     * @param header  The header text of the confirmation alert dialog, giving context for the confirmation.
     * @param message The content message of the confirmation alert dialog, explaining what the user is confirming.
     * @return true if the user clicked OK, false if the user clicked Cancel or closed the dialog.
     */
    boolean confirmationAlert(String title, String header, String message);
}
