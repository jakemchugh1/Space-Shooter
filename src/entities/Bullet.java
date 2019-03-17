package entities;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;
import static utilities.Artist.DrawQuadTex;
import static utilities.Artist.LoadTexture;
import static utilities.Artist.getFrameTimeSeconds;

public class Bullet implements Entity {

    private float x;
    private float y;

    private int height;
    private int width;

    private int speed;

    private Texture texture;

    private Vector2f pos;
    private Vector2f vel;

    private boolean remove;
    private double initialTime;
    private double timeLimit;


    public Bullet(Vector2f playerPos){
        initialTime = (Sys.getTime() * 1000 / Sys.getTimerResolution());
        timeLimit = 3*1000;
        speed = 400;
        width = 8;
        height = 8;
        this.texture = LoadTexture("bullet");


        //initial position vector
        pos = new Vector2f();
        pos.x = playerPos.x;
        pos.y = playerPos.y;
        x = pos.x-width/2;
        y = pos.y-height/2;

        //initial velocity;
        float mouseX = Mouse.getX();
        float mouseY = 960-Mouse.getY();

        float deltaX = mouseX-pos.x;
        float deltaY = mouseY-pos.y;

        float magnitude = (float) Math.sqrt(deltaX*deltaX + deltaY*deltaY);

        vel = new Vector2f();
        vel.x = deltaX/magnitude;
        vel.y = deltaY/magnitude;

        remove = false;

    }

    public void Draw() {
        DrawQuadTex(texture, x, y, width, height);
    }

    public void setPos() {
        pos.x = pos.x + vel.x*getFrameTimeSeconds() * speed;
        pos.y = pos.y + vel.y*getFrameTimeSeconds()* speed;

        x = pos.x-width/2;
        y = pos.y-height/2;

        if(pos.x < 0 || pos.x > 1280) vel.x = -vel.x;
        if(pos.y < 0 || pos.y > 960) vel.y = -vel.y;

        if ((Sys.getTime() * 1000 / Sys.getTimerResolution())> initialTime+timeLimit){
            remove = true;
        }
    }

    public Vector2f getPos() {
        return pos;
    }

    public void ApplyForce(Vector2f force) {

    }

    public void setRemove(){
        remove = true;
    }

    public boolean isRemove() {
        return remove;
    }

    public boolean checkColliding(Entity pos) {
        return false;
    }


}