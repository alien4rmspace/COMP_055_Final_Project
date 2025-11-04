package FinalProject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public final class UIUtilities {
    private UIUtilities(){}

    public static void drawPlayerProgressBar(SpriteBatch spriteBatch, Texture emptyBar, Texture fillBar, Vector2 position, float progress) {
        float emptyOffsetX = -12;
        float emptyOffsetY = 65;
        float fillOffsetX = emptyOffsetX + 9.75f;
        float fillOffsetY = emptyOffsetY + 12.5f;
        float fillBarWidth = fillBar.getWidth();
        float fillBarHeight = fillBar.getHeight();

        // Draw empty bar.
        spriteBatch.draw(emptyBar, position.x + emptyOffsetX, position.y + emptyOffsetY);

        // Draw fill bar.
        spriteBatch.draw(fillBar,
            position.x + fillOffsetX, position.y + fillOffsetY,
            0, 0,
            (int)(fillBarWidth * progress),
            (int)fillBarHeight);
    }
}
