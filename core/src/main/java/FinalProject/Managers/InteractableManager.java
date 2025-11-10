package FinalProject.Managers;

import FinalProject.Interactables.Pickable.Door;
import FinalProject.Interactables.Pickable.Exit;
import FinalProject.Screens.GameScreen;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import FinalProject.Interactables.Interactable;
import FinalProject.Interactables.Pickable.LootContainer;
import FinalProject.Interactables.LootNode;

public class InteractableManager {
    private final Array<Interactable> interactables = new Array<Interactable>();

    public InteractableManager(TiledMap tiledMap){
        MapLayer layer = tiledMap.getLayers().get("Lootables");
        if (layer != null){
            for (MapObject object : layer.getObjects()){
                // Filter out the interactable objects in the map by their class name in Tiles.
                if ("common".equals(object.getProperties().get("type",String.class))) {
                    Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
                    String type = object.getProperties().get("type", String.class);
                    interactables.add(new LootContainer(rectangle, type));
                }

                // Roll random chance and spawn in loot at our loot_spawn's
                if ("loot_node".equals(object.getProperties().get("type",String.class))) {
                    float chanceToSpawn = 0.3f;
                    if (MathUtils.random(0f, 1f) < chanceToSpawn){
                        Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
                        interactables.add(new LootNode(rectangle, "common"));
                        System.out.println("Spawning loot_node");
                    }
                }
            }
        }

        layer = tiledMap.getLayers().get("Doors");
        if (layer != null){
            for (MapObject object : layer.getObjects()) {
                // Get the object class (Tiled "Class" field)
                String objClass = object.getProperties().get("type", String.class);

                if ("House_Door".equals(objClass)) {
                    if (object instanceof RectangleMapObject) {
                        Rectangle rect = ((RectangleMapObject) object).getRectangle();
                        interactables.add(new Door(rect));

                        System.out.println("Spawning House_Door");
                    }
                }

                if ("Exit_Door".equals(objClass)) {
                    if (object instanceof RectangleMapObject) {
                        Rectangle rect = ((RectangleMapObject) object).getRectangle();
                        interactables.add(new Exit(rect));

                        System.out.println("Spawning Exit_Door");
                    }
                }
            }
        }

        System.out.println("Successfully loaded containers in LootContainerManager");
    }

    public void addInteractable(Interactable interactable){
        interactables.add(interactable);
    }

    public void removeInteractable(Interactable interactable){
        interactables.removeValue(interactable, true);
    }

    public Array<Interactable> getInteractables(){
        return interactables;
    }
}
