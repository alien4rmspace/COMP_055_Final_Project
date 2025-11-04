package FinalProject.Managers;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;

public class TextureManager {
    // This is a unique class. Only one of this class will exist at all times and
    // can be accessed from anywhere in our project by calling TextureManager.
    // You can think of it as a global Class.
	private static final HashMap<String, Texture> textureMap = new HashMap<>();

    public static void init(){
        load("character3", "Characters/Premade_Character_48x48_03.png");
        load("Amanda", "Characters/Amanda.png");
        load("pizza", "Textures/Pizza.png");

        load("ui", "Textures/Inventory.png");

        int totalUIButtons = 763;
        for (int i = 1; i <= totalUIButtons; i++) {
            String UIName = "UI-" + i;
            load(UIName, "Textures/User_Interface/Buttons/" + UIName + ".png");
        }

        System.out.println("Successfully loaded textures in TextureManager");
    }

	public static void load(String key, String texturePath) {

        textureMap.put(key, new Texture(texturePath));
	}


//	public void dispose() {
//		for (Map.Entry<String, Texture> entry : textureMap.entrySet()) {
//			entry.getValue().dispose();
//		}
//		textureMap.clear();
//	}

    public static Texture get(String key) {

        return textureMap.get(key);
    }
}
