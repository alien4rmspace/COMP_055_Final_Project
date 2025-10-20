package FinalProject.Interactables;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import FinalProject.Player;

public interface Interactable {
    boolean canInteract(Player player);
    void interact(Player player);
    void draw(SpriteBatch batch);
    Rectangle getRectangle();
}
