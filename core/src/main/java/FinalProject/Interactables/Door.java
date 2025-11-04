package FinalProject.Interactables;

import FinalProject.LockPickInteraction;
import FinalProject.Managers.SoundManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import FinalProject.Player.Player;

public class Door implements Interactable, Pickable {
    private Rectangle rectangle;
    private boolean isLocked;
    private float lockLevel;

    public Door(Rectangle rectangle){
        this.rectangle = rectangle;
        this.lockLevel = 3f;

        if (Math.random() > 0.75){
            this.isLocked = false;
        } else {
            this.isLocked = true;
        }
    }

    @Override
    public boolean canInteract(Player player) {
        Rectangle expanded = new Rectangle(player.getBounds());

        int expandAmount = 15;
        expanded.setSize(expanded.width + expandAmount, expanded.height + expandAmount); // Grow by 15 px each way
        expanded.setCenter(player.getBounds().x + player.getBounds().width / 2, player.getBounds().y + player.getBounds().height / 2);  // Account for rectangle growing not from the center.

        return expanded.overlaps(this.rectangle);
    }
    @Override
    public void interact(Player player) {
        if (isLocked) {
            SoundManager.play("locked_door");
            LockPickInteraction.start(player, this);
        } else {
            SoundManager.play("unlocked_door");
        }
    }
    @Override
    public void draw(SpriteBatch spriteBatch){

    }

    @Override
    public float getLockLevel(){
        return lockLevel;
    }

    @Override
    public Rectangle getRectangle() {
        return this.rectangle;
    }

    public void unlock(){
        this.isLocked = false;
        SoundManager.play("lock_picked");}
}
