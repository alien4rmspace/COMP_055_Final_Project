package FinalProject.Managers;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class TextureManager {
    // This is a unique class. Only one of this class will exist at all times and
    // can be accessed from anywhere in our project by calling TextureManager.
    // You can think of it as a global Class.
    private static final HashMap<String, Texture> textureMap = new HashMap<>();

    public static void init(){
        loadFromJson("manifests/textures.json");
        System.out.println("Successfully loaded textures in TextureManager");
    }

    private static void loadFromJson(String manifestPath) {
        FileHandle fh = Gdx.files.internal(manifestPath);

        if (!fh.exists()) {
            throw new IllegalStateException("Manifest not found: " + manifestPath);
        }

        JsonReader reader = new JsonReader();
        JsonValue root = reader.parse(fh);
        var assetManager = AssetLoader.getManager();

        if (root.has("textures")) {
            JsonValue textures = root.get("textures");
            for (JsonValue entry : textures) {
                String key = entry.name();
                String path = entry.asString();
                if (assetManager.isLoaded(path, Texture.class)) {
                    textureMap.put(key, assetManager.get(path, Texture.class));
                }
            }
        }

        if (root.has("buttons")) {
            JsonValue btn = root.get("buttons");
            String pattern = btn.getString("pattern");
            int from = btn.getInt("from");
            int to = btn.getInt("to");
            for (int i = from; i <= to; i++) {
                String key = "UI-" + i;
                String path = String.format(pattern, i);
                if (assetManager.isLoaded(path, Texture.class)) {
                    textureMap.put(key, assetManager.get(path, Texture.class));
                }
            }
        }
    }

    public static Texture get(String key) {
        return textureMap.get(key);
    }
}
