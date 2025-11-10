package FinalProject.Player;

import FinalProject.Loot.Item;
import com.badlogic.gdx.utils.Array;

public class Inventory {
    private final Array<Item> items;
    private final int maxSlots;
    private int coins;

    public Inventory(int maxSlots) {
        this.maxSlots = maxSlots;
        this.items = new Array<>();
        this.coins = 0; 
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

    public void addCoins(int amount) {
        this.coins += amount;
    }

    public boolean removeCoins(int amount) {
        if (coins >= amount) {
            coins -= amount;
            return true;
        }
        return false;
    }

    public int getCoins() {
        return coins;
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