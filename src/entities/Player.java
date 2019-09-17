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

public class Player implements Entity {

    private float x;
    private float y;
    private float rotation;
    private float rotationLimit;

    private int height;
    private int width;
    private int frame;
    private int crew;

    private int speed;


    private Vector2f pos;
    private Vector2f vel;

    private boolean remove;
    private boolean flip;

    private Crew[] crewMembers;



    public Player(){
        crew = 5;
        crewMembers = new Crew[5];
        crewMembers[0] = new Crew("John",16,16);
        crewMembers[1] = new Crew("Jacob",16,16);
        crewMembers[2] = new Crew("Jingleheimer Schmidt",16,16);
        crewMembers[3] = new Crew("Doofus",16,16);
        crewMembers[4] = new Crew("Doofus",16,16);

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
        if(crew >0)DrawQuadTexRot(getTexture("person_riding"), x-25, y+rotation/2 - 10, 16, 16, rotation);
        if(crew >1)DrawQuadTexRot(getTexture("person_riding"), x-10, y+rotation/4 - 10, 16, 16, rotation);
        if(crew >2)DrawQuadTexRot(getTexture("person_riding"), x+5, y- 10, 16, 16, rotation);
        if(crew >3)DrawQuadTexRot(getTexture("person_riding"), x+20, y-rotation/2.5f - 10, 16, 16, rotation);
        if(crew >4)DrawQuadTexRot(getTexture("person_riding"), x+35, y-rotation/1.5f - 10, 16, 16, rotation);
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

    public void loseCrew(){
        crew = crew - 1;
        Random rand = new Random();
        mainParticles.add(new Particle(pos.x, pos.y, rand.nextInt(3)-1.5f, rand.nextInt(3)-1.5f, 12 , 16, 16, "person",3));
        mainParticles.add(new Particle(pos.x, pos.y, rand.nextInt(3)-1.5f, rand.nextInt(3)-1.5f, 6 , 8, 8, "fill_yellow",1));
        mainParticles.add(new Particle(pos.x, pos.y, rand.nextInt(3)-1.5f, rand.nextInt(3)-1.5f, 6 , 8, 8, "fill_yellow",1));
        mainParticles.add(new Particle(pos.x, pos.y, rand.nextInt(3)-1.5f, rand.nextInt(3)-1.5f, 6 , 8, 8, "fill_yellow",1));
        mainParticles.add(new Particle(pos.x, pos.y, rand.nextInt(3)-1.5f, rand.nextInt(3)-1.5f, 6 , 8, 8, "fill_yellow",1));

    }
    public void destroySub(){
        Random rand = new Random();
        mainParticles.add(new Particle(pos.x, pos.y, rand.nextInt(3)-1.5f, rand.nextInt(3)-1.5f, 6 , 16, 16, "fill_yellow",1));
        mainParticles.add(new Particle(pos.x, pos.y, rand.nextInt(3)-1.5f, rand.nextInt(3)-1.5f, 6 , 16, 16, "fill_yellow",1));
        mainParticles.add(new Particle(pos.x, pos.y, rand.nextInt(3)-1.5f, rand.nextInt(3)-1.5f, 6 , 16, 16, "fill_yellow",1));
        mainParticles.add(new Particle(pos.x, pos.y, rand.nextInt(3)-1.5f, rand.nextInt(3)-1.5f, 6 , 16, 16, "fill_yellow",1));
        mainParticles.add(new Particle(pos.x, pos.y, rand.nextInt(3)-1.5f, rand.nextInt(3)-1.5f, 6 , 16, 16, "fill_yellow",1));

    }

    public void setRemove() {remove = true;}

    public int getCrew(){
        return crew;
    }
}
