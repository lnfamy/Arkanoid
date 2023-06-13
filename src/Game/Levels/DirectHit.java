package Game.Levels;

import Sprites.Ball;
import Sprites.Block;
import Sprites.Sprite;
import Utils.Geometry.Velocity;
import Utils.Misc.Config;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The type Direct hit.
 */
public class DirectHit implements LevelInformation {
    private int remainingBlocks = Config.DH_NUM_BLOCKS;

    public void setRemainingBlocks(int remainingBlocks) {
        this.remainingBlocks = remainingBlocks;
    }

    @Override
    public Color PaddleColor() {
        Random rand = new Random();
        int index = (int) rand.nextDouble(3) + 1;
        return Config.DIRECT_HIT_COLORS[index];
    }

    @Override
    public Color BorderColor() {
        return Config.DH_BORDER_CLR;
    }

    /**
     * Number of balls int.
     *
     * @return the int
     */
    @Override
    public int numberOfBalls() {
        return Config.DH_NUM_BALLS;
    }

    /**
     * Initial ball velocities list.
     * DirectHit's override: Only one ball in this level, hence there being
     * only one velocity.
     *
     * @return the list
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(Config.ANGLE_DOWN,
                Config.BALL_SPEED));
        return velocities;
    }

    /**
     * Paddle speed int.
     *
     * @return the int
     */
    @Override
    public int paddleSpeed() {
        return 5;
    }

    /**
     * Paddle width int.
     *
     * @return the int
     */
    @Override
    public int paddleWidth() {
        return Config.PADDLE_W;
    }

    /**
     * Level name string.
     *
     * @return the string
     */
    @Override
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * Gets background.
     *
     * @return the background
     */
    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                int bg = 0, red = 1, yellow = 2, ochre = 3;
                int width = Config.WIN_WIDTH, height = Config.WIN_HEIGHT;
                int rBig = Config.DH_R1,
                        rMed1 = rBig - 50,
                        rMed2 = rBig - 100,
                        rSm1 = rBig / 3,
                        rSm2 = rSm1 - 10,
                        rMini = rSm1 / 4;
                int y = Config.DH_CENTER_Y, bottomRowOffset = 300,
                        midRowOffset = 200;
                int xMid = Config.MID_SCREEN_W;
                d.setColor(Config.DIRECT_HIT_COLORS[bg]);
                d.fillRectangle(0, 0, width, height);

                d.setColor(Config.DIRECT_HIT_COLORS[red]);
                d.fillCircle(xMid, y, rBig);
                d.fillCircle(xMid / 4, y, rSm1);
                d.fillCircle(xMid * 7 / 4, y, rSm1);
                d.fillCircle(xMid, y + bottomRowOffset, rSm1);

                d.setColor(Config.DIRECT_HIT_COLORS[yellow]);
                d.fillCircle(xMid, y, rMed1);
                d.setColor(Color.BLACK);
                d.drawCircle(xMid, y, rMed1);

                d.setColor(Config.DIRECT_HIT_COLORS[yellow]);
                d.fillCircle(xMid / 2, y + midRowOffset, rMed2);
                d.fillCircle(xMid * 3 / 2, y + midRowOffset, rMed2);
                d.fillCircle(xMid * 3 / 2, y - midRowOffset, rMed2);
                d.fillCircle(xMid / 2, y - midRowOffset, rMed2);

                d.fillCircle(xMid / 4, y + bottomRowOffset + 10, rSm2);
                d.fillCircle(xMid * 7 / 4, y + bottomRowOffset + 10, rSm2);

                d.setColor(Config.DIRECT_HIT_COLORS[bg]);
                d.fillCircle(xMid, y, rSm1);

                d.setColor(Config.DIRECT_HIT_COLORS[ochre]);
                d.fillCircle(xMid / 4, y + bottomRowOffset + 10, rMini);
                d.fillCircle(xMid * 7 / 4, y + bottomRowOffset + 10, rMini);
                d.fillCircle(xMid / 4, y, rMini);
                d.fillCircle(xMid * 7 / 4, y, rMini);
                d.fillCircle(xMid, y + bottomRowOffset, rMini);
                d.fillCircle(xMid, y, rMini);

                d.setColor(Config.DIRECT_HIT_COLORS[red]);
                d.fillOval(xMid / 2 - 15, y + midRowOffset - rMed2, 30,
                        rMed2);
                d.fillOval(xMid / 2 - 15, y + midRowOffset, 30, rMed2);
                d.fillOval(xMid * 3 / 2 - 15, y + midRowOffset - rMed2, 30,
                        rMed2);
                d.fillOval(xMid * 3 / 2 - 15, y + midRowOffset, 30, rMed2);
                d.fillOval(xMid * 3 / 2 - 15, y - midRowOffset, 30, rMed2);
                d.fillOval(xMid / 2 - 15, y - midRowOffset, 30, rMed2);

                d.fillOval(xMid / 2, y + midRowOffset - 15, rMed2, 30);
                d.fillOval(xMid / 2 - rMed2, y + midRowOffset - 15, rMed2, 30);

                d.fillOval(xMid * 3 / 2, y + midRowOffset - 15, rMed2, 30);
                d.fillOval(xMid * 3 / 2 - rMed2, y + midRowOffset - 15, rMed2,
                        30);

                d.setColor(Color.WHITE);
                d.drawCircle(xMid, y, rBig);
                d.drawCircle(xMid, y, rBig - 25);
                d.drawCircle(xMid, y, rMed1);
                d.drawCircle(xMid, y, rMed1 - 25);
                d.drawLine(xMid - rBig - 25, y, xMid + rBig + 25, y);
                d.drawLine(xMid, y - rBig - 25, xMid, y + rBig + 25);
            }

            @Override
            public void timePassed() {

            }
        };
    }

    /**
     * Blocks list.
     *
     * @return the list
     */
    @Override
    public List<Block> blocks() {
        ArrayList<Block> blocks = new ArrayList<>();
        int blockSize = Config.DH_BLOCK_SIZE;
        int xPadding = blockSize / 2;
        int yPadding = blockSize / 2;
        blocks.add(new Block(Config.MID_SCREEN_W - xPadding,
                Config.DH_CENTER_Y - yPadding, blockSize, blockSize,
                Color.RED));
        return blocks;
    }

    /**
     * Number of blocks to remove int.
     *
     * @return the int
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.remainingBlocks;
    }

    @Override
    public List<Ball> initialBalls() {
        ArrayList<Ball> balls = new ArrayList<>();
        balls.add(new Ball(Config.MID_SCREEN_W,
                Config.WIN_HEIGHT - Config.BORDER_SIZE
                        - Config.PADDLE_H * Config.INIT_BALL_Y_PADDING,
                Config.BALL_SIZE, Config.BALL_CLR));
        return balls;
    }
}
