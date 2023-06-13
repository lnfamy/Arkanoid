package Game.Collisions.Listeners;

import Utils.Misc.Counter;
import Game.GameManager.GameLevel;
import Sprites.Ball;
import Sprites.Block;

/**
 * The type Ball remover.
 */
public class BallRemover implements HitListener {
    private final GameLevel game;
    private final Counter remainingBalls = new Counter();

    /**
     * Instantiates a new Ball remover.
     *
     * @param game           the game
     * @param remainingBalls the remaining balls in the game
     */
    public BallRemover(GameLevel game, int remainingBalls) {
        this.game = game;
        this.remainingBalls.increase(remainingBalls);

    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        remainingBalls.decrease(1);
    }
}
