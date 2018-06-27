package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import javafx.scene.image.Image;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// class for holding all static stuff
public class Globals {

    public static final double WINDOW_WIDTH = 1000;
    public static final double WINDOW_HEIGHT = 700;

    public static Image snakeHead = new Image("snake_head.png");
    public static Image snakeBody = new Image("snake_body.png");
    public static Image simpleEnemy = new Image("simple_enemy.png");
    public static Image strongerEnemy = new Image("stronger_enemy.png", 40, 40, true, true);
    public static Image annoyingEnemy = new Image("annoying_enemy.png", 40, 40, true, true);
    public static Image powerupBerry = new Image("powerup_berry.png");
    public static Image buffedFood = new Image("buffed_food.png", 40, 40, true, true);
    public static Image laserShot = new Image("laser-resized03.png");
    //.. put here the other images you want to use

    public static boolean[] leftKeyDown = new boolean[2];
    public static boolean[] rightKeyDown = new boolean[2];
    public static boolean[] shootingKeyDown = new boolean[2];
    public static boolean[] isInvulnerable = new boolean[2];
    public static Text[] textOfHealth = new Text[2];
    public static int numberOfPlayers;
    public static boolean[] isPlayerDead = {false, false};

    public static List<GameEntity> gameObjects;
    public static List<GameEntity> newGameObjects; // Holds game objects created in this frame.
    public static List<GameEntity> oldGameObjects; // Holds game objects that will be destroyed this frame.
    public static GameLoop gameLoop;
    public static Game game;
    public static double snakeHeadX;
    public static double snakeHeadY;
    public static double snakeHeadDir;

    static {
        gameObjects = new LinkedList<>();
        newGameObjects = new LinkedList<>();
        oldGameObjects = new LinkedList<>();
    }

    public static void addGameObject(GameEntity toAdd) {
        newGameObjects.add(toAdd);
    }

    public static void removeGameObject(GameEntity toRemove) {
        oldGameObjects.add(toRemove);
    }

    public static List<GameEntity> getGameObjects() {
        return Collections.unmodifiableList(gameObjects);
    }

    public static void addTextOfHealths() {
        textOfHealth[0] = new Text();
        textOfHealth[0].setText("100");
        textOfHealth[0].setFont(Font.font("Sans Serif", FontWeight.NORMAL, FontPosture.REGULAR, 32));
        textOfHealth[0].setFill(Color.RED);
        textOfHealth[0].setX(900);
        textOfHealth[0].setY(30);

        textOfHealth[1] = new Text();
        textOfHealth[1].setText("100");
        textOfHealth[1].setFont(Font.font("Sans Serif", FontWeight.NORMAL, FontPosture.REGULAR, 32));
        textOfHealth[1].setFill(Color.RED);
        textOfHealth[1].setX(10);
        textOfHealth[1].setY(30);
    }


}
