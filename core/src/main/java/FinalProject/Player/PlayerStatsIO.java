package FinalProject.Player;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public final class PlayerStatsIO {
    private static final String PATH = "playerstats.json";

    public static void save(PlayerStats stats) {
        Json json = new Json();
        json.setUsePrototypes(false);
        json.setOutputType(JsonWriter.OutputType.json);

        FileHandle file = Gdx.files.local(PATH);
        String data = json.prettyPrint(json.toJson(stats));
        file.writeString(data, false);

        Gdx.app.log("SaveLoad", "Saved to: " + file.file().getAbsolutePath());
    }

    public static PlayerStats load() {
        Json json = new Json();
        FileHandle file = Gdx.files.local(PATH);
        if (!file.exists()) return new PlayerStats();
        try {
            json.setOutputType(JsonWriter.OutputType.json);
            System.out.println("Last save was found and loaded");
            return json.fromJson(PlayerStats.class, file.readString());
        } catch (Exception e) {
            Gdx.app.error("SaveLoad", "Failed to parse save, using defaults", e);
            return new PlayerStats();
        }
    }
}
