package FinalProject.Screens;

import FinalProject.FootstepSFX;
import FinalProject.Main;
import FinalProject.Managers.LootTableManager;
import FinalProject.Managers.SoundManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import FinalProject.Managers.TextureManager;
import FinalProject.Managers.AnimationManager;



public class LoadingScreen implements Screen{

    private final Main game;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private BitmapFont font;

    public LoadingScreen(Main game){
        this.game=game;
        loadAssets();

        SoundManager.play("damn_son");
    }

    @Override
    public void show(){
        camera = new OrthographicCamera();
        camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(2f);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        float barWidth = Gdx.graphics.getWidth() * 0.6f;
        float barHeight = 40f;
        float x = (Gdx.graphics.getWidth() - barWidth) / 2f;
        float y = (Gdx.graphics.getHeight() - barHeight) / 2f;

        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(x, y, barWidth, barHeight);

        shapeRenderer.setColor(Color.LIME);
        shapeRenderer.rect(x, y, barWidth * progress, barHeight);

        shapeRenderer.end();

        batch.begin();
        font.draw(batch, "Loading...", x + barWidth / 2f - 60, y + barHeight + 50);
        batch.end();
    }

    private float progress = 0f;
    private float simulatedSpeed = 0.5f;

    private boolean assetsLoaded = false;
    private boolean switching = false;

    private void update(float delta) {
        if (!assetsLoaded) {
            progress += simulatedSpeed * delta;
            if (progress >= 1f) {
                progress = 1f;
                assetsLoaded = true;
            }
        } else if (!switching) {
            switching = true;
            Gdx.app.postRunnable(() -> {
                game.showGameScreen();
                dispose();
            });
        }
    }

    private void loadAssets() {
        TextureManager.init();
        AnimationManager.init();
        LootTableManager.init();
        SoundManager.init();
        FootstepSFX.init();
        System.out.println("Assets successfully loaded in LoadingScreen.");
    }


    @Override
    public void resize(int width, int height){
        camera.setToOrtho(false,width,height);
    }

    @Override
    public void pause(){}

    @Override
    public void resume(){}

    @Override
    public void hide(){}

    @Override
    public void dispose(){
        shapeRenderer.dispose();
        batch.dispose();
        font.dispose();
    }

}
