package Game.Menus;

import Animations.Animation;
import Utils.Misc.Counter;
import biuoop.DrawSurface;

/**
 * Game Over end screen animation.
 */
public class GameOver implements Animation {
    private int score;

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
        d.drawText(15, d.getHeight() / 2, "Game Over. Your score is "
                + this.score, 36);
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
