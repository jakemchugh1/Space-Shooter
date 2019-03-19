package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

import static entities.EntityManager.getTexture;
import static org.lwjgl.opengl.GL11.*;
import static utilities.Artist.DrawQuadTex;
import static utilities.Artist.LoadTexture;
import static utilities.Artist.getFrameTimeSeconds;

public class Player implements Entity {

    private float x;
    private float y;

    private int height;
    private int width;
    private int frame;

    private int speed;

    private Texture texture1;
    private Texture texture2;
    private Texture texture3;
    private Texture texture4;
    private Texture texture5;
    private Texture texture6;
    private Texture texture7;
    private Texture texture8;
    private Texture texture9;

    private Vector2f pos;
    private Vector2f vel;

    private boolean remove;


    public Player(){
        speed = 200;
        this.x = 640-32;
        this.y = 480-32;
        width = 64;
        height = 64;
        frame = 0;
        texture1 = LoadTexture("player1");
        texture2 = LoadTexture("player2");
        texture3 = LoadTexture("player3");
        texture4 = LoadTexture("player4");
        texture5 = LoadTexture("player5");
        texture6 = LoadTexture("player6");
        texture7 = LoadTexture("player7");
        texture8 = LoadTexture("player8");
        texture9 = LoadTexture("player9");


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
        DrawQuadTex(getTexture("player9"), x, y, width, height);
        frame = frame + 1;
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

    public boolean checkColliding(Entity enemy){
        return false;
    }

    public void reset(){
        x = 640-32;
        y = 480-32;

        pos.x = x + width/2;
        pos.y = y + height/2;
    }

    public void setRemove() {remove = true;}

}
