package FinalProject;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import FinalProject.Interactables.Interactable;

public class Window implements Interactable {
    private Rectangle rectangle;
    private boolean isLocked;

    @Override
    public boolean canInteract(Player player) {
        return false;
    }

    @Override
    public void interact(Player player) {

    }

    @Override
    public void draw(SpriteBatch batch) {

    }

    @Override
    public Rectangle getRectangle() {
        return null;
    }
}
