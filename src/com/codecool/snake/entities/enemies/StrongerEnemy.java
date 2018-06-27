package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import com.sun.javafx.geom.Vec2d;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

// a simple enemy TODO make better ones.
public class StrongerEnemy extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private static final int damage = 10;
    private int health;


    public StrongerEnemy(Pane pane, SnakeHead head) {
        super(pane);
        health = 3;

        setImage(Globals.strongerEnemy);
        pane.getChildren().add(this);
        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

        double direction = rnd.nextDouble() * 360;
        setRotate(direction);
        double speed = 0.5f;
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        if (this.health < 1) { destroy(); }
        if (isOutOfBounds()) {
            if (getX() >= 1000 || getX() <= 0) {
                heading = new Point2D(heading.getX() * -1, heading.getY());
            } else if (getY() >= 700 || getY() <= 0) {
                heading = new Point2D(heading.getX(), heading.getY() * -1);
            }
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(-damage);
        destroy();
    }

    @Override
    public String getMessage() {
        return "10 damage";
    }


    public void decreseHealth() {
        this.health--;
    }
}
