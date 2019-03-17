package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;
import static utilities.Artist.DrawQuadTex;
import static utilities.Artist.LoadTexture;
import static utilities.Artist.getFrameTimeSeconds;

public class Turret implements Entity {

    private float x;
    private float y;

    private int height;
    private int width;

    private int speed;

    private Texture texture;

    private Vector2f pos;
    private Vector2f vel;

    private boolean remove;

    public Turret(){

        //initial position vector
        pos = new Vector2f();
        pos.x = 0;
        pos.y = 0;
        speed = 200;
        this.x = pos.x-8;
        this.y = pos.y-8;
        width = 16;
        height = 16;
        this.texture = LoadTexture("square");



        //initial velocity;
        vel = new Vector2f();
        vel.x = 0f;
        vel.y = 0f;

        remove = false;

    }

    public void Draw() {
        DrawQuadTex(texture, x, y, width, height);
    }

    public void setPos() {
        pos.x = Mouse.getX();
        pos.y = 960-Mouse.getY();
        x = pos.x - 8;
        y = pos.y - 8;

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
