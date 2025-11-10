package FinalProject;

import FinalProject.Loot.Item;
import FinalProject.Managers.TextureManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;


public final class UIUtilities {
    private static final float WINDOW_WIDTH = 1296;
    private static final float WINDOW_HEIGHT = 980;
    private static final BitmapFont font = FontGenerator.getFont(); // or load a custom one
    private static Array<Item> items;

    private static SpriteBatch spriteBatch;

    private UIUtilities(){} // Prevents instantiation.

    public static void init(SpriteBatch spriteBatch){
        items = new Array<>();
        if (UIUtilities.spriteBatch == null){
            UIUtilities.spriteBatch = spriteBatch;
        }
    }

    public static void drawPlayerProgressBar(Texture emptyBar, Texture fillBar, Vector2 position, float progress) {
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

    public static void drawDialogueBox(String dialogue){
        Texture dialogueBox = TextureManager.get("UI-526");
        float dialoguePositionX = WINDOW_WIDTH / 9;
        float dialoguePositionY = -5;
        float boxWidth = dialogueBox.getWidth() * 15;
        float boxHeight = dialogueBox.getHeight() * 6;

        spriteBatch.draw(dialogueBox, dialoguePositionX, dialoguePositionY, boxWidth, boxHeight);

        float textPadding = 90f;
        float dialogueTextureBorder = 4;
        float textX = dialoguePositionX + textPadding + dialogueTextureBorder;
        float textY = dialoguePositionY + boxHeight - textPadding;

        float textAreaWidth = boxWidth - (textPadding * 2f);
        float textAreaHeight = boxHeight - (textPadding * 2f);

        GlyphLayout layout = new GlyphLayout();
        layout.setText(font, dialogue, Color.BLACK, textAreaWidth, Align.left, true);

        font.draw(spriteBatch, layout, textX, textY);
    }

    public static void drawLootSearchBox(Array<Item> items, int revealed, float progress) {
        Vector2 boxPosition = new Vector2();
        Texture searchBox = TextureManager.get("UI-394");
        Texture emptySlot = TextureManager.get("UI-72");
        Texture questionMarkFill = TextureManager.get("UI-1");
        Texture questionMarkEmpty = TextureManager.get("UI-64");

        TextureRegion textureRegion = new TextureRegion(questionMarkFill, 0, 0, questionMarkFill.getWidth(), questionMarkFill.getHeight());


        float searchBoxWidth = 450;
        float searchBoxHeight = 600;
        boxPosition.x = WINDOW_WIDTH - searchBoxWidth - 15;
        boxPosition.y = WINDOW_HEIGHT - searchBoxHeight - 15;

        int emptySlotWidth = 100;
        int emptySlotHeight = 100;

        // Draw box background
        spriteBatch.draw(searchBox, boxPosition.x, boxPosition.y, searchBoxWidth, searchBoxHeight);

        // Draw empty slots INSIDE the box
        int paddingX = 65;
        int paddingY = 187;
        int startX = (int) (boxPosition.x + paddingX);
        int startY = (int) (boxPosition.y + searchBoxHeight - paddingY);

        // Draw rows and columns
        int count = items.size;
        int cols = 3;
        int rows = 1 + items.size / 3;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (count > 0){
                    count--;
                    float slotX = startX + c * (emptySlotWidth + 10);
                    float slotY = startY - r * (emptySlotHeight + 10);
                    spriteBatch.draw(emptySlot, slotX, slotY, emptySlotWidth, emptySlotHeight);
                }
            }
        }

        count = items.size;

        int col = revealed % 3;
        int row = revealed / 3;

        if (revealed < items.size){
            for (int r = 0; r <= row; r++) {
                for (int c = 0; c <= col; c++) {
                    if (count > 0){
                        count--;
                        int slotX = startX + col * (emptySlotWidth + 10);
                        int slotY = startY - row * (emptySlotHeight + 10);
                        spriteBatch.draw(
                            questionMarkEmpty,
                            slotX, slotY,
                            emptySlotWidth, emptySlotHeight,
                            0, 0, questionMarkEmpty.getWidth(), questionMarkEmpty.getHeight(),
                            false, false
                        );

                        int texW = questionMarkFill.getWidth();
                        int texH = questionMarkFill.getHeight();

                        int srcH = Math.round(texH * (1 - progress));
                        int srcY = Math.round(texH * progress); // crop from the top downward

                        int visibleHeight = Math.round(emptySlotHeight * (1 - progress));

                        spriteBatch.draw(
                            questionMarkFill,
                            slotX, slotY,
                            emptySlotWidth, visibleHeight,   // destination size
                            0, srcY, texW, srcH,             // source rectangle (cropping top part)
                            false, false
                        );
                    }
                }
            }
        }
    }

    public static void passContainer(Array<Item> items){
        UIUtilities.items = items;
    }

}
