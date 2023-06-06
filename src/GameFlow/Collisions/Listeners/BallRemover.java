package GameFlow.Collisions.Listeners;

import Utils.Misc.Counter;
import GameFlow.GameManager.Game;
import Sprites.Ball;
import Sprites.Block;

/**
 * The type Ball remover.
 */
public class BallRemover implements HitListener {
    private final Game game;
    private final Counter remainingBalls;

    /**
     * Instantiates a new Ball remover.
     *
     * @param game           the game
     * @param remainingBalls the remaining balls in the game
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        remainingBalls.decrease(1);
    }
}
