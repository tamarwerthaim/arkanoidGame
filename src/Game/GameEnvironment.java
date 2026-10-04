package Game;

import Collision.Collidable;
import Collision.CollisionInfo;
import Geometry.Line;
import Geometry.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Game.GameEnvironment} class holds all the {@code Collision.Collidable} objects in the game.
 * It is responsible for checking possible collisions between a moving object and
 * the collidables, and returning information about the closest one.
 */
public class GameEnvironment {

    private List<Collidable> collidables;

    /**
     * Constructs a new, empty {@code Game.GameEnvironment}.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Adds a {@code Collision.Collidable} object to the environment.
     *
     * @param c the collidable to add
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * * Removes the given collidable object from the collection.
     *
     * @param c the collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

    /**
     * Checks if a given trajectory line will collide with any object in the environment.
     * If no collision will occur, returns {@code null}. Otherwise, returns a {@code Collision.CollisionInfo}
     * object with details of the closest collision point and the collidable object involved.
     *
     * @param trajectory the line representing the object's movement path
     * @return the {@code Collision.CollisionInfo} of the closest collision, or {@code null} if no collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        double closestDistance = Double.MAX_VALUE;
        Collidable closestCollidable = null;
        Point closestPoint = null;

        for (Collidable c : collidables) {
            Point p = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (p != null) {
                double distance = trajectory.start().distance(p);
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestCollidable = c;
                    closestPoint = p;
                }
            }
        }

        if (closestCollidable == null) {
            return null;
        }
        return new CollisionInfo(closestPoint, closestCollidable);
    }

    /**
     * Returns the list of all collidable objects in the environment.
     *
     * @return a list containing all collidable objects.
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }
}
