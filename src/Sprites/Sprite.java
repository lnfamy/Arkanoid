package Sprites;

import biuoop.DrawSurface;

/**
 * The interface Sprite.
 */
public interface Sprite {
    /**
     * Draw on.
     * Draws the sprite to the screen using the given drawSurface.
     *
     * @param d the drawSurface.
     */
    void drawOn(DrawSurface d);

    /**
     * Time passed.
     * Notifies the sprite that time has passed
     */
    void timePassed();
}
