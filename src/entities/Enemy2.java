package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

import java.util.Random;

import static org.lwjgl.opengl.GL11.*;
import static utilities.Artist.DrawQuadTex;
import static utilities.Artist.LoadTexture;
import static utilities.Artist.getFrameTimeSeconds;

public class Enemy2 implements Entity {

    private float x;
    private float y;

    private int height;
    private int width;

    private int speed;

    private int frame;

    private int health;

    private Texture texture;

    private Vector2f pos;
    private Vector2f vel;
    private Player target;

    private boolean remove;
    private boolean valid;

    private Texture texture1;
    private Texture texture2;


    public Enemy2(Player player){
        target = player;
        Random rand = new Random();
        speed = 50;
        this.x = rand.nextInt(1280);
        this.y = rand.nextInt(960);
        width = 64;
        height = 64;
        frame = 0;

        health = 20;

        texture1 = LoadTexture("squid1");
        texture2 = LoadTexture("squid2");


        //initial position vector
        pos = new Vector2f();
        pos.x = x + width/2;
        pos.y = y + height/2;

        //initial velocity;
        vel = new Vector2f();
        vel.x = 0f;
        vel.y = 0f;

        remove = false;

        if(pos.x > player.getPos().x - 128 && pos.x < player.getPos().x + 128 && pos.y > player.getPos().y - 128 && pos.y < player.getPos().y + 128){
            valid = false;
            remove = true;
        }else valid = true;

    }

    public void Draw() {
        if(frame < 3) {
            DrawQuadTex(texture1, x, y, width, height);
            frame = frame + 1;
        }else if(frame < 6) {
            DrawQuadTex(texture2, x, y, width, height);
            frame = frame + 1;
        }else{
            DrawQuadTex(texture1, x, y, width, height);
            frame = 0;
        }
    }

    public void setPos() {
        float mouseX = target.getPos().x;
        float mouseY = target.getPos().y;

        float deltaX = mouseX-pos.x;
        float deltaY = mouseY-pos.y;

        float magnitude = (float) Math.sqrt(deltaX*deltaX + deltaY*deltaY);

        vel = new Vector2f();
        vel.x = deltaX/magnitude;
        vel.y = deltaY/magnitude;

        pos.x = pos.x + vel.x*speed*getFrameTimeSeconds();
        pos.y = pos.y + vel.y*speed*getFrameTimeSeconds();

        x = pos.x - width/2;
        y = pos.y - height/2;
    }

    public Vector2f getPos() {
        return pos;
    }

    public void ApplyForce(Vector2f force) {

    }
    public boolean checkColliding(Entity entity){
        Vector2f bullet = entity.getPos();
        if(bullet.x < pos.x + width/2 && bullet.x > pos.x - width/2 && bullet.y < pos.y + height/2 && bullet.y > pos.y - height/2 && valid){
            health = health - 1;
            if(health<=0){
                entity.setRemove();
                remove = true;
            }
            return true;
        }return false;

    }

    public boolean isRemove() {
        return remove;
    }

    public void setRemove() {remove = true;}
}
