package FinalProject.Managers;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;
import java.util.Objects;

public class SoundManager {
    private static HashMap<String, Music> musics;
    private static HashMap<String, Sound> sounds;

    public static void init(){
        musics = new HashMap<>();
        sounds = new HashMap<>();

        var assetManager = AssetLoader.getManager();

        for (int i = 1; i <= 21; i++){
            String soundName;
            String path;
            if (i >= 10){
                soundName = "Steps_dirt-0" + i;
                path = "Sounds/Footsteps/Dirt/Steps_dirt-0" + i + ".ogg";
            } else {
                soundName = "Steps_dirt-00" + i;
                path = "Sounds/Footsteps/Dirt/Steps_dirt-00" + i + ".ogg";
            }
            if (assetManager.isLoaded(path, Sound.class)) {
                sounds.put(soundName, assetManager.get(path, Sound.class));
            }
        }

        musics.put("main_menu_soundtrack", assetManager.get("Sounds/main_menu_soundtrack.mp3", Music.class));
        musics.put("adventure_soundtrack", assetManager.get("Sounds/adventure_soundtrack.mp3", Music.class));

        sounds.put("damn_son", assetManager.get("Sounds/damn_son.mp3", Sound.class));
        sounds.put("locked_door", assetManager.get("Sounds/locked_door.mp3", Sound.class));
        sounds.put("unlocked_door", assetManager.get("Sounds/unlocked_door.mp3", Sound.class));
        sounds.put("lock_picking", assetManager.get("Sounds/lock_picking.mp3", Sound.class));
        sounds.put("lock_picked", assetManager.get("Sounds/lock_picked.mp3", Sound.class));

        System.out.println("Successfully loaded sounds in SoundManager!");
    }

    public static Sound get(String soundName){
        return sounds.get(soundName);
    }

    public static void play(String soundName){
        for (String name : sounds.keySet()){
            if (Objects.equals(name, soundName)){
                Sound sound = sounds.get(name);
                if (sound != null){
                    sound.stop();
                    sound.play();
                } else{
                    System.out.println("Sound " + soundName + "could not be found!");
                }
            }
        }

        for (String name : musics.keySet()){
            if (Objects.equals(name, soundName)){
                Music music = musics.get(name);
                if (music != null){
                    music.setLooping(true);
                    music.play();
                } else {
                    System.out.println("Music " + name + "could not be found!");
                }
            }
        }
    }

    public static void stopAll(){
        for (Sound sound : sounds.values()){
            if (!sound.equals(sounds.get("damn_son"))){
                sound.stop();
            }
        }
    }

    public static void stopAll(String soundName){
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
