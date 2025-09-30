package io.github.FinalProject;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen{
	private final Viewport viewport;
	private final SpriteBatch spriteBatch;
    private final TiledMap tiledMap;
    private final OrthogonalTiledMapRenderer renderer;
    private final Player player;
    private final PlayerInteract playerInteract;
    private final OrthographicCamera camera;
    private final CollisionManager collisionManager;
    private final InteractableManager interactableManager;
    private final LootTable lootTable;
    private Sprite ak47;
    private TextureManager textureManager;
    private float stateTime = 0f;

    public GameScreen() {
        new TextureManager();
        new AnimationManager();

        tiledMap = new TmxMapLoader().load("TiledMaps/Testing.tmx");
        renderer = new OrthogonalTiledMapRenderer(tiledMap);
        collisionManager = new CollisionManager(tiledMap);
        interactableManager = new InteractableManager(tiledMap);
        lootTable = new LootTable();

        // Set Camera up for 2d tile map.
        camera = new OrthographicCamera();
        viewport = new FitViewport(1296, 980, camera);
        camera.position.set(viewport.getWorldWidth() / 2f, viewport.getWorldHeight() / 2f, 0);
        camera.position.set(0,0,0);
        camera.zoom = 1f;
        camera.update();

        spriteBatch = new SpriteBatch();
        player = new Player(50, 50, collisionManager);
        playerInteract = new PlayerInteract(interactableManager.getInteractables());

        lootTable.addItem(new LootItem(
            "Silver Necklace",
            MaterialType.SILVER,
            20,
            0.1f,
            2,
            1,
            1,
            0,
            0));
    }

	@Override
	public void show() {
	    System.out.println("GameScreen shown");
	}

	// The render function is basically your while loop.
    @Override
    public void render(float delta) {
        update(delta);

        // Clear screen first
        ScreenUtils.clear(Color.BLACK);

        // Update camera
        camera.update();

        // Render tilemap
        renderer.setView(camera);
        renderer.render();

        // Render your game objects
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        draw();
        spriteBatch.end();
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
        player.update(delta);
        playerInteract.update(player);
    }
	public void draw() {
        player.draw(spriteBatch);
	}
	@Override
	public void dispose() {
	    spriteBatch.dispose();
	}

    public void interact(Player player) {

    }
}
