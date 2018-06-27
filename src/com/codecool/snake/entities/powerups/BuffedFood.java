package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;

public class BuffedFood extends GameEntity implements Interactable {

    public BuffedFood(Pane pane) {
        super(pane);
        setImage(Globals.buffedFood);
        pane.getChildren().add(this);

        Random rnd = new Random();
        double candidateX;
        double candidateY;

        do {
            candidateX = rnd.nextDouble() * Globals.WINDOW_WIDTH;
            candidateY = rnd.nextDouble() * Globals.WINDOW_HEIGHT;
        }while (candidateX == Globals.snakeHeadX && candidateY == Globals.snakeHeadY);
        setX(candidateX);
        setY(candidateY);
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.addPart(4);
        snakeHead.setInvulnerable(true);
        snakeHead.setInvulnerabilityTimer();
        snakeHead.setImage(Globals.strongerEnemy);
        destroy();
    }

    @Override
    public String getMessage() {
        return "Got power-up :)";
    }

}
