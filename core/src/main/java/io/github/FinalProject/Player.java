package io.github.FinalProject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Player {
    private final CollisionManager collisionManager;
    private final Rectangle collisionRectangle;

    private Animation<TextureRegion> animation;
    private final Animation<TextureRegion> idleAnimationUp;
    private final Animation<TextureRegion> idleAnimationRight;
    private final Animation<TextureRegion> idleAnimationLeft;
    private final Animation<TextureRegion> idleAnimationDown;
    private final Animation<TextureRegion> walkAnimationRight;
    private final Animation<TextureRegion> walkAnimationLeft;
    private final Animation<TextureRegion> walkAnimationUp;
    private final Animation<TextureRegion> walkAnimationDown;

    private final Vector2 position;
    private final Vector2 velocity;
    private final Vector2 lastDirection;
    private final Vector2 knockback;

    private CharacterState currentState;
    private float speed = 200f;
    private float stateTime = 0;

    public Player(float x, float y, CollisionManager collisionManager) {
        this.collisionManager = collisionManager;
        this.collisionRectangle = new Rectangle(x, y, 38, 42); // Soz for the magic numbers.

        this.idleAnimationUp = AnimationManager.get("player.standing.up");
        this.idleAnimationRight = AnimationManager.get("player.standing.right");
        this.idleAnimationLeft = AnimationManager.get("player.standing.left");
        this.idleAnimationDown = AnimationManager.get("player.standing.down");
        this.walkAnimationRight = AnimationManager.get("player.walking.right");
        this.walkAnimationLeft = AnimationManager.get("player.walking.left");
        this.walkAnimationUp = AnimationManager.get("player.walking.up");
        this.walkAnimationDown = AnimationManager.get("player.walking.down");

        this.animation = idleAnimationDown;

        this.position = new Vector2(x, y);
        this.velocity = new Vector2(0f, 0f);
        this.lastDirection = new Vector2(0f,0f);
        this.knockback = new Vector2(0f,0f);
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
            this.lastDirection.x = velocity.x;
            this.lastDirection.y = velocity.y;
        }
        if(UserInputs.isDownPressed()){
            this.velocity.y = -1;
            this.lastDirection.x = velocity.x;
            this.lastDirection.y = velocity.y;
        }
        if(UserInputs.isLeftPressed()){
            this.velocity.x = -1;
            this.lastDirection.x = velocity.x;
            this.lastDirection.y = velocity.y;
        }
        if(UserInputs.isRightPressed()){
            this.velocity.x = 1;
            this.lastDirection.x = velocity.x;
            this.lastDirection.y = velocity.y;
        }

        // Return if not moving
        if (velocity.x == 0 && velocity.y == 0) {
            if (this.currentState != CharacterState.IDLE){
                updatePlayerToIdle();
            }
            return;
        }

        // Execute code if trying to move
        normalize(velocity);
        updatePlayerFromVelocity(velocity);

        if (collisionManager.isCollide(this.collisionRectangle)){
            float knockbackStrength = 5f;
            knockback.set(lastDirection).scl(-knockbackStrength);
        }

        // Move the player
        move(velocity, speed, delta);
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

    public void move(Vector2 direction, float speed, float delta) {
        float maxMoveSpeed = 200f; // units per second

        Vector2 movement = new Vector2(direction).scl(speed).add(knockback).scl(delta);

        if (movement.len() > maxMoveSpeed * delta) {
            movement.setLength(maxMoveSpeed * delta);
        }

        // Save old position
        float oldX = position.x;
        float oldY = position.y;

        // Move
        position.add(movement);
        collisionRectangle.setPosition(position.x, position.y);

        // Check collision
        if (collisionManager.isCollide(collisionRectangle)) {
            // revert position if collided
            position.set(oldX, oldY);
            collisionRectangle.setPosition(position.x, position.y);

            // apply knockback
            knockback.set(direction).scl(-50f); // tweak knockback strength
        }

        // Decay knockback
        knockback.lerp(Vector2.Zero, 10f * delta);
    }




    private void updatePlayerToIdle(){
        if (this.currentState != CharacterState.IDLE) {
            this.currentState = CharacterState.IDLE;

            normalize(velocity);
            updateIdleFromVelocity(lastDirection);
        }
    }
    private void updatePlayerFromVelocity(Vector2 velocity){
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

    private void updateIdleFromVelocity(Vector2 velocity){
        // Velocity check to change state and animation
        float velocityWalkingThreshhold = 0.1f;

        if(this.lastDirection.y >= velocityWalkingThreshhold){
            // Idle Up
            this.currentState = CharacterState.IDLE;
            this.animation = idleAnimationUp;
        }
        if(this.lastDirection.y <= velocityWalkingThreshhold){
            // Idle Down
            this.currentState = CharacterState.IDLE;
            this.animation = idleAnimationDown;
        }

        if (this.lastDirection.x >= velocityWalkingThreshhold){
            // Idle right
            this.currentState = CharacterState.IDLE;
            this.animation = idleAnimationRight;
        }
        if (this.lastDirection.x <= -velocityWalkingThreshhold){
            // Idle left
            this.currentState = CharacterState.IDLE;
            this.animation = idleAnimationLeft;
        }
    }

    public Rectangle getBounds() {
        return collisionRectangle;
    }
}
