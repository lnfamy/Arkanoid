package Utils.Misc;

import java.awt.Color;

/**
 * The type Config. Defines various constants used all throughout the project.
 */
public final class Config {
    public static final Color[] BLOCK_COLORS, GREEN_3_COLORS, DIRECT_HIT_COLORS,
            WIDE_EASY_COLORS, G3_BLOCKS_CLR, WE_BLOCKS_CLR;

    public static final Color WE_BORDER_CLR, G3_BORDER_CLR, DH_BORDER_CLR;
    public static final Color BORDER_CLR, PADDLE_CLR, BG_CLR, DARK_BLUE,
            BALL_CLR, SCORE_BORDER_CLR, K_B, S_B;
    public static final int
            WIN_WIDTH, WIN_HEIGHT,
            FPS, MILLI,
            NUM_BORDERS, BORDER_SIZE,
            FIRST_ROW_Y, G3_NUM_ROWS,
            BLOCK_WIDTH, BLOCK_HEIGHT, G3_BLOCKS_IN_ROW,
            G3_NUM_BLOCKS, G3_FIRST_ROW_BLOCKS,
            PADDLE_W, PADDLE_H,
            BALL_SIZE, BALL_SPEED, INIT_BALL_X_PADDING, INIT_BALL_Y_PADDING,
            MID_SCREEN_W, ERR_CODE,
            SCORE_BORDER_SIZE, SCORE_Y_OFFSET, SCORE_X_OFFSET, SCORE_FONT_SIZE,
            BLOCK_SCORE, CLEAR_SCORE,
            DEATH_REGION_S,
            CD_SEC, CD_DURATION, CD_X, CD_Y_OFFSET, CD_SHDW_OFFSET, CD_SIZE,
            CD_SHDW_SIZE;

    public static final int WIN_CODE = 1, LOSE_CODE = -1,
            ANGLE_UP = 180,
            BIG_FONT = 100;

    public static final int DH_CENTER_Y, DH_R1, DH_BLOCK_SIZE, DH_NUM_BLOCKS,
            DH_NUM_BALLS;
    public static final int WE_NUM_STARS, WE_NUM_BALLS, WE_NUM_BLOCKS, WE_PADDLE;

    public static final int G3_NUM_BALLS;

    static {
        BLOCK_COLORS = new Color[]{
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

        G3_BLOCKS_CLR = new Color[]{
                new Color(235, 93, 58), //reddish orange
                new Color(242, 152, 79), //orange
                new Color(236, 192, 83), //yellow
                new Color(21, 167, 149), //deep green
                new Color(41, 170, 220), //blue
        };

        WIDE_EASY_COLORS = new Color[]{
                new Color(156, 74, 210), // background gradients:
                new Color(156, 71, 205),
                new Color(157, 69, 192),
                new Color(158, 67, 186),
                new Color(70, 0, 122), // background city
                new Color(93, 19, 142), // bg city windows
                Color.black, // foreground city
                new Color(91, 91, 91), // foreground city windows
                new Color(168, 95, 218), // biggest moon highlight
                new Color(196, 151, 224), // second moon highlight
                new Color(241, 219, 255) // moon
        };

        WE_BLOCKS_CLR = new Color[]{
                new Color(140, 120, 225), //lilac
                new Color(77, 55, 93), //dark purple
                new Color(61, 45, 38), //purplish brown
                new Color(172, 147, 223), //lightest purple
        };

        WIN_WIDTH = 800;
        WIN_HEIGHT = 600;

        MID_SCREEN_W = WIN_WIDTH / 2;

        NUM_BORDERS = 4;
        BORDER_SIZE = 25;

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


        PADDLE_W = 90;
        PADDLE_H = 25;

        INIT_BALL_Y_PADDING = 2;
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
        DH_CENTER_Y = 180;
        DH_R1 = DH_CENTER_Y - 30;
        DH_BLOCK_SIZE = 50;
        DH_NUM_BLOCKS = 1;
        DH_NUM_BALLS = 1;
        DH_BORDER_CLR = new Color(138, 76, 3);

        //Wide Easy constants
        WE_NUM_STARS = 50;
        WE_NUM_BLOCKS = 15;
        WE_NUM_BALLS = 10;
        WE_PADDLE = 600;
        WE_BORDER_CLR = new Color(54, 13, 79);

        //Green 3 constants
        G3_NUM_BALLS = 2;
        G3_NUM_ROWS = 5;
        G3_BLOCKS_IN_ROW = 10;
        G3_NUM_BLOCKS = 57;
        G3_FIRST_ROW_BLOCKS = 12;
        G3_BORDER_CLR = new Color(70, 119, 113);

        ERR_CODE = -1;
    }

    //empty private constructor to prevent accidental creation of instances
    private Config() {

    }
}
