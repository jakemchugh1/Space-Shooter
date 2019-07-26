package scene;

import particles.Particle;
import scenery.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;

import static entities.EntityManager.getTexture;
import static utilities.Artist.DrawQuadTex;

public class Background {

    private int width;
    private int height;

    private String fileName;

    private ArrayList<Scenery> scenery;
    private HashSet<Scenery> removeScenery;



    int[][] background;

    public Background(int width, int height, String fileName){
        scenery = new ArrayList<>();
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
        int tempInt = (rand.nextInt(10) - 4);
        background[319][1] = background[318][1] + tempInt;
        if(background[319][1] <= 0){
            background[319][1] = 0;
        }else if(background[319][1] > 960){
            background[319][1] = 960;
        }
        tempInt = (rand.nextInt(30) - 14);
        background[319][2] = background[318][2] + tempInt;
        if(background[319][2] <= 0){
            background[319][2] = 0;
        }else if(background[319][1] > 960){
            background[319][2] = 960;
        }


        //new terrain (sand)
        tempInt = (rand.nextInt(5) - 2);
        background[319][0] = background[318][0] + tempInt;
        if(background[319][0] <= 0){
            background[319][0] = 0;
        }else if(background[319][0] > 960){
            background[319][0] = 960;
        }

        if(background[319][1] < background[319][0]) background[319][1] = background[319][0];
        if(background[319][1] > background[319][0] + 320) background[319][1] = background[319][1] - 5;

        if(background[319][2] < background[319][1]) background[319][2] = background[319][1];
        if(background[319][2] > background[319][1] + 160) background[319][2] = background[319][2] - 15;

        if(background[319][0] < 0) background[319][0] = 0;
        if(rand.nextInt(60)==1){
            int tempInt1;
            if(background[319][0] >= 1) {
                tempInt1 = rand.nextInt(background[319][0]);
            }else{
                tempInt1 = 0;
            }
            scenery.add(new ShortSeaweed(1376,960-tempInt1,96,96));
            scenery.sort(comparator);
        }
        if(rand.nextInt(120)==2){
            int tempInt1;
            if(background[319][0] >= 1) {
                tempInt1 = rand.nextInt(background[319][0]);
            }else{
                tempInt1 = 0;
            }
            scenery.add(new DancingStarfish(1376,960-tempInt1,96,96));
            scenery.sort(comparator);
        }
        if(rand.nextInt(360)==3){
            int tempInt1;
            if(background[319][0] >= 1) {
                tempInt1 = rand.nextInt(background[319][0]);
            }else{
                tempInt1 = 0;
            }
            scenery.add(new Chest(1376,960-tempInt1,128,128));
            scenery.sort(comparator);
        }
        if(rand.nextInt(360)==4){
            int tempInt1;
            if(background[319][0] >= 1) {
                tempInt1 = rand.nextInt(background[319][0]);
            }else{
                tempInt1 = 0;
            }
            scenery.add(new Clam(1376,960-tempInt1,96,96));
            scenery.sort(comparator);
        }



        for(Scenery s : scenery){
            s.Draw();
            if(s.getX() < -128) removeScenery.add(s);
        }
        for(Scenery s : removeScenery){
            scenery.remove(s);
        }removeScenery.clear();
    }
    private Comparator<Scenery> comparator= new Comparator<Scenery>(){
        public int compare(Scenery sc1, Scenery sc2){
            if(sc1.getY() > sc2.getY()) return 1;
            if(sc1.getY() < sc2.getY()) return -1;
            else return 0;
        }
    };
}
