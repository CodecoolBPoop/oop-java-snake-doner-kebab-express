package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.enemies.StrongerEnemy;
import javafx.animation.AnimationTimer;

import java.util.Random;

public class GameLoop extends AnimationTimer {

    // This gets called every 1/60 seconds
    @Override
    public void handle(long now) {
        for (GameEntity gameObject : Globals.gameObjects) {
            if (gameObject instanceof Animatable) {
                Animatable animObject = (Animatable)gameObject;
                animObject.step();
            }
        }
        spawnMoreEnemies();

        Globals.gameObjects.addAll(Globals.newGameObjects);
        Globals.newGameObjects.clear();

        Globals.gameObjects.removeAll(Globals.oldGameObjects);
        Globals.oldGameObjects.clear();
    }

    private int countEnemies(){
        int count = 0;
        for(GameEntity entity: Globals.getGameObjects()){
            if(entity instanceof SimpleEnemy){
                count++;
            }
        }
        return count;
    }

    private void spawnMoreEnemies(){
        Random enemyRnd = new Random();
        int rnd = enemyRnd.nextInt(300);

        if(rnd == 250){
            Globals.newGameObjects.add(new SimpleEnemy(Globals.game));
        }else if(rnd == 150){
            Globals.newGameObjects.add(new StrongerEnemy(Globals.game));
        }



    }
}
