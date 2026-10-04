package Sprites;

import Game.Game;
import biuoop.DrawSurface;

/**
 * The Sprites.Sprite interface represents all objects that can be drawn on the screen
 * and that know how to update themselves over time.
 */
public interface Sprite {
    /**
     * Draws the sprite onto the given drawing surface.
     *
     * @param d the surface to draw the sprite on
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that time has passed, so it can update its state.
     */
    void timePassed();

    /**
     * Adds this object to the given game.
     *
     * @param g the game to which this object should be added.
     */
    void addToGame(Game g);

}
