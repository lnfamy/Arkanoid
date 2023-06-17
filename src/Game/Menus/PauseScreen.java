package Game.Menus;

import Animations.Animation;
import Utils.Misc.Config;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Pause screen.
 */
public class PauseScreen implements Animation {
    /**
     * Do one frame.
     *
     * @param d the drawSurface from this game's GUI
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.GRAY);
        d.drawRectangle(Config.BORDER_SIZE, Config.BORDER_SIZE,
                d.getWidth() - 2 * Config.BORDER_SIZE,
                d.getHeight() - 2 * Config.BORDER_SIZE);
        d.setColor(Color.WHITE);
        d.drawText(200, d.getHeight() / 2, "PAUSED", Config.BIG_FONT);
        d.drawText(250, d.getHeight() / 2 + Config.BIG_FONT / 2,
                "press space to continue", 28);
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
