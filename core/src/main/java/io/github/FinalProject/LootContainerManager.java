package io.github.FinalProject;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class LootContainerManager {
    private final Array<Rectangle> lootRectangles = new Array<Rectangle>();

    public LootContainerManager(TiledMap tiledMap){
        MapLayer layer = tiledMap.getLayers().get("Interactables");
        for (MapObject object : layer.getObjects()){
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            String type = object.getProperties().get("type", String.class);
            System.out.println("Spawn loot: " + type + " at " + rectangle);
        }
    }

    public boolean isCollide(Rectangle bounds){
        for (Rectangle rectangle : lootRectangles){
            if (bounds.overlaps(rectangle)){
                return true;
            }
        }
        return false;
    }
}
