package Game.Menus;

import Animations.Animation;
import Utils.Misc.Counter;
import biuoop.DrawSurface;

/**
 * Win end screen animation.
 */
public class YouWin implements Animation {
    private int score;

    /**
     * Instantiates a new You win.
     *
     * @param score the score
     */
    public YouWin(Counter score) {
        this.score = score.getValue();
    }

    /**
     * Do one frame.
     *
     * @param d the drawSurface from this game's GUI
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(20, d.getHeight() / 2, "You Win! Your score is "
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
