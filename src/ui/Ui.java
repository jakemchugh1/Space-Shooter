package ui;

import game.Game;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.TrueTypeFont;

import java.awt.*;

import static utilities.Artist.HEIGHT;
import static utilities.Artist.WIDTH;

public class Ui {

    private Font awtFont;
    private TrueTypeFont font;
    private boolean resetGame;

    public Ui(){
        awtFont = new Font("times new roman", Font.BOLD, 24);
        font = new TrueTypeFont(awtFont, true);
        resetGame = false;
    }
    public void Draw(Game game){
        font.drawString(WIDTH - 200, 0, "Score: " + game.getScore());
        font.drawString(200, 0, "Crew:" + game.getLives()/2);

        //Drawstring test build
        font.drawString(0, 100, "TEST BUILD");
        font.drawString(0, 300, "TEST BUILD");
        font.drawString(0, 500, "TEST BUILD");
        font.drawString(0, 700, "TEST BUILD");
        font.drawString(0, 900, "TEST BUILD");

        if(game.getGameOver()){
            font.drawString(WIDTH / 2 - 96, HEIGHT / 2, "GAME OVER");
            font.drawString(WIDTH / 2 - 78, HEIGHT / 2+100, "Try Again?");

            //Credits
            font.drawString(900, 680, "Artwork by:");
            font.drawString(900, 700, "Christopher Rogers");
            font.drawString(900, 740, "Programmed by:");
            font.drawString(900, 760, "Jacob McHugh");
            font.drawString(900, 800, "Game Music by:");
            font.drawString(900, 820, "Jacob McHugh");
            font.drawString(900, 860, "Game sound effects from:");
            font.drawString(900, 880, "zapsplat.com");

            if(Mouse.getX() > WIDTH / 2 - 78 && Mouse.getX() < WIDTH / 2 + 10 && HEIGHT-Mouse.getY() > HEIGHT / 2+100 && HEIGHT-Mouse.getY() < HEIGHT / 2+140){
                if(Mouse.isButtonDown(0)){
                    resetGame = true;
                }
            }


        }
    }
    public void Draw(){

    }
    public boolean getReset(){
        return resetGame;
    }
    public void newGame(){
        resetGame = false;
    }
}
