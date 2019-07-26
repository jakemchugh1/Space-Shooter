package scenery;

import entities.Entity;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

import java.util.Random;

import static entities.EntityManager.getTexture;
import static org.lwjgl.opengl.GL11.*;
import static utilities.Artist.DrawQuadTex;
import static utilities.Artist.LoadTexture;
import static utilities.Artist.getFrameTimeSeconds;

public class DancingStarfish implements Scenery{

    private float x;
    private float y;

    private int height;
    private int width;

    private int frame;

    private boolean move;

    private int distance;


    public DancingStarfish(float x, float y, int width, int height){
        frame = 0;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        distance = 0;

        move = true;

    }

    public void Draw() {
        if(frame < 24) {
            DrawQuadTex(getTexture("red_star_1"), x, y, width, height);
            frame = frame + 1;
        }else if(frame < 48) {
            DrawQuadTex(getTexture("red_star_2"), x, y, width, height);
            frame = frame + 1;
        }else if(frame < 72){
            DrawQuadTex(getTexture("red_star_3"), x, y, width, height);
            frame = frame + 1;
        }else if(frame < 96){
            DrawQuadTex(getTexture("red_star_2"), x, y, width, height);
            frame = frame + 1;
        }else{
            DrawQuadTex(getTexture("red_star_1"), x, y, width, height);
            frame = 0;
        }
        x = x - 4;
        /*if(move){
            x = x+1;
            distance = distance + 1;
            if(distance>200)move = false;
        }else{
            x = x - 1;
            distance = distance - 1;
            if(distance<0)move = true;
        }*/
    }

    public int getFrame(){
        return frame;
    }
    public float getX(){
        return x;
    }
    public float getY() {return y-10;}
}
