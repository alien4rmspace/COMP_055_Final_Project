package io.github.FinalProject;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import java.util.Map;

public class TextureManager {
	// Static makes it so only one object can be created and it's shared (Global).
	// Use TextureManager. to call any of the functions in this class.
	private static final HashMap<String, Texture> textureMap = new HashMap<>();
	
	public static void load(String key, String texture) {
		textureMap.put(key, new Texture(texture));
	}
	
	public static Texture get(String key) {
		return textureMap.get(key);
	}
	
	public void dispose() {
		for (Map.Entry<String, Texture> entry : textureMap.entrySet()) {
			entry.getValue().dispose();
		}
		textureMap.clear();
	}
}
