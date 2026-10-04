package Geometry;

/**
 * Represents a point in a two-dimensional Cartesian coordinate system.
 * This class provides methods for comparing points and calculating distances.
 */
public class Point {
    private double x;
    private double y;

    /** Threshold value used for floating-point comparisons. */
    static final double COMPARISON_THRESHOLD = 0.00001;


    /**
     * Constructs a new Geometry.Point with specified coordinates.
     *
     * @param x The x-coordinate of the point
     * @param y The y-coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the Euclidean distance between this point and another point.
     *
     * @param other The other point to calculate distance to
     * @return The distance between this point and the other point
     */
    public double distance(Point other) {
        double a = Math.pow((this.x - other.x), 2);
        double b = Math.pow((this.y - other.y), 2);
        return Math.sqrt(a + b);
    }

    /**
     * Determines whether this point is equal to another point.
     * Two points are considered equal if their x and y coordinates are
     * within the comparison threshold of each other.
     *
     * @param other The point to compare with
     * @return true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        return (doubleEquals(this.x, other.x)) && (doubleEquals(this.y, other.y));
    }

    /**
     * Compares two double values for equality within the defined threshold.
     *
     * @param a First double value
     * @param b Second double value
     * @return true if the absolute difference between the values is less than the comparison threshold
     */
    public boolean doubleEquals(double a, double b) {
        return  Math.abs(a - b) < Point.COMPARISON_THRESHOLD;
    }

    /**
     * Gets the x-coordinate of this point.
     *
     * @return The x-coordinate
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets the y-coordinate of this point.
     *
     * @return The y-coordinate
     */
    public double getY() {
        return this.y;
    }

    /**
     * Sets the x-coordinate of this point to the given value.
     *
     * @param x the new x-coordinate
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets the y-coordinate of this point to the given value.
     *
     * @param y the new y-coordinate
     */
    public void setY(double y) {
        this.y = y;
    }
}
