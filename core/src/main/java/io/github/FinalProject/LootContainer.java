package io.github.FinalProject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class LootContainer implements Interactable{
    private Rectangle rectangle;
    private ContainerType type;
    private String lootTable;
    private boolean locked;
    private boolean opened;

    public LootContainer(Rectangle bounds, String containerType){
        this.rectangle = bounds;
        this.locked = false;
        this.opened = false;

        switch (containerType){
            case "Chest":
                this.type = ContainerType.CHEST;
                break;
                default: break;
        }
    }

    @Override
    public boolean canInteract(Player player) {
        // Expand player rectangle to account for knockback knocking player out of reach.
        Rectangle expanded = new Rectangle(player.getBounds());
        expanded.setSize(expanded.width + 15, expanded.height + 15); // Grow by 15 px each way
        expanded.setCenter(player.getBounds().x + player.getBounds().width / 2,
            player.getBounds().y + player.getBounds().height / 2);  // Account for rectangle growing not from the center.

        return expanded.overlaps(this.rectangle) && !opened;
    }

    @Override
    public void interact(Player player){
        if (!opened){
            this.opened = true;
            System.out.println("Chest opened! Dropping loot from: " + lootTable);
        }
    }

    public void draw(SpriteBatch batch, Texture texture) {
        if (!opened) {
            batch.draw(texture, rectangle.x, rectangle.y);
        }
    }
}
