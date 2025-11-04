package FinalProject.Interactables;

import FinalProject.Loot.Item;
import FinalProject.Loot.LootTable;
import FinalProject.Managers.LootTableManager;
import FinalProject.Managers.SoundManager;
import FinalProject.Player.Player;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class LootContainer implements Interactable, Pickable {
    private Rectangle rectangle;
    private LootTable lootTable;
    private ContainerType containerType;
    private float lockLevel;
    private boolean isLocked;
    private boolean opened;

    public LootContainer(Rectangle bounds, String containerType){
        this.rectangle = bounds;
        this.isLocked = false;
        this.opened = false;

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
        int expandAmount = 15;
        expanded.setSize(expanded.width + expandAmount, expanded.height + expandAmount); // Grow by 15 px each way
        expanded.setCenter(player.getBounds().x + player.getBounds().width / 2,
            player.getBounds().y + player.getBounds().height / 2);  // Account for rectangle growing not from the center.

        return expanded.overlaps(this.rectangle) && !opened;
    }

    @Override
    public void interact(Player player){
        if (!opened){
            this.opened = true;
            Item item = lootTable.rollItem();
            System.out.println("Chest opened! Dropping loot from: " + item.getName());
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (!opened){

        }
    }

    @Override
    public float getLockLevel(){
        return lockLevel;
    }

    @Override
    public Rectangle getRectangle() {
        return rectangle;
    }

    public void draw(SpriteBatch batch, Texture texture) {
        if (!opened) {
            batch.draw(texture, rectangle.x, rectangle.y);
        }
    }
    public void unlock(){
        this.isLocked = false;
        SoundManager.play("lock_picked");
    }
}
