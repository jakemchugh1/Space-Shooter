package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;
import particles.Particle;

import java.util.HashSet;
import java.util.Random;

import static entities.EntityManager.getTexture;
import static game.Game.mainParticles;
import static org.lwjgl.opengl.GL11.*;
import static utilities.Artist.*;

public class BigJelly implements Entity {

    private float x;
    private float y;

    private int height;
    private int width;

    private float speed;
    private float offset1;
    private float offset2;

    private int frame;

    private int health;

    private Texture texture;

    private Vector2f pos;
    private Vector2f vel;
    private Player target;

    private boolean remove;
    private boolean valid;
    private boolean expand;

    private HashSet<Particle> particles;
    private HashSet<Particle> removeParticles;



    public BigJelly(Player player){
        health = 35;
        target = player;
        Random rand = new Random();
        speed = 35;
        this.x = 1400;
        this.y = rand.nextInt(960);
        width = 144;
        height = 144;
        frame = 0;

        offset1 = 0;
        offset2 = 0;

        particles = new HashSet<>();
        removeParticles = new HashSet<>();

        expand = true;



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
    public BigJelly(Player player, Vector2f pos){
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

        if(expand){
            if(offset1 != 5){
                offset1 = offset1 + 0.5f;
            }else if(offset2 != 5){
                offset2 = offset2 + 0.5f;
            }else expand = false;
        }else{
            if(offset1 != 0){
                offset1 = offset1 - 0.5f;
            }else if(offset2 != 0){
                offset2 = offset2 - 0.5f;
            }else expand = true;
        }

        for(Particle p : particles){
            p.Draw();
            p.Update();
            if(p.isRemove()) removeParticles.add(p);
        }for(Particle p : removeParticles){
            particles.remove(p);
        }removeParticles.clear();

        if(vel.x<=0){
            if(frame < 8) {
                DrawQuadTexVertSkew(getTexture("big_jelly_1"), x, y, width, height, offset1, offset2);
                frame = frame + 1;
            }else if(frame < 16) {
                DrawQuadTexVertSkew(getTexture("big_jelly_2"), x, y, width, height, offset1, offset2);
                frame = frame + 1;
            }else if(frame < 24) {
                DrawQuadTexVertSkew(getTexture("big_jelly_3"), x, y, width, height, offset1, offset2);
                frame = frame + 1;
            }else if(frame < 32) {
                DrawQuadTexVertSkew(getTexture("big_jelly_4"), x, y, width, height, offset1, offset2);
                frame = frame + 1;
            }else if(frame < 40) {
                DrawQuadTexVertSkew(getTexture("big_jelly_5"), x, y, width, height, offset1, offset2);
                frame = frame + 1;
            }else if(frame < 48) {
                DrawQuadTexVertSkew(getTexture("big_jelly_6"), x, y, width, height, offset1, offset2);
                frame = frame + 1;
            }else if(frame < 56) {
                DrawQuadTexVertSkew(getTexture("big_jelly_7"), x, y, width, height, offset1, offset2);
                frame = frame + 1;
            }else if(frame < 64) {
                DrawQuadTexVertSkew(getTexture("big_jelly_8"), x, y, width, height, offset1, offset2);
                frame = frame + 1;
            }else if(frame < 72) {
                DrawQuadTexVertSkew(getTexture("big_jelly_9"), x, y, width, height, offset1, offset2);
                frame = frame + 1;
            }else if(frame < 80) {
                DrawQuadTexVertSkew(getTexture("big_jelly_10"), x, y, width, height, offset1, offset2);
                frame = frame + 1;
            }else if(frame < 88) {
                DrawQuadTexVertSkew(getTexture("big_jelly_11"), x, y, width, height, offset1, offset2);
                frame = frame + 1;
            }else if(frame < 96) {
                DrawQuadTexVertSkew(getTexture("big_jelly_12"), x, y, width, height, offset1, offset2);
                frame = frame + 1;
            }else{
                DrawQuadTexVertSkew(getTexture("big_jelly_1"), x, y, width, height, offset1, offset2);
                frame = 0;
            }
        }else{
            if(frame < 8) {
                DrawQuadTexFlipVertSkew(getTexture("big_jelly_1"), x, y, width, height, offset1, offset2);
                frame = frame + 1;
            }else if(frame < 16) {
                DrawQuadTexFlipVertSkew(getTexture("big_jelly_2"), x, y, width, height, offset1, offset2);
                frame = frame + 1;
            }else if(frame < 24) {
                DrawQuadTexFlipVertSkew(getTexture("big_jelly_3"), x, y, width, height, offset1, offset2);
                frame = frame + 1;
            }else if(frame < 32) {
                DrawQuadTexFlipVertSkew(getTexture("big_jelly_4"), x, y, width, height, offset1, offset2);
                frame = frame + 1;
            }else if(frame < 40) {
                DrawQuadTexFlipVertSkew(getTexture("big_jelly_5"), x, y, width, height, offset1, offset2);
                frame = frame + 1;
            }else if(frame < 48) {
                DrawQuadTexFlipVertSkew(getTexture("big_jelly_6"), x, y, width, height, offset1, offset2);
                frame = frame + 1;
            }else if(frame < 56) {
                DrawQuadTexFlipVertSkew(getTexture("big_jelly_7"), x, y, width, height, offset1, offset2);
                frame = frame + 1;
            }else if(frame < 64) {
                DrawQuadTexFlipVertSkew(getTexture("big_jelly_8"), x, y, width, height, offset1, offset2);
                frame = frame + 1;
            }else if(frame < 72) {
                DrawQuadTexFlipVertSkew(getTexture("big_jelly_9"), x, y, width, height, offset1, offset2);
                frame = frame + 1;
            }else if(frame < 80) {
                DrawQuadTexFlipVertSkew(getTexture("big_jelly_10"), x, y, width, height, offset1, offset2);
                frame = frame + 1;
            }else if(frame < 88) {
                DrawQuadTexFlipVertSkew(getTexture("big_jelly_11"), x, y, width, height, offset1, offset2);
                frame = frame + 1;
            }else if(frame < 96) {
                DrawQuadTexFlipVertSkew(getTexture("big_jelly_12"), x, y, width, height, offset1, offset2);
                frame = frame + 1;
            }else{
                DrawQuadTexFlipVertSkew(getTexture("big_jelly_1"), x, y, width, height, offset1, offset2);
                frame = 0;
            }
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

        pos.x = pos.x + vel.x*speed*getFrameTimeSeconds()-30*getFrameTimeSeconds();
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
            if(health <= 0){
                Random rand = new Random();
                mainParticles.add(new Particle(pos.x, pos.y, rand.nextInt(3)-1.5f, rand.nextInt(3)-1.5f, 6 , 12, 12, "fill_blue",1));
                mainParticles.add(new Particle(pos.x, pos.y, rand.nextInt(3)-1.5f, rand.nextInt(3)-1.5f, 6 , 12, 12, "fill_blue",1));
                mainParticles.add(new Particle(pos.x, pos.y, rand.nextInt(3)-1.5f, rand.nextInt(3)-1.5f, 6 , 12, 12, "fill_blue",1));

                remove = true;
            }            else if(!entity.isRemove()) {
                Random rand = new Random();
                mainParticles.add(new Particle(pos.x, pos.y, rand.nextInt(5)-2.5f, rand.nextInt(5)-2.5f, 6 , 6, 6, "fill_blue",1));

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
