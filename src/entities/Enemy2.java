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

    private int tCounter;

    private float offset1;
    private float offset2;

    private Texture texture;

    private Vector2f pos;
    private Vector2f vel;
    private Player target;

    private boolean remove;


    private Tentacle tentacle1;


    public Enemy2(Player player){
        target = player;
        Random rand = new Random();
        speed = 50;
        this.x = 1400;
        this.y = rand.nextInt(960);
        width = 96;
        height = 48;
        frame = 0;

        tCounter = 0;

        offset1 = 0;
        offset2 = 0;

        rotation = 0;

        health = 50;


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

        tentacle1 = new Tentacle(1,pos.x,pos.y);

    }

    public void Draw() {

        float tRotation;

        if(vel.x > 0) {
            tRotation = rotation;

        }else{
            tRotation = -rotation;

        }

        tentacle1.move((float) (pos.x - 32 + (Math.cos(tRotation))), (float)(pos.y - 16 + (Math.sin(tRotation))));
        tentacle1.draw();


        if(frame < 48) {
            DrawQuadTexRotExpand(getTexture("squid_1"), x, y, width, height,offset1,offset2, rotation);
            frame = frame + 1;
        }else if(frame < 72) {
            DrawQuadTexRotExpand(getTexture("squid_2"), x, y, width, height,offset1,offset2, rotation);
            frame = frame + 1;
        }else if(frame < 96) {
            DrawQuadTexRotExpand(getTexture("squid_3"), x, y, width, height,offset1,offset2, rotation);
            frame = frame + 1;
        }else if(frame < 120) {
            DrawQuadTexRotExpand(getTexture("squid_4"), x, y, width, height,offset1,offset2, rotation);
            frame = frame + 1;
        }else if(frame < 144) {
            DrawQuadTexRotExpand(getTexture("squid_5"), x, y, width, height,offset1,offset2, rotation);
            frame = frame + 1;
        }else if(frame < 168) {
            DrawQuadTexRotExpand(getTexture("squid_4"), x, y, width, height,offset1,offset2, rotation);
            frame = frame + 1;
        }else if(frame < 192) {
            DrawQuadTexRotExpand(getTexture("squid_3"), x, y, width, height,offset1,offset2, rotation);
            frame = frame + 1;
        }else if(frame < 216) {
            DrawQuadTexRotExpand(getTexture("squid_2"), x, y, width, height,offset1,offset2, rotation);
            frame = frame + 1;
        }else{
            DrawQuadTexRotExpand(getTexture("squid_1"), x, y, width, height,offset1,offset2, rotation);
            frame = 0;
            }




        if(frame < 72){
            speed = speed + 2;
            if(offset2 > -20) offset2 = offset2 - 0.5f;
        }
        else if(frame < 120){
            speed = speed + 5;
            if(offset2 > -20) offset2 = offset2 - 0.25f;
        }else if(speed < 168){
            speed = speed + 1;
            if(offset2 < 10) offset2 = offset2 + 1f;
        }else{
            speed = -50;
            if(offset2 < 10) offset2 = offset2 + 5f;
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
        if(bullet.x < pos.x + height/2 && bullet.x > pos.x - height/2 && bullet.y < pos.y + height/2 && bullet.y > pos.y - height/2 ){
            if(health <= 0){
                Random rand = new Random();
                mainParticles.add(new Particle(pos.x, pos.y, rand.nextInt(3)-1.5f, rand.nextInt(3)-1.5f, 6 , 12, 12, "tentacle",1));
                mainParticles.add(new Particle(pos.x, pos.y, rand.nextInt(3)-1.5f, rand.nextInt(3)-1.5f, 6 , 12, 12, "tentacle",1));
                mainParticles.add(new Particle(pos.x, pos.y, rand.nextInt(3)-1.5f, rand.nextInt(3)-1.5f, 6 , 12, 12, "tentacle",1));

                remove = true;
            }
            else if(!entity.isRemove()) {
                Random rand = new Random();
                health = health - 1;
                mainParticles.add(new Particle(pos.x, pos.y, rand.nextInt(5)-2.5f, rand.nextInt(5)-2.5f, 6 , 6, 6, "tentacle",1));
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
