package FinalProject.Loot;

public class Item {
    private final String name;
    private final float itemPrice;
    private final float itemWeight;
    private final int amount;
    private final String iconName;

    public Item(String name, float itemPrice, float itemWeight, int amount, String iconName) {
        this.name = name;
        this.itemPrice = itemPrice;
        this.itemWeight = itemWeight;
        this.amount = amount;
        this.iconName = iconName;
    }
    
    public Item(String name, float itemPrice, float itemWeight, int amount) {
        this(name, itemPrice, itemWeight, amount, name);
    }

    public String getName() {
        return name;
    }
    public float getItemPrice() {
        return itemPrice;
    }
    public float getItemWeight() {
        return itemWeight;
    }
    public int getAmount() {
        return amount;
    }
    public String getIconName() {
        return iconName;
    }
}
