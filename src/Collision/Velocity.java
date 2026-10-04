package Collision;

import Geometry.Point;

/**
 * Collision.Velocity specifies the change in position on the x and y axes.
 * It represents the movement per unit of time in both directions.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructs a Collision.Velocity given the horizontal and vertical components.
     *
     * @param dx the change in x-coordinate (horizontal movement)
     * @param dy the change in y-coordinate (vertical movement)
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Creates a Collision.Velocity instance based on an angle and a speed.
     * The angle is measured in degrees, where 0 is upward and increases clockwise.
     *
     * @param angle the angle in degrees (0 = up, 90 = right, etc.)
     * @param speed the magnitude of the velocity
     * @return a Collision.Velocity object representing the direction and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = -speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * Returns the horizontal component (dx) of the velocity.
     *
     * @return the dx value
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Returns the vertical component (dy) of the velocity.
     *
     * @return the dy value
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Sets the horizontal component (dx) of the velocity.
     *
     * @param dx the new dx value
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Sets the vertical component (dy) of the velocity.
     *
     * @param dy the new dy value
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Takes a point and returns a new point after applying the velocity to it.
     * Moves the point by (dx, dy).
     *
     * @param p the original point
     * @return a new point translated by the velocity
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * Returns the speed (magnitude) of the velocity.
     *
     * @return the speed as a double
     */
    public double getSpeed() {
        return Math.sqrt(dx * dx + dy * dy);
    }

}
