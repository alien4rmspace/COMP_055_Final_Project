package io.github.FinalProject;

import java.util.HashMap;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen{
	private Viewport viewport;
	private SpriteBatch spriteBatch;
	private static HashMap<String, Texture> textures = new HashMap<>();
	private Sprite ak47;
	
	public GameScreen() {
		viewport = new FitViewport(800, 600);
		
		spriteBatch = new SpriteBatch();
		Texture texture = new Texture("AK47.png");
		textures.put("AK47", texture);
		ak47 = new Sprite(textures.get("AK47"));
		ak47.setSize(300,600);
		ak47.setPosition((viewport.getWorldWidth() - ak47.getWidth()) /2,
		(viewport.getWorldHeight() - ak47.getHeight()) / 2);
	}
	
	@Override
	public void show() {
	    System.out.println("GameScreen shown");
	}
	
	// The render function is basically your while loop.
	public void render(float delta) {
		draw();
	}
	public void resize(int width, int height) {
		viewport.update(width, height, true);
	}
	public void pause() {
		
	}
	public void resume() {
		
	}
	public void hide() {
		
	}
	@Override
	public void dispose() {
	    spriteBatch.dispose();
	    for (Texture t : textures.values()) {
	        t.dispose();
	    }
	}
	public void draw() {
		ScreenUtils.clear(Color.BLACK);
		viewport.apply();
		
		spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
		spriteBatch.begin();
		
		float worldWidth = viewport.getWorldWidth();
		float worldHeight = viewport.getWorldHeight();
		
		ak47.draw(spriteBatch);
		
		spriteBatch.end();
	}
}
