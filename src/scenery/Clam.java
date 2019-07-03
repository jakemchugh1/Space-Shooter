package scenery;

import entities.Entity;
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

public class Clam implements Scenery{

    private float x;
    private float y;

    private int height;
    private int width;

    private int frame;

    private HashSet<Particle> particles;
    private HashSet<Particle> particlesRemove;

    private Random rand;

    private boolean open;


    public Clam(float x, float y, int width, int height){
        frame = 0;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        particles = new HashSet<>();
        particlesRemove = new HashSet<>();
        rand = new Random();
        open = false;

    }

    public void Draw() {
        if(frame < 40){
            DrawQuadTex(getTexture("clam1"), x, y, width, height);
            frame = frame + 1;
        }else if(frame < 48 && open){
            DrawQuadTex(getTexture("clam2"), x, y, width, height);
            frame = frame + 1;
        }else{
            open = false;
            DrawQuadTex(getTexture("clam1"), x, y, width, height);
            frame = 0;
        }
        if(frame == 40 && rand.nextInt(10) == 1){
            open = true;
            particles.add(new Particle(x+84,y+30,-2,-1,3,32,32,"bubble"));
        }
        for(Particle p : particles){
            if(p.isRemove()) particlesRemove.add(p);
            else{
                p.Draw();
                p.Update();
            }
        }
        for(Particle p : particlesRemove){
            particles.remove(p);
        }
        x = x - 4;
    }

    public int getFrame(){
        return frame;
    }

    public float getX(){
        return x;
    }
}
