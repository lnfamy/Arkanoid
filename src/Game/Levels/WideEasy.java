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
 * The type Wide easy.
 */
public class WideEasy implements LevelInformation {
    /**
     * Number of balls int.
     */
    private final int[] x = new int[Config.WE_NUM_STARS];
    private final int[] y = new int[Config.WE_NUM_STARS];
    private int remainingBlocks = Config.WE_NUM_BLOCKS;
    private Color randomColor;

    /**
     * Instantiates a new Wide easy.
     */
    public WideEasy() {
        Random rand = new Random();
        for (int i = 0; i < Config.WE_NUM_STARS; i++) {
            x[i] = (int) rand.nextDouble(Config.WIN_WIDTH);
            y[i] = (int) rand.nextDouble(Config.WIN_HEIGHT * 0.6);
        }
        this.randomColor = Config.WE_BLOCKS_CLR[(int) rand.nextDouble(
                Config.WE_BLOCKS_CLR.length - 1)];
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
        for (int i = 0, angle = Config.ANGLE_UP;
             i < ballsFirstHalf; i++, angle += 10) {
            velocities.add(Velocity.fromAngleAndSpeed(angle, Config.BALL_SPEED));
        }
        for (int i = ballsFirstHalf, angle = Config.ANGLE_UP;
             i < Config.WE_NUM_BALLS; i++, angle -= 10) {
            velocities.add(Velocity.fromAngleAndSpeed(angle, Config.BALL_SPEED));
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
                    d.drawLine(x[i] - 3, y[i], x[i] + 3, y[i]);
                    d.drawLine(x[i], y[i] - 3, x[i], y[i] + 3);
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
        int numBlocks = 15;
        int blockWidth = (Config.WIN_WIDTH - Config.BORDER_SIZE * 2) / numBlocks;
        double initX = Config.BORDER_SIZE;
        double initY = Config.WIN_HEIGHT * 0.3;
        Color blockClr;
        for (int i = 0; i < numBlocks; i++) {
            if (i < 9 && i > 5) {
                blockClr = Config.WE_BLOCKS_CLR[3];
            } else if (i < 2 || i > 12) {
                blockClr = Config.WE_BLOCKS_CLR[0];
            } else if (i < 4 || i > 10) {
                blockClr = Config.WE_BLOCKS_CLR[1];
            } else {
                blockClr = Config.WE_BLOCKS_CLR[2];
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
        return this.randomColor;
    }

    @Override
    public Color BorderColor() {
        return this.randomColor;
    }
}
