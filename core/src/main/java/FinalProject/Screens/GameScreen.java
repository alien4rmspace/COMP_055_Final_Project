package FinalProject.Screens;

import FinalProject.Bullet;
import FinalProject.Interactables.Characters.Npc;
import FinalProject.Systems.DialogueSystem;
import FinalProject.Systems.LockPickSystem;
import FinalProject.Managers.*;
import FinalProject.Player.Player;
import FinalProject.Player.PlayerInteract;
import FinalProject.Player.PlayerStatsIO;
import FinalProject.Systems.LootSearchSystem;
import FinalProject.UIUtilities;
import com.badlogic.gdx.Game;
import FinalProject.Player.UserInputs;
import FinalProject.Player.InventoryUI;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import FinalProject.Interactables.Interactable;

public class GameScreen implements Screen{
    private final Game game;
    private GameScreen previousGameScreen;
    private final Viewport viewport;
    private final SpriteBatch spriteBatch;
    private final TiledMap tiledMap;
    private final OrthogonalTiledMapRenderer renderer;
    private final Player player;
    private final PlayerInteract playerInteract;
    private final OrthographicCamera camera;
    private final CollisionManager collisionManager;
    private final NpcManager npcManager;
    private final PlayerSpawnManager playerSpawnManager;
    private final InteractableManager interactableManager;
    private final InventoryUI inventoryUI;
    private float stateTime = 0f;

    private Array<Bullet> bullets;

    public GameScreen(Game game, TiledMap tiledMap) {
        this.game = game;
        this.tiledMap = tiledMap;
        this.spriteBatch = new SpriteBatch();
        this.renderer = new OrthogonalTiledMapRenderer(tiledMap);
        this.interactableManager = new InteractableManager(tiledMap);
        this.npcManager = new NpcManager(tiledMap);
        this.playerSpawnManager = new PlayerSpawnManager(tiledMap);
        this.collisionManager = new CollisionManager(tiledMap);
        this.playerInteract = new PlayerInteract(interactableManager.getInteractables());
        this.bullets = new Array<>();

        // Set Camera up for 2d tile map.
        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(1280, 960, camera);
        this.camera.position.set(viewport.getWorldWidth() / 2f, viewport.getWorldHeight() / 2f, 0);
        this.camera.position.set(0,0,0);
        this.camera.zoom = 1f;
        camera.update();

        this.player = new Player(playerSpawnManager.getPlayerSpawnPosition(), this, collisionManager);
        collisionManager.addDynamicCollidable(player);

        for (Npc npc : npcManager.getNpcs().values()){
            collisionManager.addDynamicCollidable(npc);
            interactableManager.addInteractable(npc);
        }

        inventoryUI = new InventoryUI(player.getInventory());    }

    @Override
    public void show() {
        SoundManager.play("adventure_soundtrack");

        UIUtilities.init(spriteBatch);

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
        System.out.println("Game disposed");
    }
    public void resume() {

    }
    public void hide() {

    }
    public void update(float delta){
        // delta is a built-in function in libgdx which grabs the time in between each execution.
        // Required to make animations in sync between different computer systems due to hardware speed inconsistency.
        stateTime += delta;
        player.update(delta);
        npcManager.update(delta);
        DialogueSystem.update(delta);
        LockPickSystem.update(delta);
        LootSearchSystem.update(delta);

        for (Bullet bullet : bullets){
            bullet.update(delta);
        }

        if (player.isMoving()){
            DialogueSystem.close();
            LockPickSystem.close();
        }

        if (UserInputs.isInteractPressed()){
            playerInteract.update(player);
        }

        if (UserInputs.isLeftMouseButtonPressed()){
            bullets.add(new Bullet(TextureManager.get("pizza"), player.getPosition(), player.getLastDirection(), 200f));
        }

        if (UserInputs.isInventoryTogglePressed()) {
            System.out.println("I key pressed");
            inventoryUI.toggle();
            System.out.println("Inventory visible: " + inventoryUI.isVisible());
        }
    }
    public void draw() {
        for (Interactable interactable : interactableManager.getInteractables()) {
            interactable.draw(spriteBatch);
        }
        npcManager.draw(spriteBatch);
        player.draw(spriteBatch);
        LockPickSystem.draw(spriteBatch);
        DialogueSystem.draw(spriteBatch);
        LootSearchSystem.draw(spriteBatch);

        for (Bullet bullet : bullets){
            bullet.draw(spriteBatch);
        }
        inventoryUI.draw(spriteBatch);
    }
    @Override
    public void dispose() {
        spriteBatch.dispose();
        inventoryUI.dispose();
        PlayerStatsIO.save(this.player.getStats());
    }

    public void addPreviousMap(GameScreen gameScreen) {
        this.previousGameScreen = gameScreen;
    }

    public void changeMap(TiledMap tiledMap, GameScreen prevGameScreen) {
        GameScreen gameScreen = new GameScreen(this.game, tiledMap);
        gameScreen.addPreviousMap(prevGameScreen);
        game.setScreen(gameScreen);
    }

    public void backToWorld() {
        game.setScreen(previousGameScreen);
    }
}
