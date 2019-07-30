package ui;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.TrueTypeFont;

import java.awt.*;

import static entities.EntityManager.getTexture;
import static utilities.Artist.DrawQuadTex;
import static utilities.Artist.HEIGHT;

public class Button {

    private String text;
    private float width;
    private float height;
    private float x;
    private float y;
    private Font awtFont;
    private TrueTypeFont font;

    public Button(String text, float width, float height, float x, float y){
        this.text = text;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;

        awtFont = new Font("times new roman", Font.BOLD, 24);
        font = new TrueTypeFont(awtFont, true);
    }

    public void draw(){
        font.drawString(x, y, text);
    }

    public boolean hovering(){
        if(Mouse.getX() > x && Mouse.getX() < x + width && HEIGHT-Mouse.getY() > y && HEIGHT-Mouse.getY() < y+height){
            DrawQuadTex(getTexture("fill_teal"), x, y, width, height);
            return true;
        }else return false;
    }
}
