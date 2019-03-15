package main;

import entities.*;
import org.lwjgl.util.vector.Vector2f;
import utilities.Artist.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import physics.Gravity;

import java.util.HashSet;
import java.util.Random;

import static utilities.Artist.*;
import static org.lwjgl.opengl.GL11.*;


public class SpaceEngine {

    public SpaceEngine(){
        BeginSession();


        HashSet<Enemy> enemySet = new HashSet<>();
        HashSet<Bullet> bulletSet = new HashSet<>();
        Player player = new Player();
        Turret turret = new Turret();

        Gravity gravity = new Gravity(1f);

        Random rand = new Random();

        int shootTimer = 10;

        while (!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            HashSet<Enemy> remove = new HashSet<>();
            HashSet<Bullet> removeB = new HashSet<>();

            player.Draw();
            player.setPos();

            turret.Draw();
            turret.setPos();

            if(rand.nextInt(10) == 5){
                enemySet.add(new Enemy(player));
            }

            if(Mouse.isButtonDown(0)) {
                if(shootTimer >= 5) {
                    Vector2f tempVec = player.getPos();
                    bulletSet.add(new Bullet(tempVec));
                    shootTimer = 0;
                }else shootTimer = shootTimer + 1;
            }
            for(Bullet b : bulletSet){
                b.Draw();
                b.setPos();
                if(b.isRemove()) removeB.add(b);
            }

            for(Enemy e : enemySet){
                e.Draw();
                e.setPos();
                for(Bullet b : bulletSet){
                    e.checkColliding(b);
                }
                if(e.isRemove()) remove.add(e);
            }
            for(Enemy e : remove){
                enemySet.remove(e);
            }
            for(Bullet b : removeB){
                bulletSet.remove(b);
            }


            updateDisplay();

        }


    }

    public static void main(String[] Args){
        new SpaceEngine();
    }
}
