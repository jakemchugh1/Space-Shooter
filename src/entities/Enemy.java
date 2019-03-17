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

public class Enemy implements Entity {

    private float x;
    private float y;

    private int height;
    private int width;

    private int speed;

    private int frame;

    private Texture texture;

    private Vector2f pos;
    private Vector2f vel;
    private Player target;

    private boolean remove;
    private boolean valid;

    private Texture texture1;
    private Texture texture2;
    private Texture texture3;
    private Texture texture4;
    private Texture texture5;
    private Texture texture6;
    private Texture texture7;
    private Texture texture8;
    private Texture texture9;
    private Texture texture10;
    private Texture texture11;
    private Texture texture12;
    private Texture texture13;


    public Enemy(Player player){
        target = player;
        Random rand = new Random();
        speed = 200;
        this.x = rand.nextInt(1280);
        this.y = rand.nextInt(960);
        width = 32;
        height = 32;
        frame = 0;

        texture1 = LoadTexture("enemy1");
        texture2 = LoadTexture("enemy2");
        texture3 = LoadTexture("enemy3");
        texture4 = LoadTexture("enemy4");
        texture5 = LoadTexture("enemy5");
        texture6 = LoadTexture("enemy6");
        texture7 = LoadTexture("enemy7");
        texture8 = LoadTexture("enemy8");
        texture9 = LoadTexture("enemy9");
        texture6 = LoadTexture("enemy10");
        texture7 = LoadTexture("enemy11");
        texture8 = LoadTexture("enemy12");
        texture9 = LoadTexture("enemy13");


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
        }else if(frame < 9) {
            DrawQuadTex(texture3, x, y, width, height);
            frame = frame + 1;
        }else if(frame < 12) {
            DrawQuadTex(texture4, x, y, width, height);
            frame = frame + 1;
        }else if(frame < 15) {
            DrawQuadTex(texture5, x, y, width, height);
            frame = frame + 1;
        }else if(frame < 18) {
            DrawQuadTex(texture6, x, y, width, height);
            frame = frame + 1;
        }else if(frame < 21) {
            DrawQuadTex(texture7, x, y, width, height);
            frame = frame + 1;
        }else if(frame < 24) {
            DrawQuadTex(texture8, x, y, width, height);
            frame = frame + 1;
        }else if(frame < 27) {
            DrawQuadTex(texture9, x, y, width, height);
            frame = frame + 1;
        }else if(frame < 3) {
            DrawQuadTex(texture10, x, y, width, height);
            frame = frame + 1;
        }else if(frame < 6) {
            DrawQuadTex(texture11, x, y, width, height);
            frame = frame + 1;
        }else if(frame < 9) {
            DrawQuadTex(texture12, x, y, width, height);
            frame = frame + 1;
        }else if(frame < 12) {
            DrawQuadTex(texture13, x, y, width, height);
            frame = frame + 1;
        }else if(frame < 15) {
            DrawQuadTex(texture12, x, y, width, height);
            frame = frame + 1;
        }else if(frame < 18) {
            DrawQuadTex(texture11, x, y, width, height);
            frame = frame + 1;
        }else if(frame < 21) {
            DrawQuadTex(texture10, x, y, width, height);
            frame = frame + 1;
        }else if(frame < 24) {
            DrawQuadTex(texture9, x, y, width, height);
            frame = frame + 1;
        }else if(frame < 27) {
            DrawQuadTex(texture8, x, y, width, height);
            frame = frame + 1;
        }else if(frame < 30) {
            DrawQuadTex(texture7, x, y, width, height);
            frame = frame + 1;
        }else if(frame < 33) {
            DrawQuadTex(texture6, x, y, width, height);
            frame = frame + 1;
        }else if(frame < 36) {
            DrawQuadTex(texture5, x, y, width, height);
            frame = frame + 1;
        }else if(frame < 39) {
            DrawQuadTex(texture4, x, y, width, height);
            frame = frame + 1;
        }else if(frame < 42) {
            DrawQuadTex(texture3, x, y, width, height);
            frame = frame + 1;
        }else if(frame < 45) {
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
