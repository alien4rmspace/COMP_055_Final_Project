package io.github.FinalProject;

import java.util.HashMap;
import java.util.Map;

public class LootTableManager {
    private static final Map<String, LootTable> tables = new HashMap<>();

    public static void init() {
        // Common loot table
        LootTable commonLootTable = new LootTable();
        commonLootTable.addItem(new LootItem(
            "Silver Necklace",
            MaterialType.SILVER,
            50,
            0.1f,
            2,
            1, 1,
            0, 0));
        commonLootTable.addItem(new LootItem(
            "Gold Necklace",
            MaterialType.GOLD,
            10,
            0.1f,
            2,
            1, 1,
            0, 0));
        tables.put("commonLootTable", commonLootTable);

        LootTable rareLootTable = new LootTable();
        // TODO: ADD ITEMS HERE
        tables.put("rareLootTable", commonLootTable);

        LootTable legendaryLootTable = new LootTable();
        // TODO: ADD ITEMS HERE
        tables.put("legendaryLootTable", commonLootTable);


        System.out.println("Successfully loaded loot tables in LootTableManager!");
    }

    public static LootTable get(String id) {
        return tables.get(id);
    }
}
