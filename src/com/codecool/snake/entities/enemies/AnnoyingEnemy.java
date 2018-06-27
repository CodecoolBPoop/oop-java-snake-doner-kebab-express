package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.List;
import java.util.Random;

public class AnnoyingEnemy extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private static final int damage = 5;


    public AnnoyingEnemy(Pane pane) {
        super(pane);

        setImage(Globals.annoyingEnemy);
        pane.getChildren().add(this);
        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    @Override
    public void step() {
        List<Point2D> laserPositions = Utils.laserPositions();
        for (Point2D laser : laserPositions) {
            if (Math.abs(laser.getX() - this.getX()) < 25 && Math.abs(laser.getY() - this.getY()) < 25) {
                Random rnd = new Random();
                this.setX(getX() + (rnd.nextDouble() * 15));
                this.setY(getY() + (rnd.nextDouble() * 15));
                if (isOutOfBounds()) {
                    this.setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
                    this.setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
                }
            }
        }
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

}
