package Animations;

import Game.GameManager.SpriteCollection;
import Utils.Misc.Config;
import Utils.Misc.Counter;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Countdown animation.
 */
public class CountdownAnimation implements Animation {
    private final double numOfSeconds;
    private final int countFrom;
    private final SpriteCollection gameScreen;
    private boolean stop;
    private double secondsRemaining;
    private final Counter currentNum;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom,
                              SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;

        secondsRemaining = this.numOfSeconds / this.countFrom;
        currentNum = new Counter();
        currentNum.increase(countFrom);
    }

    /**
     * Do one frame.
     *
     * @param d the drawSurface from this game's GUI
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);

        //mimicking a shadow effect behind the number for visibility purposes
        d.setColor(Color.BLACK);
        d.drawText(Config.CD_X, d.getHeight() / 2 + Config.CD_SHDW_OFFSET,
                String.valueOf(currentNum.getValue()), Config.CD_SHDW_SIZE);

        d.setColor(Color.WHITE);
        d.drawText(Config.CD_X, d.getHeight() / 2 + Config.CD_Y_OFFSET,
                String.valueOf(currentNum.getValue()), Config.CD_SIZE);

        //subtracting 1/60 to deduct one frame for each call
        this.secondsRemaining -= 1.0 / Config.FPS;

        if (this.secondsRemaining <= 0) {
            currentNum.decrease(1);
            this.secondsRemaining = this.numOfSeconds / this.countFrom;
        }

        if (currentNum.getValue() == 0) {
            this.stop = true;
        }

    }

    /**
     * Should stop boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
