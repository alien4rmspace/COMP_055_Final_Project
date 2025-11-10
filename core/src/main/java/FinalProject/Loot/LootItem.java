package FinalProject.Loot;

import FinalProject.Enums.MaterialType;

// Holds the Loot items constraints that will go into item.
public class LootItem {
    public String name;
    public MaterialType materialType;
    public int dropWeight;
    public float minItemWeight;
    public float maxItemWeight;
    public int minAmount;
    public int maxAmount;
    public float minPrice;
    public float maxPrice;

    public LootItem(String name, MaterialType materialType, int dropWeight, float minItemWeight, float maxItemWeight, int minAmount, int maxAmount,  float minPrice, float maxPrice) {
        this.name = name;
        this.materialType = materialType;
        this.dropWeight = dropWeight;
        this.minItemWeight = minItemWeight;
        this.maxItemWeight = maxItemWeight;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }
}
