package Game.Collisions.CollisionManager;

import Utils.Geometry.Point;

/**
 * The type Collision info. Holds information about the collision point and
 * the collision object of a collision.
 */
public class CollisionInfo {
    private final Point collisionPoint;
    private final Collidable collisionObject;

    /**
     * Instantiates a new Collision info.
     *
     * @param collisionPoint  the collision point
     * @param collisionObject the collision object
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Gets collision point.
     *
     * @return the collision point
     */
    public Point getCollisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Gets collision object.
     *
     * @return the collision object
     */
    public Collidable getCollisionObject() {
        return this.collisionObject;
    }
}
