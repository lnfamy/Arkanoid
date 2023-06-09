package Game.GameManager;

import Game.Collisions.CollisionManager.Collidable;
import Game.Collisions.CollisionManager.CollisionInfo;
import Utils.Geometry.Line;
import Utils.Geometry.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * The type GameLevel environment.
 */
public class GameEnvironment {
    private final java.util.List<Collidable> collidables;

    /**
     * Instantiates a new GameFlow.GameManager.GameLevel environment.
     * Creates a new empty array list of collidable objects. (chose array
     * list because array lists are better for storage and reading rather
     * than manipulating data)
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Add collidable.
     * Method adds a collidable to the collidable list of the gameEnvironment.
     *
     * @param c the collidable object.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Remove collidable.
     * Removes a collidable object from the game by deleting it from the
     * collidable objects list of the GameEnvironment object.
     *
     * @param c the collidable object to be added
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * Gets closest collision.
     *
     * @param trajectory the trajectory line
     * @return the closest collision that occurred with this line. returns
     * null if no collision occurred.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        int numCollisions = 0;
        int index = 0;
        while (index < this.collidables.size()) {
            if (trajectory.closestIntersectionToStartOfLine(
                    collidables.get(index).getCollisionRectangle()) != null) {
                numCollisions++;
            }
            index++;
        }
        //if no collisions occurred
        if (numCollisions == 0) {
            return null;
        }
        /*
        if we got here, that means collision(s) did happen. Now it's time to
        collect the information about the collision closest to the start.
        */
        Collidable[] collisionObjects = new Collidable[numCollisions];
        List<Point> collisionPoints = new ArrayList<>(numCollisions);

        int itrIndex = 0, collIndex = 0;
        while (itrIndex < this.collidables.size()) {
            Point pt = trajectory.closestIntersectionToStartOfLine(
                    this.collidables.get(itrIndex).getCollisionRectangle());
            if (pt != null) {
                collisionPoints.add(pt);
                collisionObjects[collIndex] = this.collidables.get(itrIndex);
                collIndex++;
            }
            itrIndex++;
        }

        int closestIndex = trajectory.closestToStartIndex(collisionPoints);
        return new CollisionInfo(collisionPoints.get(closestIndex),
                collisionObjects[closestIndex]);
    }
}
