package Animations;

import GameFlow.GameManager.SpriteCollection;
import Utils.Misc.Config;
import Utils.Misc.Counter;
import biuoop.DrawSurface;

import java.awt.*;

/**
 * The type Countdown animation.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private int countUntil = 1;
    private double secondsRemaining;
    private Counter currentNum;

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

        //mimicking a shadow behind the number for visibility purposes
        d.setColor(Color.BLACK);
        d.drawText(330, d.getHeight() / 2 + 105,
                String.valueOf(currentNum.getValue()), 270);

        d.setColor(Color.WHITE);
        d.drawText(330, d.getHeight() / 2 + 100,
                String.valueOf(currentNum.getValue()), 250);

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
