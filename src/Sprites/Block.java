package Sprites;

import Collision.Collidable;
import Collision.Velocity;
import Game.Game;
import Geometry.Point;
import Geometry.Rectangle;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import Game.HitNotifier;
import Game.HitListener;

/**
 * The {@code Sprites.Block} class represents a rectangular object in the game that implements
 * both {@code Collision.Collidable} and {@code Sprites.Sprite}. It can detect and respond to collisions,
 * and can be drawn on the screen.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private Rectangle block;
    private java.awt.Color color;
    private List<HitListener> hitListeners;

    static final double COMPARISON_THRESHOLD = 0.00001;

    /**
     * Constructs a block with a given rectangle and color.
     *
     * @param block the {@code Geometry.Rectangle} that defines the block's shape and position.
     * @param color the color to fill the block with.
     */
    public Block(Rectangle block, Color color) {
        this.block = block;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Returns the collision shape of the block, which is a rectangle.
     *
     * @return the {@code Geometry.Rectangle} of the block.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    /**
     * Notifies the block that a collision occurred at a specific point with a given velocity.
     * The method calculates the new velocity after the collision.
     *
     * @param collisionPoint  the point where the collision occurred.
     * @param currentVelocity the current velocity of the object before collision.
     * @return the new velocity after the collision.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity v = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());

        // Reverse dx if collision is on left or right edge
        if (doubleEquals(collisionPoint.getX(), block.getLeft().start().getX())
                || doubleEquals(collisionPoint.getX(), block.getRight().start().getX())) {
            v.setDx(-v.getDx());
        }

        // Reverse dy if collision is on top or bottom edge
        if (doubleEquals(collisionPoint.getY(), block.getTop().start().getY())
                || doubleEquals(collisionPoint.getY(), block.getBottom().start().getY())) {
            v.setDy(-v.getDy());
        }
        if (!ballColorMatch(hitter)) {
            this.notifyHit(hitter);
        }

        return v;
    }

    // Add hl as a listener to hit events.
    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    // Remove hl from the list of listeners to hit events.
    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * Utility method for comparing doubles with a precision threshold.
     *
     * @param a first value.
     * @param b second value.
     * @return {@code true} if values are "equal" within a small threshold.
     */
    public boolean doubleEquals(double a, double b) {
        return Math.abs(a - b) < COMPARISON_THRESHOLD;
    }

    /**
     * Draws the block on the given drawing surface.
     *
     * @param d the surface to draw the block on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        int x = (int) this.block.getUpperLeft().getX();
        int y = (int) this.block.getUpperLeft().getY();
        int width = (int) this.block.getWidth();
        int height = (int) this.block.getHeight();
        d.fillRectangle(x, y, width, height);
        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, width, height);
    }

    /**
     * Notifies the block that time has passed.
     * Currently does nothing, but required by the {@code Sprites.Sprite} interface.
     */
    @Override
    public void timePassed() {
        // No behavior for now
    }

    /**
     * Adds the block to the game, as both a {@code Sprites.Sprite} and {@code Collision.Collidable}.
     *
     * @param g the game to add the block to.
     */
    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    @Override
    public boolean isDynmic() {
        return false;
    }

    /**
     * Checks whether the given ball has the same color as this object.
     *
     * @param ball the ball to compare color with.
     * @return {@code true} if the ball's color matches this object's color; {@code false} otherwise.
     */
    public boolean ballColorMatch(Ball ball) {
        if (ball.getColor().equals(this.color)) {
            return true;
        }
        return false;
    }

    /**
     * Removes this object from the game, both from the sprite collection and the collidables.
     *
     * @param game the game instance from which this object should be removed.
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }


    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Returns the color of the object.
     *
     * @return the object's color.
     */
    public Color getColor() {
        return color;
    }


}
