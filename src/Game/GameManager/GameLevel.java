package Game.GameManager;

import Animations.Animation;
import Animations.AnimationRunner;
import Animations.CountdownAnimation;
import Game.Collisions.Listeners.BallAdder;
import Game.Collisions.Listeners.BallRemover;
import Game.Collisions.Listeners.BlockRemover;
import Game.Collisions.Listeners.ScoreTrackingListener;
import Game.Menus.PauseScreen;
import Utils.Misc.Config;
import Sprites.Ball;
import Sprites.Block;
import Sprites.Paddle;

import Game.Collisions.CollisionManager.Collidable;
import Utils.Geometry.Rectangle;
import Utils.Geometry.Velocity;
import Sprites.Sprite;
import Utils.Misc.Counter;
import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;
import java.util.Random;

/**
 * The type GameLevel.
 */
public class GameLevel implements Animation {
    private final SpriteCollection sprites = new SpriteCollection();
    private final GameEnvironment environment = new GameEnvironment();
    private final GUI g = new GUI("Arkanoid", Config.WIN_WIDTH,
            Config.WIN_HEIGHT);
    private final biuoop.KeyboardSensor keyboard = g.getKeyboardSensor();
    private final Counter remainingBlocks = new Counter();
    private final Counter remainingBalls = new Counter();
    private final Counter score = new Counter();
    private AnimationRunner runner = new AnimationRunner(this.g);
    private boolean running;

    /**
     * Adds collidable to the game.
     * Adds a collidable object to this game's GameEnvironment.
     *
     * @param c the collidable object to be added
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds sprite to the game.
     * Adds a sprite object to this game's GameEnvironment.
     *
     * @param s the sprite to be added
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Gets environment GameEnvironment.
     *
     * @return this GameLevel object's GameEnvironment.
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * Gets sprites SpriteCollection.
     *
     * @return the sprite collection of all the sprites in this game.
     */
    public SpriteCollection getSprites() {
        return this.sprites;
    }

    /**
     * Initialize.
     * Method initializes all game objects: background, borders, blocks,
     * paddle, balls and all appropriate hit listeners.
     */
    public void initialize() {
        /*
        Initializing all HitListeners:
            + BlockRemover: HitListener for blocks, in charge of removing a
            block once it has been hit.

            + BallRemover: HitListener for the DeathRegion block (bottom of
            game window) and the killing block (randomized location in
            between all game blocks), in charge of removing a ball once it has
            hit either the death region or the killing block.

            + BallAdder: HitListener for the special block (randomized
            location in between all game blocks), in charge of adding another
             ball once a ball hits it.

            + ScoreTrackingListener: HitListener for all blocks in game. In
            charge of adding 5 points to the game's score for every block hit.
         */
        BlockRemover blockRemover = new BlockRemover(this, remainingBlocks);
        BallRemover ballRemover = new BallRemover(this, remainingBalls);
        BallAdder ballAdder = new BallAdder(this,
                remainingBalls);
        ScoreTrackingListener sc = new ScoreTrackingListener(score);
        initDeathRegion(ballRemover);

        //initializing background, score sprite, blocks, paddle and balls
        //initBg();
        initBgTest();
        initScoreBoard();
//        initBlocks(blockRemover, ballRemover, ballAdder, sc);
        initPaddle();
        initBalls();

    }

    /**
     * Init Bg.
     * initializes the game borders and the background.
     */
    public void initBg() {
        /*
        Background is made using a screen-sized block, which is then added
        only to the sprite collection and not to the collidables list, to
        prevent collisions with the background
        */
        Block background = new Block(0, 0, Config.WIN_WIDTH,
                Config.WIN_HEIGHT, Config.BG_CLR);
        this.addSprite(background);

        Color borderColor = Config.BORDER_CLR;
        int width = Config.WIN_WIDTH,
                height = Config.WIN_HEIGHT,
                size = Config.BORDER_SIZE,
                upperOffset = Config.SCORE_BORDER_SIZE;

        Block[] borders = new Block[]{
                /*
                ORDER: left border, right border, upper border
                 */
                new Block(0, 0, size, height, borderColor),
                new Block(width - size, 0, size, height, borderColor),
                new Block(0, upperOffset, width, size, borderColor)
        };

        /*
        adding borders to game as both collidable and sprite, but not giving
        them hit listeners so that they don't disappear upon collision and
        serve as a frame for the game so the ball doesn't leave the game window
         */
        for (Block b : borders) {
            b.addToGame(this);
        }
    }

    /**
     * Init bg test.
     */
    public void initBgTest() {
        this.addSprite();
    }

    /**
     * Init score board.
     * Initializes the game's score border sprite (borderless rectangle), and
     * its scoreIndicator. Adds the score indicator to the game.
     */
    public void initScoreBoard() {
        Rectangle scoreBorder = new Rectangle(0, 0, Config.WIN_WIDTH,
                Config.SCORE_BORDER_SIZE);
        ScoreIndicator scoreIndicator = new ScoreIndicator(score, scoreBorder);
        scoreIndicator.addToGame(this);
    }


    /**
     * Init death region.
     * Initializes death region, which is an off-screen block positioned
     * a little under the screen to allow the ball to vanish off-screen
     * before its removal.
     *
     * @param ballRemover Ball remover listener to remove a ball upon its
     *                    collision with the death region block
     */
    public void initDeathRegion(BallRemover ballRemover) {
        Block deathRegion = new Block(0,
                Config.WIN_HEIGHT + Config.BALL_SIZE,
                Config.WIN_WIDTH, Config.BORDER_SIZE,
                Config.BG_CLR);
        deathRegion.addToGame(this);
        deathRegion.addHitListener(ballRemover);
    }

    /**
     * Init blocks.
     * Initializes all collidable blocks in game.
     *
     * @param blockRemover the block remover listener, removes blocks upon
     *                     collision with a ball.
     * @param ballRemover  the ball remover listener, removes a ball upon
     *                     collision with the death region block or the
     *                     killer block
     * @param ballAdder    the ball adder listener, adds a ball upon
     *                     collision with the special block
     * @param score        the score tracking listener, adds 5 points to the
     *                     score counter for every block removed
     */
    public void initBlocks(BlockRemover blockRemover, BallRemover ballRemover,
                           BallAdder ballAdder, ScoreTrackingListener score) {
        Block[][] blocks = new Block[Config.NUM_ROWS][Config.BLOCKS_IN_ROW];
        int blockW = Config.BLOCK_WIDTH, blockH = Config.BLOCK_HEIGHT;
        int initHeight = Config.FIRST_ROW_Y;
        int blocksInRow = Config.FIRST_ROW_BLOCKS;

        /*
        Killer block / special block implementation:
        Initializing random variable to randomize the location of the killer
        block (kBlock) and the special block (sBlock). sBlock and kBlock can
        appear in the same row, or the same column, but not in the same spot.

        sets initial column index value to -1, as it's an impossible index,
        therefore no killer block will be created before both indexes
        are initialized.
         */
        Random rand = new Random();
        int kBlockI = (int) rand.nextDouble(Config.NUM_ROWS);
        int kBlockJ = -1;

        int sBlockI = (int) rand.nextDouble(Config.NUM_ROWS);
        int sBlockJ = -1;

        /*
        creates 6 rows of blocks where the first row has 12 blocks, the
        second one has 11, and so on up until the last row, which has 7
        blocks -- using nested loops. outer loop is responsible for the rows,
        where the inner loop is responsible for the columns.
        */
        double y = initHeight;
        for (int i = 0; i < Config.NUM_ROWS; i++) {
            /*
            For each inner loop iteration, j goes from blocksInRow to 0. Once
             the inner loop is done, blocksInRow is decremented by 1 to
             create the decreasing number of blocks across the rows.
             */
            int j = blocksInRow - 1;
            /*
            j index of kBlock/sBlock is only computed once we've reached the
            correct row, as j's max value changes with every row.
             */
            if (i == kBlockI) {
                kBlockJ = (int) rand.nextDouble(j);
            }
            if (i == sBlockI) {
                sBlockJ = (int) rand.nextDouble(j);
                //if kBlock and jBlock are in the same spot, get new j index
                if (kBlockI == sBlockI) {
                    while (sBlockJ == kBlockJ) {
                        sBlockJ = (int) rand.nextDouble(j);
                    }
                }
            }
            while (j >= 0) {
                double x = Config.WIN_WIDTH
                        - Config.BORDER_SIZE - blockW * (j + 1);
                /*
                initialize kBlock, sBlock and all other blocks separately as
                they have different colors, and kBlock and sBlock have
                different listeners in addition to blockRemover
                 */
                if (j == kBlockJ) {
                    blocks[i][j] = new Block(x, y, blockW, blockH, Config.K_B);
                    blocks[i][j].addHitListener(ballRemover);
                    kBlockJ = -1;
                } else if (j == sBlockJ) {
                    blocks[i][j] = new Block(x, y, blockW, blockH, Config.S_B);
                    blocks[i][j].addHitListener(ballAdder);
                    sBlockJ = -1;
                } else {
                    blocks[i][j] = new Block(x, y, blockW,
                            blockH, Config.COLORS[i]);
                }
                //all blocks have a blockRemover and score listener
                blocks[i][j].addHitListener(blockRemover);
                blocks[i][j].addHitListener(score);
                blocks[i][j].addToGame(this);
                j--;
            }
            //y increases by block height for the next row of blocks.
            y += blockH;
            blocksInRow--;
        }

        //setting game blocks counter to initial value
        this.remainingBlocks.increase(Config.NUM_BLOCKS);
    }

    /**
     * Init paddle.
     * Method initializes a new paddle and adds it to the game.
     */
    public void initPaddle() {
        //to place the paddle in the middle of the screen
        double x = Config.WIN_WIDTH / 2.0 - Config.PADDLE_W / 2.0;
        double y = Config.WIN_HEIGHT - Config.BORDER_SIZE - Config.PADDLE_H;
        Rectangle rect = new Rectangle(x, y, Config.PADDLE_W, Config.PADDLE_H);

        Paddle paddle = new Paddle(rect, Config.PADDLE_CLR, this.keyboard);

        paddle.addToGame(this);
    }

    /**
     * Init balls.
     * Method initializes the balls for the game and adds them to it.
     */
    public void initBalls() {
        double genX = Config.MID_SCREEN_W;
        double y = Config.WIN_HEIGHT - Config.BORDER_SIZE
                - Config.PADDLE_H * Config.INIT_BALL_Y_PADDING;
        Ball[] balls = new Ball[]{
                new Ball(genX, y, Config.BALL_SIZE, Config.BALL_CLR),
                new Ball(genX + Config.INIT_BALL_X_PADDING, y,
                        Config.BALL_SIZE, Config.BALL_CLR),
                new Ball(genX - Config.INIT_BALL_X_PADDING, y,
                        Config.BALL_SIZE, Config.BALL_CLR),
        };

        for (Ball b : balls) {
            remainingBalls.increase(1);
            b.setGameEnvironment(this.environment);
            b.setVelocity(Velocity
                    .fromAngleAndSpeed(0, Config.BALL_SPEED));
            b.addToGame(this);
        }
    }

    /**
     * Remove collidable.
     *
     * @param c the collidable to be removed
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Remove sprite.
     *
     * @param s the sprite to be removed
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * Run.
     */
    public void run() {
        this.initialize();
        this.runner.run(new CountdownAnimation(Config.CD_DURATION,
                Config.CD_SEC, this.sprites));
        this.running = true;
        this.runner.run(this);
    }

    /**
     * Do one frame.
     *
     * @param d the drawSurface from this game's GUI
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        boolean finishedGame = this.remainingBlocks.getValue() == 0
                || this.remainingBalls.getValue() == 0;

        if (finishedGame) {
            this.running = false;
        }

        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }

    }

    /**
     * Should stop boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
