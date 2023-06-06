package Sprites;

import GameFlow.GameManager.Game;
import Utils.Geometry.Point;
import Utils.Geometry.Rectangle;
import Utils.Geometry.Velocity;
import Utils.Misc.Config;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type Paddle. Acts as a movable block (extends block and implements
 * sprite.)
 */
public class Paddle extends Block implements Sprite {
    static final int CHANGE_DIR = -1;
    private final biuoop.KeyboardSensor keyboard;
    private final int oneStep = 5;

    /**
     * Instantiates a new Sprites.Block.
     *
     * @param rect     the collision rectangle of the paddle.
     * @param color    the color of the paddle.
     * @param keyboard the keyboard sensor for the paddle.
     */
    public Paddle(Rectangle rect, Color color, KeyboardSensor keyboard) {
        super(rect, color);
        this.keyboard = keyboard;
    }

    /**
     * Move left.
     * Method moves paddle to the left according to keyboard input by
     * setting a new collision rectangle to the paddle object.
     */
    public void moveLeft() {
        int leftBorder = Config.BORDER_SIZE;

        Rectangle rect = this.getCollisionRectangle();
        Point upperL = rect.getUpperLeft();
        double width = rect.getWidth(), height = rect.getHeight();
        /*
        to prevent moving past the screen's edges, moves to the bigger of
        the two values: the x value of the next step to the left, or the
        leftmost edge of the screen
         */
        double newX = Math.max(upperL.getX() - oneStep, leftBorder);

        this.setNewCollisionRect(newX, upperL.getY(), width, height);
    }

    /**
     * Move right.
     * Method moves paddle to the right according to keyboard input by
     * setting a new collision rectangle to the paddle object.
     */
    public void moveRight() {
        int rightBorder = Config.WIN_WIDTH - Config.BORDER_SIZE;

        Rectangle rect = this.getCollisionRectangle();
        Point upperL = rect.getUpperLeft();
        double width = rect.getWidth(), height = rect.getHeight();

        /*
        to prevent moving past the screen's edges, moves to the smaller of
        the two values: the next step to the right, or the edge of the screen
         */
        double newX = Math.min(upperL.getX() + oneStep, rightBorder - width);

        this.setNewCollisionRect(newX, upperL.getY(), width, height);

    }

    /**
     * Adds this paddle to the game.
     *
     * @param g the game.
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
//            System.out.println("Left arrow button pressed");
            this.moveLeft();
        }

        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
//            System.out.println("Right arrow button pressed");
            this.moveRight();
        }
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        double paddleFifth = this.getCollisionRectangle().getWidth() / 5;
        double upperLeftX = this.getCollisionRectangle().getUpperLeft().getX();
        double pX = collisionPoint.getX();

        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        if (pX <= upperLeftX + 1 * paddleFifth) {
            Velocity v = Velocity.fromAngleAndSpeed(300,
                    Config.BALL_SPEED);
            dy = Math.abs(v.getDy()) * CHANGE_DIR;
            dx = Math.abs(v.getDx()) * CHANGE_DIR;
            return new Velocity(dx, dy);
        }

        if (pX <= upperLeftX + 2 * paddleFifth) {
            Velocity v = Velocity.fromAngleAndSpeed(330,
                    Config.BALL_SPEED);
            return new Velocity(v.getDx(),
                    Math.abs(v.getDy()) * CHANGE_DIR);
        }

        //ball hit the 3rd segment, therefore only its vertical direction flips
        if (pX <= upperLeftX + 3 * paddleFifth) {
            return new Velocity(dx, dy * CHANGE_DIR);
        }

        if (pX <= upperLeftX + 4 * paddleFifth) {
            Velocity v = Velocity.fromAngleAndSpeed(30,
                    Config.BALL_SPEED);
            return new Velocity(v.getDx(),
                    Math.abs(v.getDy()) * CHANGE_DIR);
        }

        //if we got here, the ball hit the fifth segment or the right side
        Velocity v = Velocity.fromAngleAndSpeed(60, Config.BALL_SPEED);
        dx = v.getDx();
        dy = CHANGE_DIR * Math.abs(v.getDy());
        if (this.getCollisionRectangle().getRight().
                onSegment(collisionPoint)) {
            dx = Math.abs(v.getDx());
        }
        return new Velocity(dx, dy);
    }
}
