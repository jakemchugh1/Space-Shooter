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
    private float offset;
    private Font awtFont;
    private TrueTypeFont font;

    public Button(String text, float width, float height, float x, float y){
        this.text = text;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        offset = 0;

        awtFont = new Font("times new roman", Font.BOLD, 24);
        font = new TrueTypeFont(awtFont, true);
    }

    public void draw(){
        DrawQuadTex(getTexture("fill_teal"), x, y, offset, height);
        font.drawString(x+offset, y, text);
    }

    public boolean hovering(){
        if(Mouse.getX() > x && Mouse.getX() < x + width && HEIGHT-Mouse.getY() > y && HEIGHT-Mouse.getY() < y+height){
            if(offset <= 20)offset = offset + 10;

            return true;
        }else{
            if(offset > 0)offset = offset - 5;
            if(offset < 0)offset = 0;
            return false;
        }
    }
}
