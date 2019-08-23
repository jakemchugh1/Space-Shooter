package entities;

import static entities.EntityManager.getTexture;
import static utilities.Artist.DrawQuadTex;

public class Tentacle {

    private Tentacle next;

    private int level;

    private float x;
    private float y;

    private float width;
    private float height;

    private float velX;
    private float velY;


    public Tentacle(int level, float posX, float posY){
        this.level = level;
        x = posX;
        y = posY;
        width = 16;
        height = 16;


        if(level != 20) next = new Tentacle(level + 1, x, y);
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

    private boolean checkColliding(float posX, float posY){

        float deltaX = x - posX;
        float deltaY = y - posY;
        float magnitude = (float) Math.sqrt((deltaX*deltaX) + (deltaY*deltaY));

        if(magnitude < 16){
            return true;
        }else return false;
    }
}
