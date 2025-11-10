package FinalProject.Player;

import FinalProject.Loot.Item;
import FinalProject.Managers.TextureManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class InventoryUI {

    private final Inventory inventory;
    private final BitmapFont font;
    private boolean visible = false;

    private TextureRegion bgTile;
    private TextureRegion slotFrame;

    private final int slotsPerRow = 5;
    private final int slotSize = 48;
    private final int padding = 12;

    private float x, y, width, height;

    public InventoryUI(Inventory inv) {
        this.inventory = inv;

        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(1.2f);

        loadRegions();
        computePanelSize();
    }

    private void loadRegions() {
        Texture tex = TextureManager.get("Inventory");
        
        if (tex != null) {
            bgTile = new TextureRegion(tex, 0, 0, 48, 48);
            slotFrame = new TextureRegion(tex, 48, 0, 48, 48);
        }
    }

    private void computePanelSize() {
        int rows = (int) Math.ceil(inventory.getMaxSlots() / (float) slotsPerRow);

        width = slotsPerRow * (slotSize + padding) + padding * 2;
        height = rows * (slotSize + padding) + padding * 3 + 50;

        x = (1296 - width) / 2;
        y = (980 - height) / 2;
    }

    public void toggle() {
        visible = !visible;
    }

    public boolean isVisible() {
        return visible;
    }

    public void draw(SpriteBatch batch) {
        if (!visible) return;

        drawPanel(batch);

        font.draw(batch, "Inventory", x + padding, y + height - 20);
        font.draw(batch, "Coins: " + inventory.getCoins(), x + padding, y + height - 45);

        Array<Item> items = inventory.getItems();

        for (int i = 0; i < inventory.getMaxSlots(); i++) {
            float sx = x + padding + (i % slotsPerRow) * (slotSize + padding);
            float sy = y + height - 90 - (i / slotsPerRow) * (slotSize + padding);

            if (slotFrame != null) {
                batch.draw(slotFrame, sx, sy, slotSize, slotSize);
            }

            if (i < items.size) {
                Item item = items.get(i);
                Texture t = TextureManager.get(item.getIconName());
                if (t != null) {
                    batch.draw(t, sx + 6, sy + 6, slotSize - 12, slotSize - 12);
                }

                if (item.getAmount() > 1) {
                    font.draw(batch, "x" + item.getAmount(), sx + 2, sy + 14);
                }
            }
        }

        font.draw(batch, "Press I to close", x + padding, y + 25);
    }

    private void drawPanel(SpriteBatch batch) {
        if (bgTile == null) return;

        int tileW = bgTile.getRegionWidth();
        int tileH = bgTile.getRegionHeight();

        for (float px = x; px < x + width; px += tileW) {
            for (float py = y; py < y + height; py += tileH) {
                batch.draw(bgTile, px, py);
            }
        }
    }

    public void dispose() {
        font.dispose();
    }
}