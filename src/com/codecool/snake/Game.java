package com.codecool.snake;

import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.enemies.StrongerEnemy;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;




public class Game extends Pane {

    public Game() {

    }

    public void menu(Stage stage) {
        MenuPopUp menu = new MenuPopUp(stage, this);
    }

    public void start(int numberOfPlayers) {

        Globals.numberOfPlayers = numberOfPlayers;

        Scene scene = getScene();
        setPlayers(scene);

        if (Globals.numberOfPlayers == 1) { setEnemies(); }

        setPowerUps();

        Globals.addTextOfHealths();
        this.getChildren().add(Globals.textOfHealth[0]);
        this.getChildren().add(Globals.textOfHealth[1]);

        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();
    }

    private void setPlayers(Scene scene) {
        SnakeHead player1 = new SnakeHead(this, 400, 500, 0);
        if (Globals.numberOfPlayers == 2) {
            SnakeHead player2 = new SnakeHead(this, 700, 500, 1);
        }
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown[0] = true; break;
                case RIGHT: Globals.rightKeyDown[0] = true; break;
                case M: Globals.shootingKeyDown[0] = true; break;
                case A: if (Globals.numberOfPlayers == 2) { Globals.leftKeyDown[1] = true; break; }
                case D: if (Globals.numberOfPlayers == 2) { Globals.rightKeyDown[1] = true; break; }
                case W: if (Globals.numberOfPlayers == 2) { Globals.shootingKeyDown[1] = true; break; }
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown[0] = false; break;
                case RIGHT: Globals.rightKeyDown[0] = false; break;
                case M: Globals.shootingKeyDown[0] = false; break;
                case A: if (Globals.numberOfPlayers == 2) { Globals.leftKeyDown[1] = false; break; }
                case D: if (Globals.numberOfPlayers == 2) { Globals.rightKeyDown[1] = false; break; }
                case W: if (Globals.numberOfPlayers == 2) { Globals.shootingKeyDown[1] = false; break; }
            }
        });

    }

    private void setEnemies() {
        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new StrongerEnemy(this);
        new StrongerEnemy(this);
        new StrongerEnemy(this);
        new StrongerEnemy(this);
        new StrongerEnemy(this);
        new StrongerEnemy(this);
        new StrongerEnemy(this);
    }

    private void setPowerUps() {
        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
    }
}
