package GameFlow.GameManager;

import Sprites.Sprite;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The type Sprite collection. Holds all the sprites in the game.
 */
public class SpriteCollection {
    private final java.util.List<Sprite> sprites;

    /**
     * Instantiates a new Sprite collection.
     */
//chose linked list rather than array list as it is better suited for
    //the dynamic memory manipulation needed for this game (adding sprites,
    // adding and deleting blocks...)
    public SpriteCollection() {
        this.sprites = new LinkedList<>();
    }

    /**
     * Adds a sprite to the list.
     *
     * @param s the sprite to be added
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Remove sprite.
     *
     * @param s the sprite to be removed from this sprite collection.
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * Notify all time passed.
     * Notifies all sprites in collection that time has passed.
     */
    public void notifyAllTimePassed() {
        ArrayList<Sprite> sp = new ArrayList<>(this.sprites);
        for (Sprite s : sp) {
            s.timePassed();
        }
    }

    /**
     * Draw all on.
     * Method draws all sprites in the sprite collection on the screen using
     * the given drawSurface.
     *
     * @param d the drawSurface
     */
    public void drawAllOn(DrawSurface d) {
        //making a copy of sprite list before drawing them to prevent exceptions
        List<Sprite> sp = new ArrayList<>(this.sprites);

        for (Sprite s : sp) {
            s.drawOn(d);
        }
    }
}
