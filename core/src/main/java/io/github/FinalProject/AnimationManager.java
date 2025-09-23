package io.github.FinalProject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

public class AnimationManager {
    // Refer to texture manager for similar documention on static final usage.
    private static final HashMap<String, Animation<TextureRegion>> animations = new HashMap<>();
    private TextureRegion[] animationFrames;

    public AnimationManager(){
        animationFrames = parseRegion(TextureManager.get("character3"), 48, 96, 1, 6);
        load("player.walking", new Animation<>(0.1f, animationFrames));

        System.out.println("Successfully loaded animations in AnimationManager");
    }

    private static void load(String key, Animation<TextureRegion> animation){
        animations.put(key, animation);
    }

    public static TextureRegion[] parseRegion(Texture texture, int frameWidth, int frameHeight, int row, int frameCount){
        TextureRegion[][] tmp = TextureRegion.split(texture, 48, 96);
        TextureRegion[] frames = new TextureRegion[frameCount];

        for (int i = 0; i < frameCount; i++){
            frames[i] = tmp[row][i];
        }
        return frames;
    }

    public static Animation<TextureRegion> get(String key) {
        return animations.get(key);
    }
}
