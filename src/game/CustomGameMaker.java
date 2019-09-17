package game;

import org.lwjgl.input.Mouse;
import entities.*;

import static entities.EntityManager.getTexture;
import static utilities.Artist.*;

public class CustomGameMaker {

    public CustomPlayer player;
    public CustomEnemy enemy;

    public CustomGameMaker(){
        player = new CustomPlayer();
        enemy = new CustomEnemy();
    }

    public void runPlayer(){
        player.run();
    }

    public void runEnemy(){
        enemy.run(player);
    }

    public void draw(){

    }
}
