package FinalProject.Screens;

import FinalProject.Main;
import FinalProject.Managers.*;
import FinalProject.FootstepSFX;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;



public class LoadingScreen implements Screen {
    private final Main game;

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;

    private Texture bgTexture;
    private Texture barFrameTexture;
    private Texture barFillTexture;

    private boolean initializedAssets = false;
    private boolean finishedLoading = false;

    public LoadingScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, (float)Gdx.graphics.getWidth(), (float)Gdx.graphics.getHeight());
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
        this.font.getData().setScale(1.4F);

        AssetLoader.init();
        AssetLoader.loadLoadingScreenAssets();
        this.bgTexture = AssetLoader.get("Loading Screen BG.png", Texture.class);
        this.barFrameTexture = AssetLoader.get("Loading Bar.png", Texture.class);
        this.barFillTexture = AssetLoader.get("Loading Fill.png", Texture.class);

        AssetLoader.queueAssets();
        System.out.println("Queued all assets for loading...");
    }



    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float progress = AssetLoader.getProgress();
        System.out.println(progress);

        if (!finishedLoading) {
            finishedLoading = AssetLoader.update();
        }

        batch.begin();
        batch.draw(bgTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        float barWidth = (float)Gdx.graphics.getWidth() * 0.5f;
        float barHeight = 80.0f;
        float x = ((float)Gdx.graphics.getWidth() - barWidth) / 2.0f;
        float y = ((float)Gdx.graphics.getHeight() - barHeight) / 2.0f;


        batch.draw(barFrameTexture, x, y);
        batch.draw(barFillTexture, x, y, barWidth * progress, barHeight);

        String text = "Loading... " + (int) (progress * 100) + "%";
        GlyphLayout layout = new GlyphLayout(font, text);
        float textWidth = layout.width;
        font.draw(batch, text, x + barWidth / 2f - (textWidth / 2f), y + barHeight + 100f);

        batch.end();

        if (finishedLoading && !initializedAssets) {
            initializedAssets = true;
            initializeManagers();
            game.showGameScreen();
        }
    }

    private void initializeManagers() {
        System.out.println("All assets loaded!");

        TextureManager.init();
        AnimationManager.init();
        LootTableManager.init();
        SoundManager.init();
        FootstepSFX.init();


        System.out.println("Managers initialized successfully.");
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        bgTexture.dispose();
        barFrameTexture.dispose();
        barFillTexture.dispose();
    }
}
