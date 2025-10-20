package FinalProject.Interactables;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import FinalProject.Player.Player;

public class Door implements Interactable {
    private Rectangle rectangle;
    private boolean isOpen;

    public Door(Rectangle rectangle){
        this.rectangle = rectangle;
        this.isOpen = false;
    }

    @Override
    public boolean canInteract(Player player) {
        Rectangle expanded = new Rectangle(player.getBounds());
        expanded.setSize(expanded.width + 15, expanded.height + 15); // Grow by 15 px each way
        expanded.setCenter(player.getBounds().x + player.getBounds().width / 2,
            player.getBounds().y + player.getBounds().height / 2);  // Account for rectangle growing not from the center.

        return expanded.overlaps(this.rectangle);
    }
    @Override
    public void interact(Player player) {}
    @Override
    public void draw(SpriteBatch spriteBatch){

    }

    @Override
    public Rectangle getRectangle() {
        return this.rectangle;
    }
}
