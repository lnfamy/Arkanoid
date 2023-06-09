package Game.Levels;

import Sprites.Block;
import Sprites.Sprite;
import Utils.Geometry.Velocity;
import Utils.Misc.Config;
import biuoop.DrawSurface;

import java.util.List;

/**
 * The type Green 3.
 */
public class Green3 implements LevelInformation {
    /**
     * Number of balls int.
     *
     * @return the int
     */
    @Override
    public int numberOfBalls() {
        return 0;
    }

    /**
     * Initial ball velocities list.
     *
     * @return the list
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        return null;
    }

    /**
     * Paddle speed int.
     *
     * @return the int
     */
    @Override
    public int paddleSpeed() {
        return 0;
    }

    /**
     * Paddle width int.
     *
     * @return the int
     */
    @Override
    public int paddleWidth() {
        return 0;
    }

    /**
     * Level name string.
     *
     * @return the string
     */
    @Override
    public String levelName() {
        return null;
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
        return null;
    }

    /**
     * Number of blocks to remove int.
     *
     * @return the int
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 0;
    }
}
