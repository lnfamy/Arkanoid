package Game.Collisions.Listeners;

import Game.GameManager.GameLevel;
import Utils.Misc.Counter;
import Sprites.Ball;
import Sprites.Block;

/**
 * The type Block remover.
 */
public class BlockRemover implements HitListener {
    private final GameLevel game;
    private final Counter remainingBlocks;

    /**
     * @param game          the reference to the game object
     * @param removedBlocks a counter to keep track of remaining blocks in game
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        /*
        remove hit listener from block to be removed, remove hit block from
        the game, and decrease the counter for remaining blocks in the game.
         */
        beingHit.removeFromGame(game);
        beingHit.removeHitListener(this);
        remainingBlocks.decrease(1);
        game.getLevelInfo().setRemainingBlocks(remainingBlocks.getValue());
    }
}
