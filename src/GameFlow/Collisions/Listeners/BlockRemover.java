package GameFlow.Collisions.Listeners;

import Utils.Misc.Counter;
import GameFlow.GameManager.Game;
import Sprites.Ball;
import Sprites.Block;

/**
 * The type Block remover.
 */
public class BlockRemover implements HitListener {
    private final Game game;
    private final Counter remainingBlocks;

    /**
     * @param game          the reference to the game object
     * @param removedBlocks a counter to keep track of remaining blocks in game
     */
    public BlockRemover(Game game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        /*
        remove hit listener from block to be removed, remove hit block from
        the game, and decrease the counter for remaining blocks in the game.
         */
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.game);
        remainingBlocks.decrease(1);
    }
}
