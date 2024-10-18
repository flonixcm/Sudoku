module org.example.sudoku {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    exports org.example.sudoku;
    opens org.example.sudoku.Controller to javafx.fxml; // Abre el paquete para JavaFX FXML
}
