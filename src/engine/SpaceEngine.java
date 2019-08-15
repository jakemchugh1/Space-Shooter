package engine;

import audio.AudioManager;
import manager.GameManager;
import org.lwjgl.opengl.Display;
import static utilities.Artist.*;
import static org.lwjgl.opengl.GL11.*;


public class SpaceEngine {

    public static boolean exitGame;



    public SpaceEngine()  {
        BeginSession();

        exitGame = false;

        GameManager manager = new GameManager();

        while (!Display.isCloseRequested() && !exitGame) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            manager.run();
            updateDisplay();
            }
        AudioManager.cleanUp();

    }

    public static void main(String[] Args){

        SpaceEngine game = new SpaceEngine();
    }
}
