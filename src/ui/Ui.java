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
    private int menuSelect;


    //main menu buttons
    Button newGame;

    //game over menu buttons
    Button tryAgain;
    Button returnToMain;


    public Ui(){
        awtFont = new Font("times new roman", Font.BOLD, 24);
        font = new TrueTypeFont(awtFont, true);
        resetGame = false;
        menuSelect = 0;
        //main menu buttons
        newGame = new Button("New Game", 256, 32, 480, 500);

        //game over menu buttons
        tryAgain = new Button("Try Again",256,32,480, 500);
        returnToMain = new Button("Return to Main Menu",256,32,480, 550);

    }
    public void run(Game game){
        if(menuSelect == 0){
            mainMenu();
        }else if(menuSelect == 1){
            inGame(game);
        }
    }

    public void mainMenu(){
        newGame.draw();
        if(newGame.hovering() && Mouse.isButtonDown(0)){
            menuSelect = 1;
            System.out.println("New Game Selected");
        }

    }


    public void inGame(Game game){
        game.play();
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
            tryAgain.draw();
            returnToMain.draw();


            //Credits
            font.drawString(900, 680, "Artwork by:");
            font.drawString(900, 700, "Christopher Rogers");
            font.drawString(900, 740, "Programmed by:");
            font.drawString(900, 760, "Jacob McHugh");
            font.drawString(900, 800, "Game Music by:");
            font.drawString(900, 820, "Jacob McHugh");
            font.drawString(900, 860, "Game sound effects from:");
            font.drawString(900, 880, "zapsplat.com");

            if(tryAgain.hovering() && Mouse.isButtonDown(0)){
                resetGame = true;
            }
            else if(returnToMain.hovering() && Mouse.isButtonDown(0)){
                menuSelect = 0;
            }


        }
    }
    public boolean getReset(){
        return resetGame;
    }
    public void newGame(){
        resetGame = false;
    }
    public int getMenuSelect(){
        return menuSelect;
    }
}
