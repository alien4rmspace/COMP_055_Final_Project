package io.github.FinalProject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Arrays;
import java.util.HashMap;

public class AnimationManager {
    // Refer to texture manager for similar documention on static final usage.
    private static final HashMap<String, Animation<TextureRegion>> animations = new HashMap<>();

    public AnimationManager(){
        // Parse our sprite sheet in a 48 x 96 section starting at row 2, there are 6 animation frames in that row.
        TextureRegion[] animationFrames = parseRegion(TextureManager.get("character3"), 48, 96, 2, 0 , 6);
        load("player.walking.right", new Animation<>(0.1f, animationFrames));
        animationFrames = parseRegion(TextureManager.get("character3"), 48, 96, 2, 7, 12);
        load("player.walking.up", new Animation<>(0.1f, animationFrames));
        animationFrames = parseRegion(TextureManager.get("character3"), 48, 96, 2, 13 , 18);
        load("player.walking.left", new Animation<>(0.1f, animationFrames));
        animationFrames = parseRegion(TextureManager.get("character3"), 48, 96, 2, 19 , 24);
        load("player.walking.down", new Animation<>(0.1f, animationFrames));


        animationFrames = parseRegion(TextureManager.get("character3"), 48, 96, 1, 0, 6);
        load("player.standing.right", new Animation<>(0.1f, animationFrames));
        animationFrames = parseRegion(TextureManager.get("character3"), 48, 96, 1, 7, 12);
        load("player.standing.up", new Animation<>(0.1f, animationFrames));
        animationFrames = parseRegion(TextureManager.get("character3"), 48, 96, 1, 13, 18);
        load("player.standing.left", new Animation<>(0.1f, animationFrames));
        animationFrames = parseRegion(TextureManager.get("character3"), 48, 96, 1, 19, 24);
        load("player.standing.down", new Animation<>(0.1f, animationFrames));

        System.out.println("Successfully loaded animations in AnimationManager");
    }

    private static void load(String key, Animation<TextureRegion> animation){
        animations.put(key, animation);
    }

    public static TextureRegion[] parseRegion(Texture texture, int frameWidth, int frameHeight, int row, int startingColumn, int endingColumn){
        TextureRegion[][] tmp = TextureRegion.split(texture, frameWidth, frameHeight);

        TextureRegion[] frames = new TextureRegion[endingColumn - startingColumn];

        for (int i = 0; i < frames.length; i++){
            frames[i] = tmp[row][startingColumn + i];
        }
        return frames;
    }

    public static Animation<TextureRegion> get(String key) {
        return animations.get(key);
    }
}
