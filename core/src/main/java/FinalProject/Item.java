package FinalProject;

public class Item {
    private final String name;
    private final float itemPrice;
    private final float itemWeight;
    private final int amount;

    public Item(String name, float itemPrice, float itemWeight, int amount) {
        this.name = name;
        this.itemPrice = itemPrice;
        this.itemWeight = itemWeight;
        this.amount = amount;
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
}
