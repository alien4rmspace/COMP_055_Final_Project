package io.github.FinalProject;

import java.util.*;

public class LootTable {
    private final List<LootItem> lootItems = new ArrayList<>();
    private final Random rand = new Random();

    public void addItem(LootItem item) {
        lootItems.add(item);
    }

    public Item rollItem() {
        // Grab the total weight of each item in items.
        int totalWeight = lootItems.stream().mapToInt(i -> i.dropWeight).sum();
        int roll = rand.nextInt(totalWeight);
        int count = 0;

        for (LootItem lootItem : lootItems) {
            {
                count += lootItem.dropWeight;
                if (count >= roll) {

                    int amount = lootItem.minAmount + rand.nextInt(lootItem.minAmount + lootItem.maxAmount - lootItem.minAmount);  // Something like coins for an example.

                    // Calculate weight of the item.
                    float weight = lootItem.minItemWeight + rand.nextFloat(lootItem.minItemWeight + lootItem.maxItemWeight - lootItem.minItemWeight);

                    //Calculate price of the item.
                    float price = 0;
                    if (lootItem.materialType != null){
                        price = lootItem.materialType.costPerGram * weight;
                    } else{
                        price = lootItem.minPrice + rand.nextFloat(lootItem.minPrice + lootItem.maxPrice - lootItem.minPrice);
                    }

                    return new Item(lootItem.name, price, weight, amount);
                }
            }
        }
        return null;
    }
}
