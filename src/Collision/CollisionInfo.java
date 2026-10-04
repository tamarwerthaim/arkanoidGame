package Collision;

import Geometry.Point;

/**
 * The {@code Collision.CollisionInfo} class represents information about a collision
 * that is about to occur in the game. It holds both the collision point and
 * the object (of type {@code Collision.Collidable}) with which the collision is expected.
 */
public class CollisionInfo {

    private Collidable collidable;
    private Point closestPoint;

    /**
     * Constructs a {@code Collision.CollisionInfo} object with the given point and collidable object.
     *
     * @param closestPoint the point at which the collision occurs.
     * @param collidable   the collidable object involved in the collision.
     */
    public CollisionInfo(Point closestPoint, Collidable collidable) {
        this.closestPoint = closestPoint;
        this.collidable = collidable;
    }

    /**
     * Returns the point at which the collision occurs.
     *
     * @return the collision point.
     */
    public Point collisionPoint() {
        return this.closestPoint;
    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the object that the collision occurs with.
     */
    public Collidable collisionObject() {
        return this.collidable;
    }
}
