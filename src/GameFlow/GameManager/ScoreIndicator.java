package GameFlow.GameManager;

import Utils.Geometry.Rectangle;
import Sprites.Sprite;
import Utils.Misc.Config;
import Utils.Misc.Counter;
import biuoop.DrawSurface;

import java.awt.Color;


/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {
    private final Counter score;
    private final Rectangle rect;

    /**
     * Instantiates a new Score indicator.
     *
     * @param score the score counter.
     * @param rect  the rectangle of the score sprite (for drawing purposes).
     */
    public ScoreIndicator(Counter score, Rectangle rect) {
        this.score = score;
        this.rect = rect;
    }

    /**
     * Draw on.
     * Draws the score sprite to the screen using the given drawSurface.
     *
     * @param d the drawSurface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Config.SCORE_BORDER_CLR);
        d.fillRectangle((int) rect.getUpperLeft().getX(),
                (int) rect.getUpperLeft().getY(), (int) rect.getWidth(),
                (int) rect.getHeight());

        d.setColor(Color.BLACK);

        int x = (int) (rect.getUpperLeft().getX() + (rect.getWidth() / 2)
                - Config.SCORE_X_OFFSET);
        int y = (int) rect.getLowerLeft().getY() - Config.SCORE_Y_OFFSET;
        d.drawText(x, y, "Score: " + this.score.getValue(),
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
     * Adds this score indicator sprite to the game.
     *
     * @param game the game
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }
}
