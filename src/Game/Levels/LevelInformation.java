package Game.Levels;

import Sprites.Ball;
import Sprites.Block;
import Sprites.Sprite;
import Utils.Geometry.Velocity;

import java.awt.*;
import java.util.List;

/**
 * The interface Level information.
 */
public interface LevelInformation {
    /**
     * Number of balls int.
     *
     * @return the int
     */
    int numberOfBalls();

    /**
     * Initial ball velocities list.
     * The initial velocity of each ball. Note that
     * initialBallVelocities().size() == numberOfBalls().
     *
     * @return the list
     */
    List<Velocity> initialBallVelocities();

    /**
     * Paddle speed int.
     *
     * @return the int
     */
    int paddleSpeed();

    /**
     * Paddle width int.
     *
     * @return the int
     */
    int paddleWidth();

    /**
     * Level name string.
     * the level name will be displayed at the top of the screen.
     *
     * @return the string
     */
    String levelName();

    /**
     * Gets background.
     * Returns a sprite with the background of the level
     *
     * @return the background
     */
    Sprite getBackground();

    /**
     * Blocks list.
     * The Blocks that make up this level, each block contains its size,
     * color and location.
     *
     * @return the list
     */
    List<Block> blocks();

    /**
     * Number of blocks to remove int.
     * Number of blocks that should be removed before the level is considered
     * to be "cleared". This number should be <= blocks.size();
     *
     * @return the int
     */
    int numberOfBlocksToRemove();

    void setRemainingBlocks(int remainingBlocks);

    Color PaddleColor();

    Color BorderColor();

    /**
     * Initial balls list.
     *
     * @return a list of the specified level's beginning balls.
     */
    List<Ball> initialBalls();
}
