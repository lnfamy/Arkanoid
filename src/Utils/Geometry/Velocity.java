package Utils.Geometry;

/**
 * The type Utilities.Geometry.Velocity.
 */
public class Velocity {
    private final double dx, dy;

    /**
     * Instantiates a new Utilities.Geometry.Velocity.
     *
     * @param dx x component of the new velocity instance.
     * @param dy y component of the new velocity instance.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * From angle and speed (Utilities.Geometry.Velocity).
     * Method computes dx and dy components from given angle and speed using
     * trigonometric calculations, and returns a new Utilities.Geometry.Velocity with
     * said dx, dy.
     *
     * @param angle the angle
     * @param speed the speed
     * @return returns a new Utilities.Geometry.Velocity with the calculated dx and dy by
     * calling the Utilities.Geometry.Velocity constructor and returning the created
     * object.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double rad = Math.toRadians(angle);
        double dx = Math.sin(rad) * speed;
        double dy = Math.cos(rad) * speed;

        return new Velocity(dx, dy);
    }

    /**
     * Apply to point (Utilities.Geometry.Point).
     * Method adds the x and y components of a given point p respectively to
     * this velocity's dx and dy components and returns a new point by the
     * newly computed coordinates.
     *
     * @param p the point to apply the velocity to.
     * @return returns a new point after applying the dx and dy components of
     * this velocity to the given point p (using the Utilities.Geometry.Point class
     * constructor).
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * Get dx (double).
     *
     * @return the dx component of this instance of velocity.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Get dy (double).
     *
     * @return the dy component of this instance of velocity.
     */
    public double getDy() {
        return this.dy;
    }
}
