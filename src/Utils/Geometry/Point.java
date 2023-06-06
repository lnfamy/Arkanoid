package Utils.Geometry;

import Utils.Misc.Config;

/**
 * The type Utilities.Geometry.Point.
 */
public class Point {
    static final double SQUARED = 2;
    private final double x, y;

    /**
     * Instantiates a new Utilities.Geometry.Point.
     *
     * @param x the x component of the new point instance.
     * @param y the y component of the new point instance.
     */
// constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Distance (double).
     * Method calculates the distance from this instance of point to another
     * given point using formula for the distance between two points:
     * distance = sqrt((x2 – x1)² + (y2 – y1)²))
     *
     * @param other the other point.
     * @return returns the computed distance between the two points. returns
     * an error code if the given point does not exist (is a null object).
     */
    public double distance(Point other) {
        if (other == null) {
            return Config.ERR_CODE;
        }
        return Math.sqrt(Math.pow((this.x - other.getX()), SQUARED)
                + Math.pow((this.y - other.getY()), SQUARED));
    }

    /**
     * Equals (boolean).
     *
     * @param other the other point
     * @return returns true if the points are exactly equal, false otherwise.
     */
    public boolean equals(Point other) {
        return this.x == other.getX() && this.y == other.getY();
    }

    /**
     * equalThreshold (boolean).
     * Method checks if this instance of point is equal to another given
     * point utilizing a threshold parameter of 0.0001. The check is
     * performed by calculating the difference between this point's x and y
     * coordinates and the other point's x and y coordinates (xDelta and
     * yDelta), respectively, and comparing each difference against the
     * threshold. The threshold is used to accommodate for rounding errors
     * associated with storage of double parameters (such as the x and y
     * coordinates of a point, in this case).
     *
     * @param other the other point to compare this instance with.
     * @return returns true if both xDelta and yDelta are smaller than the
     * threshold, and false otherwise.
     */
    public boolean equalThreshold(Point other) {
        double threshold = 0.0001;

        double xDelta = Math.abs(this.getX() - other.getX());
        double yDelta = Math.abs(this.getY() - other.getY());

        return xDelta < threshold && yDelta < threshold;
    }

    /**
     * Slope (double).
     * Method returns the slope calculates between this instance of point and
     * another given point using the formula: slope = (y1 – y2)/(x1 – x2)
     *
     * @param other the other point
     * @return returns the newly calculates slope between the two points.
     */
    public double slope(Point other) {
        return ((this.getY() - other.getY()) / (this.getX() - other.getX()));
    }

    /**
     * Get x (double).
     *
     * @return the x component of this instance of Utilities.Geometry.Point.
     */
// Return the x and y values of this point
    public double getX() {
        return x;
    }

    /**
     * Get y (double).
     *
     * @return the y component of this instance of Utilities.Geometry.Point.
     */
    public double getY() {
        return y;
    }

}
