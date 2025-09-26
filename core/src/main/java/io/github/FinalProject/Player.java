package io.github.FinalProject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player {
    private Texture texture;
    private Animation<TextureRegion> animation;

    private CharacterState currentState = CharacterState.IDLE;

    private Vector2 position;
    private Vector2 velocity;

    private float speed = 200f;
    private float stateTime = 0;

    public Player(float x, float y){
        this.texture = TextureManager.get("character3");
        this.animation = AnimationManager.get("player.standing");
        this.position = new Vector2(x, y);
        this.velocity = new Vector2(0f, 0f);
    }

    public void draw(SpriteBatch spriteBatch){
        TextureRegion currentFrame = this.animation.getKeyFrame(this.stateTime, true);

        spriteBatch.draw(currentFrame, this.position.x, this.position.y);
    }

    public void update(float delta){
        this.stateTime += delta;
        this.velocity.x = 0;
        this.velocity.y = 0;

        if(UserInputs.isUpPressed()){
            this.velocity.y = 1;
        }
        if(UserInputs.isDownPressed()){
            this.velocity.y = -1;
        }
        if(UserInputs.isLeftPressed()){
            this.velocity.x = -1;
        }
        if(UserInputs.isRightPressed()){
            this.velocity.x = 1;
        }

        if (velocity.x != 0 || velocity.y != 0) {
            normalize(velocity);

            this.position.x += velocity.x * speed * delta;
            this.position.y += velocity.y * speed * delta;
        }
    }


    public void normalize(Vector2 vector2){
        float length = (float)Math.sqrt(vector2.x * vector2.x + vector2.y * vector2.y);

        if (length != 0f){
            vector2.x /= length;
            vector2.y /= length;
        }
        return;
    }

    public void changeState(CharacterState newState){
        this.currentState = newState;
    }

    public CharacterState getState(CharacterState newState){
        return this.currentState;
    }
}
