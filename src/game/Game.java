package game;

import entities.*;
import org.lwjgl.Sys;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import particles.Particle;
import scene.Background;

import java.util.HashSet;
import java.util.Random;

import static utilities.Artist.*;

public class Game {
    public static EntityManager entityManager;

    public static HashSet<Enemy> enemySet = new HashSet<>();
    public static HashSet<Enemy2> bossSet = new HashSet<>();
    public static HashSet<Cuddle> cuddleSet = new HashSet<>();
    public static HashSet<BigJelly> jellySet = new HashSet<>();
    public static HashSet<Bullet> bulletSet = new HashSet<>();
    public static HashSet<Particle> mainParticles = new HashSet<>();

    private int score;


    private int lives = 10;

    private int spawnChance1;

    private boolean gameOver;

    private double shootingTimer;

    private double oxygenTimer;

    private Music music;
    private Sound shootSound;
    private Sound ambience;
    private Sound splash;
    private Sound pop;
    private Sound bubbles;
    private Sound electric;

    private float musicSpeed;

    private Random rand;

    HashSet<Enemy> remove;
    HashSet<Bullet> removeB;
    HashSet<Enemy2> removeBoss;
    HashSet<Particle> removeP;
    HashSet<Cuddle> removeC;
    HashSet<BigJelly> removeJ;

    Player player;
    Turret turret;

    Background background;


    public Game(){
        entityManager = new EntityManager();

        background = new Background();

        music = null;
        shootSound = null;
        ambience = null;
        splash = null;
        pop = null;
        bubbles = null;
        electric = null;

        try {
            music = new Music("res/audio/music2.wav");
            shootSound = new Sound("res/audio/shotA.wav");
            ambience = new Sound("res/audio/underwater_ambience_flowing.wav");
            splash = new Sound("res/audio/splash.wav");
            pop = new Sound("res/audio/pop.wav");
            bubbles = new Sound("res/audio/bubbles_blown.wav");
            electric = new Sound("res/audio/zap.wav");
        } catch (SlickException e) {
            e.printStackTrace();
        }

        musicSpeed = 1.0f;


        player = new Player();
        turret = new Turret();

        rand = new Random();

        score = 0;

        lives = 10;

        spawnChance1 = 500;

        gameOver = false;

        shootingTimer = Sys.getTime() * 1000 / Sys.getTimerResolution();

        oxygenTimer = Sys.getTime() * 1000 / Sys.getTimerResolution();

        remove = new HashSet<>();
        removeB = new HashSet<>();
        removeBoss = new HashSet<>();
        removeP = new HashSet<>();
        removeC = new HashSet<>();
        removeJ = new HashSet<>();

    }

    public void play(){

        background.draw();

        //ambient music playing
        if(!ambience.playing()) {
            ambience.play(1, 1);
        }
        if(!music.playing()){
            music.play(musicSpeed,1);
            musicSpeed = musicSpeed + 0.05f;
        }
        ///////////////////////
        if((Sys.getTime() * 1000 / Sys.getTimerResolution()) - oxygenTimer >= 1000){
            oxygenTimer = Sys.getTime() * 1000 / Sys.getTimerResolution();
            if(!gameOver) score = score + 1;
            if(spawnChance1 > 10){
                spawnChance1 = spawnChance1 - 1;
            }
        }

        for(Particle p : mainParticles){
            p.Update();
            p.Draw();
            if(p.isRemove())removeP.add(p);
        }for(Particle p : removeP){
            mainParticles.remove(p);
        }

        if (rand.nextInt(spawnChance1) == 1 && !gameOver) {enemySet.add(new Enemy(player));
        }
        if (rand.nextInt(spawnChance1*7) == 4 && !gameOver) {
            bossSet.add(new Enemy2(player));
        }
        if (rand.nextInt(spawnChance1*2) == 2 && !gameOver) {
            cuddleSet.add(new Cuddle(player));
        }
        if (rand.nextInt(spawnChance1*3) == 3 && !gameOver) {
            jellySet.add(new BigJelly(player));
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
        for (Bullet b : bulletSet) {
            b.Draw();
            b.setPos();
            if (b.isRemove()) removeB.add(b);
        }

        for (Enemy e : enemySet) {
            e.Draw();
            e.setPos();
            for (Bullet b : bulletSet) {
                if(e.checkColliding(b)){
                    splash.play(1.5f,0.2f);
                }
            }
            if (e.isRemove() || gameOver) remove.add(e);
            if (e.checkColliding(player)) {
                if (lives > 0) {
                    lives--;
                }
            }

        }
        for (Enemy2 e : bossSet) {
            if(e.getFrame() == 72){
                bubbles.play(0.8f,1);
            }
            e.Draw();
            e.setPos();
            for (Bullet b : bulletSet) {
                if(e.checkColliding(b)){
                    splash.play(1.5f,0.2f);
                }
            }
            if (e.isRemove() || gameOver) removeBoss.add(e);
            if (e.checkColliding(player)) {
                if (lives > 0) {
                    lives--;
                }
            }
        }
        for (Cuddle e : cuddleSet) {
            if(e.getFrame() == 13){
                bubbles.play(1.5f,1);
            }
            e.Draw();
            e.setPos();
            for (Bullet b : bulletSet) {
                if(e.checkColliding(b)){
                    splash.play(1.5f,0.2f);
                }
            }
            if (e.isRemove() || gameOver) removeC.add(e);
            if (e.checkColliding(player)) {
                if (lives > 0) {
                    lives--;
                }
            }
        }
        for (BigJelly e : jellySet) {
            e.Draw();
            e.setPos();
            for (Bullet b : bulletSet) {
                if(e.checkColliding(b)){
                    splash.play(1.5f,0.2f);
                }
            }
            if (e.isRemove() || gameOver) {

                Vector2f tempPos = e.getPos();
                enemySet.add(new Enemy(player, tempPos.x, tempPos.y));
                enemySet.add(new Enemy(player, tempPos.x-40, tempPos.y));
                enemySet.add(new Enemy(player, tempPos.x+40, tempPos.y));
                enemySet.add(new Enemy(player, tempPos.x, tempPos.y+40));
                enemySet.add(new Enemy(player, tempPos.x, tempPos.y-40));
                enemySet.add(new Enemy(player, tempPos.x-32, tempPos.y-32));
                enemySet.add(new Enemy(player, tempPos.x+32, tempPos.y+32));
                enemySet.add(new Enemy(player, tempPos.x-40, tempPos.y+32));
                enemySet.add(new Enemy(player, tempPos.x+40, tempPos.y-32));

                removeJ.add(e);
            }
            if (e.checkColliding(player)) {
                if (lives > 0) {
                    lives--;
                }
            }
            if(e.getFrame() == 10 || e.getFrame() == 40){
                electric.play(0.5f,0.7f);
            }
        }
        if (lives == 0) gameOver = true;
        for (Enemy e : remove) {
            enemySet.remove(e);
            if (!gameOver) score = score + 1;
            pop.play(1.5f,1);
        }remove.clear();
        for (Bullet b : removeB) {
            bulletSet.remove(b);
        }removeB.clear();
        for (Enemy2 e : removeBoss) {
            bossSet.remove(e);
            if(!gameOver)score = score + 5;
            pop.play(0.5f,1);
        }removeBoss.clear();
        for (Cuddle e : removeC) {
            cuddleSet.remove(e);
            if(!gameOver)score = score + 2;
            pop.play(1,1);
        }removeC.clear();
        for (BigJelly e : removeJ) {

            jellySet.remove(e);

            if(!gameOver)score = score + 5;
            pop.play(0.5f,1);
        }removeJ.clear();
        if (!gameOver) {
            player.Draw();
            player.setPos();
            turret.Draw();
            turret.setPos();
        }

        if (gameOver){
            bossSet.clear();
            enemySet.clear();
            bulletSet.clear();
            jellySet.clear();
            cuddleSet.clear();
            music.stop();
        }

    }

    public int getScore() {
        return score;
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public int getLives() {
        return lives;
    }
}
