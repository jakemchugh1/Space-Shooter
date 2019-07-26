package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;
import particles.Particle;

import java.util.HashSet;
import java.util.Random;

import static engine.SpaceEngine.mainParticles;
import static entities.EntityManager.getTexture;
import static org.lwjgl.opengl.GL11.*;
import static utilities.Artist.*;

public class Enemy2 implements Entity {

    private float x;
    private float y;

    private float tentacleModifier;
    private float rotation;

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

    private HashSet<Particle> particles;
    private HashSet<Particle> removeParticles;


    public Enemy2(Player player){
        target = player;
        Random rand = new Random();
        speed = 50;
        this.x = 1400;
        this.y = rand.nextInt(960);
        width = 96;
        height = 48;
        frame = 0;

        rotation = 0;

        health = 50;

        particles = new HashSet<>();
        removeParticles = new HashSet<>();

        tentacleModifier = 0;


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
        for(Particle p : particles){
            p.Draw();
            p.Update();
            if(p.isRemove()) removeParticles.add(p);
        }for(Particle p : removeParticles){
            particles.remove(p);
        }removeParticles.clear();


        if(frame < 48) {
            DrawQuadTexRot(getTexture("squid_1"), x, y, width, height, rotation);
            frame = frame + 1;
        }else if(frame < 72) {
            DrawQuadTexRot(getTexture("squid_2"), x, y, width, height, rotation);
            frame = frame + 1;
        }else if(frame < 96) {
            DrawQuadTexRot(getTexture("squid_3"), x, y, width, height, rotation);
            frame = frame + 1;
        }else if(frame < 120) {
            DrawQuadTexRot(getTexture("squid_4"), x, y, width, height, rotation);
            frame = frame + 1;
        }else if(frame < 144) {
            DrawQuadTexRot(getTexture("squid_5"), x, y, width, height, rotation);
            frame = frame + 1;
        }else if(frame < 168) {
            DrawQuadTexRot(getTexture("squid_4"), x, y, width, height, rotation);
            frame = frame + 1;
        }else if(frame < 192) {
            DrawQuadTexRot(getTexture("squid_3"), x, y, width, height, rotation);
            frame = frame + 1;
        }else if(frame < 216) {
            DrawQuadTexRot(getTexture("squid_2"), x, y, width, height, rotation);
            frame = frame + 1;
        }else{
            DrawQuadTexRot(getTexture("squid_1"), x, y, width, height, rotation);
            frame = 0;
            }

        mainParticles.add(new Particle(pos.x-vel.x*64-50, pos.y-vel.y*64-36, 0, 0, 3, 6, 6, "tentacle"));
        mainParticles.add(new Particle(pos.x-vel.x*64-40, pos.y-vel.y*64-36, 0, 0, 3, 6, 6, "tentacle"));
        mainParticles.add(new Particle(pos.x-vel.x*64-42, pos.y-vel.y*64-40, 0, 0, 3, 6, 6, "tentacle"));
        mainParticles.add(new Particle(pos.x-vel.x*64-42, pos.y-vel.y*64-32, 0, 0, 3, 6, 6, "tentacle"));


        if(frame < 72){
            speed = speed + 1;

        }
        else if(frame < 120){
            speed = speed + 5;
        }else if(speed < 168){
            speed = speed + 1;
        }else{
            speed = -50;
        }
        tentacleModifier = (-speed) / 100 * 3;



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

        if(vel.x > 0) {
            rotation = (float) -(Math.toDegrees(Math.atan(((vel.y) / vel.x))))+90+90;

        }else{
            rotation = (float) -(Math.toDegrees(Math.atan(((vel.y) / vel.x))))+90;
            rotation = 180+rotation+90;
            
        }


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
        if(bullet.x < pos.x + height/2 && bullet.x > pos.x - height/2 && bullet.y < pos.y + height/2 && bullet.y > pos.y - height/2 && valid){
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

    public int getFrame(){
        return frame;
    }
}
