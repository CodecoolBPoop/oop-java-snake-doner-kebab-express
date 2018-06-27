package com.codecool.snake;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;



public class GameOverPopUp {

    public GameOverPopUp(Stage stage){
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(stage);
        VBox dialogVbox = new VBox(40);
        dialogVbox.setAlignment(Pos.CENTER);
        String text;
        if(Globals.numberOfPlayers == 1){
            text = "Final length of your snake was " + Globals.snakeLengths[0];
        }else{
            text = "Final length of Snake 1 was " + Globals.snakeLengths[0] + "\n Final length of Snake 2 was " + Globals.snakeLengths[1];
        }
        dialogVbox.getChildren().add(new Text(text));

        Button okButton = new Button("CLOSE");
        okButton.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent arg0) {
                dialog.close();
            }

        });

        dialogVbox.getChildren().add(okButton);



        Scene dialogScene = new Scene(dialogVbox, 350, 150);
        dialog.setScene(dialogScene);
        dialog.show();

    }



}
