package FinalProject.Player;

import FinalProject.Loot.Item;
import com.badlogic.gdx.utils.Array;

public class Inventory {
    private final Array<Item> items;
    private final int maxSlots;

    public Inventory(int maxSlots) {
        this.maxSlots = maxSlots;
        this.items = new Array<>();
    }

    public boolean addItem(Item item) {
        if (items.size < maxSlots) {
            items.add(item);
            System.out.println("Added " + item.getName() + " to inventory");
            return true;
        }
        System.out.println("Inventory full!");
        return false;
    }

    public void removeItem(Item item) {
        items.removeValue(item, true);
    }

    public Array<Item> getItems() {
        return items;
    }

    public int getSize() {
        return items.size;
    }

    public int getMaxSlots() {
        return maxSlots;
    }
}