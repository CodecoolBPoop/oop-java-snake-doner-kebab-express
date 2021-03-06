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
    private static final int damage = 3;


    public AnnoyingEnemy(Pane pane) {
        super(pane);

        setImage(Globals.annoyingEnemy);
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
    public void step() {
        List<Point2D> laserPositions = Utils.laserPositions();
        for (Point2D laser : laserPositions) {
            if (Math.abs(laser.getX() - this.getX()) < 25 && Math.abs(laser.getY() - this.getY()) < 25) {
                Random rnd = new Random();
                this.setX(getX() + (rnd.nextInt(30) - 15));
                this.setY(getY() + (rnd.nextInt(30) - 15));
                if (isOutOfBounds()) {
                    double candidateX;
                    double candidateY;

                    do {
                        candidateX = rnd.nextDouble() * Globals.WINDOW_WIDTH;
                        candidateY = rnd.nextDouble() * Globals.WINDOW_HEIGHT;
                    }while (candidateX == Globals.snakeHeadX && candidateY == Globals.snakeHeadY);

                    setX(candidateX);
                    setY(candidateY);
                }
            }
        }
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
