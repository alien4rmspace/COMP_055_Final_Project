package FinalProject;

import FinalProject.Managers.SoundManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public final class FootstepSFX {
    private static Array<Sound> sounds;
    private static int stepIndex;
    private static float stepTimer;
    private static final float BASE_INTERVAL = 54f;

    public static void init(){
        sounds = new Array<Sound>();
        stepIndex = 0;
        stepTimer = 0;

        // Putting all of our footstep sounds into  sounds.
        for (int i = 1; i <= 21; i++) {
            String soundName;

            if (i >= 10) {
                soundName = "Steps_dirt-0" + i;
            } else {
                soundName = "Steps_dirt-00" + i;
            }
            sounds.add(SoundManager.get(soundName));
        }

        System.out.println("Successfully loaded FootstepSFX.");
    }

    public static void playWalk(float speed, float delta){
        if (stepTimer < BASE_INTERVAL / speed){
            stepTimer += delta;
            return;
        }
        stepTimer = 0;

        String soundName;
        float volume = MathUtils.random(0.45f, 0.65f); // random volume
        float pitch  = MathUtils.random(0.93f, 1.07f); // random pitch
        float pan    = MathUtils.random(-0.15f, 0.15f); // slight stereo shift

        if (stepIndex >= 10) {
            soundName = "Steps_dirt-0" + stepIndex;
        } else {
            soundName = "Steps_dirt-00" + stepIndex;
        }

        sounds.get(stepIndex).play(volume, pitch, pan);

        if (stepIndex >= 20){
            stepIndex = 1;
        } else {
            stepIndex++;
        }
    }
}
