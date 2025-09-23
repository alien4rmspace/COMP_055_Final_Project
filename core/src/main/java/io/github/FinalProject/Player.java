package io.github.FinalProject;

import com.badlogic.gdx.graphics.Texture;

public class Player {
    private Texture texture;
    private float x, y;

    public Player(float x, float y){
        this.texture = TextureManager.get("character3");
        this.x = x;
        this.y = y;
    }

    public void Update(float delta){

    }
}
