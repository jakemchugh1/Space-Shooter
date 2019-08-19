package ui;

import game.Game;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.TrueTypeFont;

import java.awt.*;

import static engine.SpaceEngine.exitGame;
import static utilities.Artist.HEIGHT;
import static utilities.Artist.WIDTH;

public class Ui {

    private Font awtFont;
    private TrueTypeFont font;
    private boolean resetGame;
    private int menuSelect;


    //main menu buttons
    Button newGame;
    Button mod;
    Button exit;
    Button credits;

    //game over menu buttons
    Button tryAgain;
    Button returnToMain;


    public Ui(){
        awtFont = new Font("times new roman", Font.BOLD, 24);
        font = new TrueTypeFont(awtFont, true);
        resetGame = false;
        menuSelect = 0;
        //main menu buttons
        newGame = new Button("New Game", 256, 32, 100, 600);
        mod = new Button("Mods", 256, 32, 100, 632);
        credits = new Button("Credits", 256, 32, 100, 664);
        exit = new Button("Exit Game", 256, 32, 100, 698);

        //credits buttons
        returnToMain = new Button("Return to Main Menu",256,32,480, 550);

        //game over menu buttons
        tryAgain = new Button("Try Again",256,32,480, 500);
        returnToMain = new Button("Return to Main Menu",256,32,100, 550);

    }
    public void run(Game game){
        if(menuSelect == 0){
            mainMenu();
        }else if(menuSelect == 1){
            inGame(game);
        }else if(menuSelect == 2){
            credits();
        }else if(menuSelect == 3){
            mods();
        }
    }

    public void mods(){
        returnToMain.draw();
        if(returnToMain.hovering() && Mouse.isButtonDown(0)){
            menuSelect = 0;
            System.out.println("Returned to main menu");
        }
    }

    public void mainMenu(){
        newGame.draw();
        mod.draw();
        credits.draw();
        exit.draw();

        if(newGame.hovering() && Mouse.isButtonDown(0)){
            menuSelect = 1;
            System.out.println("New game selected");
            newGame.reset();
        }if(exit.hovering() && Mouse.isButtonDown(0)){
            System.out.println("Exit game selected");
            exitGame = true;
            exit.reset();
        }if(credits.hovering() && Mouse.isButtonDown(0)){
            System.out.println("Credits selected");
            menuSelect = 2;
            credits.reset();
        }if(mod.hovering() && Mouse.isButtonDown(0)){
            System.out.println("Credits selected");
            menuSelect = 3;
            mod.reset();
        }

    }

    public void credits(){
        returnToMain.draw();
        if(returnToMain.hovering() && Mouse.isButtonDown(0)){
            menuSelect = 0;
            System.out.println("Returned to main menu");
        }

        //Credits
        font.drawString(400, 680, "Artwork by:");
        font.drawString(400, 700, "Christopher Rogers");
        font.drawString(400, 740, "Programmed by:");
        font.drawString(400, 760, "Jacob McHugh");
        font.drawString(400, 800, "Game Music by:");
        font.drawString(400, 820, "Jacob McHugh");
        font.drawString(400, 860, "Game sound effects from:");
        font.drawString(400, 880, "zapsplat.com");

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

            if(tryAgain.hovering() && Mouse.isButtonDown(0)){
                resetGame = true;
                tryAgain.reset();
            }
            else if(returnToMain.hovering() && Mouse.isButtonDown(0)){
                menuSelect = 0;
                game.ambience.stop();
                game.resetGame();
                returnToMain.reset();
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
