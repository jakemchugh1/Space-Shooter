package utilities;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.glMatrixMode;

public class Artist {


    //width and height of screen
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 960;

    //time based movement
    private static long lastFrameTime;
    private static float delta;
    private static int offset;

    public static void BeginSession(){
        offset = 0;

        Display.setTitle("Jake's Physics");
        try {
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.create();
        }catch(LWJGLException e){
            e.printStackTrace();
        }

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0,WIDTH,HEIGHT,0,1,-1);//camera
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        lastFrameTime = getCurrentTime();

    }

    public static void updateDisplay(){
        Display.update();
        Display.sync(60);
        long currentFrameTime = getCurrentTime();
        delta = (currentFrameTime - lastFrameTime)/1000f;
        lastFrameTime = currentFrameTime;

    }

    public static float getFrameTimeSeconds(){
        return delta;
    }

    public static void DrawQuad(float x, float y, float width, float height){

        glBegin(GL_QUADS);
        glVertex2f(x,y);
        glVertex2f(x + width,y);
        glVertex2f(x + width,y + height);
        glVertex2f(x,y + height);
        glEnd();


    }

    public static void DrawQuadTex(Texture tex, float x, float y, float width, float height){
        if(tex == null){
            tex = LoadTexture("null");
        }
        tex.bind();
        glTranslatef(x, y, 0);
        glBegin(GL_QUADS);
        glTexCoord2f(0,0);
        glVertex2f(0,0);
        glTexCoord2f(1,0);
        glVertex2f(width,0);
        glTexCoord2f(1,1);
        glVertex2f(width,height);
        glTexCoord2f(0,1);
        glVertex2f(0,height);
        glEnd();
        glLoadIdentity();

    }

    public static void DrawQuadTexRot(Texture tex, float x, float y, float width, float height, float rotation){
        if(tex == null){
            tex = LoadTexture("null");
        }
        tex.bind();
        glTranslatef(x, y, 0);
        GL11.glRotatef(rotation, 0.0f, 0.0f, -1.0f);
        GL11.glTranslatef(width/4, height/4, 0);
        glBegin(GL_QUADS);
        glTexCoord2f(0,0);
        glVertex2f(-width,-height);
        glTexCoord2f(1,0);
        glVertex2f(width,-height);
        glTexCoord2f(1,1);
        glVertex2f(width,height);
        glTexCoord2f(0,1);
        glVertex2f(-width,height);



        glEnd();
        glLoadIdentity();

    }

    public static void DrawQuadTexWave(Texture tex, float x, float y, float width, float height, float modifier){
        if(tex == null){
            tex = LoadTexture("null");
        }
        tex.bind();
        glTranslatef(x, y, 0);
        glBegin(GL_QUADS);
        glTexCoord2f(0-0.05f,0);
        glVertex2f(0 + modifier,0);
        glTexCoord2f(1-0.05f,0);
        glVertex2f(width + modifier,0);
        glTexCoord2f(1-0.05f,1);
        glVertex2f(width,height);
        glTexCoord2f(0-0.05f,1);
        glVertex2f(0,height);
        glEnd();
        glLoadIdentity();

    }

    public static void DrawQuadTexFlip(Texture tex, float x, float y, float width, float height){
        if(tex == null){
            tex = LoadTexture("null");
        }
        tex.bind();
        glTranslatef(x+ width, y, 0);
        glBegin(GL_QUADS);
        glTexCoord2f(0,0);
        glVertex2f(0,0);
        glTexCoord2f(1,0);
        glVertex2f(-width,0);
        glTexCoord2f(1,1);
        glVertex2f(-width,height);
        glTexCoord2f(0,1);
        glVertex2f(0,height);
        glEnd();
        glLoadIdentity();

    }

    public static Texture LoadTexture(String path, String fileType){
        Texture tex = null;
        InputStream in = ResourceLoader.getResourceAsStream(path);
        try {
            tex = TextureLoader.getTexture(fileType,in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tex;
    }

    public static Texture LoadTexture(String name){
        Texture tex = null;
        tex = LoadTexture("res/"+name+".png", "PNG");
        return tex;
    }

    private static long getCurrentTime(){
        return Sys.getTime()*1000/Sys.getTimerResolution();
    }

    public void adjustCamera(float x, float y){

    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }
}
