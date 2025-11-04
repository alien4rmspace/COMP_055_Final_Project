package FinalProject;

import FinalProject.Screens.GameScreen;
import FinalProject.Screens.LoadingScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import java.util.Stack;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private LoadingScreen  loadingScreen;
    private GameScreen gameScreen;

    @Override
    public void create() {
        loadingScreen = new LoadingScreen(this);
        setScreen(loadingScreen);
	}

    @Override
    public void dispose() {
    }

    public void showMainScreen(){
        //setScreen(gameScreen);
    }
    public void showLoadingScreen(){
        if (loadingScreen == null){
            loadingScreen = new LoadingScreen(this);
        }
    }

    public void showGameScreen(){
        if (gameScreen == null){
            gameScreen = new GameScreen(this);
        }
        setScreen(gameScreen);
    }

    public void returnToMenuScreen(){
        // TODO
    }
}


