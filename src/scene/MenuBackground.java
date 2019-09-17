package scene;

import particles.Particle;

import java.awt.*;

import static entities.EntityManager.getTexture;
import static game.Game.mainParticles;
import static utilities.Artist.DrawQuadTex;
import static utilities.Artist.DrawQuadTexRot;

public class MenuBackground {

    private int backgroundType;
    private int frame;
    private float offset;

    public MenuBackground(){
        backgroundType = 0;
        offset = 0;
        frame = 0;
    }

    public MenuBackground(int in){
        backgroundType = in;
    }

    public void draw(){
        if(offset >= 64) offset = 0;
        for(int i = 0 ; i < 16 ; i++){
            for(int j = 0 ; j < 21 ; j++){
                if(backgroundType == 0)DrawQuadTex(getTexture("background_tile3"), (j - 1)*64 + offset, (i - 1)*64 + offset, 64, 64);
                else if(backgroundType == 1)DrawQuadTex(getTexture("background_tile2"), (j - 1)*64 + offset, (i - 1)*64 + offset, 64, 64);
            }
        }

        frame = frame + 1;
        offset = offset + 0.5f;
    }

    public void setBackgroundType(int in){
        backgroundType = in;
    }
}
