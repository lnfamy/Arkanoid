package Game.Levels;

import Sprites.Ball;
import Sprites.Block;
import Sprites.Sprite;
import Utils.Geometry.Velocity;
import Utils.Misc.Config;
import biuoop.DrawSurface;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The type Wide easy.
 */
public class WideEasy implements LevelInformation {
    /**
     * Number of balls int.
     */
    private final int[] x = new int[Config.WE_NUM_STARS];
    private final int[] y = new int[Config.WE_NUM_STARS];
    private final int numBlocks = 15;
    private int remainingBlocks = Config.WE_NUM_BLOCKS;

    /**
     * Instantiates a new Wide easy.
     */
    public WideEasy() {
        Random rand = new Random();
        for (int i = 0; i < Config.WE_NUM_STARS; i++) {
            x[i] = (int) rand.nextDouble(Config.WIN_WIDTH);
            y[i] = (int) rand.nextDouble(Config.WIN_HEIGHT * 0.6);
        }
    }

    @Override
    public int numberOfBalls() {
        return Config.WE_NUM_BALLS;
    }

    /**
     * Initial ball velocities list.
     *
     * @return the list
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocities = new ArrayList<>();
        int ballsFirstHalf = Config.WE_NUM_BALLS / 2;
        for (int i = 0; i < ballsFirstHalf; i++) {
            velocities.add(Velocity.fromAngleAndSpeed(
                    Config.ANGLE_DOWN + i * 5, Config.BALL_SPEED));
        }
        for (int i = ballsFirstHalf; i < Config.WE_NUM_BALLS; i++) {
            velocities.add(Velocity.fromAngleAndSpeed(
                    Config.ANGLE_DOWN - i * 5, Config.BALL_SPEED));
        }
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
        return Config.WIN_WIDTH * 8 / 10;
    }

    /**
     * Level name string.
     *
     * @return the string
     */
    @Override
    public String levelName() {
        return "Wide Easy";
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
                int width = Config.WIN_WIDTH, height = Config.WIN_HEIGHT;
                int bgMax = 4;
                for (int i = 0; i < bgMax; i++) {
                    d.setColor(Config.WIDE_EASY_COLORS[i]);
                    d.fillRectangle(0, height * i / 4, width,
                            height * (bgMax - i) / 4);
                }

                int starsToDraw = 50, starRadius = 3;
                for (int i = 0; i < starsToDraw; i++) {
                    d.setColor(Config.WIDE_EASY_COLORS[8]);
                    d.fillCircle(x[i], y[i], starRadius);
                    d.setColor(Config.WIDE_EASY_COLORS[10]);
                    d.fillCircle(x[i], y[i], starRadius - 1);
                }

                d.setColor(Config.WIDE_EASY_COLORS[bgMax]);
                int med = 100,
                        med2 = 150,
                        big = 200,
                        small = 50,
                        mini = 25;

                int[] winRows = new int[10];
                for (int i = 0, j = 1; i < winRows.length; i++, j++) {
                    winRows[i] = height * j / 10;
                }
                int currX = 0;
                d.fillRectangle(currX, winRows[5], med, winRows[3]);
                currX += med;
                d.fillRectangle(currX, winRows[3], med, winRows[5]);
                currX += med;
                d.fillRectangle(currX, winRows[6], small, winRows[2]);
                currX += small;
                d.fillRectangle(currX, winRows[2], med2, winRows[6]);
                currX += med2;
                d.fillRectangle(currX, winRows[4], mini, winRows[4]);
                currX += mini;
                d.fillRectangle(currX, winRows[5], mini, winRows[3]);
                currX += mini;
                d.fillRectangle(currX, winRows[2], small, winRows[6]);
                currX += small;
                d.fillRectangle(currX, winRows[5], mini, winRows[3]);
                d.fillRectangle(currX, winRows[3], med, winRows[5]);
                currX += med;
                d.fillRectangle(currX, winRows[7], small, winRows[1]);
                currX += small;
                d.fillRectangle(currX, winRows[4], small, winRows[4]);
                currX += small;
                d.fillRectangle(currX, winRows[3], small, winRows[5]);
                currX += small;
                d.fillRectangle(currX, winRows[2], big, winRows[6]);

                //foreground buildings
                int foreground = bgMax + 2;
                currX = 0;
                d.setColor(Config.WIDE_EASY_COLORS[foreground]);
                d.fillRectangle(currX, winRows[6], big * 2 / 3, winRows[2]);
                currX += big / 3;
                d.fillRectangle(currX, winRows[7], med2, winRows[1]);
                currX += med2 - small / 2;
                d.fillRectangle(currX, winRows[4], small, winRows[4]);
                currX += small;
                d.fillRectangle(currX, winRows[3], small, winRows[5]);
                currX += small;
                d.fillRectangle(currX, winRows[9] + 10, small, winRows[8]);
                currX += small;
                d.fillRectangle(currX, winRows[8] - 10, small, winRows[1]);
                currX += small;
                d.fillRectangle(currX, winRows[5], med, winRows[3]);
                currX += med;
                d.fillRectangle(currX, winRows[8], mini, winRows[0]);
                currX += mini;
                d.fillRectangle(currX, winRows[4], small, winRows[4]);
                currX += small;
                d.fillRectangle(currX, winRows[6], med, winRows[2]);
                currX += med;
                d.fillRectangle(currX, winRows[8], mini, winRows[0]);
                currX += mini;
                d.fillRectangle(currX, winRows[3], med2, winRows[5]);

                //adding moon
                int bigRadius = 60,
                        offset = 5,
                        moonX = width * 5 / 6;
                int moonLayer = 8;
                d.setColor(Config.WIDE_EASY_COLORS[moonLayer]);
                d.fillCircle(moonX, 2 * bigRadius, bigRadius);
                moonLayer++;
                d.setColor(Config.WIDE_EASY_COLORS[moonLayer]);
                d.fillCircle(moonX, 2 * bigRadius, bigRadius - offset);
                moonLayer++;
                d.setColor(Config.WIDE_EASY_COLORS[moonLayer]);
                d.fillCircle(moonX, 2 * bigRadius, bigRadius - 2 * offset);
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
        int blockWidth = (Config.WIN_WIDTH - Config.BORDER_SIZE*2) / numBlocks;
        double initX = Config.BORDER_SIZE;
        double initY = Config.WIN_HEIGHT * 0.3;
        Color blockClr = Color.BLACK;
        for (int i = 0; i < this.numBlocks; i++) {
            if (i < 2 || i > 12 || (i > 5 && i < 9)) {
                blockClr = Config.WIDE_EASY_COLORS[10];
            } else if ((i < 4) || ((i > 10) && (i < 13))) {
                blockClr = Config.WIDE_EASY_COLORS[5];
            } else if (i < 6 || i > 9 && i < 12) {
                blockClr = Config.WIDE_EASY_COLORS[6];
            }
            blocks.add(new Block(initX + i * blockWidth, initY, blockWidth,
                    Config.BLOCK_HEIGHT, blockClr));

        }
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
    public void setRemainingBlocks(int remainingBlocks) {
        this.remainingBlocks = remainingBlocks;
    }

    @Override
    public Color PaddleColor() {
        Random rand = new Random();
        return Config.WIDE_EASY_COLORS[(int) rand.nextDouble(
                Config.WIDE_EASY_COLORS.length)];
    }

    @Override
    public Color BorderColor() {
        return Config.WE_BORDER_CLR;
    }

    @Override
    public List<Ball> initialBalls() {
        ArrayList<Ball> balls = new ArrayList<>();
        int initX = Config.MID_SCREEN_W - Config.INIT_BALL_X_PADDING;
        int initY = Config.WIN_HEIGHT / 3 + Config.BLOCK_HEIGHT;
        for (int i = 0; i < Config.WE_NUM_BALLS / 2; i++) {
            balls.add(new Ball(initX + i * Config.INIT_BALL_X_PADDING,
                    initY + i * Config.INIT_BALL_Y_PADDING, Config.BALL_SIZE,
                    Config.BALL_CLR));
        }
        for (int i = 0; i < Config.WE_NUM_BALLS / 2; i++) {
            balls.add(new Ball(initX - i * Config.INIT_BALL_X_PADDING,
                    initY + i * Config.INIT_BALL_Y_PADDING, Config.BALL_SIZE,
                    Config.BALL_CLR));
        }

        return balls;
    }
}
