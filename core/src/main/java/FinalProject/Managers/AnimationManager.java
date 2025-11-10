package FinalProject.Managers;

import FinalProject.Enums.CharacterId;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

public class AnimationManager {
    // Refer to texture manager for similar documention on static final usage.
    private static final HashMap<String, Animation<TextureRegion>> animations = new HashMap<>();

    public static void init(){
        TextureRegion[] animationFrames;
        String name;
        float animationFrameDuration = 0.22f;
        // Parse our sprite sheet in a 48 x 96 section starting at row 2, there are 6 animation frames in that row.
        animationFrames = parseRegion(TextureManager.get("character3"), 48, 96, 2, 0 , 6);
        animations.put("player.walking.right", new Animation<>(animationFrameDuration, animationFrames));
        animationFrames = parseRegion(TextureManager.get("character3"), 48, 96, 2, 7, 12);
        animations.put("player.walking.up", new Animation<>(animationFrameDuration, animationFrames));
        animationFrames = parseRegion(TextureManager.get("character3"), 48, 96, 2, 13 , 18);
        animations.put("player.walking.left", new Animation<>(animationFrameDuration, animationFrames));
        animationFrames = parseRegion(TextureManager.get("character3"), 48, 96, 2, 19 , 24);
        animations.put("player.walking.down", new Animation<>(animationFrameDuration, animationFrames));

        // Idle Animation
        animationFrames = parseRegion(TextureManager.get("character3"), 48, 96, 1, 0, 6);
        animations.put("player.standing.right", new Animation<>(animationFrameDuration, animationFrames));
        animationFrames = parseRegion(TextureManager.get("character3"), 48, 96, 1, 7, 12);
        animations.put("player.standing.up", new Animation<>(animationFrameDuration, animationFrames));
        animationFrames = parseRegion(TextureManager.get("character3"), 48, 96, 1, 13, 18);
        animations.put("player.standing.left", new Animation<>(animationFrameDuration, animationFrames));
        animationFrames = parseRegion(TextureManager.get("character3"), 48, 96, 1, 19, 24);
        animations.put("player.standing.down", new Animation<>(animationFrameDuration, animationFrames));

        // Amanda's Animation
        animationFrames = parseRegion(TextureManager.get("Amanda"), 48, 96, 2, 0 , 6);
        animations.put("Amanda.walking.right", new Animation<>(animationFrameDuration, animationFrames));
        animationFrames = parseRegion(TextureManager.get("Amanda"), 48, 96, 2, 7, 12);
        animations.put("Amanda.walking.up", new Animation<>(animationFrameDuration, animationFrames));
        animationFrames = parseRegion(TextureManager.get("Amanda"), 48, 96, 2, 13 , 18);
        animations.put("Amanda.walking.left", new Animation<>(animationFrameDuration, animationFrames));
        animationFrames = parseRegion(TextureManager.get("Amanda"), 48, 96, 2, 19 , 24);
        animations.put("Amanda.walking.down", new Animation<>(animationFrameDuration, animationFrames));
        // Idle Animation
        animationFrames = parseRegion(TextureManager.get("Amanda"), 48, 96, 1, 0, 6);
        animations.put("Amanda.standing.right", new Animation<>(animationFrameDuration, animationFrames));
        animationFrames = parseRegion(TextureManager.get("Amanda"), 48, 96, 1, 7, 12);
        animations.put("Amanda.standing.up", new Animation<>(animationFrameDuration, animationFrames));
        animationFrames = parseRegion(TextureManager.get("Amanda"), 48, 96, 1, 13, 18);
        animations.put("Amanda.standing.left", new Animation<>(animationFrameDuration, animationFrames));
        animationFrames = parseRegion(TextureManager.get("Amanda"), 48, 96, 1, 19, 24);
        animations.put("Amanda.standing.down", new Animation<>(animationFrameDuration, animationFrames));

        // Mr.Moncler's Animation
        name = CharacterId.MR_MONCLER.name;
        animationFrames = parseRegion(TextureManager.get(name), 48, 96, 2, 0 , 6);
        animations.put(name + ".walking.right", new Animation<>(animationFrameDuration, animationFrames));
        animationFrames = parseRegion(TextureManager.get(name), 48, 96, 2, 7, 12);
        animations.put(name + ".walking.up", new Animation<>(animationFrameDuration, animationFrames));
        animationFrames = parseRegion(TextureManager.get(name), 48, 96, 2, 13 , 18);
        animations.put(name + ".walking.left", new Animation<>(animationFrameDuration, animationFrames));
        animationFrames = parseRegion(TextureManager.get(name), 48, 96, 2, 19 , 24);
        animations.put(name + ".walking.down", new Animation<>(animationFrameDuration, animationFrames));
        // Idle Animation
        animationFrames = parseRegion(TextureManager.get(name), 48, 96, 1, 0, 6);
        animations.put(name + ".standing.right", new Animation<>(animationFrameDuration, animationFrames));
        animationFrames = parseRegion(TextureManager.get(name), 48, 96, 1, 7, 12);
        animations.put(name + ".standing.up", new Animation<>(animationFrameDuration, animationFrames));
        animationFrames = parseRegion(TextureManager.get(name), 48, 96, 1, 13, 18);
        animations.put(name + ".standing.left", new Animation<>(animationFrameDuration, animationFrames));
        animationFrames = parseRegion(TextureManager.get(name), 48, 96, 1, 19, 24);
        animations.put(name + ".standing.down", new Animation<>(animationFrameDuration, animationFrames));

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
