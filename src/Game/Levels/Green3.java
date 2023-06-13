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
 * The type Green 3.
 */
public class Green3 implements LevelInformation {
    private int remainingBlocks = 0;
    private int killBlockI, killBlockJ, spBlockI, spBlockJ;

    /**
     * Number of balls int.
     *
     * @return the int
     */
    @Override
    public int numberOfBalls() {
        return Config.G3_NUM_BALLS;
    }

    /**
     * Initial ball velocities list.
     *
     * @return the list
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocities = new ArrayList<>();
        for (int i = 0; i < Config.G3_NUM_BALLS; i++) {
            velocities.add(Velocity.fromAngleAndSpeed(Config.ANGLE_DOWN,
                    Config.BALL_SPEED));
        }
        return velocities;
    }

    /**
     * Paddle speed int.
     * double the default paddle speed.
     *
     * @return the int
     */
    @Override
    public int paddleSpeed() {
        return 10;
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
        return "Green 3";
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
                int currentLayer = 0;
                int width = Config.WIN_WIDTH, height = Config.WIN_HEIGHT;

                int treeWidth = 30;
                int r = 75;
                int[] y = {height - 230, height - 240, height - 250, height - 260,
                        height - 270};

                d.setColor(Config.GREEN_3_COLORS[currentLayer]);
                d.fillRectangle(0, 0, width, height);

                currentLayer++;
                d.setColor(Config.GREEN_3_COLORS[currentLayer]);
                d.fillCircle(20, y[0], r);
                d.fillCircle(30 + 1 * 75, y[3], r);
                d.fillCircle((int) (30 + 2.5 * 75), y[0], r);
                d.fillCircle(30 + 4 * 75, y[4], r);
                d.fillCircle((int) (30 + 5.5 * 75), y[2], r);
                d.fillCircle((int) (30 + 7 * 75), y[1], r);
                d.fillCircle((int) (30 + 8.5 * 75), y[3], r);
                d.fillCircle((int) (30 + 10 * 75), y[0], r);
                d.fillRectangle(0, 0, width, y[0]);
                d.fillRectangle((int) (r), y[0], treeWidth,
                        Math.abs(height - y[0]));
                d.fillRectangle(width - r * 5, y[0], treeWidth,
                        Math.abs(height - y[0]));

                currentLayer++;
                d.setColor(Config.GREEN_3_COLORS[currentLayer]);
                r = 120;
                treeWidth += 10;
                for (int i = 0; i < y.length; i++) {
                    y[i] = y[i] - 100;
                }
                d.fillCircle(20, y[0], r);
                d.fillCircle(30 + 1 * r, y[0], r);
                d.fillCircle((int) (2 * r), y[1], r);
                d.fillCircle((int) (30 + 2.5 * r), y[2], (int) (r * 0.75));
                d.fillCircle((int) (30 + 5.5 * 75), y[2], r);
                d.fillCircle((int) (30 + 7 * 75), y[1], r);
                d.fillCircle((int) (30 + 8.5 * 75), y[3], r);
                d.fillCircle((int) (30 + 10 * 75), y[0], r);
                d.fillRectangle(0, 0, width, y[0]);
                d.fillRectangle((int) (r * 1.5), y[0], treeWidth,
                        Math.abs(height - y[0]));
                d.fillRectangle(width - r * 2, y[0], treeWidth,
                        Math.abs(height - y[0]));

                currentLayer++;
                d.setColor(Config.GREEN_3_COLORS[currentLayer]);
                r -= 20;
                treeWidth += 20;
                for (int i = 0; i < y.length; i++) {
                    y[i] = y[i] - 30;
                }
                d.fillCircle(20, y[0], r);
                d.fillCircle(30 + 1 * r, y[0], r);
                d.fillCircle((int) (2 * r), y[1], r);
//                d.setColor(Color.BLACK);
                d.fillCircle((int) (30 + 2.5 * r), y[2], (int) (r * 1.25));
//                d.setColor(Config.GREEN_3_COLORS[currentLayer]);

                d.fillCircle((int) (30 + 5.5 * 75), y[2], r);
                d.fillCircle((int) (30 + 7 * 75), y[1], r);
                d.fillCircle((int) (30 + 8.5 * 75), y[3], r);
                d.fillCircle((int) (30 + 10 * 75), y[0], r);
                d.fillRectangle(0, 0, width, y[0]);
                d.fillRectangle((int) (r * 2.5), y[0], treeWidth,
                        Math.abs(height - y[0]));
                d.fillRectangle((int) (width - r * 3), y[0], treeWidth,
                        Math.abs(height - y[0]));
                d.fillRectangle((int) (width - r / 2), y[0], treeWidth - 20,
                        Math.abs(height - y[0]));

                currentLayer++;
                d.setColor(Config.GREEN_3_COLORS[currentLayer]);
                r += 20;
                for (int i = 0; i < y.length; i++) {
                    y[i] = y[i] - 75;
                }

                treeWidth -= 10;
                d.fillCircle(20, y[0], r);
                d.fillCircle(30 + 1 * r, y[2], r);
                d.fillCircle((int) (30 + 2 * r), y[1], (int) (r * 1.25));
                d.fillCircle((int) (30 + 3.5 * r), y[3], r);
                d.fillCircle((int) (30 + 5 * r), y[4], r);
                d.fillCircle((int) (30 + 6 * r), y[0], r);

                d.fillRectangle(0, 0, width, y[0]);
                d.fillRectangle((int) (r / 2), y[0], treeWidth,
                        Math.abs(height - y[0]));
                d.fillRectangle((int) (width - r * 4.25), y[0], treeWidth,
                        Math.abs(height - y[0]));
                d.fillRectangle((int) (width - r * 1.5), y[0], treeWidth - 5,
                        Math.abs(height - y[0]));


                currentLayer++;
                d.setColor(Config.GREEN_3_COLORS[currentLayer]);
                r -= 30;
                for (int i = 0; i < y.length; i++) {
                    y[i] = y[i] - 60;
                }
                d.fillCircle(20, y[0], (int) (r * 1.25));
                d.fillCircle((int) (30 + 1.25 * r), y[2], r);
                d.fillCircle((int) (30 + 2.5 * r), y[3], r);
                d.fillCircle(30 + 4 * r, y[2], (int) (r * 1.5));

                d.fillCircle((int) (30 + 5.5 * r), y[0] + 15, (int) (r * 0.75));
                d.fillCircle((int) (30 + 4.5 * r), y[0], r);
                d.fillCircle((int) (30 + 6 * r), y[0], (int) (r));

                d.fillCircle((int) (30 + 7.5 * r), y[0], (int) (r * 1.25));
                d.fillCircle((int) (30 + 8.5 * r), y[0], r);
                d.fillRectangle(0, 0, width, y[2]);
                d.fillRectangle((int) (r), y[0], treeWidth,
                        Math.abs(height - y[0]));
                d.fillRectangle(width - r * 5, y[0], treeWidth,
                        Math.abs(height - y[0]));
                d.fillRectangle(width - r, y[0], treeWidth - 15,
                        Math.abs(height - y[0]));
                d.fillRectangle(0, height - 30, width, 50);
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
        int blockW = Config.BLOCK_WIDTH, blockH = Config.BLOCK_HEIGHT;

        Random rand = new Random();
        int kBlockI = (int) rand.nextDouble(Config.G3_NUM_ROWS);
        int kBlockJ = -1;

        int sBlockI = (int) rand.nextDouble(Config.G3_NUM_ROWS);
        int sBlockJ = -1;

        int y = Config.FIRST_ROW_Y;

        for (int i = 0; i < Config.G3_NUM_ROWS; i++) {
            if (i == kBlockI) {
                kBlockJ = (int) rand.nextDouble(
                        Config.G3_BLOCKS_IN_ROW - i);
            }
            if (i == sBlockI) {
                sBlockJ = (int) rand.nextDouble(
                        Config.G3_BLOCKS_IN_ROW - i);
                if (kBlockI == sBlockI) {
                    while (kBlockJ == sBlockJ) {
                        sBlockJ = (int) rand.nextDouble(
                                Config.G3_BLOCKS_IN_ROW - i);
                    }
                }
            }

            for (int j = 0; j < Config.G3_BLOCKS_IN_ROW - i; j++) {
                int x = Config.WIN_WIDTH - Config.BORDER_SIZE - blockW * (j + 1);
                Color clr = Config.BLOCK_COLORS[i % Config.BLOCK_COLORS.length];
                if (i == kBlockI && j == kBlockJ) {
                    clr = Config.K_B;
                    kBlockI = -1;
                } else if (i == sBlockI && j == sBlockJ) {
                    clr = Config.S_B;
                    sBlockI = -1;
                }
                Block b = new Block(x, y + i * blockH, blockW, blockH, clr);
                blocks.add(b);
                this.remainingBlocks++;
            }
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
        return Config.GREEN_3_COLORS[(int) rand.nextDouble(
                Config.GREEN_3_COLORS.length-2)];
    }

    @Override
    public Color BorderColor() {
        return Config.G3_BORDER_CLR;
    }

    @Override
    public List<Ball> initialBalls() {
        double genX = Config.MID_SCREEN_W;
        double y = Config.WIN_HEIGHT - Config.BORDER_SIZE
                - Config.PADDLE_H * Config.INIT_BALL_Y_PADDING;

        ArrayList<Ball> balls = new ArrayList<>();
        balls.add(new Ball(genX + Config.INIT_BALL_X_PADDING, y,
                Config.BALL_SIZE, Config.BALL_CLR));
        balls.add(new Ball(genX - Config.INIT_BALL_X_PADDING, y,
                Config.BALL_SIZE, Config.BALL_CLR));

        return balls;
    }

}
