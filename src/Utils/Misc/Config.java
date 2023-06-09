package Utils.Misc;

import java.awt.Color;

/**
 * The type Config. Defines various constants used all throughout the project.
 */
public final class Config {
    public static final Color[] COLORS, GREEN_3_COLORS, DIRECT_HIT_COLORS;
    public static final Color BORDER_CLR, PADDLE_CLR, BG_CLR, DARK_BLUE,
            BALL_CLR, SCORE_BORDER_CLR, K_B, S_B;
    public static final int
            WIN_WIDTH, WIN_HEIGHT,
            FPS, MILLI,
            NUM_BORDERS, BORDER_SIZE,
            FIRST_ROW_Y, NUM_ROWS,
            BLOCK_WIDTH, BLOCK_HEIGHT, BLOCKS_IN_ROW,
            NUM_BLOCKS, FIRST_ROW_BLOCKS,
            PADDLE_W, PADDLE_H,
            BALL_SIZE, BALL_SPEED, INIT_BALL_X_PADDING, INIT_BALL_Y_PADDING,
            MID_SCREEN_W, ERR_CODE,
            SCORE_BORDER_SIZE, SCORE_Y_OFFSET, SCORE_X_OFFSET, SCORE_FONT_SIZE,
            BLOCK_SCORE, CLEAR_SCORE,
            DEATH_REGION_S,
            CD_SEC, CD_DURATION, CD_X, CD_Y_OFFSET, CD_SHDW_OFFSET, CD_SIZE,
            CD_SHDW_SIZE;

    public static final int DH_CENTER_Y, DH_R1;


    static {
        COLORS = new Color[]{
                new Color(30, 108, 251),
                new Color(163, 16, 253),
                new Color(239, 26, 171),
                new Color(245, 0, 11),
                new Color(252, 116, 0),
                new Color(253, 200, 9)
        };
        DARK_BLUE = new Color(0, 0, 136);
        BORDER_CLR = new Color(49, 19, 198);
        BG_CLR = new Color(36, 4, 105);
        PADDLE_CLR = new Color(121, 208, 255);
        SCORE_BORDER_CLR = new Color(215, 215, 217);
        K_B = new Color(57, 81, 94);
        S_B = new Color(143, 197, 225);
        BALL_CLR = Color.white;

        GREEN_3_COLORS = new Color[]{
                new Color(197, 255, 218),
                new Color(127, 202, 137),
                new Color(77, 155, 114),
                new Color(0, 82, 79),
                new Color(0, 38, 44),
                new Color(0, 4, 11)
        };

        DIRECT_HIT_COLORS = new Color[]{
                new Color(0, 9, 35), //dark blue
                new Color(163, 0, 0), // red
                new Color(242, 170, 92), //bright yellow
                new Color(214, 171, 116) //muted yellow
        };

        WIN_WIDTH = 800;
        WIN_HEIGHT = 600;

        MID_SCREEN_W = WIN_WIDTH / 2;

        NUM_BORDERS = 4;
        BORDER_SIZE = 30;

        DEATH_REGION_S = 25;

        SCORE_BORDER_SIZE = 20;
        SCORE_Y_OFFSET = 2;
        SCORE_X_OFFSET = 50;
        SCORE_FONT_SIZE = 18;
        BLOCK_SCORE = 5;
        CLEAR_SCORE = 100;

        FIRST_ROW_Y = 150;
        BLOCK_WIDTH = 48;
        BLOCK_HEIGHT = 23;

        NUM_ROWS = 6;
        BLOCKS_IN_ROW = 12;
        NUM_BLOCKS = 57;
        FIRST_ROW_BLOCKS = 12;

        PADDLE_W = 90;
        PADDLE_H = 25;

        INIT_BALL_Y_PADDING = 5;
        INIT_BALL_X_PADDING = BLOCK_WIDTH / 2;
        BALL_SIZE = 5;
        BALL_SPEED = 8;

        //frames per second and milliseconds per frame
        FPS = 60;
        MILLI = 1000;
        CD_SEC = 3;
        CD_DURATION = 2;
        CD_X = 330;
        CD_Y_OFFSET = 100;
        CD_SHDW_OFFSET = 105;
        CD_SIZE = 250;
        CD_SHDW_SIZE = 270;

        //direct hit constants
        DH_CENTER_Y = 190;
        DH_R1 = DH_CENTER_Y - 30;

        ERR_CODE = -1;
    }

    //empty private constructor to prevent accidental creation of instances
    private Config() {

    }
}
