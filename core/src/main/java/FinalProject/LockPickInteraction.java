package FinalProject;

import FinalProject.Interactables.Door;
import FinalProject.Interactables.Pickable;
import FinalProject.Managers.SoundManager;
import FinalProject.Managers.TextureManager;
import FinalProject.Player.Player;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public final class LockPickInteraction {
    private static Player player;
    private static Pickable pickable;
    private static float progress;
    private static float progressMultiplier;
    private static float timer;
    private static boolean isActive;

    private static LockPickInteraction lockPickInteraction;

    public void reset() {
        progress = 0f;
        timer = 0f;
        isActive = false;
    }

    public static void start(Player player, Pickable pickable) {
        LockPickInteraction.player = player;
        LockPickInteraction.pickable = pickable;
        timer = 0f;
        progress = 0f;
        isActive = true;
        progressMultiplier =  player.getStats().getDexterity() / 10 + 1;

        SoundManager.play("lock_picking");
    }

    public static void draw(SpriteBatch spriteBatch) {
        if (!isActive){
            return;
        }
        float currentProgress = MathUtils.clamp(progress / pickable.getLockLevel(), 0f, 1f);
        UIUtilities.drawPlayerProgressBar(spriteBatch, TextureManager.get("UI-561"), TextureManager.get("UI-575"), player.getPosition(), currentProgress);
    }

    public static void update(float delta) {
        if (!isActive) return;

        timer += delta;

        // Example: every 1 second, increase progress a bit
        if (timer >= 1f) {
            progress += MathUtils.random(0.2f, 0.5f) * progressMultiplier;
            timer = 0f;
        }

        if (progress >= pickable.getLockLevel()) {
            finish();
        }
    }

    private static void finish() {
        isActive = false;
        SoundManager.stop("lock_picking");
        pickable.unlock();
        System.out.println("Lock picked!");
    }

    public static boolean isActive() { return isActive; }
}

