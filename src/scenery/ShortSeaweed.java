package scenery;

import entities.Entity;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

import java.util.Random;

import static entities.EntityManager.getTexture;
import static org.lwjgl.opengl.GL11.*;
import static utilities.Artist.*;

public class ShortSeaweed implements Scenery{

    private float x;
    private float y;

    private int height;
    private int width;

    private int frame;

    private float modifier;
    private float modifierChange;
    private float skewRange;


    public ShortSeaweed(float x, float y, int width, int height){
        frame = 0;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        modifier = 0;
        modifierChange = 0.25f;
        skewRange = 20;

    }

    public void Draw() {
        if(frame < 24) {
            DrawQuadTexWave(getTexture("seaweed_1"), x, y, width, height, modifier);
            frame = frame + 1;
        }else if(frame < 48) {
            DrawQuadTexWave(getTexture("seaweed_2"), x, y, width, height, modifier);
            frame = frame + 1;
        }else if(frame < 72){
            DrawQuadTexWave(getTexture("seaweed_3"), x, y, width, height, modifier);
            frame = frame + 1;
        }else if(frame < 96) {
            DrawQuadTexWave(getTexture("seaweed_2"), x, y, width, height, modifier);
            frame = frame + 1;
        }else{
            DrawQuadTexWave(getTexture("seaweed_1"), x, y, width, height, modifier);
            frame = 0;
        }
        modifier = modifier + modifierChange;

        if(modifier >= skewRange){
            modifierChange = -modifierChange;
        }if(modifier <= -skewRange){
            modifierChange = -modifierChange;
        }
        x = x-4;
    }

    public int getFrame(){
        return frame;
    }

    public float getX(){
        return x;
    }
    public float getY() {return y;}
}
