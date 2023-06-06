package Sprites;

import GameFlow.Collisions.CollisionManager.CollisionInfo;
import GameFlow.GameManager.Game;
import GameFlow.GameManager.GameEnvironment;
import Utils.Geometry.Line;
import Utils.Geometry.Point;
import Utils.Geometry.Rectangle;
import Utils.Geometry.Velocity;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Sprites.Ball.
 */
public class Ball implements Sprite {
    static final int UPPER_L = 10;
    static final int UPPER_R = 20;
    static final int LOWER_R = 30;
    static final int LOWER_L = 40;
    static final int LEFT = 0;
    static final int RIGHT = 1;
    static final int UP = 2;
    static final int DOWN = 3;
    private final int r;
    private final java.awt.Color color;
    private Point center;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    /**
     * Instantiates a new Sprites.Ball.
     * Constructor receives a center point's coordinates (double x,y) and
     * creates a new Utilities.Geometry.Point using those and sets it to be this ball's
     * center. Constructor also receives a radius (int r) and a color
     * (java.awt.Color color).
     *
     * @param x     the x coordinate of the ball's center point
     * @param y     the y coordinate of the ball's center point
     * @param r     the radius of the ball
     * @param color the color of the ball
     */
    public Ball(double x, double y, int r, Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.velocity = null;
        this.gameEnvironment = null;
    }

    /**
     * Sets game environment.
     *
     * @param environment the environment
     */
    public void setGameEnvironment(GameEnvironment environment) {
        this.gameEnvironment = environment;
    }

    /**
     * Adds this ball to the game.
     *
     * @param g the game this ball is to be added to.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * Remove from game.
     *
     * @param game the game this ball is to be removed from.
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
    }


    /**
     * Get x (int).
     *
     * @return the x coordinate of this ball's center point, cast to int.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Get y (int).
     *
     * @return the y coordinate of this ball's center point, cast to int.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Set velocity.
     * Method sets this ball's velocity field to a given velocity v.
     *
     * @param v the velocity value to give this ball.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Get velocity (velocity).
     *
     * @return the velocity of this ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Draw on.
     * Method receives a drawSurface and sets its color to the color of this
     * ball, then draws this ball by filling a circle at this ball's center
     * coordinates with its size.
     *
     * @param surface the drawSurface
     */
    public void drawOn(DrawSurface surface) {
        //filling in a ball
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.r);

        //adding a black outline to the ball
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.r);
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Move one step.
     * Method 'moves' this ball one step by applying its velocity to its center
     * point on the condition that the ball will not collide with one of the
     * borders should it move.
     */
    public void moveOneStep() {
        if (this.getVelocity() != null) {
            //calculating ball's trajectory
            Line trj = new Line(this.center,
                    this.getVelocity().applyToPoint(this.center));
            //checking if this trajectory will result in a collision
            CollisionInfo c = this.gameEnvironment.getClosestCollision(trj);

            /*
            if there's no collision info, move ball one step according to its
             velocity.
             Otherwise, there has been a collision. The ball is moved to
             'almost' where the hit point is, just slightly before it. The
             hit object is notified about the collision using its hit()
             method. The velocity is then updated through that same hit method.
             */
            if (c == null) {
                this.center = trj.end();
            } else {
                int colRes = c.getCollisionObject().getCollisionRectangle()
                        .whichLineCollided(c.getCollisionPoint(),
                                this.getVelocity());
                Point p = byCollision(c.getCollisionPoint(), colRes,
                        c.getCollisionObject().getCollisionRectangle());
                this.velocity = c.getCollisionObject().hit(this,
                        c.getCollisionPoint(), this.velocity);
                this.center = p;
            }
        }
    }

    private Point byCollision(Point col, int colRes, Rectangle rec) {
        double x = col.getX(), y = col.getY();

        //handling corner collisions
        switch (colRes) {
            case UPPER_L:
                return new Point(rec.getUpperLeft().getX() - this.r,
                        rec.getUpperLeft().getY() - this.r);
            case UPPER_R:
                return new Point(rec.getUpperRight().getX() + this.r,
                        rec.getUpperRight().getY() - this.r);
            case LOWER_L:
                return new Point(rec.getLowerLeft().getX() - this.r,
                        rec.getLowerLeft().getY() + this.r);
            case LOWER_R:
                return new Point(rec.getLowerRight().getX() + this.r,
                        rec.getLowerRight().getY() + this.r);
            case LEFT:
                x = rec.getLeft().start().getX() - this.r;
                break;
            case RIGHT:
                x = rec.getRight().start().getX() + this.r;
                break;
            case UP:
                y = rec.getUpper().start().getY() - this.r;
                break;
            case DOWN:
                y = rec.getLower().start().getY() + this.r;
                break;
            default:
                // do nothing
                break;
        }
        return new Point(x, y);
    }

}
