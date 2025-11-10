package FinalProject.Managers;

import FinalProject.Interactables.Characters.InteractionBehavior.DialogueBehavior;
import FinalProject.Interactables.Characters.Npc;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;
import java.util.Objects;

public final class NpcManager {
    private final HashMap<String, Npc> npcs = new HashMap<String, Npc>();

    private final String dialogue = "Hello, my name is Mr.Moncler. " +
        "How may I help you on this very fine day sir! " +
        "Can't you see there are houses to rob! " +
        "Go make me money! ";

    public NpcManager(TiledMap tiledMap){
        MapLayer mapLayer = tiledMap.getLayers().get("Characters");
        if (mapLayer == null) return;

        for (MapObject object : mapLayer.getObjects()) {
            if (object instanceof RectangleMapObject){
                RectangleMapObject rectangleMapObject = (RectangleMapObject) object;
                Rectangle rectangle = rectangleMapObject.getRectangle();

                Npc npc = new Npc(object.getName(), new DialogueBehavior(this.dialogue));
                npc.setPosition(rectangle.getCenter(new Vector2()));

                npcs.put(npc.getName(), npc);
            }
        }

        System.out.println("Successfully loaded Npc Manager");
    }

    public void draw(SpriteBatch spriteBatch){
        for (Npc npc : npcs.values()){
            npc.draw(spriteBatch);
        }
    }

    public void update(float delta){
        for (Npc npc : npcs.values()){
            npc.update(delta);
        }
    }

    public void add(Npc npc){
        npcs.put(npc.getName(), npc);
    }

    public Npc getNpc(String name){
        return npcs.get(name);
    }

    public HashMap<String, Npc> getNpcs(){
        return this.npcs;
    }
}
