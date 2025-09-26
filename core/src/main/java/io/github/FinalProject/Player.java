package io.github.FinalProject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player {
    private Animation<TextureRegion> animation;
    private final Animation<TextureRegion> idleAnimation;
    private final Animation<TextureRegion> walkAnimationRight;
    private final Animation<TextureRegion> walkAnimationLeft;
    private final Animation<TextureRegion> walkAnimationUp;
    private final Animation<TextureRegion> walkAnimationDown;

    private CharacterState currentState = CharacterState.IDLE;

    private final Vector2 position;
    private final Vector2 velocity;

    private float speed = 200f;
    private float stateTime = 0;

    public Player(float x, float y){
        this.idleAnimation = AnimationManager.get("player.standing");
        this.walkAnimationRight = AnimationManager.get("player.walking.right");
        this.walkAnimationLeft = AnimationManager.get("player.walking.left");
        this.walkAnimationUp = AnimationManager.get("player.walking.up");
        this.walkAnimationDown = AnimationManager.get("player.walking.down");
        this.animation = idleAnimation;
        this.position = new Vector2(x, y);
        this.velocity = new Vector2(0f, 0f);
    }

    public void draw(SpriteBatch spriteBatch){
        TextureRegion frame = animation.getKeyFrame(stateTime, true);
        spriteBatch.draw(frame, this.position.x, this.position.y);
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

        // Return if not moving
        if (velocity.x == 0 && velocity.y == 0) {
            if (currentState != CharacterState.IDLE) {
                currentState = CharacterState.IDLE;
                animation =  idleAnimation;
            }
            return;
        }

        // Execute code if moving
        updateStateFromVelocity(velocity);
        normalize(velocity);

        // Move the player
        this.position.x += velocity.x * speed * delta;
        this.position.y += velocity.y * speed * delta;



    }

    public void normalize(Vector2 vector2){
        float length = (float)Math.sqrt(vector2.x * vector2.x + vector2.y * vector2.y);

        if (length != 0f){
            vector2.x /= length;
            vector2.y /= length;
        }
        return;
    }

    public void changeAnimation(){

    }

    public void changeState(CharacterState newState){
        this.currentState = newState;
    }

    public CharacterState getState(CharacterState newState){
        return this.currentState;
    }

    public void changeSpeed(float speed){
        this.speed = speed;
    }

    private void updateStateFromVelocity(Vector2 velocity){
        // Velocity check to change state and animation
        float velocityWalkingThreshhold = 0.1f;

        if(this.velocity.y >= velocityWalkingThreshhold){
            // Moving Up
            this.currentState = CharacterState.WALKING;
            this.animation = walkAnimationUp;
        }
        if(this.velocity.y <= velocityWalkingThreshhold){
            // Moving Down
            this.currentState = CharacterState.WALKING;
            this.animation = walkAnimationDown;
        }

        if (this.velocity.x >= velocityWalkingThreshhold){
            // Moving right
            this.currentState = CharacterState.WALKING;
            this.animation = walkAnimationRight;
        }
        if (this.velocity.x <= -velocityWalkingThreshhold){
            // Moving left
            this.currentState = CharacterState.WALKING;
            this.animation = walkAnimationLeft;
        }
    }
}
