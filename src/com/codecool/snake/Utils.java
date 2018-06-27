package com.codecool.snake;

import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Laser;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    /*
    Converts a direction in degrees (0...360) to x and y coordinates.
    The length of this vector is the second parameter
    */
    public static Point2D directionToVector(double directionInDegrees, double length) {
        double directionInRadians = directionInDegrees / 180 * Math.PI;
        Point2D heading = new Point2D(length * Math.sin(directionInRadians), - length * Math.cos(directionInRadians));
        return heading;
    }

    public static List<Point2D> laserPositions() {
        List<Point2D> laserPositions = new ArrayList<>();
        for (GameEntity gameObject : Globals.gameObjects) {
            if (gameObject instanceof Laser) {
                laserPositions.add(new Point2D(gameObject.getX(), gameObject.getY()));
            }
        }
        return laserPositions;
    }
}
