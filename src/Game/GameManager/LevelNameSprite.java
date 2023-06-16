package Game.GameManager;

import Sprites.Sprite;
import Utils.Geometry.Rectangle;
import Utils.Misc.Config;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Level name sprite.
 */
public class LevelNameSprite implements Sprite {
    private String name;
    private Rectangle rect;

    /**
     * Instantiates a new Level name sprite.
     *
     * @param name the name
     * @param rect the rect
     */
    public LevelNameSprite(String name, Rectangle rect) {
        this.name = name;
        this.rect = rect;
    }

    /**
     * Draw on.
     * Draws the sprite to the screen using the given drawSurface.
     *
     * @param d the drawSurface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);

        int x = (int) (rect.getUpperLeft().getX() + (rect.getWidth() * 3 / 4)
                - Config.SCORE_X_OFFSET);
        int y = (int) rect.getLowerLeft().getY() - Config.SCORE_Y_OFFSET;
        d.drawText(x, y, "Level Name: " + this.name,
                Config.SCORE_FONT_SIZE);
    }

    /**
     * Time passed.
     * Notifies the sprite that time has passed
     */
    @Override
    public void timePassed() {

    }

    /**
     * Add to game.
     *
     * @param game the game
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
