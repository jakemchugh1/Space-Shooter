package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;
import particles.Particle;

import java.util.HashSet;
import java.util.Random;

import static entities.EntityManager.getTexture;
import static org.lwjgl.opengl.GL11.*;
import static utilities.Artist.*;

public class Enemy implements Entity {

    private float x;
    private float y;

    private int height;
    private int width;

    private float speed;

    private int frame;

    private int health;
    private float offset_1;
    private float offset_2;

    private Texture texture;

    private Vector2f pos;
    private Vector2f vel;
    private Player target;

    private boolean remove;
    private boolean valid;
    boolean expand;

    private HashSet<Particle> particles;
    private HashSet<Particle> removeParticles;



    public Enemy(Player player){
        health = 3;
        target = player;
        Random rand = new Random();
        speed = 75;
        this.x = 1400;
        this.y = rand.nextInt(960);
        width = 96;
        height = 96;
        frame = 0;
        offset_1 = 0;
        offset_2 = 0;
        expand = true;

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

        if(pos.x > player.getPos().x - 128 && pos.x < player.getPos().x + 128 && pos.y > player.getPos().y - 128 && pos.y < player.getPos().y + 128){
            valid = false;
            remove = true;
        }else valid = true;

    }
    public Enemy(Player player, float x, float y){
        health = 3;
        target = player;
        Random rand = new Random();
        speed = 75;
        this.x = x;
        this.y = y;
        width = 96;
        height = 96;
        frame = 0;
        offset_1 = 0;
        offset_2 = 0;

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

        if(pos.x > player.getPos().x - 128 && pos.x < player.getPos().x + 128 && pos.y > player.getPos().y - 128 && pos.y < player.getPos().y + 128){
            valid = false;
            remove = true;
        }else valid = true;

    }

    public void Draw() {
        if(expand){
            if(offset_1 != 5){
                offset_1 = offset_1 + 1;
            }else if(offset_2 != 5){
                offset_2 = offset_2 + 1;
            }else expand = false;
        }else{
            if(offset_1 != 0){
                offset_1 = offset_1 - 1;
            }else if(offset_2 != 0){
                offset_2 = offset_2 - 1;
            }else expand = true;
        }

        for(Particle p : particles){
            p.Draw();
            p.Update();
            if(p.isRemove()) removeParticles.add(p);
        }for(Particle p : removeParticles){
            particles.remove(p);
        }removeParticles.clear();

        if(frame < 24) {
            DrawQuadTexVertSkew(getTexture("jellyfish_1"), x, y, width, height, offset_1, offset_2);
            frame = frame + 1;
        }else if(frame < 48) {
            DrawQuadTexVertSkew(getTexture("jellyfish_2"), x, y, width, height, offset_1, offset_2);
            frame = frame + 1;
        }else if(frame < 72) {
            DrawQuadTexVertSkew(getTexture("jellyfish_3"), x, y, width, height, offset_1, offset_2);
            frame = frame + 1;
        }else{
            DrawQuadTexVertSkew(getTexture("jellyfish_1"), x, y, width, height, offset_1, offset_2);
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

        pos.x = pos.x + vel.x*speed*getFrameTimeSeconds()-50*getFrameTimeSeconds();
        pos.y = pos.y + vel.y*speed*getFrameTimeSeconds();

        x = pos.x - width/2;
        y = pos.y - height/2;

        if(x < 0 - width) remove = true;
    }

    public Vector2f getPos() {
        return pos;
    }

    public void ApplyForce(Vector2f force) {

    }
    public boolean checkColliding(Entity entity){
        Vector2f bullet = entity.getPos();
        if(bullet.x < pos.x + width/3 && bullet.x > pos.x - width/3 && bullet.y < pos.y + height/3 && bullet.y > pos.y - height/3 && valid){
            if(health <= 0)remove = true;
            else if(!entity.isRemove()) {
                health = health - 1;
                if(entity instanceof Bullet) entity.setRemove();
            }
            return true;
        }return false;

    }

    public boolean isRemove() {
        return remove;
    }

    public void setRemove() {remove = true;}
}
