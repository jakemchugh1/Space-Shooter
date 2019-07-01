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

    private HashSet<Particle> particles;
    private HashSet<Particle> removeParticles;



    public Enemy(Player player){
        target = player;
        Random rand = new Random();
        speed = 75;
        this.x = 1400;
        this.y = rand.nextInt(960);
        width = 96;
        height = 96;
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
        for(Particle p : particles){
            p.Draw();
            p.Update();
            if(p.isRemove()) removeParticles.add(p);
        }for(Particle p : removeParticles){
            particles.remove(p);
        }removeParticles.clear();

        if(frame < 24) {
            DrawQuadTex(getTexture("jellyfish_1"), x, y, width, height);
            frame = frame + 1;
        }else if(frame < 48) {
            DrawQuadTex(getTexture("jellyfish_2"), x, y, width, height);
            frame = frame + 1;
        }else if(frame < 72) {
            DrawQuadTex(getTexture("jellyfish_3"), x, y, width, height);
            frame = frame + 1;
        }else{
            DrawQuadTex(getTexture("jellyfish_1"), x, y, width, height);
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
