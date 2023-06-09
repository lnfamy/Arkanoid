package Game.Collisions.CollisionManager;

import Utils.Geometry.Point;
import Utils.Geometry.Rectangle;
import Utils.Geometry.Velocity;
import Sprites.Ball;

/**
 * The interface GameFlow.Collisions.CollisionManager.Collidable.
 */
public interface Collidable {
    /**
     * Gets collision rectangle.
     *
     * @return the collision rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Hit velocity.
     *
     * @param hitter          the colliding ball
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity of hitter
     * @return the new velocity of the ball after the collision
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

}


