package entities;

import org.newdawn.slick.opengl.Texture;

import java.util.HashMap;

import static utilities.Artist.LoadTexture;

public class EntityManager {

    private static HashMap<String,Texture> textureBank;

    public EntityManager(){
        textureBank = new HashMap<>();

        load("enemy1");
        load("enemy2");
        load("enemy3");
        load("enemy4");
        load("enemy5");
        load("enemy6");
        load("enemy7");
        load("enemy8");
        load("enemy9");
        load("enemy10");
        load("enemy11");
        load("enemy12");
        load("enemy13");

        load("squid1");
        load("squid2");

        load("bullet");

        load("square");

        load("player9");

        load("particle");

        load("particle2");

        load("spark");

        load("tentacle");



        System.out.println("here");


    }

    public void load(String name) {
        System.out.println("loaded");
        textureBank.put(name, LoadTexture(name));
        System.out.println(textureBank.get(name));
    }

    public static Texture getTexture(String key){
        Texture temp = textureBank.get(key);
        return temp;
    }
}
