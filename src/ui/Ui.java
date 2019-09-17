package ui;

import game.CustomGameMaker;
import game.Game;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.TrueTypeFont;
import particles.Particle;
import scene.Background;
import scene.MenuBackground;

import java.awt.*;

import static engine.SpaceEngine.exitGame;
import static entities.EntityManager.getTexture;
import static game.Game.mainParticles;
import static utilities.Artist.*;
import static utilities.Artist.DrawQuadTexRot;

public class Ui {

    private Font awtFont;
    private TrueTypeFont font;
    private boolean resetGame;
    private int menuSelect;


    //main menu buttons
    private Button newGame;
    private Button mod;
    private Button exit;
    private Button credits;

    //game over menu buttons
    private Button tryAgain;
    private Button returnToMain;

    //custom game maker buttons
    private int modMenuSelect;

    private Button customPlayer;
    private Button customEnemy;
    private Button backGround;
    private Button sounds;
    private Button test;

    private Button increaseSpeed;
    private Button decreaseSpeed;

    private Button increaseHealth;
    private Button decreaseHealth;

    private Button increaseSize;
    private Button decreaseSize;

    private Button increaseResolution;
    private Button decreaseResolution;

    private CustomGameMaker gameMaker;

    private boolean buttonHold;
    private Background creditsBackground;
    private MenuBackground menuBackground;
    private float textRotator;

    private int frame;


    public Ui(){
        awtFont = new Font("times new roman", Font.BOLD, 24);
        font = new TrueTypeFont(awtFont, true);
        resetGame = false;
        menuSelect = 0;
        creditsBackground = new Background();
        menuBackground = new MenuBackground();
        textRotator = 0;
        frame = 0;
        //main menu buttons
        newGame = new Button("New Game", 256, 32, 100, 600);
        mod = new Button("Mods", 256, 32, 100, 632);
        credits = new Button("Credits", 256, 32, 100, 664);
        exit = new Button("Exit Game", 256, 32, 100, 698);


        //credits buttons

        //game over menu buttons
        tryAgain = new Button("Try Again",256,32,480, 500);
        returnToMain = new Button("Return to Main Menu",256,32,100, 910);

        //custom game menu buttons
        customPlayer = new Button("Player", 256, 32, 1000, 100);
        customEnemy = new Button("Enemy", 256, 32, 1000, 150);
        modMenuSelect = 0;

        backGround = new Button("BackGround", 256, 32, 1000, 200);
        sounds = new Button("Sounds", 256, 32, 1000, 250);
        test = new Button("Test", 256, 32, 1000, 300);

        increaseSpeed = new Button("Increase Speed", 256, 32, 50, 100);
        decreaseSpeed = new Button("Decrease Speed", 256, 32, 50, 150);
        increaseHealth = new Button("Increase Health", 256, 32, 50, 200);
        decreaseHealth= new Button("Decrease Health", 256, 32, 50, 250);
        increaseSize = new Button("Increase Size", 256, 32, 50, 300);
        decreaseSize = new Button("Decrease Size", 256, 32, 50, 350);
        increaseResolution = new Button("Increase Resolution", 256, 32, 50, 400);
        decreaseResolution = new Button("Decrease Resolution", 256, 32, 50, 450);

        buttonHold = false;

    }
    public void run(Game game){

        if(menuSelect == 0){
            menuBackground.draw();
            mainMenu();
        }else if(menuSelect == 1){
            inGame(game);
        }else if(menuSelect == 2){
            creditsBackground.draw();
            game.particles();
            credits();
        }else if(menuSelect == 3){
            menuBackground.draw();
            mods();
        }
    }

    public void mods(){
        returnToMain.draw();
        customEnemy.draw();
        customPlayer.draw();
        backGround.draw();
        sounds.draw();
        test.draw();
        if(returnToMain.hovering() && Mouse.isButtonDown(0)){
            menuSelect = 0;
            modMenuSelect = 0;
            System.out.println("Returned to main menu");
            menuBackground.setBackgroundType(0);
        }else if(customPlayer.hovering() && Mouse.isButtonDown(0)){
            modMenuSelect = 1;
            gameMaker.player.setInGame(false);
            gameMaker.enemy.setInGame(false);

        }else if(customEnemy.hovering() && Mouse.isButtonDown(0)){
            modMenuSelect = 2;
            gameMaker.player.setInGame(false);
            gameMaker.enemy.setInGame(false);

        }else if(backGround.hovering() && Mouse.isButtonDown(0)){
            modMenuSelect = 3;
            gameMaker.player.setInGame(false);
            gameMaker.enemy.setInGame(false);

        }else if(sounds.hovering() && Mouse.isButtonDown(0)){
            modMenuSelect = 4;
            gameMaker.player.setInGame(false);
            gameMaker.enemy.setInGame(false);

        }else if(test.hovering() && Mouse.isButtonDown(0)){
            modMenuSelect = 4;
            gameMaker.player.setInGame(true);
            gameMaker.enemy.setInGame(true);

        }
        if(modMenuSelect == 1){
            gameMaker.runPlayer();

            increaseSpeed.draw();
            decreaseSpeed.draw();
            increaseHealth.draw();
            decreaseHealth.draw();
            increaseSize.draw();
            decreaseSize.draw();
            increaseResolution.draw();
            decreaseResolution.draw();

            if(increaseSpeed.hovering() && Mouse.isButtonDown(0) && !buttonHold){
                buttonHold = true;
                gameMaker.player.increaseSpeed();
            }else if(decreaseSpeed.hovering() && Mouse.isButtonDown(0) && !buttonHold){
                buttonHold = true;
                gameMaker.player.decreaseSpeed();
            }else if(increaseHealth.hovering() && Mouse.isButtonDown(0) && !buttonHold){
                buttonHold = true;
                gameMaker.player.increaseHealth();
            }else if(decreaseHealth.hovering() && Mouse.isButtonDown(0) && !buttonHold){
                buttonHold = true;
                gameMaker.player.decreaseHealth();
            }else if(increaseSize.hovering() && Mouse.isButtonDown(0) && !buttonHold){
                buttonHold = true;
                gameMaker.player.increaseSize();
            }else if(decreaseSize.hovering() && Mouse.isButtonDown(0) && !buttonHold){
                buttonHold = true;
                gameMaker.player.decreaseSize();
            }else if(decreaseResolution.hovering() && Mouse.isButtonDown(0) && !buttonHold){
                buttonHold = true;
                gameMaker.player.decreaseResolution();
            }else if(increaseResolution.hovering() && Mouse.isButtonDown(0) && !buttonHold){
                buttonHold = true;
                gameMaker.player.increaseResolution();
            }
            font.drawString(275, 100, "Speed: "+gameMaker.player.getSpeed());
            font.drawString(275, 200, "Health: "+gameMaker.player.getHealth());
        }else if(modMenuSelect == 2){
            gameMaker.runEnemy();

            increaseSpeed.draw();
            decreaseSpeed.draw();
            increaseHealth.draw();
            decreaseHealth.draw();
            increaseSize.draw();
            decreaseSize.draw();
            increaseResolution.draw();
            decreaseResolution.draw();
            increaseResolution.draw();
            decreaseResolution.draw();

            if(increaseSpeed.hovering() && Mouse.isButtonDown(0) && !buttonHold){
                buttonHold = true;
                gameMaker.enemy.increaseSpeed();
            }else if(decreaseSpeed.hovering() && Mouse.isButtonDown(0) && !buttonHold){
                buttonHold = true;
                gameMaker.enemy.decreaseSpeed();
            }else if(increaseHealth.hovering() && Mouse.isButtonDown(0) && !buttonHold){
                buttonHold = true;
                gameMaker.enemy.increaseHealth();
            }else if(decreaseHealth.hovering() && Mouse.isButtonDown(0) && !buttonHold){
                buttonHold = true;
                gameMaker.enemy.decreaseHealth();
            }else if(increaseSize.hovering() && Mouse.isButtonDown(0) && !buttonHold){
                buttonHold = true;
                gameMaker.enemy.increaseSize();
            }else if(decreaseSize.hovering() && Mouse.isButtonDown(0) && !buttonHold){
                buttonHold = true;
                gameMaker.enemy.decreaseSize();
            }else if(decreaseResolution.hovering() && Mouse.isButtonDown(0) && !buttonHold){
                buttonHold = true;
                gameMaker.enemy.decreaseResolution();
            }else if(increaseResolution.hovering() && Mouse.isButtonDown(0) && !buttonHold){
                buttonHold = true;
                gameMaker.enemy.increaseResolution();
            }
            font.drawString(275, 100, "Speed: "+gameMaker.enemy.getSpeed());
            font.drawString(275, 200, "Health: "+gameMaker.enemy.getHealth());
        }else if(modMenuSelect == 3){

        }else if(modMenuSelect == 4){
            gameMaker.runPlayer();
            gameMaker.runEnemy();
        }
        if(!Mouse.isButtonDown(0))buttonHold = false;
    }

    public void customPlayer(){

    }

    public void mainMenu(){
        newGame.draw();
        mod.draw();
        credits.draw();
        exit.draw();
        font.drawString(900, (float) (68 + 3*Math.sin(textRotator)), "P");
        font.drawString(916, (float) (68 + 3*Math.sin(textRotator+5)), "R");
        font.drawString(932, (float) (68 + 3*Math.sin(textRotator+10)), "O");
        font.drawString(948, (float) (68 + 3*Math.sin(textRotator+15)), "J");
        font.drawString(964, (float) (68 + 3*Math.sin(textRotator+20)), "E");
        font.drawString(980, (float) (68 + 3*Math.sin(textRotator+25)), "C");
        font.drawString(996, (float) (68 + 3*Math.sin(textRotator+30)), "T");
        //bananna
        font.drawString(900, (float) (100 + 3*Math.sin(textRotator)), "B");
        font.drawString(916, (float) (100 + 3*Math.sin(textRotator+5)), "A");
        font.drawString(932, (float) (100 + 3*Math.sin(textRotator+10)), "N");
        font.drawString(948, (float) (100 + 3*Math.sin(textRotator+15)), "A");
        font.drawString(964, (float) (100 + 3*Math.sin(textRotator+20)), "N");
        font.drawString(980, (float) (100 + 3*Math.sin(textRotator+25)), "N");
        font.drawString(996, (float) (100 + 3*Math.sin(textRotator+30)), "A");
        //boat
        font.drawString(1028, (float) (100 + 3*Math.sin(textRotator+35)), "B");
        font.drawString(1044, (float) (100 + 3*Math.sin(textRotator+40)), "O");
        font.drawString(1060, (float) (100 + 3*Math.sin(textRotator+45)), "A");
        font.drawString(1076, (float) (100 + 3*Math.sin(textRotator+50)), "T");

        textRotator = textRotator + 0.125f;
        if(textRotator == 360) textRotator = 0;



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
            System.out.println("Custom game selected");
            gameMaker = new CustomGameMaker();
            menuSelect = 3;
            mod.reset();
            menuBackground.setBackgroundType(1);
        }

    }

    public void credits(){
        returnToMain.draw();
        if(returnToMain.hovering() && Mouse.isButtonDown(0)){
            menuSelect = 0;
            mainParticles.clear();
            System.out.println("Returned to main menu");
            menuBackground.setBackgroundType(0);
        }

        //Credits
        font.drawString(360, (float) (240 + 10*Math.sin(textRotator)), "Artwork by:");
        font.drawString(370, (float) (260 + 10*Math.sin(textRotator)), "Christopher Rogers");
        font.drawString(400, (float) (340 + 10*Math.sin(textRotator+90)), "Programmed by:");
        font.drawString(390, (float) (360 + 10*Math.sin(textRotator+90)), "Jacob McHugh");
        font.drawString(520, (float) (140 + 10*Math.sin(textRotator+180)), "Game Music by:");
        font.drawString(530, (float) (160 + 10*Math.sin(textRotator+180)), "Jacob McHugh");
        font.drawString(800, (float) (740 + 10*Math.sin(textRotator+270)), "Game sound effects from:");
        font.drawString(820, (float) (760 + 10*Math.sin(textRotator+270)), "zapsplat.com");


        if(frame < 3) {
            DrawQuadTexRot(getTexture("sub_1"), 1000, (float) (432 + 15*Math.sin(textRotator)), 64, 32, 0);


            frame = frame + 1;
        }else if(frame < 6) {
            DrawQuadTexRot(getTexture("sub_2"), 1000, (float) (432 + 15*Math.sin(textRotator)), 64, 32, 0);

            frame = frame + 1;
        }else if(frame < 9) {
            DrawQuadTexRot(getTexture("sub_3"), 1000, (float) (432 + 15*Math.sin(textRotator)), 64, 32, 0);

            frame = frame + 1;
        }else{
            DrawQuadTexRot(getTexture("sub_1"), 1000, (float) (432 + 15*Math.sin(textRotator)), 64, 32, 0);

            frame = 0;
            mainParticles.add(new Particle(968, (float) (440 + 15*Math.sin(textRotator)), -4, 0, 4f , 12, 12, "bubble_1"));

        }

        textRotator = textRotator + 0.06f;
        if(textRotator >= 360) textRotator = 0;


    }


    public void inGame(Game game){
        game.play();
        font.drawString(WIDTH - 200, 0, "Score: " + game.getScore());
        font.drawString(200, 0, "Crew:" + game.getLives());

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
                menuBackground.setBackgroundType(0);
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
