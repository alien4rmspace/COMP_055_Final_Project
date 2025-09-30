package io.github.FinalProject;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class CollisionManager {
    private final Array<Rectangle> collisionRectangles = new Array<Rectangle>();

    public CollisionManager(TiledMap tiledMap){
        MapLayer layer = tiledMap.getLayers().get("Collisions");
        for (MapObject object : layer.getObjects()){
            collisionRectangles.add(((RectangleMapObject) object).getRectangle());
        }
        layer = tiledMap.getLayers().get("Interactables");
        for (MapObject object : layer.getObjects()){
            if ("loot_spawn".equals(object.getProperties().get("type", String.class))){
                continue;  // Get use to seeing continue. Highly utilized in game development.
            }
            collisionRectangles.add(((RectangleMapObject) object).getRectangle());
        }

        System.out.println("Successfully loaded collisions in CollisionManager");
    }

    public boolean isCollide(Rectangle bounds){
        for (Rectangle rectangle : collisionRectangles){
            if (bounds.overlaps(rectangle)){
                return true;
            }
        }
        return false;
    }
}
