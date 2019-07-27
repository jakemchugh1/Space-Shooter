package scene;

public class Scene {

    private Background background;

    private Scene(int width, int height){
        background = new Background(1280,960);
    }

    public void Draw(){
        background.draw();
    }
}
