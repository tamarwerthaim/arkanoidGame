package Sprites;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The Sprites.SpriteCollection class holds a list of Sprites.Sprite objects and allows performing
 * operations on all of them, such as drawing or notifying them that time has passed.
 */
public class SpriteCollection {
    private List<Sprite> spriteList;

    /**
     * Constructs a new empty Sprites.SpriteCollection.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<>();
    }

    /**
     * Adds a sprite to the collection.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        spriteList.add(s);
    }

    /**
     * Removes the given sprite from the collection.
     *
     * @param s the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        spriteList.remove(s);
    }

    /**
     * Calls timePassed() on all sprites in the collection.
     * This is typically called once per frame to update all sprite states.
     */
    public void notifyAllTimePassed() {
        List<Sprite> copy = new ArrayList<>(this.spriteList);
        for (Sprite s : copy) {
            s.timePassed();
        }
    }

    /**
     * Calls drawOn(d) on all sprites in the collection.
     * This draws all sprites to the given DrawSurface.
     *
     * @param d the drawing surface to render the sprites on
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : spriteList) {
            s.drawOn(d);
        }
    }
}
