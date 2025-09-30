package io.github.FinalProject;

public enum MaterialType {
    SILVER(1.48f),
    PLATINUM(51f),
    GOLD(120.00f);

    public final float costPerGram;

    MaterialType(float costPerGram) {
        this.costPerGram = costPerGram;
    }
}
