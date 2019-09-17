package entities;

import static entities.EntityManager.getTexture;
import static utilities.Artist.DrawQuadTex;
import static utilities.Artist.DrawQuadTexRot;

public class Crew {

    private String name;
    private float width;
    private float height;

    public Crew(String name, float width, float height){
        this.name = name;
    }

    public void Draw(float x, float y){
        DrawQuadTex(getTexture("person_riding"), x, y, width, height);

    }
}
