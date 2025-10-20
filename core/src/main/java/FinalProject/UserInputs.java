package FinalProject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;

public class UserInputs {
    public static boolean isUpPressed() {
        return Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP);
    }
    public static boolean isDownPressed(){
        return Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN);
    }
    public static boolean isLeftPressed(){
        return Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT);
    }
    public static boolean isRightPressed(){
        return Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT);
    }
    public static boolean isInteractPressed(){
        return Gdx.input.isKeyPressed(Input.Keys.E);
    }
    public static boolean isPausedPressed(){
        return Gdx.input.isKeyPressed(Input.Keys.ESCAPE);
    }
    public static boolean isInventoryPressed(){
        return Gdx.input.isKeyPressed(Input.Keys.I);
    }
}
