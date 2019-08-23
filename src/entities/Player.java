package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;
import particles.Particle;

import java.util.HashSet;

import static entities.EntityManager.getTexture;
import static game.Game.mainParticles;
import static org.lwjgl.opengl.GL11.*;
import static utilities.Artist.*;

public class Player implements Entity {

    private float x;
    private float y;
    private float rotation;
    private float rotationLimit;

    private int height;
    private int width;
    private int frame;

    private int speed;


    private Vector2f pos;
    private Vector2f vel;

    private boolean remove;
    private boolean flip;



    public Player(){
        flip = false;
        speed = 200;
        this.x = 640-32;
        this.y = 480-32;
        width = 64;
        height = 32;
        frame = 0;
        rotation = 0;
        rotationLimit = 30;


        //initial position vector
        pos = new Vector2f();
        pos.x = x + width/2;
        pos.y = y + height/2;

        //initial velocity;
        vel = new Vector2f();
        vel.x = 0f;
        vel.y = 0f;


    }

    public void Draw() {
            if(frame < 3) {
                DrawQuadTexRot(getTexture("sub_1"), x, y, width, height, rotation);
                frame = frame + 1;
            }else if(frame < 6) {
                DrawQuadTexRot(getTexture("sub_2"), x, y, width, height, rotation);
                frame = frame + 1;
            }else if(frame < 9) {
                DrawQuadTexRot(getTexture("sub_3"), x, y, width, height, rotation);
                frame = frame + 1;
            }else{
                DrawQuadTexRot(getTexture("sub_1"), x, y, width, height, rotation);
                frame = 0;
                mainParticles.add(new Particle(pos.x-64, pos.y, -4, 0, 4f , 12, 12, "bubble_1"));

            }
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
            rotationLimit = 6;
        }
        else if(Keyboard.isKeyDown(Keyboard.KEY_D)&& pos.x < 1248){
            pos.x = pos.x + speed*getFrameTimeSeconds();
            x = pos.x - width/2;
            rotationLimit = 6;
        }else{
            rotationLimit = 20;
        }


        if(Keyboard.isKeyDown(Keyboard.KEY_W)&& pos.y > 32){
            pos.y = pos.y - speed*getFrameTimeSeconds();
            y = pos.y - height/2;
            if(rotation < rotationLimit)rotation = rotation + 5;
        }
        else if(Keyboard.isKeyDown(Keyboard.KEY_S) && pos.y < 928){
            pos.y = pos.y + speed*getFrameTimeSeconds();
            y = pos.y - height/2;
            if(rotation > -rotationLimit)rotation = rotation - 5;
        }
        if(rotation > 0) rotation = rotation - 2;
        else if(rotation < 0) rotation = rotation + 2;



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
