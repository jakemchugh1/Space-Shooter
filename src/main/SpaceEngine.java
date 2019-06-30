package main;

import audio.AudioManager;
import audio.Source;
import entities.*;
import org.lwjgl.Sys;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Music;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.OpenALStreamPlayer;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.openal.StreamSound;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import particles.Particle;
import scene.Background;
import scene.Scene;
import scenery.ShortSeaweed;
import utilities.Artist.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import physics.Gravity;

import javax.sound.midi.spi.SoundbankReader;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Random;

import static entities.EntityManager.getTexture;
import static utilities.Artist.*;
import static org.lwjgl.opengl.GL11.*;


public class SpaceEngine {

    public static EntityManager entityManager;

    public static HashSet<Enemy> enemySet = new HashSet<>();
    public static HashSet<Enemy2> bossSet = new HashSet<>();
    public static HashSet<Bullet> bulletSet = new HashSet<>();
    public static HashSet<Particle> mainParticles = new HashSet<>();

    public SpaceEngine()  {
        BeginSession();

        entityManager = new EntityManager();




        Music music = null;
        Sound shootSound = null;
        try {
            music = new Music("res/audio/music1.wav");
            shootSound = new Sound("res/audio/shotA.wav");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        music.loop();



        Player player = new Player();
        Turret turret = new Turret();
        Background background = new Background(WIDTH, HEIGHT, "background");

        Random rand = new Random();

        Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
        TrueTypeFont font = new TrueTypeFont(awtFont, true);
        int score = 0;

        int oxygen = 100;

        int lives = 10;

        int spawnChance1 = 300;

        boolean gameOver = false;

        double shootingTimer = Sys.getTime() * 1000 / Sys.getTimerResolution();

        double oxygenTimer = Sys.getTime() * 1000 / Sys.getTimerResolution();

        float testRotation = 0;

        bossSet.add(new Enemy2(player));

        while (!Display.isCloseRequested()) {
            if((Sys.getTime() * 1000 / Sys.getTimerResolution()) - oxygenTimer >= 1000){
                oxygenTimer = Sys.getTime() * 1000 / Sys.getTimerResolution();
                oxygen = oxygen - 1;
                if(spawnChance1 > 10){
                    spawnChance1 = spawnChance1 - 1;
                }
            }
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            HashSet<Enemy> remove = new HashSet<>();
            HashSet<Bullet> removeB = new HashSet<>();
            HashSet<Enemy2> removeBoss = new HashSet<>();
            HashSet<Particle> removeP = new HashSet<>();
            background.Draw();

            for(Particle p : mainParticles){
                p.Update();
                p.Draw();
                if(p.isRemove())removeP.add(p);
            }for(Particle p : removeP){
                mainParticles.remove(p);
            }

            if (rand.nextInt(spawnChance1) == 1 && !gameOver) {
                enemySet.add(new Enemy(player));
            }//if (rand.nextInt(1000) == 1 && !gameOver) {
                //bossSet.add(new Enemy2(player));
           // }
            if (Mouse.isButtonDown(0)&& !gameOver) {
                if ((Sys.getTime() * 1000 / Sys.getTimerResolution())-shootingTimer >= 100) {
                   // bulletSounds.play(bulletSound);
                    Vector2f tempVec = player.getPos();
                    bulletSet.add(new Bullet(tempVec));
                    shootingTimer = Sys.getTime() * 1000 / Sys.getTimerResolution();
                    shootSound.play(1,0.2f);
                }
            }
            for (Bullet b : bulletSet) {
                b.Draw();
                b.setPos();
                if (b.isRemove()) removeB.add(b);
            }


            for (Enemy e : enemySet) {
                e.Draw();
                e.setPos();
                for (Bullet b : bulletSet) {
                    e.checkColliding(b);
                }
                if (e.isRemove() || gameOver) remove.add(e);
                if (e.checkColliding(player)) {
                    if (lives > 0) {
                        lives--;
                    }
                }

            }
            for (Enemy2 e : bossSet) {
                e.Draw();
                e.setPos();
                for (Bullet b : bulletSet) {
                    e.checkColliding(b);
                }
                if (e.isRemove() || gameOver) removeBoss.add(e);
                if (e.checkColliding(player)) {
                    if (lives > 0) {
                        lives--;
                    }
                }


            }
            if (lives == 0) gameOver = true;
            for (Enemy e : remove) {
                enemySet.remove(e);
                if (!gameOver) score = score + 1;
            }remove.clear();
            for (Bullet b : removeB) {
                bulletSet.remove(b);
            }removeB.clear();
            for (Enemy2 e : removeBoss) {
                bossSet.remove(e);
                if(!gameOver)score = score + 5;
            }removeBoss.clear();
            if (!gameOver) {
                player.Draw();
                player.setPos();
                turret.Draw();
                turret.setPos();
            }

            String scoreString = "Score: " + score;
            String livesString = "Crew: " + lives/2;
            String oxygenString = "Oxygen: " + oxygen;
            font.drawString(WIDTH - 200, 0, scoreString);
            font.drawString(200, 0, livesString);
            font.drawString(200, 50, oxygenString);
            if (gameOver){
                bossSet.clear();
                enemySet.clear();
                bulletSet.clear();
                music.stop();
                font.drawString(WIDTH / 2 - 96, HEIGHT / 2, "GAME OVER");
                font.drawString(WIDTH / 2 - 78, HEIGHT / 2+100, "Try Again?");
                if(Mouse.getX() > WIDTH / 2 - 78 && Mouse.getX() < WIDTH / 2 + 10 && HEIGHT-Mouse.getY() > HEIGHT / 2+100 && HEIGHT-Mouse.getY() < HEIGHT / 2+140){
                    if(Mouse.isButtonDown(0)){
                        score = 0;
                        lives = 10;
                        player.reset();
                        gameOver = false;
                        music.loop();
                    }
                }
            }
            updateDisplay();


            }
        AudioManager.cleanUp();


    }

    public static void main(String[] Args){

        SpaceEngine game = new SpaceEngine();
    }
}
