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
public class ChaserEnemy extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private static final int damage = 10;
    double direction;
    float speed;

    public ChaserEnemy(Pane pane) {
        super(pane);

        setImage(Globals.chaserEnemy);
        pane.getChildren().add(this);
        speed = 0.3f;
        Random rnd = new Random();

        double candidateX;
        double candidateY;

        do {
            candidateX = rnd.nextDouble() * Globals.WINDOW_WIDTH;
            candidateY = rnd.nextDouble() * Globals.WINDOW_HEIGHT;
        } while (candidateX == Globals.snakeHeadX && candidateY == Globals.snakeHeadY);

        setX(candidateX);
        setY(candidateY);

        direction = 0;
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }

        double headX = Globals.snakeHeadX;
        double headY = Globals.snakeHeadY;
        double chaserX = this.getX();
        double chaserY = this.getY();
        double angle = (double) Math.toDegrees(Math.atan2(headY - chaserY, headX - chaserX));

        if (angle < 0) {angle += 360;}
        angle += 90;
        if (angle > 360) {angle -= 360;}

        this.direction = angle;
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
        this.setX(getX() + heading.getX());
        this.setY(getY() + heading.getY());

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

