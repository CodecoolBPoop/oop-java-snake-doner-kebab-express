package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.Laser;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

// a simple enemy TODO make better ones.
public class SimpleEnemy extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private static final int damage = 10;

    public SimpleEnemy(Pane pane) {
        super(pane);

        setImage(Globals.simpleEnemy);
        pane.getChildren().add(this);
        int speed = 1;
        Random rnd = new Random();


        // kirakni a headeket globalba és csekkolni, először, hogy létezik-e és utána, hogy intersectelnek

        double candidateX;
        double candidateY;

        do {
            candidateX = rnd.nextDouble() * Globals.WINDOW_WIDTH;
            candidateY = rnd.nextDouble() * Globals.WINDOW_HEIGHT;
        }while (candidateX == Globals.snakeHeadX && candidateY == Globals.snakeHeadY);

        setX(candidateX);
        setY(candidateY);

        double direction = rnd.nextDouble() * 360;
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(SnakeHead player) {
        if (!player.isInvulnerable()) {
            player.changeHealth(-damage);
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return "10 damage";
    }
}
