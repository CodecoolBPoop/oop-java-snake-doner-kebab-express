package com.codecool.snake;

import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.enemies.StrongerEnemy;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;




public class Game extends Pane {

    public Game() {
        SnakeHead player1 = new SnakeHead(this, 500, 500, 0);
        SnakeHead player2 = new SnakeHead(this, 700, 400, 1);

        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new StrongerEnemy(this);
        new StrongerEnemy(this);
        new StrongerEnemy(this);


        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
        Globals.addTextOfHealths();
        this.getChildren().add(Globals.textOfHealth[0]);
        this.getChildren().add(Globals.textOfHealth[1]);
    }

    public void start() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown[0] = true; break;
                case RIGHT: Globals.rightKeyDown[0] = true; break;
                case A:  Globals.leftKeyDown[1] = true; break;
                case D: Globals.rightKeyDown[1] = true; break;
                case M: Globals.shootingKeyDown[0] = true; break;
                case W: Globals.shootingKeyDown[1] = true; break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown[0] = false; break;
                case RIGHT: Globals.rightKeyDown[0] = false; break;
                case A:  Globals.leftKeyDown[1] = false; break;
                case D: Globals.rightKeyDown[1] = false; break;
                case M: Globals.shootingKeyDown[0] = false; break;
                case W: Globals.shootingKeyDown[1] = false; break;
            }
        });
        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();
        Globals.game = this;
    }

    public Pane getPane(){
        return this;
    }
}
