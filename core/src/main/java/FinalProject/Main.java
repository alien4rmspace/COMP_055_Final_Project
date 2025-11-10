//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package FinalProject;

import FinalProject.Screens.GameScreen;
import FinalProject.Screens.LoadingScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class Main extends Game {
    private LoadingScreen loadingScreen;
    private GameScreen gameScreen;

    public void create() {
        // Start with the loading screen first
        this.loadingScreen = new LoadingScreen(this);
        this.setScreen(this.loadingScreen);
    }

    public void dispose() {
        super.dispose(); // Added to properly clean up resources
    }

    public void showMainScreen() {
        // Not implemented yet, placeholder for future main menu
    }

    public void showLoadingScreen() {
        if (this.loadingScreen == null) {
            this.loadingScreen = new LoadingScreen(this);
        }
        // Make sure to actually switch to the loading screen
        this.setScreen(this.loadingScreen);
    }

    public void showGameScreen() {
        if (this.gameScreen == null) {
            this.gameScreen = new GameScreen(this, (new TmxMapLoader()).load("TiledMaps/rural.tmx"));
        }

        this.setScreen(this.gameScreen);
    }

    public void returnToMenuScreen() {
        // Placeholder for future return to menu logic
    }
}
