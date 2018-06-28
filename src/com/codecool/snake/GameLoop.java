package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.enemies.AnnoyingEnemy;
import com.codecool.snake.entities.enemies.ChaserEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.enemies.StrongerEnemy;
import com.codecool.snake.entities.powerups.BuffedFood;
import com.codecool.snake.entities.powerups.MoreHealthPowerUp;
import com.codecool.snake.entities.powerups.SimplePowerup;
import javafx.animation.AnimationTimer;
import com.codecool.snake.GameOverPopUp;
import javafx.stage.Stage;

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

        if (Globals.numberOfPlayers == 1) {
            spawnMoreEnemies();
        }

        spawnMorePowerUps();


        Globals.gameObjects.addAll(Globals.newGameObjects);
        Globals.newGameObjects.clear();

        Globals.gameObjects.removeAll(Globals.oldGameObjects);
        Globals.oldGameObjects.clear();

        if (Globals.isPlayerDead[0] == true && Globals.isPlayerDead[1] == true) {
            this.stop();
            GameOverPopUp popUp = new GameOverPopUp(Globals.stage);
        }

        if(Globals.numberOfPlayers == 1 && Globals.isPlayerDead[0] == true){
            this.stop();
            GameOverPopUp popUp = new GameOverPopUp(Globals.stage);
        }

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
        }else if(rnd == 270){
            Globals.newGameObjects.add(new AnnoyingEnemy(Globals.game));
        }else if(rnd == 280){
            //Globals.newGameObjects.add(new ChaserEnemy(Globals.game));
        }
    }

    private void spawnMorePowerUps(){
        Random powerUpRnd = new Random();
        int rnd = powerUpRnd.nextInt(3000);

        if(rnd == 500){
            Globals.newGameObjects.add(new SimplePowerup(Globals.game));
        }else if (rnd == 250){
            Globals.newGameObjects.add(new MoreHealthPowerUp(Globals.game));

        }else if(rnd == 650){
            Globals.newGameObjects.add(new BuffedFood(Globals.game));
        }
    }
}
