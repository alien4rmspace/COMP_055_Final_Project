package FinalProject.Managers;

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

        // Add interactables bounds here
        MapLayer interactableLayer = tiledMap.getLayers().get("Lootables");
        for (MapObject object : interactableLayer.getObjects()){
            if ("loot_node".equals(object.getProperties().get("type", String.class))){
                // Ignore all loot_spawn interactable objects. No collision, just interactable.
                continue;
            }
            collisionRectangles.add(((RectangleMapObject) object).getRectangle());
        }

        System.out.println("Successfully loaded collisions in CollisionManager");
    }

    public void addCollisionRectangle(Rectangle rectangle){
        collisionRectangles.add(rectangle);
    }

    public void removeCollisionRectangle(Rectangle rectangle){
        collisionRectangles.removeValue(rectangle, true);
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
