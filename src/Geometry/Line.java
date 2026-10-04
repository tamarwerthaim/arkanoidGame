package Geometry;

import java.util.List;

/**
 * Represents a line segment in a 2D coordinate system defined by two points.
 * Provides functionality for geometric calculations and line relationships.
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * Constructs a line segment from two points.
     *
     * @param start the starting point of the line
     * @param end   the ending point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a line segment from the coordinates of two points.
     *
     * @param x1 the x-coordinate of the starting point
     * @param y1 the y-coordinate of the starting point
     * @param x2 the x-coordinate of the ending point
     * @param y2 the y-coordinate of the ending point
     */
    public Line(double x1, double y1, double x2, double y2) {
        Point p1 = new Point(x1, y1);
        Point p2 = new Point(x2, y2);
        this.start = p1;
        this.end = p2;
    }

    /**
     * Checks if two double values are approximately equal.
     *
     * @param a first double value
     * @param b second double value
     * @return true if the absolute difference between values is less than the comparison threshold
     */
    public boolean doubleEquals(double a, double b) {
        return Math.abs(a - b) < Point.COMPARISON_THRESHOLD;
    }

    /**
     * Calculates the length of the line segment.
     *
     * @return the length of the line
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Finds the middle point of the line segment.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        double x = (start.getX() + end.getX()) / 2;
        double y = (start.getY() + end.getY()) / 2;
        return new Point(x, y);
    }

    /**
     * Gets the starting point of the line.
     *
     * @return the start point
     */
    public Point start() {
        return this.start;
    }

    /**
     * Gets the ending point of the line.
     *
     * @return the end point
     */
    public Point end() {
        return this.end;
    }

    /**
     * Finds the minimum x-coordinate of the line endpoints.
     *
     * @return the minimal x-coordinate
     */
    public double minX() {
        return Math.min(this.start.getX(), this.end.getX());
    }

    /**
     * Finds the maximum x-coordinate of the line endpoints.
     *
     * @return the maximum x-coordinate
     */
    public double maxX() {
        return Math.max(this.start.getX(), this.end.getX());
    }

    /**
     * Finds the minimum y-coordinate of the line endpoints.
     *
     * @return the minimal y-coordinate
     */
    public double minY() {
        return Math.min(this.start.getY(), this.end.getY());
    }

    /**
     * Finds the maximum y-coordinate of the line endpoints.
     *
     * @return the maximum y-coordinate
     */
    public double maxY() {
        return Math.max(this.start.getY(), this.end.getY());
    }

    /**
     * Sets the maximum Y coordinate of the line to the given value.
     * Updates the endpoint that currently has the maximum Y.
     *
     * @param y the new maximum Y value
     */
    public void setMaxY(double y) {
        if (doubleEquals(this.start.getY(), this.maxY())) {
            this.start.setY(y);
        } else {
            this.end.setY(y);
        }
    }

    /**
     * Sets the minimum Y coordinate of the line to the given value.
     * Updates the endpoint that currently has the minimum Y.
     *
     * @param y the new minimum Y value
     */
    public void setMinY(double y) {
        if (doubleEquals(this.start.getY(), this.minY())) {
            this.start.setY(y);
        } else {
            this.end.setY(y);
        }
    }

    /**
     * Sets the maximum X coordinate of the line to the given value.
     * Updates the endpoint that currently has the maximum X.
     *
     * @param x the new maximum X value
     */
    public void setMaxX(double x) {
        if (doubleEquals(this.start.getX(), this.maxX())) {
            this.start.setY(x);
        } else {
            this.end.setY(x);
        }
    }

    /**
     * Sets the minimum X coordinate of the line to the given value.
     * Updates the endpoint that currently has the minimum X.
     *
     * @param x the new minimum X value
     */
    public void setMinX(double x) {
        if (doubleEquals(this.start.getX(), this.minX())) {
            this.start.setY(x);
        } else {
            this.end.setY(x);
        }
    }

    /**
     * Determines if the line is vertical.
     *
     * @return true if the line is vertical
     */
    public Boolean vertical() {
        return doubleEquals(this.start.getX(), this.end.getX());
    }

    /**
     * Calculates the slope of the line.
     *
     * @return the slope value
     */
    public double getM() {
        return (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
    }

    /**
     * Calculates the y-intercept of the line.
     *
     * @param m the slope of the line
     * @return the y-intercept
     */
    public double getB(double m) {
        return (this.start.getY() - (m * this.start.getX()));
    }

    /**
     * Calculates the y-coordinate for a given x-coordinate.
     *
     * @param m the slope
     * @param x the x-coordinate
     * @param b the y-intercept
     * @return the y-coordinate
     */
    public double findY(double m, double x, double b) {
        return ((m * x) + b);
    }

    /**
     * Checks if this line and another parallel line overlap.
     *
     * @param other another line
     * @return true if the lines are parallel and overlap
     */
    public Boolean parallels(Line other) {
        // if there are the same b
        if (doubleEquals(this.getB(this.getM()), other.getB(this.getM()))) {
            // there is an overlap
            if (this.maxX() >= other.minX() && this.minX() <= other.maxX()) {
                return true;
            }
        }
        // there is not an overlap or if there are a different b
        return false;
    }

    /**
     * Checks if this line and another vertical line overlap.
     *
     * @param other another line
     * @return true if the lines are vertical and overlap
     */
    public Boolean isOverlap(Line other) {
        // same x
        if (doubleEquals(this.start.getX(), other.start.getX())) {
            // there is an overlap
            if (this.maxY() >= other.minY() && this.minY() <= other.maxY()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a vertical line intersects with another non-vertical line.
     *
     * @param other another line
     * @return true if one line is vertical and they have an intersection point
     */
    public Boolean oneVertical(Line other) {
        // checks x
        if (this.end.getX() >= other.minX() && this.end.getX() <= other.maxX()) {
            double otherY = findY(other.getM(), this.end.getX(), other.getB(other.getM()));
            if (doubleEquals(otherY, this.maxY()) || doubleEquals(otherY, this.minY())
                    || (otherY < this.maxY() && otherY > this.minY())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines if this line intersects with another line.
     *
     * @param other another line
     * @return true if the lines intersect, false otherwise
     */
    public Boolean isIntersecting(Line other) {
        if (this.start.equals(this.end)) {
            return other.containsPoint(this.start);
        }
        if (other.start.equals(other.end)) {
            return this.containsPoint(other.start);
        }

        // Both lines are vertical
        if (this.vertical() && other.vertical()) {
            return this.isOverlap(other);
        }

        // One line is vertical
        if (this.vertical()) {
            return this.oneVertical(other);
        }
        if (other.vertical()) {
            return other.oneVertical(this);
        }

        // Parallel lines
        if (doubleEquals(this.getM(), other.getM())) {
            return this.parallels(other);
        }

        // Calculate intersection point
        double x = (other.getB(other.getM()) - this.getB(this.getM())) / (this.getM() - other.getM());
        double y = (this.getM() * x) + this.getB(this.getM());

        // Check if intersection is within both line segments
        boolean withinThisLine = (doubleEquals(x, this.minX()) || doubleEquals(x, this.maxX())
                || (x > this.minX() && x < this.maxX())) && (doubleEquals(y, this.minY())
                || doubleEquals(y, this.maxY()) || (y > this.minY() && y < this.maxY()));

        boolean withinOtherLine = (doubleEquals(x, other.minX()) || doubleEquals(x, other.maxX())
                || (x > other.minX() && x < other.maxX())) && (doubleEquals(y, other.minY())
                || doubleEquals(y, other.maxY()) || (y > other.minY() && y < other.maxY()));

        return withinThisLine && withinOtherLine;
    }

    /**
     * Checks if a point lies on this line segment.
     *
     * @param p the point to check
     * @return true if the point lies on the line segment
     */
    public boolean containsPoint(Point p) {
        if (this.vertical()) {
            return doubleEquals(p.getX(), this.start.getX()) && p.getY() >= this.minY() && p.getY() <= this.maxY();
        }
        double expectedY = this.findY(this.getM(), p.getX(), this.getB(this.getM()));
        return doubleEquals(p.getY(), expectedY) && p.getX() >= this.minX() && p.getX() <= this.maxX();
    }

    /**
     * Checks if this line intersects with two other lines.
     *
     * @param other1 first line to check
     * @param other2 second line to check
     * @return true if this line intersects with both other lines
     */
    public boolean isIntersecting(Line other1, Line other2) {
        if (this.isIntersecting(other1) && this.isIntersecting(other2)) {
            return true;
        }
        return false;
    }

    /**
     * Computes the intersection point between this line and another line.
     * The method handles several cases:
     * <ul>
     *     <li>If the lines do not intersect, returns null.</li>
     *     <li>If the lines are equal or overlapping, returns null.</li>
     *     <li>If the lines are parallel, returns null.</li>
     *     <li>If one of the lines is vertical, handles as a special case.</li>
     *     <li>Otherwise, calculates the intersection point using the line equations.</li>
     * </ul>
     *
     * @param other the other line to check intersection with
     * @return the intersection point, or null if none exists or the intersection is ambiguous
     */
    public Point intersectionWith(Line other) {
        double y, x;

        //if there is not intersection point
        if (!this.isIntersecting(other)) {
            return null;
        }
        //if the same line
        if (this.equals(other)) {
            return null;
        }
        //if there is an overlap in the lines
        if (this.vertical() && other.vertical()) {
            if (this.isOverlap(other)) {
                // if there is more one of intersection points
                if (this.minX() < other.maxX() || this.maxX() > other.minX() || this.minY() < other.maxY()
                        || this.maxY() > other.minY()) {
                    return null;
                }
            }
        }
        // if the lines are parallels
        if (doubleEquals(this.getM(), other.getM())) {
            if (this.parallels(other)) {
                // if there is more one of intersection points
                if (this.minX() < other.maxX() || this.maxX() > other.minX() || this.minY() < other.maxY()
                        || this.maxY() > other.minY()) {
                    return null;
                }
            }
        }

        // just this line is vertical and have an intersection points
        if (this.vertical()) {
            if (this.oneVertical(other)) {
                x = this.start.getX();
                y = other.findY(other.getM(), x, other.getB(other.getM()));
                return new Point(x, y);
            }
            return null;
        }
        // just other line is vertical and have an intersection points
        if (other.vertical()) {
            if (other.oneVertical(this)) {
                x = other.start.getX();
                y = this.findY(this.getM(), x, this.getB(this.getM()));
                return new Point(x, y);
            }
            return null;
        }

        //if there is just one intersection point
        //find x and y of the intersection point
        x = ((other.getB(other.getM()) - this.getB(this.getM())) / (this.getM() - other.getM()));
        y = ((this.getM() * x) + this.getB(this.getM()));

        return new Point(x, y);
    }

    /**
     * Checks if this line is equal to another line.
     *
     * @param other another line
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        //if the lines are vertical
        if (this.vertical() && other.vertical()) {
            if (this.isOverlap(other)) {
                // if there equals
                if (doubleEquals(this.maxX(), other.maxX()) && doubleEquals(this.maxY(), other.maxY())
                        && doubleEquals(this.minX(), other.minX()) && doubleEquals(this.minY(), other.minY())) {
                    return true;
                }
            }
        }
        // if the lines are parallels
        if (doubleEquals(this.getM(), other.getM())) {
            if (this.parallels(other)) {
                // if there equals
                if (doubleEquals(this.maxX(), other.maxX()) && doubleEquals(this.maxY(), other.maxY())
                        && doubleEquals(this.minX(), other.minX()) && doubleEquals(this.minY(), other.minY())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param p distance from this line to p
     * @return the distance
     */
    public double distanceFromPoint(Point p) {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();

        double a = y2 - y1;
        double b = x1 - x2;
        double c = (x2 * y1) - (y2 * x1);

        return Math.abs(a * p.getX() + b * p.getY() + c) / Math.sqrt(a * a + b * b);
    }

    /**
     * Calculates the closest point on this line segment to a given external point.
     * If the projection of the point onto the line falls outside the segment,
     * returns the nearest endpoint instead.
     *
     * @param p the external point from which to find the closest point on the segment
     * @return the closest point on this line segment to the given point
     */
    public Point closePoint(Point p) {
        double x1 = this.start().getX();
        double y1 = this.start().getY();
        double x2 = this.end().getX();
        double y2 = this.end().getY();

        double px = p.getX();
        double py = p.getY();
        double dx = x2 - x1;
        double dy = y2 - y1;

        if (doubleEquals(dx, 0) && doubleEquals(dy, 0)) {
            return new Point(x1, y1);
        }

        double s = ((px - x1) * dx + (py - y1) * dy) / (dx * dx + dy * dy);
        s = Math.max(0, Math.min(1, s));
        double closeX = x1 + s * dx;
        double closeY = y1 + s * dy;

        return new Point(closeX, closeY);
    }

    // If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.

    /**
     * Returns the closest intersection point between this line and a given rectangle,
     * measured from the start of the line.
     * If there is no intersection between the line and the rectangle, returns null.
     *
     * @param rect the rectangle to check for intersections with
     * @return the closest intersection point to the start of the line, or null if no intersection exists
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> listP = rect.intersectionPoints(this);
        if (listP.isEmpty()) {
            return null;
        }
        Point closestPoint = listP.get(0);
        for (Point p : listP) {
            if (this.start.distance(p) < this.start.distance(closestPoint)) {
                closestPoint = p;
            }
        }
        return closestPoint;
    }
}