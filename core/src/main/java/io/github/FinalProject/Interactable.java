package io.github.FinalProject;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public interface Interactable {
    boolean canInteract(Player player);
    void interact(Player player);
    void draw(SpriteBatch batch);
    Rectangle getRectangle();
}
