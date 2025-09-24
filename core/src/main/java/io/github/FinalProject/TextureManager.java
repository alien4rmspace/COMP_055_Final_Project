package io.github.FinalProject;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import java.util.Map;

public class TextureManager {
    // This is a unique class. Only one of this class will exist at all times and
    // can be accessed from anywhere in our project by calling TextureManager.
    // You can think of it as a global Class.
	private static final HashMap<String, Texture> textureMap = new HashMap<>();

    public TextureManager(){
        load("character3", "Characters/Premade_Character_48x48_03.png");

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
