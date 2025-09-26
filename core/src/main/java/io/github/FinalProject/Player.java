package io.github.FinalProject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player {
    private Texture texture;
    private Animation<TextureRegion> animation;

    private CharacterState currentState = CharacterState.IDLE;

    private float x, y;
    private float speed = 200f;
    private float stateTime = 0;

    public Player(float x, float y){
        this.texture = TextureManager.get("character3");
        this.animation = AnimationManager.get("player.walking");
        this.x = x;
        this.y = y;
    }

    public void draw(SpriteBatch spriteBatch){
        TextureRegion currentFrame = this.animation.getKeyFrame(this.stateTime, true);

        spriteBatch.draw(currentFrame, this.x, this.y);
    }

    public void update(float delta){
        this.stateTime += delta;

        if(UserInputs.isUpPressed()){
            this.y += this.speed * delta;
        }
        if(UserInputs.isDownPressed()){
            this.y -= this.speed * delta;
        }
        if(UserInputs.isLeftPressed()){
            this.x -= this.speed * delta;
        }
        if(UserInputs.isRightPressed()){
            this.x += this.speed * delta;
        }
    }

    public void changeState(CharacterState newState){
        this.currentState = newState;
    }

    public CharacterState getState(CharacterState newState){
        return this.currentState;
    }
}
