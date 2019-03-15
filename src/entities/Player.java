package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;
import static utilities.Artist.DrawQuadTex;
import static utilities.Artist.LoadTexture;
import static utilities.Artist.getFrameTimeSeconds;

public class Player implements Entity {

    private float x;
    private float y;

    private int height;
    private int width;

    private int speed;

    private Texture texture;

    private Vector2f pos;
    private Vector2f vel;

    private boolean remove;


    public Player(){
        speed = 200;
        this.x = 640;
        this.y = 480;
        width = 64;
        height = 64;
        this.texture = LoadTexture("ball");


        //initial position vector
        pos = new Vector2f();
        pos.x = x + width/2;
        pos.y = y + height/2;

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
        checkInput();
    }

    public Vector2f getPos() {
        return pos;
    }

    public void ApplyForce(Vector2f force) {

    }

    public boolean isRemove() {
        return false;
    }

    public void checkInput(){
        if(Keyboard.isKeyDown(Keyboard.KEY_A)&& pos.x > 32){
            pos.x = pos.x - speed*getFrameTimeSeconds();
            x = pos.x - width/2;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_D)&& pos.x < 1248){
            pos.x = pos.x + speed*getFrameTimeSeconds();
            x = pos.x - width/2;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_W)&& pos.y > 32){
            pos.y = pos.y - speed*getFrameTimeSeconds();
            y = pos.y - height/2;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_S) && pos.y < 928){
            pos.y = pos.y + speed*getFrameTimeSeconds();
            y = pos.y - height/2;
        }
    }

    public void checkColliding(Entity enemy){

    }

    public void setRemove() {remove = true;}

}
