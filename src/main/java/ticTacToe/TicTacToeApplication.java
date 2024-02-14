package ticTacToe;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TicTacToeApplication extends Application {
    String currentPlayer = "X";
    ArrayList<Button> buttons = new ArrayList<>();

    public static void main(String[] args) {
        launch(TicTacToeApplication.class);
    }

    @Override
    public void start(Stage window) throws Exception {
        BorderPane layout = new BorderPane();
        layout.setPrefSize(400, 400);

        Label text = new Label("Turn: " + currentPlayer);
        text.setFont(Font.font("", 40));
        text.setPadding(new Insets(0, 0, 0, 10));
        layout.setTop(text);

        GridPane game = new GridPane();
        for (int i = 0; i < 9; i++) {
            Button button = new Button();
            button.setFont(Font.font("", 60));
            button.setMinSize(140, 140);
            button.setMaxSize(140, 140);
            
            button.setOnMouseClicked((event) -> {
                if (text.getText().startsWith("The end!")) {
                    button.disarm();
                    return;
                }
                if (button.getText().isEmpty()) {
                    button.setText(currentPlayer);
                    changeTurn();
                    text.setText("Turn: " + currentPlayer);
                }
                if(checkWin()) {
                    changeTurn();
                    text.setText("The end!");
                    return;
                }
                if(boxesFilled()) {
                    text.setText("The end! It's a draw!");
                }
            });
            buttons.add(button);
        }
        
        game.add(buttons.get(0), 0, 0);
        game.add(buttons.get(1), 0, 1);
        game.add(buttons.get(2), 0, 2);
        game.add(buttons.get(3), 1, 0);
        game.add(buttons.get(4), 1, 1);
        game.add(buttons.get(5), 1, 2);
        game.add(buttons.get(6), 2, 0);
        game.add(buttons.get(7), 2, 1);
        game.add(buttons.get(8), 2, 2);
        
        layout.setCenter(game);
        game.setVgap(10);
        game.setHgap(10);
        game.setPadding(new Insets(10, 10, 10, 10));
        game.setAlignment(Pos.CENTER);

        Scene view = new Scene(layout);
        window.setScene(view);
        window.show();
    }
    
    public void changeTurn() {
        if (currentPlayer.equals("X")) {
            currentPlayer = "O";
            return;
        }
        if (currentPlayer.equals("O")) {
            currentPlayer = "X";
        }
    }
    
    public boolean boxesFilled() {
        for(Button b: buttons) {
            if(b.getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }
    
    public boolean checkWin() {
        if(winVertical() || winHorizontal() || winDiagonal()) {
            return true;
        }
        return false;
    }
    
    public boolean winVertical() {
        if(!buttons.get(0).getText().isEmpty() 
                && buttons.get(0).getText().equals(buttons.get(1).getText())
                && buttons.get(0).getText().equals(buttons.get(2).getText())) {
            return true;
        }
        if(!buttons.get(3).getText().isEmpty() 
                && buttons.get(3).getText().equals(buttons.get(4).getText())
                && buttons.get(3).getText().equals(buttons.get(5).getText())) {
            return true;
        }
        if(!buttons.get(6).getText().isEmpty() 
                && buttons.get(6).getText().equals(buttons.get(7).getText())
                && buttons.get(6).getText().equals(buttons.get(8).getText())) {
            return true;
        }
        return false;
    }
    
    public boolean winHorizontal() {
        if(!buttons.get(0).getText().isEmpty() 
                && buttons.get(0).getText().equals(buttons.get(3).getText())
                && buttons.get(0).getText().equals(buttons.get(6).getText())) {
            return true;
        }
        if(!buttons.get(1).getText().isEmpty() 
                && buttons.get(1).getText().equals(buttons.get(4).getText())
                && buttons.get(1).getText().equals(buttons.get(7).getText())) {
            return true;
        }
        if(!buttons.get(2).getText().isEmpty() 
                && buttons.get(2).getText().equals(buttons.get(5).getText())
                && buttons.get(2).getText().equals(buttons.get(8).getText())) {
            return true;
        }
        return false;
    }
    
    public boolean winDiagonal() {
        if(!buttons.get(0).getText().isEmpty() 
                && buttons.get(0).getText().equals(buttons.get(4).getText())
                && buttons.get(0).getText().equals(buttons.get(8).getText())) {
            return true;
        }
        if(!buttons.get(6).getText().isEmpty() 
                && buttons.get(6).getText().equals(buttons.get(4).getText())
                && buttons.get(6).getText().equals(buttons.get(2).getText())) {
            return true;
        }
        return false;
    }
}
