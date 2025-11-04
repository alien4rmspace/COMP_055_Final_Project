package FinalProject.Player;

import FinalProject.CharacterState;
import FinalProject.FootstepSFX;
import FinalProject.Interactables.Door;
import FinalProject.LockPickInteraction;
import FinalProject.Managers.AnimationManager;
import FinalProject.Managers.CollisionManager;
import FinalProject.Managers.TextureManager;
import FinalProject.UIUtilities;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Player {
    private final CollisionManager collisionManager;
    private final Rectangle collisionRectangle;
    private final Inventory inventory;
    private final PlayerStats stats;

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

    private CharacterState currentState;
    private float speed = 150f;
    private float stateTime = 0;

    public Player(float x, float y, CollisionManager collisionManager) {
        this.collisionManager = collisionManager;
        this.collisionRectangle = new Rectangle(x, y, 47, 42); // Soz for the magic numbers.
        this.stats = PlayerStatsIO.load();
        this.inventory = new Inventory(20);

        String characterModel = "Amanda";   // Easy way to change characterModel.
        this.idleAnimationUp = AnimationManager.get(characterModel + ".standing.up");
        this.idleAnimationRight = AnimationManager.get(characterModel + ".standing.right");
        this.idleAnimationLeft = AnimationManager.get(characterModel + ".standing.left");
        this.idleAnimationDown = AnimationManager.get(characterModel + ".standing.down");
        this.walkAnimationRight = AnimationManager.get(characterModel + ".walking.right");
        this.walkAnimationLeft = AnimationManager.get(characterModel + ".walking.left");
        this.walkAnimationUp = AnimationManager.get(characterModel + ".walking.up");
        this.walkAnimationDown = AnimationManager.get(characterModel + ".walking.down");

        this.animation = idleAnimationDown;

        this.position = new Vector2(x, y);
        this.velocity = new Vector2(0f, 0f);
        this.lastDirection = new Vector2(0f,0f);
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
            this.velocity.y += 1;
        }
        if(UserInputs.isDownPressed()){
            this.velocity.y += -1;
        }
        if(UserInputs.isLeftPressed()){
            this.velocity.x += -1;
        }
        if(UserInputs.isRightPressed()){
            this.velocity.x += 1;
        }

        // Return if player not moving
        if (velocity.isZero()){
            if (this.currentState != CharacterState.IDLE){
                this.currentState = CharacterState.IDLE;
                updateIdleAnimation();
            }
            return;
        }

        // Execute code if trying to move
        this.currentState = CharacterState.WALKING;
        this.lastDirection.set(this.velocity.x, this.velocity.y);   //Lets our animation know which way player is facing.
        updateMovementAnimation(); //Updates which animations to play based on player's velocity.

        // Move the player
        normalize(velocity);
        move(velocity, speed, delta);

        // Play foosteps
        FootstepSFX.playWalk(speed, delta);
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

        Vector2 movement = new Vector2(direction).scl(speed).scl(delta);

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
        }
    }

    // Velocity check to change state and animation
    private void updateMovementAnimation(){
        if(this.velocity.y > 0){
            // Moving Up
            this.animation = walkAnimationUp;
        }
        else if(this.velocity.y < 0){
            // Moving Down
            this.animation = walkAnimationDown;
        }

        if (this.velocity.x > 0){
            // Moving right
            this.animation = walkAnimationRight;
        }
        else if (this.velocity.x < -0){
            // Moving left
            this.animation = walkAnimationLeft;
        }
    }

    private void updateIdleAnimation(){
        // Velocity check to change state and animation

        if(this.lastDirection.y > 0){
            // Idle Up
            this.animation = idleAnimationUp;
        }
        else if(this.lastDirection.y < 0){
            // Idle Down
            this.animation = idleAnimationDown;
        }

        if (this.lastDirection.x > 0){
            // Idle right
            this.animation = idleAnimationRight;
        }
        else if (this.lastDirection.x < 0){
            // Idle left
            this.animation = idleAnimationLeft;
        }
    }

    public Vector2 getPosition(){
        return this.position;
    }

    public Rectangle getBounds() {
        return collisionRectangle;
    }
    public Inventory getInventory() {
        return inventory;
    }
    public PlayerStats getStats() {
        return stats;
    }
}
