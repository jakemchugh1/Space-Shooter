package manager;

import game.Game;
import ui.Ui;

public class GameManager {
    Game level1;
    Ui ui;
    public GameManager(){

        ui = new Ui();
        level1 = new Game();

    }
    public void run(){
        if(ui.getReset()){
            level1 = new Game();
            ui.newGame();
        }
        ui.run(level1);
    }
}
