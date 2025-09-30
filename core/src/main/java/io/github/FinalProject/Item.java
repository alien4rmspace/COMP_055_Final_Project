package io.github.FinalProject;

public class Item {
    private String name;
    private float itemPrice;
    private float itemWeight;
    private int amount;

    public Item(String name, float itemPrice, float itemWeight, int amount) {
        this.name = name;
        this.itemPrice = itemPrice;
        this.itemWeight = itemWeight;
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
