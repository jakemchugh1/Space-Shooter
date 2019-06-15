package scene;

import particles.Particle;
import scenery.*;

import java.util.HashSet;

import static entities.EntityManager.getTexture;
import static utilities.Artist.DrawQuadTex;

public class Background {

    private int width;
    private int height;

    private String fileName;

    private HashSet<Scenery> scenery;

    public Background(int width, int height, String fileName){
        scenery = new HashSet<>();
        this.width = width;
        this.height = height;
        this.fileName = fileName;

        scenery.add(new ShortSeaweed(400,500,128,128));
        scenery.add(new ShortSeaweed(420,510,128,128));
        scenery.add(new ShortSeaweed(435,515,128,128));

        scenery.add(new ShortSeaweed(1200,490,128,128));
        scenery.add(new ShortSeaweed(1100,485,128,128));
        scenery.add(new ShortSeaweed(900,495,128,128));

        scenery.add(new DancingStarfish(200,800,128,128));
        scenery.add(new DancingStarfish(1000,700,128,128));

        scenery.add(new Chest(1000,750,256,256));

        scenery.add(new Clam(100,490,128,128));

    }

    public void Draw(){
        DrawQuadTex(getTexture(fileName), 0, 0, width+764, height+84);
        for(Scenery s : scenery){
            s.Draw();
        }
    }
}
