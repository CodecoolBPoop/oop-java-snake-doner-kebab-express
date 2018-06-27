package com.codecool.snake.entities.snakes;

import com.codecool.snake.Game;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.Laser;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class SnakeHead extends GameEntity implements Animatable {

    private static final float speed = 0.5f;
    private static final float turnRate = 2;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private int health;
    private int snakeID;
    private double currentX;
    private double currentY;
    private double currentDir;
    private long laserLastShot;

    public SnakeHead(Pane pane, int xc, int yc, int snakeID) {
        super(pane);
        setX(xc);
        setY(yc);
        health = 100;
        tail = this;
        this.snakeID = snakeID;
        setImage(Globals.snakeHead);
        pane.getChildren().add(this);

        addPart(4);

        laserLastShot = System.currentTimeMillis();
    }

    public int getHealth() {
        return this.health;
    }

    public void step() {
        double dir = getRotate();
        if (Globals.leftKeyDown[snakeID]) {
            dir = dir - turnRate;
        }
        if (Globals.rightKeyDown[snakeID]) {
            dir = dir + turnRate;
        }
        // set rotation and position
        setRotate(dir);
        Point2D heading = Utils.directionToVector(dir, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
        currentX = getX() + heading.getX();
        currentY = getY() + heading.getY();
        currentDir = dir;
        Globals.snakeHeadDir = dir;
        Globals.snakeHeadX = currentX;
        Globals.snakeHeadY = currentY;

        if(Globals.shootingKeyDown[snakeID]){
            if(System.currentTimeMillis() - laserLastShot > 125) {
                Laser blast = new Laser(pane, this);
                blast.getMessage();
                laserLastShot = System.currentTimeMillis();
            }
        }

        // check if collided with an enemy or a powerup
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof Interactable) {
                    Interactable interactable = (Interactable) entity;
                    interactable.apply(this);
                }
            }
        }

        // check if collided with the other player
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof SnakeBody) {
                    if (((SnakeBody) entity).getSnakeID() != this.snakeID) {
                        collision(this.snakeID == 1 ? 1 : 0);
                        break;
                    }
                }
            }
        }

        // check for game over condition
        if (isOutOfBounds() || health <= 0) {
            System.out.println("Game Over");
            Globals.gameLoop.stop();
        }
    }

    public void addPart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            SnakeBody newPart = new SnakeBody(pane, tail, this.snakeID);
            tail = newPart;
        }
    }

    public void changeHealth(int diff) {
        health += diff;
        Globals.textOfHealth[snakeID].setText(String.valueOf(health));
    }

    public void collision(int snakeID) {
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof SnakeBody) {
                    if (((SnakeBody) entity).getSnakeID() == snakeID) {
                        entity.destroy();
                    }
                }
                if (entity instanceof SnakeHead) {
                    if (((SnakeHead) entity).getSnakeID() == snakeID) {
                        entity.destroy();
                    }
                }
            }
        }
    }

    public int getSnakeID() {
        return this.snakeID;
    }


}
