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

import static utilities.Artist.*;
import static org.lwjgl.opengl.GL11.*;


public class SpaceEngine {

    public static EntityManager entityManager;

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


        HashSet<Enemy> enemySet = new HashSet<>();
        HashSet<Enemy2> bossSet = new HashSet<>();
        HashSet<Bullet> bulletSet = new HashSet<>();
        HashSet<Particle> particles = new HashSet<>();
        Player player = new Player();
        Turret turret = new Turret();

        Gravity gravity = new Gravity(1f);

        Random rand = new Random();

        Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
        TrueTypeFont font = new TrueTypeFont(awtFont, true);
        int score = 0;

        int shootTimer = 10;

        int lives = 10;

        boolean gameOver = false;

        double shootingTimer = Sys.getTime() * 1000 / Sys.getTimerResolution();

        while (!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            HashSet<Enemy> remove = new HashSet<>();
            HashSet<Bullet> removeB = new HashSet<>();
            HashSet<Particle> removeParticles = new HashSet<>();
            HashSet<Enemy2> removeBoss = new HashSet<>();
            int tentacleAlternator = 1;


            if (rand.nextInt(75) == 1 && !gameOver) {
                enemySet.add(new Enemy(player));
            }if (rand.nextInt(1000) == 1 && !gameOver) {
                bossSet.add(new Enemy2(player));
            }
            if (Mouse.isButtonDown(0)&& !gameOver) {
                if ((Sys.getTime() * 1000 / Sys.getTimerResolution())-shootingTimer >= 100) {
                   // bulletSounds.play(bulletSound);
                    Vector2f tempVec = player.getPos();
                    bulletSet.add(new Bullet(tempVec));
                    shootingTimer = Sys.getTime() * 1000 / Sys.getTimerResolution();
                    shootSound.play(1,0.2f);
                }
            }
            for (Particle p : particles){
                p.Draw();
                p.Update();
                if(p.isRemove())removeParticles.add(p);
            }
            for (Bullet b : bulletSet) {
                b.Draw();
                b.setPos();
                if (b.isRemove()) removeB.add(b);
                if(rand.nextInt(4)== 0)particles.add(new Particle(b.getPos().x,b.getPos().y,0.5f, 0.5f ,0.25, "bullet"));
                if(rand.nextInt(4)== 1) particles.add(new Particle(b.getPos().x,b.getPos().y,-0.5f, 0.5f ,0.25, "bullet"));
                if(rand.nextInt(4)== 2)particles.add(new Particle(b.getPos().x,b.getPos().y,0.5f, -0.5f ,0.25, "bullet"));
                if(rand.nextInt(4)== 3)particles.add(new Particle(b.getPos().x,b.getPos().y,-0.5f, -0.5f ,0.25, "bullet"));
            }
            for(Particle p : removeParticles){
                particles.remove(p);
            }removeParticles.clear();

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
                }if(!e.isRemove()) {
                    particles.add(new Particle(e.getPos().x, e.getPos().y + 16, 0, 0, 0.5, "particle2"));
                    particles.add(new Particle(e.getPos().x - 16, e.getPos().y - 16, 0, 0, 0.5, "particle2"));
                    particles.add(new Particle(e.getPos().x + 16, e.getPos().y - 16, 0, 0, 0.5, "particle2"));
                }

            }
            for (Enemy2 e : bossSet) {
                e.Draw();
                e.setPos();
                /*if(rand.nextInt(75) == 23){
                    Vector2f tempPos = new Vector2f();
                    tempPos.x = e.getPos().x;
                    tempPos.y = e.getPos().y;
                    enemySet.add(new Enemy(player, tempPos));
                }*/
                for (Bullet b : bulletSet) {
                    e.checkColliding(b);
                }
                if (e.isRemove() || gameOver) removeBoss.add(e);
                if (e.checkColliding(player)) {
                    if (lives > 0) {
                        lives--;
                    }
                }
                particles.add(new Particle(e.getPos().x, e.getPos().y + 32, 0, 0, 3, 8, 8, "tentacle"));
                particles.add(new Particle(e.getPos().x, e.getPos().y - 32, 0, 0, 3, 8, 8, "tentacle"));
                particles.add(new Particle(e.getPos().x + 32, e.getPos().y - 16, 0, 0, 3, 8, 8, "tentacle"));
                particles.add(new Particle(e.getPos().x - 32, e.getPos().y - 16, 0, 0, 3, 8, 8, "tentacle"));
                particles.add(new Particle(e.getPos().x - 32, e.getPos().y+16, 0, 0, 3, 8, 8, "tentacle"));
                particles.add(new Particle(e.getPos().x + 32, e.getPos().y+16, 0, 0, 3, 8, 8, "tentacle"));

            }
            if (lives == 0) gameOver = true;
            for (Enemy e : remove) {
                enemySet.remove(e);
                if(rand.nextInt(2)== 0){
                    particles.add(new Particle(e.getPos().x,e.getPos().y,1, 1 ,1, "particle"));
                    particles.add(new Particle(e.getPos().x,e.getPos().y,-1, 1 ,1, "particle"));
                }else {
                    particles.add(new Particle(e.getPos().x, e.getPos().y, 1, -1, 1, "particle2"));
                    particles.add(new Particle(e.getPos().x, e.getPos().y, -1, -1, 1, "particle2"));
                }
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
            String livesString = "Lives: " + lives/2;
            font.drawString(WIDTH - 200, 0, scoreString);
            font.drawString(200, 0, livesString);
            if (gameOver){

                bossSet.clear();
                enemySet.clear();
                particles.clear();
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
