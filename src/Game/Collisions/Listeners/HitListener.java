package Game.Collisions.Listeners;

import Sprites.Ball;
import Sprites.Block;

/**
 * The interface Hit listener.
 */
public interface HitListener {
    /**
     * Hit event.
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the being hit
     * @param hitter   the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
