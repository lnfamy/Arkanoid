package Utils.Geometry;

import Utils.Misc.Config;

import java.util.List;

/**
 * The type Utilities.Geometry.Line.
 */
public class Line {
    static final double HALF = 2;
    static final int COL = 0, CLOCKWISE = 1, COUNTERCLOCKWISE = -1;
    static final double THRESHOLD = 0.0001;

    private final Point start, end;

    /**
     * Instantiates a new Utilities.Geometry.Line from start and end points.
     *
     * @param start the start point of the line
     * @param end   the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Instantiates a new Utilities.Geometry.Line from start and end coordinates.
     *
     * @param x1 the x component of this line's start point
     * @param y1 the y component of this line's start point
     * @param x2 the x component of this line's end point
     * @param y2 the y component of this line's end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        Point st = new Point(x1, y1);
        Point ed = new Point(x2, y2);

        this.start = st;
        this.end = ed;
    }

    /**
     * Middle (point).
     * Method computes the middle point of the line by calculating the
     * average of the line's start and end points x and y components
     * separately.
     *
     * @return a new point with the newly calculated coordinates for the
     * middle of the line.
     */
    public Point middle() {
        double xMid = (start.getX() + end.getX()) / HALF;
        double yMid = (start.getY() + end.getY()) / HALF;

        return new Point(xMid, yMid);
    }

    /**
     * Start (point).
     * A getter method for this line's start point.
     *
     * @return the start point of this line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * End (point).
     * A getter method for this line's end point.
     *
     * @return the end point of this line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * Utilities.Geometry.Line slope (double).
     * Method calculates the slope of this line by calling Utilities.Geometry. Point
     * class' slope() function with its start and end points.
     *
     * @return the newly calculated slope of this line.
     */
    public double lineSlope() {
        return this.start.slope(this.end);
    }

    /**
     * Y intercept (double).
     * Method calculates the y intercept of this line segment's equivalent
     * line (where a line segment has start and end points and a line is
     * infinitely extending on the x and y axes) using an equation derived
     * from the straight line equation y = mx + b where b is where the line
     * intersects with the y-axis (y intercept for short) and where x is the
     * start point of this line's x, treated as the x coordinate of the y-axis.
     *
     * @return the newly calculated y intercept of this line.
     */
    public double yIntercept() {
        return (start.getY() - this.lineSlope() * start.getX());
    }

    /**
     * On sg (boolean).
     * Method's name is short for "on segment"; given a line and a point from
     * another line that is collinear to the given line, checks if the given
     * point lies on the given line. The line is given by its start and end
     * points.
     *
     * @param start the start point of the line segment
     * @param end   the end point of the line segment
     * @param p     the point to be checked (if it's on the segment or not).
     * @return returns true if the point is on the segment and false otherwise.
     */
    public boolean onSg(Point start, Point end, Point p) {
        double stX = start.getX(), endX = end.getX(), pX = p.getX();
        double stY = start.getY(), endY = end.getY(), pY = p.getY();

        /*
        if point p's x and y coordinates are within the line's start and
        end x and y coordinates, then p is on the given segment
         */

        return (pX <= Math.max(stX, endX) && pX >= Math.min(stX, endX))
                && (pY <= Math.max(stY, endY) && pY >= Math.max(stY, endY));
    }

    /**
     * Is intersecting (boolean).
     * Method checks if this line segment and another given line segment have
     * any intersection at all, utilizing the direction() method to compare
     * the start and end points of each line against each other as ordered
     * triplets and treating the lines as if they're directed (vectors).
     *
     * @param other the other line.
     * @return returns true if the lines intersect at all (whether by one
     * intersection point or infinite intersection points) and false otherwise.
     */
    public boolean isIntersecting(Line other) {
        //naming the start and end points for convenience
        Point p1 = this.start, p2 = this.end;
        Point p3 = other.start, p4 = other.end;

        int dir1 = direction(p1, p2, p3), dir2 = direction(p1, p2, p4);
        int dir3 = direction(p3, p4, p1), dir4 = direction(p3, p4, p2);

        /*
        general case (orientations are different):
        if the vector corresponding to a line segment has two different
        orientations with the other line's start and end points, then the
        line segments intersect with one intersection point.
         */
        if ((dir1 != dir2) && (dir3 != dir4)) {
            return true;
        }

        /*
        special case (collinearity):
        if the orientation between the lines is collinear and one of the lines
        has a point on the other line (checked by onSg), then the line
        segments intersect with infinite intersection points, as one of the
        lines lays either in part or wholly directly on top of the other line.
         */
        return ((dir1 == COL && onSg(p1, p2, p3))
                || (dir2 == COL && onSg(p1, p2, p4)));

    }

    /**
     * Direction (int).
     * Method checks the orientation/direction of an ordered triplet of
     * points in the 2D plane. The orientation is computed using the
     * difference between the slopes of the lines (p1 -> p2) and (p2 -> p3).
     *
     * @param p1 point 1
     * @param p2 point 2
     * @param p3 point 3
     * @return returns 0 for collinear orientation, 1 for clockwise orientation
     * and -1 for counterclockwise orientation
     */
    public int direction(Point p1, Point p2, Point p3) {
        // computing slope(p1 -> p2) - slope(p2 -> p3)
        double deltaDiff = (p2.getY() - p1.getY()) * (p3.getX() - p2.getX())
                - (p2.getX() - p1.getX()) * (p3.getY() - p2.getY());
        /*
        using threshold to compensate for rounding errors with doubles
        (deltaDiff, in this case). if the absolute value of deltaDiff is
        approx. 0, then the slope difference is minimal and the lines'
        orientation is collinear.
        */
        if (Math.abs(deltaDiff) < THRESHOLD) {
            return COL;
        }
        //if deltaDiff is positive; clockwise. otherwise; counterclockwise.
        return (deltaDiff > 0) ? CLOCKWISE : COUNTERCLOCKWISE;
    }

    /**
     * Intersection with (point).
     * Method checks if this line has an intersection with another line, and
     * if so, returns said intersection point.
     *
     * @param other the other point
     * @return returns 'null' if lines do not intersect or if they intersect
     * infinitely. If they intersect once, returns their intersection point.
     */
    public Point intersectionWith(Line other) {
        /*
        if lines do not intersect at all, or if isIntersecting returned true
        and the lines are collinear to each other (and therefore they are
        intersecting infinitely), return null.
         */
        if (!this.isIntersecting(other)) {
            return null;
        } else if (direction(this.start, this.end, other.start) == COL) {
            return null;
        }

        /*
        if one or both of the lines is horizontal or vertical, no need to
        calculate slope.
         */
        if (this.isVertical()) {
            if (other.isHorizontal()) {
                return new Point(this.start.getX(), other.start.getY());
            }
            double m = other.lineSlope();
            return new Point(this.start.getX(),
                    m * this.start.getX() + other.yIntercept());
        }

        if (this.isHorizontal()) {
            if (other.isVertical()) {
                return new Point(other.start.getX(), this.start.getY());
            }
            double m = other.lineSlope();
            return new Point((this.start.getY() - other.yIntercept()) / m,
                    this.start.getY());
        }

        /*
        if we reached here, both lines have computable slopes.
         */
        double m1 = this.lineSlope(), m2 = other.lineSlope();
        double b1 = this.yIntercept(), b2 = other.yIntercept();

        double pX = (b2 - b1) / (m1 - m2);
        double pY = m1 * pX + b1;

        return new Point(pX, pY);
    }

    /**
     * Equals (boolean).
     * Method uses the equalThreshold method from Utilities.Geometry.Point class to
     * compare between the lines' start and end points.
     *
     * @param other the other line to be compared with this line
     * @return returns true if the two lines have the same start and end
     * points or if one line's start is the other's end and vice versa
     */
    public boolean equals(Line other) {
        return (this.start.equalThreshold(other.start)
                && this.end.equalThreshold(other.end)
                || this.start.equalThreshold(other.end)
                && this.end.equalThreshold(other.start));
    }

    /**
     * Closest intersection to start of line point.
     *
     * @param rect the rectangle
     * @return the closest intersection point with the given rectangle to the
     * start of this line. if no such point exists, returns null.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        //getting all intersections of this line with the rectangle
        List<Point> intersections = rect.intersectionPoints(this);
        //if no intersections exist, returns null
        if (intersections.isEmpty()) {
            return null;
        } else {
            int index = this.closestToStartIndex(intersections);
            if (index == Config.ERR_CODE) {
                return null;
            }
            return intersections.get(this.closestToStartIndex(intersections));
        }
    }

    /**
     * Closest to start index int.
     *
     * @param points a list of points
     * @return the index from the given list indicating the point whose
     * distance to this line is minimal. if no such index exists, returns
     * an error code.
     */
    public int closestToStartIndex(List<Point> points) {
        //if there's no elements in the given list, return an error code
        if (points.isEmpty()) {
            return Config.ERR_CODE;
        }
        /*
        initializing min variable to index 0 to provide with an initial
        value to compare with the rest of the calculated distances.
        */
        double min = this.start.distance(points.get(0));
        int minIndex = 0;
        for (int i = 1; i < points.size(); i++) {
            double dist = this.start.distance(points.get(i));
            if (dist < min) {
                min = dist;
                minIndex = i;
            }
        }
        return minIndex;
    }

    /**
     * Is vertical boolean.
     * Method checks if this line is vertical by comparing its start and
     * end point x values using a threshold approach.
     *
     * @return true if this line is vertical, false otherwise.
     */
    public boolean isVertical() {
        double epsilon = 0.0001;
        return (Math.abs(this.start.getX() - this.end.getX()) < epsilon);
    }

    /**
     * Is horizontal boolean.
     * Method checks if this line is horizontal by comparing its start and
     * end point y values using a threshold approach.
     *
     * @return true if this line is horizontal, false otherwise.
     */
    public boolean isHorizontal() {
        double epsilon = 0.0001;
        return (Math.abs(this.start.getY() - this.end.getY()) < epsilon);
    }

    /**
     * On segment boolean.
     * Method checks whether a given point is on this line.
     *
     * @param cPoint the collision point
     * @return true if given point is on this line segment, false otherwise
     */
    public boolean onSegment(Point cPoint) {
        double epsilon = 0.0001;
        /*
        if this line is vertical or horizontal, checks whether given point
        has approximately the same x or y value using a threshold. Otherwise,
        sends this line and the point to onSg, which uses a slope calculation.
         */
        if (this.isVertical()) {
            return Math.abs(cPoint.getX() - this.start.getX()) < epsilon;
        } else if (this.isHorizontal()) {
            return Math.abs(cPoint.getY() - this.start.getY()) < epsilon;
        } else {
            return this.onSg(this.start, this.end, cPoint);
        }
    }
}
