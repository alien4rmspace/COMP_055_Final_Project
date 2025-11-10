package FinalProject;

import FinalProject.Managers.SoundManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public final class FootstepSFX {
    private static Array<Sound> sounds;
    private static int stepIndex;
    private static float stepTimer;
    private static final float BASE_INTERVAL = 60f;

    public static void init() {
        sounds = new Array<Sound>();
        stepIndex = 0;
        stepTimer = 0;

        // Putting all of our footstep sounds into sounds.
        for (int i = 1; i <= 21; i++) {
            String soundName;

            if (i >= 10) {
                soundName = "Steps_dirt-0" + i;
            } else {
                soundName = "Steps_dirt-00" + i;
            }

            Sound sound = SoundManager.get(soundName);
            if (sound != null) {
                sounds.add(sound);
            } else {
                System.out.println("⚠️ Missing footstep sound: " + soundName);
            }
        }

        System.out.println("Successfully loaded FootstepSFX (" + sounds.size + " sounds).");
    }

    public static void playWalk(float speed, float delta) {
        if (sounds == null || sounds.isEmpty()) {
            System.out.println("⚠️ FootstepSFX.playWalk() called before initialization.");
            return;
        }

        if (stepTimer < BASE_INTERVAL / speed) {
            stepTimer += delta;
            return;
        }
        stepTimer = 0;

        float volume = MathUtils.random(0.75f, 0.95f); // random volume
        float pitch = MathUtils.random(0.93f, 1.07f);  // random pitch
        float pan = MathUtils.random(-0.15f, 0.15f);   // slight stereo shift

        if (stepIndex < 0 || stepIndex >= sounds.size) {
            stepIndex = 0;
        }

        sounds.get(stepIndex).play(volume, pitch, pan);

        if (stepIndex >= sounds.size - 1) {
            stepIndex = 0;
        } else {
            stepIndex++;
        }
    }
}
