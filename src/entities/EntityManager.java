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

        load("background");

        load("seaweed_short1");
        load("seaweed_short2");
        load("seaweed_short3");

        load("starfish1");
        load("starfish2");
        load("starfish3");

        load("chest1");
        load("chest2");

        load("bubble");

        load("submarine1");
        load("submarine2");
        load("submarine3");
        load("submarine4");
        load("submarine5");

        load("clam1");
        load("clam2");

        load("red_star_1");
        load("red_star_2");
        load("red_star_3");

        load("seaweed_1");
        load("seaweed_2");
        load("seaweed_3");

        load("jellyfish_1");
        load("jellyfish_2");
        load("jellyfish_3");

        load("squid_1");
        load("squid_2");
        load("squid_3");
        load("squid_4");
        load("squid_5");

        load("reticle_1");
        load("reticle_2");
        load("reticle_3");

        load("sub_1");
        load("sub_2");
        load("sub_3");

        load("ink_squid_1");
        load("ink_squid_2");
        load("ink_squid_3");
        load("ink_squid_4");
        load("ink_squid_5");
        load("ink_squid_6");
        load("ink_squid_7");
        load("ink_squid_8");


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
