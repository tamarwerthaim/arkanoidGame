package Collision;

import Geometry.Point;
import Geometry.Rectangle;
import Sprites.Ball;

/**
 * The {@code Collision.Collidable} interface should be implemented by any object
 * that can be collided with in the game (like blocks, paddles, etc.).
 * It defines methods to get the shape of the object and to notify the object
 * about a collision that occurred.
 */
public interface Collidable {

    /**
     * Returns the shape of the object that can be collided with.
     *
     * @return the {@code Geometry.Rectangle} representing the object's collision boundaries.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that a collision with a ball occurred at a specific point.
     * Computes the new velocity of the ball after the hit.
     *
     * @param hitter the ball that hit the object.
     * @param collisionPoint the point at which the collision occurred.
     * @param currentVelocity the velocity of the ball before the collision.
     * @return the new velocity of the ball after the collision.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);


    /**
     * Indicates whether this object is dynamic (i.e., capable of changing or moving over time).
     *
     * @return true if the object is dynamic, false if it is static.
     */
    boolean isDynmic();
}
