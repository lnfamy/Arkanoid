package Sprites;

import Game.Collisions.CollisionManager.Collidable;
import Game.GameManager.GameLevel;
import Utils.Geometry.Point;
import Utils.Geometry.Rectangle;
import Utils.Geometry.Velocity;
import Game.Collisions.Listeners.HitListener;
import Game.Collisions.Listeners.HitNotifier;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The type Sprites.Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    static final int CHANGE_DIR = -1;

    private final List<HitListener> hitListeners;
    private Rectangle collisionRectangle;
    private final Color color;

    /**
     * Instantiates a new Sprites.Block.
     *
     * @param rect  the collision rectangle of this block.
     * @param color the color of the block.
     */
    public Block(Rectangle rect, Color color) {
        this.hitListeners = new ArrayList<>();
        this.collisionRectangle = rect;
        this.color = color;
    }

    /**
     * Instantiates a new Sprites.Block.
     *
     * @param x   the x coordinate of upperLeft for the block.
     * @param y   the y coordinate of upperLeft for the block.
     * @param w   the width of the block
     * @param h   the height of the block
     * @param clr the color of the block.
     */
    public Block(double x, double y, double w, double h, Color clr) {
        this.hitListeners = new ArrayList<>();
        this.collisionRectangle = new Rectangle(new Point(x, y), w, h);
        this.color = clr;
    }

    /**
     * Adds this block to the game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.getEnvironment().addCollidable(this);
        g.getSprites().addSprite(this);
    }

    /**
     * Remove from game.
     *
     * @param game the game this block is to be removed from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.collisionRectangle;
    }

    /**
     * Notify hit.
     * Notifies all listeners of this block of a hit event.
     *
     * @param hitter the hitter
     */
    public void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new LinkedList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        double epsilon = 0.00001;
        Rectangle rectangle = this.getCollisionRectangle();
        Point upperLeft = rectangle.getUpperLeft();
        double xCol = collisionPoint.getX();
        double yCol = collisionPoint.getY();

        if (Math.abs(xCol - upperLeft.getX()) < epsilon
                || (Math.abs(xCol - upperLeft.getX() - rectangle.getWidth()))
                < epsilon) {
            dx *= CHANGE_DIR;
        }
        if (Math.abs(yCol - upperLeft.getY()) < epsilon
                || (Math.abs(yCol - upperLeft.getY() - rectangle.getHeight()))
                < epsilon) {
            dy *= CHANGE_DIR;
        }

        notifyHit(hitter);
        return new Velocity(dx, dy);
    }

    @Override
    public void drawOn(DrawSurface d) {
        // draw filled in blocks on screen
        d.setColor(this.color);

        Rectangle rec = this.collisionRectangle;
        Point upperLeft = rec.getUpperLeft();

        //adding black outline to blocks
        d.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(),
                (int) rec.getWidth(), (int) rec.getHeight());

        d.setColor(Color.BLACK);

        d.drawRectangle((int) upperLeft.getX(), (int) upperLeft.getY(),
                (int) rec.getWidth(), (int) rec.getHeight());
    }

    /**
     * Sets new collision rect.
     *
     * @param x the x coordinate of the upperLeft point of the new collision
     *          rectangle.
     * @param y the y coordinate of the upperLeft point of the new collision
     *          rectangle.
     * @param w the width of the new collision rectangle.
     * @param h the height of the new collision rectangle.
     */
    public void setNewCollisionRect(double x, double y, double w, double h) {
        this.collisionRectangle = new Rectangle(x, y, w, h);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor() {
        return this.color;
    }
}