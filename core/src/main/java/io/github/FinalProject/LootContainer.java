package io.github.FinalProject;

import com.badlogic.gdx.math.Rectangle;

public class LootContainer {
    private Rectangle rectangle;
    private ContainerType containerType;
    private String lootTable;
    private boolean locked;

    public LootContainer(Rectangle bounds, ContainerType type, boolean isLock){
        this.rectangle = bounds;
        this.containerType = type;
        this.locked = isLock;
    }

    public Rectangle getRectangle(){
        return this.rectangle;
    }
    public ContainerType getContainerType(){
        return this.containerType;
    }
    public boolean isLocked(){
        return this.locked;
    }
}
