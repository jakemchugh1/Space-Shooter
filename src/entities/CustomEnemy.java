package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import static entities.EntityManager.getTexture;
import static utilities.Artist.DrawQuadTex;
import static utilities.Artist.getFrameTimeSeconds;

public class CustomEnemy {
    private int matrix[][];
    private int sizeX;
    private int sizeY;
    private float xOffset;
    private float yOffset;
    private float width;
    private float height;

    private int pmatrix[][];
    private int psizeX;
    private int psizeY;
    private float pxOffset;
    private float pyOffset;
    private float pwidth;
    private float pheight;

    private int selectedColor;

    private boolean buttonDown;
    private boolean inGame;
    ///attributes
    private int speed;
    private int health;

    //ingame
    private float posX;
    private float posY;

    public CustomEnemy(){
        sizeX = 32;
        sizeY = 32;
        matrix = new int[sizeY][sizeX];
        xOffset = 450;
        yOffset = 256;
        width = 16;
        height = 16;
        psizeX = 2;
        psizeY = 10;
        pmatrix = new int[psizeY][psizeX];
        pxOffset = 350;
        pyOffset = 256;
        pwidth = 32;
        pheight = 32;

        speed = 100;
        health = 1;

        buttonDown = false;
        inGame = false;

        selectedColor = 1;
        int tempColor = 1;
        for(int i = 0 ; i < psizeY ; i++){
            for(int j = 0 ; j < psizeX ; j++){
                pmatrix[i][j] = tempColor;
                tempColor = tempColor + 1;
            }
        }
    }


    public void run(CustomPlayer player){
        checkInputs();
        draw();
        if(posX < player.getXTarget()) posX = posX + speed*getFrameTimeSeconds();
        else posX = posX - speed*getFrameTimeSeconds();
        if(posY < player.getYTarget()) posY = posY + speed*getFrameTimeSeconds();
        else posY = posY - speed*getFrameTimeSeconds();

    }

    public void checkInputs(){
        if(!inGame){
            if(Mouse.isButtonDown(0)) {
                int mouseX = (int) ((Mouse.getX() - xOffset)/width);
                int mouseY = (int) ((960-Mouse.getY() - yOffset)/height);
                int pmouseX = (int) ((Mouse.getX() - pxOffset)/pwidth);
                int pmouseY = (int) ((960-Mouse.getY() - pyOffset)/pheight);
                if(mouseX >= 0 && mouseX <= sizeX-1 && mouseY >= 0 && mouseY <= sizeY-1){
                    matrix[mouseY][mouseX] = selectedColor;
                }else if(pmouseX >= 0 && pmouseX < psizeX && pmouseY >= 0 && pmouseY < psizeY && !buttonDown){
                    selectedColor = pmatrix[pmouseY][pmouseX];
                    buttonDown = true;
                }


            }if(Mouse.isButtonDown(1)) {
                int mouseX = (int) ((Mouse.getX() - xOffset)/width);
                int mouseY = (int) ((960-Mouse.getY() - yOffset)/height);
                if(mouseX >= 0 && mouseX <= sizeX-1 && mouseY >= 0 && mouseY <= sizeY-1){
                    matrix[mouseY][mouseX] = 0;
                }

            }
            if(!Mouse.isButtonDown(0)) buttonDown = false;
        }
    }

    public void draw(){
        if(!inGame) {
            coolorMatrixDraw(matrix, sizeX, sizeY, xOffset, yOffset, width, height);
            coolorMatrixDraw(pmatrix, psizeX, psizeY, pxOffset, pyOffset, pwidth, pheight);
        }else{
            coolorMatrixDraw(matrix, sizeX, sizeY, posX, posY, width, height);
        }

    }
    private void coolorMatrixDraw(int[][] matrixIn,float sizexIn,float sizeyIn,float xOffsetIn,float yOffsetIn, float widthIn, float heightIn){
        for(int i = 0 ; i < sizeyIn; i++){
            for(int j = 0 ; j < sizexIn ; j++){
                if(!inGame)DrawQuadTex(getTexture("outline_blue"), xOffsetIn + (j*widthIn), yOffsetIn + (i*heightIn), widthIn, heightIn);
                if(matrixIn[i][j] == 1)DrawQuadTex(getTexture("white"), xOffsetIn + (j*widthIn), yOffsetIn + (i*heightIn), widthIn, heightIn);
                else if(matrixIn[i][j] == 2)DrawQuadTex(getTexture("black"), xOffsetIn + (j*widthIn), yOffsetIn + (i*heightIn), widthIn, heightIn);
                else if(matrixIn[i][j] == 3)DrawQuadTex(getTexture("grey2"), xOffsetIn + (j*widthIn), yOffsetIn + (i*heightIn), widthIn, heightIn);
                else if(matrixIn[i][j] == 4)DrawQuadTex(getTexture("grey1"), xOffsetIn + (j*widthIn), yOffsetIn + (i*heightIn), widthIn, heightIn);
                else if(matrixIn[i][j] == 5)DrawQuadTex(getTexture("brown"), xOffsetIn + (j*widthIn), yOffsetIn + (i*heightIn), widthIn, heightIn);
                else if(matrixIn[i][j] == 6)DrawQuadTex(getTexture("red1"), xOffsetIn + (j*widthIn), yOffsetIn + (i*heightIn), widthIn, heightIn);
                else if(matrixIn[i][j] == 7)DrawQuadTex(getTexture("pink"), xOffsetIn + (j*widthIn), yOffsetIn + (i*heightIn), widthIn, heightIn);
                else if(matrixIn[i][j] == 8)DrawQuadTex(getTexture("red2"), xOffsetIn + (j*widthIn), yOffsetIn + (i*heightIn), widthIn, heightIn);
                else if(matrixIn[i][j] == 9)DrawQuadTex(getTexture("orange2"), xOffsetIn + (j*widthIn), yOffsetIn + (i*heightIn), widthIn, heightIn);
                else if(matrixIn[i][j] == 10)DrawQuadTex(getTexture("orange1"), xOffsetIn + (j*widthIn), yOffsetIn + (i*heightIn), widthIn, heightIn);
                else if(matrixIn[i][j] == 11)DrawQuadTex(getTexture("yellow2"), xOffsetIn + (j*widthIn), yOffsetIn + (i*heightIn), widthIn, heightIn);
                else if(matrixIn[i][j] == 12)DrawQuadTex(getTexture("yellow1"), xOffsetIn + (j*widthIn), yOffsetIn + (i*heightIn), widthIn, heightIn);
                else if(matrixIn[i][j] == 13)DrawQuadTex(getTexture("green2"), xOffsetIn + (j*widthIn), yOffsetIn + (i*heightIn), widthIn, heightIn);
                else if(matrixIn[i][j] == 14)DrawQuadTex(getTexture("green1"), xOffsetIn + (j*widthIn), yOffsetIn + (i*heightIn), widthIn, heightIn);
                else if(matrixIn[i][j] == 15)DrawQuadTex(getTexture("blue4"), xOffsetIn + (j*widthIn), yOffsetIn + (i*heightIn), widthIn, heightIn);
                else if(matrixIn[i][j] == 16)DrawQuadTex(getTexture("blue1"), xOffsetIn + (j*widthIn), yOffsetIn + (i*heightIn), widthIn, heightIn);
                else if(matrixIn[i][j] == 17)DrawQuadTex(getTexture("blue3"), xOffsetIn + (j*widthIn), yOffsetIn + (i*heightIn), widthIn, heightIn);
                else if(matrixIn[i][j] == 18)DrawQuadTex(getTexture("blue2"), xOffsetIn + (j*widthIn), yOffsetIn + (i*heightIn), widthIn, heightIn);
                else if(matrixIn[i][j] == 19)DrawQuadTex(getTexture("purple2"), xOffsetIn + (j*widthIn), yOffsetIn + (i*heightIn), widthIn, heightIn);
                else if(matrixIn[i][j] == 20)DrawQuadTex(getTexture("purple1"), xOffsetIn + (j*widthIn), yOffsetIn + (i*heightIn), widthIn, heightIn);
            }
        }

    }


    public void increaseSpeed(){
        speed = speed + 1;
    }

    public void decreaseSpeed(){
        speed = speed - 1;
    }

    public int getSpeed(){
        return speed;
    }

    public void increaseHealth(){
        health = health + 1;
    }

    public void decreaseHealth(){
        if(health > 1) health = health - 1;
    }

    public int getHealth(){
        return health;
    }

    public void setInGame(boolean in){
        inGame = in;
    }

    public void increaseSize(){
        if(width < 32)width = width + 1;
        if(height < 32)height = height + 1;
    }

    public void decreaseSize(){
        if(width > 1)width = width - 1;
        if(height > 1)height = height - 1;
    }

    public void increaseResolution(){
        if(sizeX < 32) sizeX = sizeX + 1;
        if(sizeY < 32) sizeY = sizeY + 1;
    }

    public void decreaseResolution(){
        if(sizeX > 1) sizeX = sizeX - 1;
        if(sizeY > 1) sizeY = sizeY - 1;
    }


}
