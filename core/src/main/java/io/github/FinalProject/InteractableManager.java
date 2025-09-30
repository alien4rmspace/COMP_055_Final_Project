package io.github.FinalProject;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class InteractableManager {
    private final Array<Interactable> interactables = new Array<Interactable>();

    public InteractableManager(TiledMap tiledMap){
        MapLayer layer = tiledMap.getLayers().get("Interactables");
        for (MapObject object : layer.getObjects()){
            // Filter out the interactable objects in the map by their class name in Tiles.
            if ("common".equals(object.getProperties().get("type",String.class))) {
                Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
                String type = object.getProperties().get("type", String.class);
                interactables.add(new LootContainer(rectangle, type));
            }

            // Roll random chance and spawn in loot at our loot_spawn's
            if ("loot_spawn".equals(object.getProperties().get("type",String.class))) {
                float chanceToSpawn = 0.2f;
                if (MathUtils.random(0f, 1f) < chanceToSpawn){
                    System.out.println("Spawning loot_spawn");
                }
            }
        }

        System.out.println("Successfully loaded containers in LootContainerManager");
    }

    public Array<Interactable> getInteractables(){
        return interactables;
    }
}
