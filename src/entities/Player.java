package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;
import particles.Particle;

import java.util.HashSet;

import static entities.EntityManager.getTexture;
import static org.lwjgl.opengl.GL11.*;
import static utilities.Artist.*;

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
    private boolean flip;

    private HashSet<Particle> particles;
    private HashSet<Particle> removeParticles;


    public Player(){
        flip = false;
        speed = 200;
        this.x = 640-32;
        this.y = 480-32;
        width = 128;
        height = 64;
        frame = 0;

        particles = new HashSet<>();
        removeParticles = new HashSet<>();


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
        if(flip){
            if(frame < 3) {
                DrawQuadTexFlip(getTexture("sub_1"), x, y, width, height);
                frame = frame + 1;
            }else if(frame < 6) {
                DrawQuadTexFlip(getTexture("sub_2"), x, y, width, height);
                frame = frame + 1;
            }else if(frame < 9) {
                DrawQuadTexFlip(getTexture("sub_3"), x, y, width, height);
                frame = frame + 1;
            }else{
                DrawQuadTexFlip(getTexture("sub_1"), x, y, width, height);
                frame = 0;
            }
        }else{
            if(frame < 3) {
                DrawQuadTex(getTexture("sub_1"), x, y, width, height);
                frame = frame + 1;
            }else if(frame < 6) {
                DrawQuadTex(getTexture("sub_2"), x, y, width, height);
                frame = frame + 1;
            }else if(frame < 9) {
                DrawQuadTex(getTexture("sub_3"), x, y, width, height);
                frame = frame + 1;
            }else{
                DrawQuadTex(getTexture("sub_1"), x, y, width, height);
                frame = 0;
            }
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
            flip = true;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_D)&& pos.x < 1248){
            pos.x = pos.x + speed*getFrameTimeSeconds();
            x = pos.x - width/2;
            flip = false;
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
