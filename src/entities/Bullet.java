package entities;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;
import particles.Particle;

import java.util.HashSet;
import java.util.Random;

import static entities.EntityManager.getTexture;
import static engine.SpaceEngine.mainParticles;
import static org.lwjgl.opengl.GL11.*;
import static utilities.Artist.DrawQuadTex;
import static utilities.Artist.LoadTexture;
import static utilities.Artist.getFrameTimeSeconds;

public class Bullet implements Entity {

    private float x;
    private float y;

    private float height;
    private float width;

    private int speed;

    private Texture texture;

    private Vector2f pos;
    private Vector2f vel;

    private boolean remove;
    private double initialTime;
    private double timeLimit;

    private HashSet<Particle> particles;
    private HashSet<Particle> removeParticles;

    private Random rand;


    public Bullet(Vector2f playerPos){
        initialTime = (Sys.getTime() * 1000 / Sys.getTimerResolution());
        timeLimit = 1*1000;
        speed = 400;
        width = 8;
        height = 8;
        this.texture = LoadTexture("bubble_1");
        rand = new Random();
        particles = new HashSet<>();
        removeParticles = new HashSet<>();


        //initial position vector
        pos = new Vector2f();
        pos.x = playerPos.x;
        pos.y = playerPos.y;
        x = pos.x-width/2;
        y = pos.y-height/2;

        //initial velocity;
        float mouseX = Mouse.getX();
        float mouseY = 960-Mouse.getY();

        float deltaX = mouseX-pos.x;
        float deltaY = mouseY-pos.y;

        float magnitude = (float) Math.sqrt(deltaX*deltaX + deltaY*deltaY);

        vel = new Vector2f();
        vel.x = deltaX/magnitude;
        vel.y = deltaY/magnitude;

        remove = false;

    }

    public void Draw() {

        for(Particle p : particles){
            p.Draw();
            p.Update();
            if(p.isRemove()) removeParticles.add(p);
        }for(Particle p : removeParticles){
            particles.remove(p);
        }removeParticles.clear();

        DrawQuadTex(getTexture("bullet"), x, y, width, height);


        if(rand.nextInt(4)== 0)mainParticles.add(new Particle(pos.x,pos.y,0.5f, 0.5f ,0.25, "bullet"));
        if(rand.nextInt(4)== 1) mainParticles.add(new Particle(pos.x,pos.y,-0.5f, 0.5f ,0.25, "bullet"));
        if(rand.nextInt(4)== 2)mainParticles.add(new Particle(pos.x,pos.y,0.5f, -0.5f ,0.25, "bullet"));
        if(rand.nextInt(4)== 3)mainParticles.add(new Particle(pos.x,pos.y,-0.5f, -0.5f ,0.25, "bullet"));

    }

    public void setPos() {
        pos.x = pos.x + vel.x*getFrameTimeSeconds() * speed;
        pos.y = pos.y + vel.y*getFrameTimeSeconds()* speed;

        x = pos.x-width/2;
        y = pos.y-height/2;

        if(pos.y > 960) vel.y = -vel.y;

        if ((Sys.getTime() * 1000 / Sys.getTimerResolution())> initialTime+timeLimit){
            remove = true;
        }
    }

    public Vector2f getPos() {
        return pos;
    }

    public void ApplyForce(Vector2f force) {

    }

    public void setRemove(){
        remove = true;
    }

    public boolean isRemove() {
        return remove;
    }

    public boolean checkColliding(Entity pos) {
        return false;
    }


}