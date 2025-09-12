package io.github.FinalProject;

import java.util.HashMap;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen{
	private Viewport viewport;
	private SpriteBatch spriteBatch;
	private Sprite ak47;
	private ShapeRenderer shapeRenderer;
	
	public GameScreen() {
		viewport = new FitViewport(1024, 768);
		
		shapeRenderer = new ShapeRenderer();
		spriteBatch = new SpriteBatch();
		TextureManager.load("AK47", "AK47.png");
		ak47 = new Sprite(TextureManager.get("AK47"));
		ak47.setSize(100,250);
		ak47.setPosition(1, 0);
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
	public void draw() {
		ScreenUtils.clear(Color.BLACK);
		viewport.apply();
		
		spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
		spriteBatch.begin();
		
		float worldWidth = viewport.getWorldWidth();
		float worldHeight = viewport.getWorldHeight();
		
		ak47.draw(spriteBatch);
		
		spriteBatch.end();
		
		shapeRenderer.setProjectionMatrix(spriteBatch.getProjectionMatrix());
		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		shapeRenderer.setColor(Color.RED);
		shapeRenderer.rect(ak47.getX(), ak47.getY(), ak47.getWidth(), ak47.getHeight());
		shapeRenderer.end();
	}
	@Override
	public void dispose() {
	    spriteBatch.dispose();
	}
}
