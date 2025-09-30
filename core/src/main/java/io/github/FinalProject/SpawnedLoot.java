package io.github.FinalProject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class SpawnedLoot implements Interactable{
    Rectangle rectangle;
    LootTable lootTable;
    ContainerType containerType;
    Texture texture;
    boolean taken;

    public SpawnedLoot(Rectangle bounds, String containerType){
        this.rectangle = bounds;
        this.taken = false;

        this.texture = TextureManager.get("pizza");

        switch (containerType){
            case "common":
                this.containerType = ContainerType.COMMON;
                this.lootTable = LootTableManager.get("commonLootTable");
                break;

            case "rare":
                this.containerType = ContainerType.RARE;
                break;

            case "legendary":
                this.containerType = ContainerType.LEGENDARY;
                break;

            default:
                break;
        }
    }

    @Override
    public boolean canInteract(Player player) {
        // Expand player rectangle to account for knockback knocking player out of reach.
        Rectangle expanded = new Rectangle(player.getBounds());
        expanded.setSize(expanded.width + 15, expanded.height + 15); // Grow by 15 px each way
        expanded.setCenter(player.getBounds().x + player.getBounds().width / 2,
            player.getBounds().y + player.getBounds().height / 2);  // Account for rectangle growing not from the center.

        return expanded.overlaps(this.rectangle) && !taken;
    }

    @Override
    public void interact(Player player){
        if (!taken){
            this.taken = true;
            Item item = lootTable.rollItem();

            System.out.println("Chest opened! Dropping loot from: " + item.getName());
        }
    }

    @Override
    public void draw(SpriteBatch spritebatch){
        if (!this.taken){
            spritebatch.draw(this.texture, this.rectangle.x, this.rectangle.y);
        }
    }

    @Override
    public Rectangle getRectangle() {
        return rectangle;
    }
}
