package Sprites;

import Collision.Collidable;
import Collision.CollisionInfo;
import Collision.Velocity;
import Game.Game;
import Game.GameEnvironment;
import Geometry.Line;
import Geometry.Point;
import biuoop.DrawSurface;

/**
 * The {@code Sprites.Ball} class represents a ball that can move around the screen, bounce off objects,
 * and be drawn on a surface. It has properties such as position (center), radius, color, velocity,
 * and a reference to the game environment to check for collisions.
 */
public class Ball implements Sprite {

    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v;
    private Line[] limits;
    private GameEnvironment game;

    /**
     * Constructs a ball with a given center point, radius, color, and game environment.
     *
     * @param center the center point of the ball.
     * @param r      the radius of the ball.
     * @param color  the color of the ball.
     * @param game   the game environment to use for collision detection.
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment game) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.game = game;
    }

    /**
     * Constructs a ball from x, y coordinates, radius, color, and game environment.
     *
     * @param x     the x-coordinate of the center.
     * @param y     the y-coordinate of the center.
     * @param r     the radius of the ball.
     * @param color the color of the ball.
     * @param game  the game environment to use for collision detection.
     */
    public Ball(int x, int y, int r, java.awt.Color color, GameEnvironment game) {
        this(new Point(x, y), r, color, game);
    }

    /**
     * @return the x-coordinate of the ball's center.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return the y-coordinate of the ball's center.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * @return the radius (size) of the ball.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Sets a new radius for the ball.
     *
     * @param size the new radius.
     */
    public void setSize(int size) {
        this.r = size;
    }

    /**
     * @return the center {@code Geometry.Point} of the ball.
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * @return the current movement boundary lines (if any).
     */
    public Line[] getLimit() {
        return this.limits;
    }

    /**
     * Sets boundary lines for the ball's movement.
     *
     * @param limits an array of lines representing boundaries.
     */
    public void setLimits(Line[] limits) {
        this.limits = limits;
    }

    /**
     * Changes the ball's color.
     *
     * @param color the new color.
     */
    public void setColor(java.awt.Color color) {
        this.color = color;
    }

    /**
     * Draws the ball on the given drawing surface.
     *
     * @param surface the drawing surface to draw the ball on.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param v the new velocity.
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * Sets the velocity of the ball using dx and dy.
     *
     * @param dx the change in x per time unit.
     * @param dy the change in y per time unit.
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * @return the current velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * Moves the ball one step forward based on its velocity,
     * checking for collisions along the trajectory.
     * The movement is divided into small sub-steps to ensure smooth motion
     * and avoid skipping over objects. If no collision is detected, the ball moves normally.
     * If the ball is found to be inside a dynamic object (like a paddle), it simulates a collision
     * by calling the hit method and repositions the ball just above the object.
     * Otherwise, in case of a standard collision, the ball updates its velocity and position
     * to reflect the bounce.
     */
    public void moveOneStep() {
        int steps = 20;
        double dxStep = this.v.getDx() / steps;
        double dyStep = this.v.getDy() / steps;

        for (int i = 0; i < steps; i++) {
            Point start = this.center;
            Point end = new Point(start.getX() + dxStep, start.getY() + dyStep);
            Line trajectory = new Line(start, end);

            CollisionInfo collisionInfo = this.game.getClosestCollision(trajectory);

            if (collisionInfo == null) {
                for (Collidable c : this.game.getCollidables()) {
                    if (c.isDynmic() && c.getCollisionRectangle().contains(this.center)) {
                        Velocity v = c.hit(this, this.center, this.getVelocity());
                        this.setVelocity(v);
                        double y = c.getCollisionRectangle().getUpperLeft().getY() - 10;
                        this.center = new Point(end.getX(), y);
                        return;
                    } else {
                        this.center = end;
                    }
                }
            } else {
                Point collisionPoint = collisionInfo.collisionPoint();
                Collidable collisionObject = collisionInfo.collisionObject();

                double epsilon = 0.01;
                double normX = dxStep;
                double normY = dyStep;
                double length = Math.sqrt(normX * normX + normY * normY);

                if (length > 0) {
                    normX /= length;
                    normY /= length;
                }

                this.center = new Point(
                        collisionPoint.getX() - normX * epsilon,
                        collisionPoint.getY() - normY * epsilon
                );

                this.v = collisionObject.hit(this, collisionPoint, this.v);
                break;
            }
        }
    }

    /**
     * Called when time has passed in the game. Moves the ball accordingly.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Adds the ball to the game as a {@code Sprites.Sprite}.
     *
     * @param g the game instance.
     */
    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * Compares two double values for equality, accounting for rounding errors.
     *
     * @param a the first value.
     * @param b the second value.
     * @return {@code true} if the values are close enough to be considered equal.
     */
    public boolean doubleEquals(double a, double b) {
        return Math.abs(a - b) < COMPARISON_THRESHOLD;
    }

    /**
     * Threshold value used for floating-point comparisons.
     */
    static final double COMPARISON_THRESHOLD = 0.00001;

    /**
     * Removes this object from the game by removing it from the game's sprite collection.
     *
     * @param game the game instance from which this sprite should be removed.
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
    }


}
