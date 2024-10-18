package org.example.sudoku.View;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class SudokuView extends Stage {
    public SudokuView() throws IOException{
        FXMLLoader loader= new FXMLLoader(
                getClass().getResource("/org/example/sudoku/sudoku-view.fxml")
        );
        Parent root= loader.load();
        this.setTitle("Sudoku");
        Scene scene = new Scene(root);
        this.getIcons().add(new Image(getClass().getResourceAsStream("/org/example/sudoku/icons/favicon.png")));
        this.setScene(scene);
        this.show();
    }
    public static SudokuView getInstance() throws IOException{
        if(SudokuViewHolder.INSTANCE == null){
            return SudokuViewHolder.INSTANCE = new SudokuView();
        }else{
            return  SudokuViewHolder.INSTANCE;
        }
    }
    public static class SudokuViewHolder{

        private static SudokuView INSTANCE;
    }
}
