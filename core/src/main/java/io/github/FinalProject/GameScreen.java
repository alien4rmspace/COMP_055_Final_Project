package io.github.FinalProject;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen{
	private Viewport viewport;
	private SpriteBatch spriteBatch;
	private Sprite ak47;
    private TextureManager textureManager;
    private float stateTime = 0f;
    private Player player;

	public GameScreen() {
        new TextureManager();
        new AnimationManager();
		viewport = new FitViewport(1024, 768);

		spriteBatch = new SpriteBatch();

        player = new Player(50, 50);

	}

	@Override
	public void show() {
	    System.out.println("GameScreen shown");
	}

	// The render function is basically your while loop.
	public void render(float delta) {
        this.update(delta);
        this.draw();
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
    public void update(float delta){
        // delta is a built in function in libgdx which grabs the time in between each execution.
        // Required to make animations in sync between different computer systems due to hardware speed inconsistency.
        stateTime += delta;
    }
	public void draw() {
        ScreenUtils.clear(Color.BLACK);

        spriteBatch.begin();
        player.draw(stateTime, spriteBatch);
        spriteBatch.end();
	}
	@Override
	public void dispose() {
	    spriteBatch.dispose();
	}
}
