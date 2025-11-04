package FinalProject.Player;

import FinalProject.Loot.Item;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class InventoryUI {
    private final Inventory inventory;
    private final Texture uiTexture;
    private final NinePatch slotPatch;
    private final NinePatch panelPatch;
    private final BitmapFont font;
    
    private boolean isVisible;
    private final int slotsPerRow = 5;
    private final int slotSize = 64;
    private final int slotPadding = 8;
    private final float panelX;
    private final float panelY;
    private final float panelWidth;
    private final float panelHeight;

    public InventoryUI(Inventory inventory, Texture uiTexture) {
        this.inventory = inventory;
        this.uiTexture = uiTexture;
        this.isVisible = false;
        this.font = new BitmapFont();
        this.font.setColor(Color.WHITE);
        this.font.getData().setScale(1.2f);

        this.panelPatch = new NinePatch(uiTexture, 8, 8, 8, 8);
        this.slotPatch = new NinePatch(uiTexture, 4, 4, 4, 4);

        int rows = (int) Math.ceil((float) inventory.getMaxSlots() / slotsPerRow);
        this.panelWidth = slotsPerRow * (slotSize + slotPadding) + slotPadding * 2;
        this.panelHeight = rows * (slotSize + slotPadding) + slotPadding * 2 + 50;
        
        this.panelX = (1296 - panelWidth) / 2;
        this.panelY = (980 - panelHeight) / 2;
    }

    public void toggle() {
        isVisible = !isVisible;
    }

    public void setVisible(boolean visible) {
        this.isVisible = visible;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void draw(SpriteBatch batch) {
        if (!isVisible) return;

        batch.setColor(0, 0, 0, 0.7f);
        batch.draw(uiTexture, 0, 0, 1296, 980);
        batch.setColor(1, 1, 1, 1);

        panelPatch.draw(batch, panelX, panelY, panelWidth, panelHeight);

        font.draw(batch, "Inventory", panelX + 20, panelY + panelHeight - 20);

        // Draw inventory slots
        Array<Item> items = inventory.getItems();
        for (int i = 0; i < inventory.getMaxSlots(); i++) {
            int row = i / slotsPerRow;
            int col = i % slotsPerRow;
            
            float slotX = panelX + slotPadding + col * (slotSize + slotPadding);
            float slotY = panelY + panelHeight - 60 - row * (slotSize + slotPadding);

            slotPatch.draw(batch, slotX, slotY, slotSize, slotSize);

            if (i < items.size) {
                Item item = items.get(i);
                
                batch.setColor(0.8f, 0.6f, 0.2f, 1f);
                batch.draw(uiTexture, slotX + 8, slotY + 8, slotSize - 16, slotSize - 16);
                batch.setColor(1, 1, 1, 1);
                
                String displayName = item.getName();
                if (displayName.length() > 8) {
                    displayName = displayName.substring(0, 7) + "..";
                }
                font.draw(batch, displayName, slotX + 4, slotY - 4);
                
                if (item.getAmount() > 1) {
                    font.draw(batch, "x" + item.getAmount(), 
                             slotX + slotSize - 24, slotY + 16);
                }
            }
        }

        font.draw(batch, "Press I to close", panelX + 20, panelY + 20);
    }

    public void dispose() {
        font.dispose();
    }
}
