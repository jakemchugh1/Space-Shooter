package main;

import entities.*;
import utilities.Artist.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import physics.Gravity;

import java.util.HashSet;

import static utilities.Artist.*;
import static org.lwjgl.opengl.GL11.*;


public class SpaceEngine {

    public SpaceEngine(){
        BeginSession();


        HashSet<Entity> entitySet = new HashSet<>();
        Player player = new Player();
        Turret turret = new Turret();

        Gravity gravity = new Gravity(1f);

        while (!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            HashSet<Entity> remove = new HashSet<>();

            player.Draw();
            player.setPos();

            turret.Draw();
            turret.setPos();

            for(Entity ball : entitySet){
                ball.Draw();
                ball.setPos();
                if(ball.isRemove()) remove.add(ball);
            }
            for(Entity ball : remove){
                entitySet.remove(ball);
            }


            updateDisplay();

        }


    }

    public static void main(String[] Args){
        new SpaceEngine();
    }
}
