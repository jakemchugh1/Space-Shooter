package entities;

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

public class Enemy implements Entity {

    private float x;
    private float y;

    private int height;
    private int width;

    private float speed;

    private int frame;

    private Texture texture;

    private Vector2f pos;
    private Vector2f vel;
    private Player target;

    private boolean remove;
    private boolean valid;



    public Enemy(Player player){
        target = player;
        Random rand = new Random();
        speed = 200;
        this.x = rand.nextInt(1280);
        this.y = rand.nextInt(960);
        width = 32;
        height = 32;
        frame = 0;



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
    public Enemy(Player player, Vector2f pos){
        target = player;
        speed = 50;
        this.pos = pos;
        width = 24;
        height = 24;
        frame = 0;



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
        if(frame < 3) {
            DrawQuadTex(getTexture("enemy1"), x, y, width, height);
            frame = frame + 1;
        }else if(frame < 6) {
            DrawQuadTex(getTexture("enemy2"), x, y, width, height);
            frame = frame + 1;
        }else if(frame < 9) {
            DrawQuadTex(getTexture("enemy3"), x, y, width, height);
            frame = frame + 1;
        }else if(frame < 12) {
            DrawQuadTex(getTexture("enemy4"), x, y, width, height);
            frame = frame + 1;
        }else if(frame < 15) {
            DrawQuadTex(getTexture("enemy5"), x, y, width, height);
            frame = frame + 1;
        }else if(frame < 18) {
            DrawQuadTex(getTexture("enemy6"), x, y, width, height);
            frame = frame + 1;
        }else if(frame < 21) {
            DrawQuadTex(getTexture("enemy7"), x, y, width, height);
            frame = frame + 1;
        }else if(frame < 24) {
            DrawQuadTex(getTexture("enemy8"), x, y, width, height);
            frame = frame + 1;
        }else if(frame < 27) {
            DrawQuadTex(getTexture("enemy9"), x, y, width, height);
            frame = frame + 1;
        }else if(frame < 3) {
            DrawQuadTex(getTexture("enemy10"), x, y, width, height);
            frame = frame + 1;
        }else if(frame < 6) {
            DrawQuadTex(getTexture("enemy11"), x, y, width, height);
            frame = frame + 1;
        }else if(frame < 9) {
            DrawQuadTex(getTexture("enemy12"), x, y, width, height);
            frame = frame + 1;
        }else if(frame < 12) {
            DrawQuadTex(getTexture("enemy13"), x, y, width, height);
            frame = frame + 1;
        }else if(frame < 15) {
            DrawQuadTex(getTexture("enemy12"), x, y, width, height);
            frame = frame + 1;
        }else if(frame < 21) {
            DrawQuadTex(getTexture("enemy10"), x, y, width, height);
            frame = frame + 1;
        }else if(frame < 24) {
            DrawQuadTex(getTexture("enemy9"), x, y, width, height);
            frame = frame + 1;
        }else if(frame < 27) {
            DrawQuadTex(getTexture("enemy8"), x, y, width, height);
            frame = frame + 1;
        }else if(frame < 30) {
            DrawQuadTex(getTexture("enemy7"), x, y, width, height);
            frame = frame + 1;
        }else if(frame < 33) {
            DrawQuadTex(getTexture("enemy6"), x, y, width, height);
            frame = frame + 1;
        }else if(frame < 36) {
            DrawQuadTex(getTexture("enemy5"), x, y, width, height);
            frame = frame + 1;
        }else if(frame < 39) {
            DrawQuadTex(getTexture("enemy4"), x, y, width, height);
            frame = frame + 1;
        }else if(frame < 42) {
            DrawQuadTex(getTexture("enemy3"), x, y, width, height);
            frame = frame + 1;
        }else if(frame < 45) {
            DrawQuadTex(getTexture("enemy2"), x, y, width, height);
            frame = frame + 1;
        }else{
            DrawQuadTex(getTexture("enemy1"), x, y, width, height);
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
        speed = speed + 0.1f;
    }

    public Vector2f getPos() {
        return pos;
    }

    public void ApplyForce(Vector2f force) {

    }
    public boolean checkColliding(Entity entity){
        Vector2f bullet = entity.getPos();
        if(bullet.x < pos.x + width/2 && bullet.x > pos.x - width/2 && bullet.y < pos.y + height/2 && bullet.y > pos.y - height/2 && valid){
            remove = true;
            entity.setRemove();
            return true;
        }return false;

    }

    public boolean isRemove() {
        return remove;
    }

    public void setRemove() {remove = true;}
}
