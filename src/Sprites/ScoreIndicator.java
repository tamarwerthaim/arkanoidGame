package Sprites;

import Game.Counter;
import Game.Game;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The {@code ScoreIndicator} class is a sprite responsible for displaying
 * the current score at the top of the game screen.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * Constructs a {@code ScoreIndicator} with the given score counter.
     *
     * @param score the counter that tracks the current score.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    /**
     * Draws the score indicator on the given drawing surface.
     * It displays a rectangle at the top of the screen with the current score.
     *
     * @param d the drawing surface to draw the score on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        Color midLightBlue = new Color(100, 149, 237);
        d.setColor(midLightBlue);
        int x = 0;
        int y = 0;
        int width = 800;
        int height = 20;
        d.fillRectangle(x, y, width, height);
        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, width, height);
        d.setColor(Color.WHITE);
        d.drawText(370, 15, "Score: " + score.getValue(), 16);
    }

    /**
     * Called every frame to notify the sprite that time has passed.
     * This implementation does nothing since the score is static.
     */
    @Override
    public void timePassed() {
        // No action needed.
    }

    /**
     * Adds this score indicator to the game as a sprite.
     *
     * @param g the game to which this sprite should be added.
     */
    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}
