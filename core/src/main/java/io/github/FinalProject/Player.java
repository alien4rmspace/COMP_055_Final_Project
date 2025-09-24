package io.github.FinalProject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player {
    private Texture texture;
    private float x, y;
    private Animation<TextureRegion> animation;

    public Player(float x, float y){
        this.texture = TextureManager.get("character3");
        this.x = x;
        this.y = y;
        this.animation = AnimationManager.get("player.walking");
    }

    public void draw(float stateTime, SpriteBatch spriteBatch){
        TextureRegion currentFrame = AnimationManager.get("player.walking").getKeyFrame(stateTime, true);

        spriteBatch.draw(currentFrame, 50, 50);
    }

    public void update(float delta){

    }
}
