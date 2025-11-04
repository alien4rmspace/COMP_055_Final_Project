package FinalProject.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import java.io.Serializable;
import java.util.HashMap;

public final class SoundManager {
    private static HashMap<String, Sound> sounds;

    public static void init(){
        sounds = new HashMap<>();

        // Load all footsteps sound.
        for (int i = 1; i <= 21; i++){
            String soundName;
            if (i >= 10){
                soundName = "Steps_dirt-0" + i;
            } else {
                soundName = "Steps_dirt-00" + i;
            }
            sounds.put(soundName, Gdx.audio.newSound(Gdx.files.internal("Sounds/Footsteps/Dirt/" + soundName + ".ogg")));
        }

        sounds.put("damn_son", Gdx.audio.newSound(Gdx.files.internal("sounds/damn_son.mp3")));
        sounds.put("main_menu_soundtrack", Gdx.audio.newSound(Gdx.files.internal("sounds/main_menu_soundtrack.mp3")));
        sounds.put("adventure_soundtrack", Gdx.audio.newSound(Gdx.files.internal("sounds/adventure_soundtrack.mp3")));
        sounds.put("locked_door", Gdx.audio.newSound(Gdx.files.internal("sounds/locked_door.mp3")));
        sounds.put("unlocked_door", Gdx.audio.newSound(Gdx.files.internal("sounds/unlocked_door.mp3")));
        sounds.put("lock_picking", Gdx.audio.newSound(Gdx.files.internal("sounds/lock_picking.mp3")));
        sounds.put("lock_picked", Gdx.audio.newSound(Gdx.files.internal("sounds/lock_picked.mp3")));


        System.out.println("Successfully loaded sounds in SoundManager!");
    }

    public static Sound get(String soundName){
        return sounds.get(soundName);
    }

    public static void play(String soundName){
        Sound sound = sounds.get(soundName);
        if (sound != null){
            sound.stop();
            sound.play();
        } else{
            System.out.println("Sound " + soundName + "could not be found!");
        }
    }

    public static void stop(String soundName){
        Sound sound = sounds.get(soundName);
        if (sound != null){
            sound.stop();
        }
    }

    public static void loop(String soundName){
        Sound sound = sounds.get(soundName);

        if (sound != null){
            sound.loop();
        } else {
            System.out.println("Sound " + soundName + " could not be found!");
        }
    }
}
