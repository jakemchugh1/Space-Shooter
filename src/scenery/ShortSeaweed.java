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

public class ShortSeaweed implements Scenery{

    private float x;
    private float y;

    private int height;
    private int width;

    private int frame;


    public ShortSeaweed(float x, float y, int width, int height){
        frame = 0;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    public void Draw() {
        if(frame < 12) {
            DrawQuadTex(getTexture("seaweed_short1"), x, y, width, height);
            frame = frame + 1;
        }else if(frame < 24) {
            DrawQuadTex(getTexture("seaweed_short2"), x, y, width, height);
            frame = frame + 1;
        }else if(frame < 36){
            DrawQuadTex(getTexture("seaweed_short3"), x, y, width, height);
            frame = frame + 1;
        }else if(frame < 48){
            DrawQuadTex(getTexture("seaweed_short2"), x, y, width, height);
            frame = frame + 1;
        }else{
            DrawQuadTex(getTexture("seaweed_short1"), x, y, width, height);
            frame = 0;
        }
    }

    public int getFrame(){
        return frame;
    }
}
