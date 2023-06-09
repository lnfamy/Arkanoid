package Game.Levels;

import Sprites.Block;
import Sprites.Sprite;
import Utils.Geometry.Velocity;
import Utils.Misc.Config;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.List;

/**
 * The type Direct hit.
 */
public class DirectHit implements LevelInformation {
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
