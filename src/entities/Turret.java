package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

import static entities.EntityManager.getTexture;
import static org.lwjgl.opengl.GL11.*;
import static utilities.Artist.*;

public class Turret implements Entity {

    private float x;
    private float y;

    private int height;
    private int width;

    private int speed;
    private int frame;

    private Vector2f pos;
    private Vector2f vel;

    private boolean remove;

    public Turret(){

        //initial position vector
        pos = new Vector2f();
        pos.x = 0;
        pos.y = 0;
        speed = 200;
        this.x = pos.x-16;
        this.y = pos.y-16;
        width = 32;
        height = 32;
        frame = 0;



        //initial velocity;
        vel = new Vector2f();
        vel.x = 0f;
        vel.y = 0f;

        remove = false;

    }

    public void Draw() {
        if(frame < 6) {
            DrawQuadTex(getTexture("reticle_1"), x, y, width, height);
            frame = frame + 1;
        }else if(frame < 12){
            DrawQuadTex(getTexture("reticle_2"), x, y, width, height);
            frame = frame + 1;
        }else if(frame < 18){
            DrawQuadTexFlip(getTexture("reticle_1"), x, y, width, height);
            frame = frame + 1;
        }else if(frame < 24){
            DrawQuadTex(getTexture("reticle_3"), x, y, width, height);
            frame = frame + 1;
        }else{
            frame = 0;
            DrawQuadTex(getTexture("reticle_1"), x, y, width, height);
        }
    }

    public void setPos() {
        pos.x = Mouse.getX();
        pos.y = 960-Mouse.getY();
        x = pos.x - 16;
        y = pos.y - 16;

    }

    public Vector2f getPos() {
        return null;
    }

    public void ApplyForce(Vector2f force) {

    }

    public boolean isRemove() {
        return false;
    }

    public boolean checkColliding(Entity none){
        return false;
    }

    public void setRemove() {remove = true;}
}
