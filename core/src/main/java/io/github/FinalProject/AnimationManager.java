package io.github.FinalProject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

public class AnimationManager {
    // Refer to texture manager for similar documention on static final usage.
    private static final HashMap<String, Animation<TextureRegion>> animations = new HashMap<>();

    public AnimationManager(){
        // Parse our sprite sheet in a 48 x 96 section starting at row 2, there are 6 animation frames in that row.
        TextureRegion[] animationFrames = parseRegion(TextureManager.get("character3"), 48, 96, 2, 6);
        load("player.walking", new Animation<>(0.1f, animationFrames));

        animationFrames = parseRegion(TextureManager.get("character3"), 48, 96, 1, 6);
        load("player.standing", new Animation<>(0.1f, animationFrames));

        System.out.println("Successfully loaded animations in AnimationManager");
    }

    private static void load(String key, Animation<TextureRegion> animation){
        animations.put(key, animation);
    }

    public static TextureRegion[] parseRegion(Texture texture, int frameWidth, int frameHeight, int row, int frameCount){
        int regionWidth = 48;   // Width of the region we want to show on screen.
        int regionHeight = 96;  // Height of the region we want to show on screen.

        TextureRegion[][] tmp = TextureRegion.split(texture, regionWidth, regionHeight);
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
