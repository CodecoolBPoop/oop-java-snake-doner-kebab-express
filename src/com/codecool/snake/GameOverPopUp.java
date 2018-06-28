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
            text = "Final length of Snake 1 was " + Globals.snakeLengths[0] + "\nFinal length of Snake 2 was " + Globals.snakeLengths[1];
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


        Scene dialogScene = new Scene(dialogVbox, 350, 200);
        dialog.setScene(dialogScene);
        dialog.show();

        Button restart = new Button("RESTART");
        dialogVbox.getChildren().add(restart);
        restart.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent arg0) {
                Globals.gameObjects.clear();
                Globals.game.getChildren().clear();
                dialog.close();
                Globals.isPlayerDead[0] = false;
                Globals.snakeLengths[0] = 0;
                if(Globals.numberOfPlayers == 2) {
                    Globals.isPlayerDead[1] = false;
                    Globals.snakeLengths[1] = 0;
                }
                Globals.game.start(Globals.numberOfPlayers);
            }

        });


    }



}
