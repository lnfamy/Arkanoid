package Utils.Geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Rectangle.
 */
public class Rectangle {
    static final int LINES = 4;
    static final int LEFT = 0, RIGHT = 1, UP = 2, DOWN = 3,
            NO_COLL_CODE = -2, UPPER_L = 10, UPPER_R = 20, LOWER_R = 30,
            LOWER_L = 40;
    private final Point upperLeft;
    private Point upperRight;
    private Point lowerLeft;
    private Point lowerRight;
    private Line left, right, upper, lower;
    private final Line[] lines;
    private final double width;
    private final double height;

    /**
     * Instantiates a new Rectangle.
     *
     * @param upperLeft the upper left
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.width = width;
        this.height = height;
        this.upperLeft = upperLeft;

        setLinesAndCorners(upperLeft, width, height);
        lines = new Line[]{this.left, this.right, this.upper, this.lower};
    }

    /**
     * Instantiates a new Rectangle.
     *
     * @param x      the x
     * @param y      the y
     * @param width  the width of the rectangle
     * @param height the height of the rectangle
     */
    public Rectangle(double x, double y, double width, double height) {
        this.upperLeft = new Point(x, y);
        this.width = width;
        this.height = height;

        setLinesAndCorners(this.upperLeft, width, height);
        lines = new Line[]{this.left, this.right, this.upper, this.lower};
    }

    private void setLinesAndCorners(Point upperL, double w, double h) {
        double y = upperL.getY(), x = upperL.getX();
        this.upperRight = new Point(x + w, y);
        this.lowerRight = new Point(x + w, y + h);
        this.lowerLeft = new Point(x, y + h);

        this.left = new Line(upperL, this.lowerLeft);
        this.upper = new Line(upperL, this.upperRight);
        this.lower = new Line(this.lowerLeft, this.lowerRight);
        this.right = new Line(this.upperRight, this.lowerRight);
    }

    /**
     * Intersection points java . util . list.
     *
     * @param line the line to be checked for intersections
     * @return an arrayList of all the intersections between this
     * rectangle's sides and a given line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> intersections = new ArrayList<>(LINES);
        for (Line value : this.lines) {
            if (value.isIntersecting(line)) {
                intersections.add(value.intersectionWith(line));
            }
        }
        return intersections;
    }

    /**
     * Corner collision int.
     * Method checks if a given collision point is on one of this rectangle's
     * corners.
     *
     * @param cPoint the collision point
     * @param v      the velocity at which the collision happened.
     * @return returns an integer value corresponding to each of the four
     * corners of the rectangle, or returns an error code (NO_COLL_CODE) if
     * there was no corner collision.
     */
    public int cornerCollision(Point cPoint, Velocity v) {
        double dx = v.getDx(), dy = v.getDy();
        if (this.getLeft().onSegment(cPoint) && dx > 0) {
            if (this.getUpper().onSegment(cPoint) && dy > 0) {
                return UPPER_L;
            } else if (this.getLower().onSegment(cPoint) && dy < 0) {
                return LOWER_L;
            }
        } else if (this.getRight().onSegment(cPoint) && dx < 0) {
            if (this.getUpper().onSegment(cPoint) && dy > 0) {
                return UPPER_R;
            } else if (this.getLower().onSegment(cPoint) && dy < 0) {
                return LOWER_R;
            }
        }
        //returns no collision code if no corner collisions happened
        return NO_COLL_CODE;
    }

    /**
     * Which line collided int.
     *
     * @param cPoint the collision point
     * @param v      the velocity at which the collision happened.
     * @return an integer value corresponding to which line the given
     * collision point is on.
     */
    public int whichLineCollided(Point cPoint, Velocity v) {
        double dx = v.getDx(), dy = v.getDy();

        int cornerColl = this.cornerCollision(cPoint, v);
        if (cornerColl != NO_COLL_CODE) {
            return cornerColl;
        }

        if (this.getLeft().onSegment(cPoint) && dx > 0) {
            return LEFT;
        }

        if (this.getRight().onSegment(cPoint) && dx < 0) {
            return RIGHT;
        }

        /*
        if our particle has collided with the upper line of the collision
        rectangle, that must mean it was going down towards it, therefore the
         dy aspect of its velocity must've been positive (positive y-axis
         velocity = item going down on screen)
         */
        if (this.getUpper().onSegment(cPoint) && dy > 0) {
            return UP;
        }

        if (this.getLower().onSegment(cPoint) && dy < 0) {
            return DOWN;
        }

        return NO_COLL_CODE;
    }

    /**
     * Gets width.
     *
     * @return the width of this rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets height.
     *
     * @return the height of this rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets left.
     *
     * @return the left line
     */
    public Line getLeft() {
        return this.left;
    }

    /**
     * Gets right.
     *
     * @return the right line
     */
    public Line getRight() {
        return this.right;
    }

    /**
     * Gets lower.
     *
     * @return the lower line
     */
    public Line getLower() {
        return this.lower;
    }

    /**
     * Gets upper.
     *
     * @return the upper line
     */
    public Line getUpper() {
        return this.upper;
    }

    /**
     * Gets upper left.
     *
     * @return the upper left corner point
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Gets upper right.
     *
     * @return the upper right corner point
     */
    public Point getUpperRight() {
        return this.upperRight;
    }

    /**
     * Gets lower left.
     *
     * @return the lower left corner point
     */
    public Point getLowerLeft() {
        return this.lowerLeft;
    }

    /**
     * Gets lower right.
     *
     * @return the lower right corner point
     */
    public Point getLowerRight() {
        return this.lowerRight;
    }

}
