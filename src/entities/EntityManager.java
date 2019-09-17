package entities;

import org.newdawn.slick.opengl.Texture;

import java.util.HashMap;

import static utilities.Artist.LoadTexture;

public class EntityManager {

    private static HashMap<String,Texture> textureBank;

    public EntityManager(){
        textureBank = new HashMap<>();

        load("bullet");

        load("square");

        load("tentacle");

        load("background");

        load("chest1");
        load("chest2");

        load("bubble");
        load("bubble_1");

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

        load("big_jelly_1");
        load("big_jelly_2");
        load("big_jelly_3");
        load("big_jelly_4");
        load("big_jelly_5");
        load("big_jelly_6");
        load("big_jelly_7");
        load("big_jelly_8");
        load("big_jelly_9");
        load("big_jelly_10");
        load("big_jelly_11");
        load("big_jelly_12");

        load("water");

        load("sand_1");
        load("sand_2");

        load("hill_1");
        load("hill_2");

        load("outline_yellow");
        load("outline_green");
        load("outline_red");
        load("outline_blue");
        load("outline_teal");

        load("fill_yellow");
        load("fill_green");
        load("fill_red");
        load("fill_blue");
        load("fill_teal");

        load("person");

        load("person_riding");
        load("person_riding1");

        load("black");
        load("blue1");
        load("blue2");
        load("blue3");
        load("blue4");
        load("brown");
        load("green1");
        load("green2");
        load("grey1");
        load("grey2");
        load("orange1");
        load("orange2");
        load("pink");
        load("purple1");
        load("purple2");
        load("red1");
        load("red2");
        load("yellow1");
        load("yellow2");
        load("white");

        load("background_tile2");
        load("background_tile3");



    }

    public void load(String name) {
        textureBank.put(name, LoadTexture(name));
        System.out.println(name + " loaded");
    }

    public static Texture getTexture(String key){
        Texture temp = textureBank.get(key);
        return temp;
    }
}
