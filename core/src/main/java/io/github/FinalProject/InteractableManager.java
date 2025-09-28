package io.github.FinalProject;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class InteractableManager {
    private final Array<Interactable> interactables = new Array<Interactable>();

    public InteractableManager(TiledMap tiledMap){
        MapLayer layer = tiledMap.getLayers().get("Interactables");
        for (MapObject object : layer.getObjects()){
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            String type = object.getProperties().get("type", String.class);
            interactables.add(new LootContainer(rectangle, type));
        }

        System.out.println("Successfully loaded containers in LootContainerManager");
    }

    public Array<Interactable> getInteractables(){
        return interactables;
    }
}
