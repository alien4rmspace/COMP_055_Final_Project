package FinalProject;

import FinalProject.Screens.GameScreen;
import com.badlogic.gdx.Game;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
	private GameScreen gameScreen;  // Go to screen

    @Override
    public void create() {
        setScreen(new GameScreen());
	}
}


