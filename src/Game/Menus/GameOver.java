package Game.Menus;

import Animations.Animation;
import Utils.Misc.Config;
import Utils.Misc.Counter;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Game Over end screen animation.
 */
public class GameOver implements Animation {
    private final int score;

    /**
     * Instantiates a new Game over.
     *
     * @param score the score
     */
    public GameOver(Counter score) {
        this.score = score.getValue();
    }

    /**
     * Do one frame.
     *
     * @param d the drawSurface from this game's GUI
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.RED);
        d.drawRectangle(Config.BORDER_SIZE, Config.BORDER_SIZE,
                d.getWidth() - 2 * Config.BORDER_SIZE,
                d.getHeight() - 2 * Config.BORDER_SIZE);
        d.drawText(100, d.getHeight() / 2, "GAME OVER", Config.BIG_FONT);
        d.drawText(95, d.getHeight() / 2, "GAME OVER", Config.BIG_FONT);
        d.drawText(300, d.getHeight() / 2 + Config.BIG_FONT / 2,
                "Your score is " + this.score, 28);

    }

    /**
     * Should stop boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean shouldStop() {
        return false;
    }
}
