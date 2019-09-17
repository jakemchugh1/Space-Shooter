package entities;

import particles.Particle;

import java.util.Random;

import static entities.EntityManager.getTexture;
import static game.Game.mainParticles;
import static utilities.Artist.DrawQuadTex;

public class Tentacle {

    private Tentacle next;

    private int level;

    private float x;
    private float y;

    private float width;
    private float height;



    public Tentacle(int level, float posX, float posY){
        this.level = level;
        x = posX;
        y = posY;
        width = 16;
        height = 16;


        if(level != 30) next = new Tentacle(level + 1, x, y);
    }

    public void move(float posX, float posY){
        x = x+((posX-x)/4);
        y = y+((posY-y)/4);
        if(next != null){
            next.move(x,y);
        }
    }

    public void draw(){
        DrawQuadTex(getTexture("tentacle"), x-(width/2), y-(height/2), width, height);
        if(next != null){
            next.draw();
        }
    }

    public void particle(){
        Random rand = new Random();
        mainParticles.add(new Particle(x-(width/2), y-(height/2), rand.nextInt(3)-1.5f, rand.nextInt(3)-1.5f, 6 , width, height, "tentacle",1));
        if(next != null) next.particle();
    }

    private boolean checkColliding(float posX, float posY){

        float deltaX = x - posX;
        float deltaY = y - posY;
        float magnitude = (float) Math.sqrt((deltaX*deltaX) + (deltaY*deltaY));

        if(magnitude < 16){
            return true;
        }else return false;
    }
}
