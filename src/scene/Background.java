package scene;

import particles.Particle;
import scenery.*;

import java.util.HashSet;
import java.util.Random;

import static entities.EntityManager.getTexture;
import static utilities.Artist.DrawQuadTex;

public class Background {

    private int width;
    private int height;

    private String fileName;

    private HashSet<Scenery> scenery;
    private HashSet<Scenery> removeScenery;



    int[][] background;

    public Background(int width, int height, String fileName){
        scenery = new HashSet<>();
        removeScenery = new HashSet<>();
        this.width = width;
        this.height = height;
        this.fileName = fileName;

        background = new int[320][3];
        Random rand = new Random();

        background[0][0] = rand.nextInt(360)+100;
        background[0][1] = 200 + background[0][0];
        background[0][2] = 150 + background[0][1];






        //initial generation of sand
        for(int i = 1 ; i < 320 ; i++){
            background[i][0] = background[i-1][0] + (rand.nextInt(5) - 2) ;
        }
        //initial generation of hills
        for(int i = 1 ; i < 320 ; i++){
            background[i][1] = background[i-1][1] + (rand.nextInt(10) - 4) ;
        }
        for(int i = 1 ; i < 320 ; i++){
            background[i][1] = background[i-1][1] + (rand.nextInt(30) - 14) ;
        }


    }

    public void Draw(){

        //procedural hills

        DrawQuadTex(getTexture("water"), 0, 0, 1280, 960);

        for(int i = 0 ; i < 320 ; i++){
            DrawQuadTex(getTexture("hill_2"), i*4, 960, 4, -background[i][2]);
        }
        for(int i = 0 ; i < 320 ; i++){
            DrawQuadTex(getTexture("hill_1"), i*4, 960, 4, -background[i][1]);
        }



        //procedural sand
        for(int i = 0 ; i < 320 ; i++){
            DrawQuadTex(getTexture("sand_2"), i*4, 960, 4, -background[i][0] - 16);
        }

        for(int i = 0 ; i < 320 ; i++){
            DrawQuadTex(getTexture("sand_1"), i*4, 960, 4, -background[i][0]);
        }

        //shuffle and update
        for(int i = 1 ; i < 320 ; i++){
            int tempInt = background[i][0];
            background[i-1][0] = tempInt;
        }
        for(int i = 1 ; i < 320 ; i++){
            int tempInt = background[i][1];
            background[i-1][1] = tempInt;
        }
        for(int i = 1 ; i < 320 ; i++){
            int tempInt = background[i][2];
            background[i-1][2] = tempInt;
        }
        Random rand = new Random();

        //new terrain (hill_1)
        background[319][1] = background[318][1] + (rand.nextInt(10) - 4) ;
        background[319][2] = background[318][2] + (rand.nextInt(30) - 14) ;


        //new terrain (sand)
        background[319][0] = background[318][0] + (rand.nextInt(5) - 2) ;

        if(background[319][1] < background[319][0]) background[319][1] = background[319][0];
        if(background[319][1] > background[319][0] + 320) background[319][1] = background[319][1] - 5;

        if(background[319][2] < background[319][1]) background[319][2] = background[319][1];
        if(background[319][2] > background[319][1] + 160) background[319][2] = background[319][2] - 15;

        if(rand.nextInt(60)==1){
            scenery.add(new ShortSeaweed(1376,960-(rand.nextInt(background[319][0])),96,96));
        }
        if(rand.nextInt(120)==2){
            scenery.add(new DancingStarfish(1376,960-(rand.nextInt(background[319][0])),96,96));
        }
        if(rand.nextInt(360)==3){
            scenery.add(new Chest(1376,960-(rand.nextInt(background[319][0])),128,128));
        }
        if(rand.nextInt(360)==4){
            scenery.add(new Clam(1376,960-(rand.nextInt(background[319][0])),96,96));
        }



        for(Scenery s : scenery){
            s.Draw();
            if(s.getX() < -128) removeScenery.add(s);
        }
        for(Scenery s : removeScenery){
            scenery.remove(s);
        }removeScenery.clear();
    }
}
