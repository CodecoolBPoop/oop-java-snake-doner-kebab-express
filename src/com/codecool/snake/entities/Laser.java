package com.codecool.snake.entities;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.enemies.StrongerEnemy;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import javafx.geometry.Point2D;
;

public class Laser extends GameEntity implements Interactable,Animatable {

    private Point2D heading;
    private double speed = 2;

    public Laser(Pane pane, SnakeHead snakeHead){
        super(pane);
        setImage(Globals.laserShot);
        pane.getChildren().add(this);
        setX(Globals.snakeHeadX);
        setY(Globals.snakeHeadY);
        setRotate(Globals.snakeHeadDir);
        heading = Utils.directionToVector(Globals.snakeHeadDir,speed);
    }



    public void apply(GameEntity entity) {
        entity.destroy();
    }

    @Override
    public void apply(SnakeHead snakeHead) {

    }

    @Override
    public String getMessage() {
        return "Laser shot!!";
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof SimpleEnemy || entity instanceof StrongerEnemy) {
                    apply(entity);
                    System.out.println("Enemy killed!");
                }
            }
        }



    }
}
