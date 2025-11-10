package FinalProject.Managers;

import FinalProject.Interactables.Characters.Character;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class CollisionManager {
    private final Array<Rectangle> collisionRectangles = new Array<Rectangle>();
    private final Array<Character> dynamicCollidables = new Array<Character>();

    public CollisionManager(TiledMap tiledMap){
        MapLayer layer = tiledMap.getLayers().get("Collisions");
        if (layer == null) return;

        for (MapObject object : layer.getObjects()){
            collisionRectangles.add(((RectangleMapObject) object).getRectangle());
        }

        // Add interactables bounds here
        MapLayer interactableLayer = tiledMap.getLayers().get("Lootables");
        if (interactableLayer == null) return;

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

    public void addDynamicCollidable(Character character){dynamicCollidables.add(character);}

    public boolean isCollide(Character self){
        for (Rectangle rectangle : collisionRectangles){
            if (self.getCollisionRectangle().overlaps(rectangle)){
                return true;
            }
        }

        for (Character character : dynamicCollidables){
            if (character == self){ continue; }
            if (self.getCollisionRectangle().overlaps(character.getCollisionRectangle())){
                return true;
            }
        }

        return false;
    }
}
