package com.codecool.snake;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MenuPopUp {

    public MenuPopUp(Stage stage, Game game) {

        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(stage);
        VBox dialogVbox = new VBox(40);
        dialogVbox.setAlignment(Pos.CENTER);

        Button onePlayerBtn = new Button();
        onePlayerBtn.setText("1 PLAYER");
        dialogVbox.getChildren().add(onePlayerBtn);
        onePlayerBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
                game.start(1);
            }
        });
        Button twoPlayerBtn = new Button();
        twoPlayerBtn.setText("2 PLAYER");
        dialogVbox.getChildren().add(twoPlayerBtn);
        twoPlayerBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
                game.start(2);
            }
        });

        Scene dialogScene = new Scene(dialogVbox, 200, 150);
        dialog.setScene(dialogScene);
        dialog.show();

    }

}
