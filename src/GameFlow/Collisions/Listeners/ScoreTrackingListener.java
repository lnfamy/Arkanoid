package GameFlow.Collisions.Listeners;

import Utils.Misc.Config;
import Utils.Misc.Counter;
import Sprites.Ball;
import Sprites.Block;

/**
 * The type Score tracking listener.
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(Config.BLOCK_SCORE);
    }
}
