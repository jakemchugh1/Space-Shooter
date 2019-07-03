package particles;

import org.lwjgl.Sys;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

import java.util.Random;

import static entities.EntityManager.getTexture;
import static utilities.Artist.DrawQuadTex;
import static utilities.Artist.LoadTexture;
import static utilities.Artist.getFrameTimeSeconds;

public class Particle {

    private int speed;
    private double endTime;
    private float x;
    private float y;
    private float velX;
    private float velY;
    private boolean remove;
    private float width;
    private float height;
    private String textureName;

    public Particle(float x, float y, float velX, float velY, double timeLimit, String textureName){
        this.textureName = textureName;
        this.x = x;
        this.y = y;
        remove = false;
        speed = 100;
        endTime = (Sys.getTime() * 1000 / Sys.getTimerResolution()) + (timeLimit * 1000);
        this.velX = velX;
        this.velY = velY;
        remove = false;
        width = 4;
        height = 4;

    }
    public Particle(float x, float y, float velX, float velY, double timeLimit, float width, float height,  String textureName){
        this.textureName = textureName;
        this.x = x;
        this.y = y;
        remove = false;
        speed = 100;
        endTime = (Sys.getTime() * 1000 / Sys.getTimerResolution()) + (timeLimit * 1000);
        this.velX = velX;
        this.velY = velY;
        remove = false;
        this.width = width;
        this.height = height;

    }

    public void Update(){
        if(y > 960) velY = -velY;
        x = x + velX*getFrameTimeSeconds()*speed;
        y = y + velY*getFrameTimeSeconds()*speed;
        if(Sys.getTime() * 1000 / Sys.getTimerResolution()> endTime) remove = true;
    }

    public void Draw(){
        DrawQuadTex(getTexture(textureName), x-width/2, y-height/2, width, height);
    }

    public boolean isRemove(){
        return remove;
    }
}
