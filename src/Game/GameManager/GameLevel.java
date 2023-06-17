package Game.GameManager;

import Animations.Animation;
import Animations.AnimationRunner;
import Animations.CountdownAnimation;
import Game.Collisions.Listeners.BallAdder;
import Game.Collisions.Listeners.BallRemover;
import Game.Collisions.Listeners.BlockRemover;
import Game.Collisions.Listeners.ScoreTrackingListener;
import Game.Levels.DirectHit;
import Game.Levels.Green3;
import Game.Levels.LevelInformation;
import Game.Levels.WideEasy;
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
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The type GameLevel.
 */
public class GameLevel implements Animation {
    private final SpriteCollection sprites = new SpriteCollection();
    private final GameEnvironment environment = new GameEnvironment();
    private final biuoop.KeyboardSensor keyboard;
    private final Counter remainingBlocks = new Counter();
    private final Counter remainingBalls = new Counter();
    private final Counter score;
    private AnimationRunner runner;
    private LevelInformation levelInfo;

    private int gameStatus = 0;
    private boolean running;

    public GameLevel(LevelInformation levelInfo, KeyboardSensor keyboard,
                     AnimationRunner runner, Counter score) {
        this.runner = runner;
        this.score = score;
        this.levelInfo = levelInfo;
        this.keyboard = keyboard;
    }

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
        //initializing level listeners
        BallRemover ballRemover = new BallRemover(this,
                levelInfo.numberOfBalls());
        BlockRemover blockRemover = new BlockRemover(this,
                this.levelInfo.numberOfBlocksToRemove());
        BallAdder ballAdder = new BallAdder(this,
                this.levelInfo.numberOfBalls());
        ScoreTrackingListener sc = new ScoreTrackingListener(this.score);

        //initializing all level objects and sprites
        initBg();
        initScoreBoard();
        initBalls();
        initPaddle();
        initDeathRegion(ballRemover);
//        initGroundTest();
        initBlocks(blockRemover, ballRemover, ballAdder, sc);
    }

    /**
     * Init Bg.
     * initializes the game borders and the background.
     */
    public void initBg() {
        //adding level specific background sprites
        this.addSprite(this.levelInfo.getBackground());

        //adding level borders
        Color borderColor = this.levelInfo.BorderColor();
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
        adding borders to game as both collidables and sprites, but not giving
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
        DirectHit dh = new DirectHit();
        Green3 g = new Green3();
        WideEasy w = new WideEasy();
        this.addSprite(w.getBackground());
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

        LevelNameSprite levelName = new LevelNameSprite(
                levelInfo.levelName(), scoreBorder);
        levelName.addToGame(this);
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

    public void initGroundTest() {
        Block deathRegion = new Block(0,
                Config.WIN_HEIGHT + Config.BALL_SIZE,
                Config.WIN_WIDTH, Config.BORDER_SIZE,
                Config.BG_CLR);
        deathRegion.addToGame(this);
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
        List<Block> blocks = this.levelInfo.blocks();

        for (Block b : blocks) {
            b.addHitListener(score);
            b.addHitListener(blockRemover);

            /*if (b.getColor() == Config.K_B) {
                b.addHitListener(ballRemover);
            } else if (b.getColor() == Config.S_B) {
                b.addHitListener(ballAdder);
            }*/
            b.addToGame(this);
        }
    }

    /**
     * Init paddle.
     * Method initializes a new paddle and adds it to the game.
     */
    public void initPaddle() {
        //to place the paddle in the middle of the screen
        double x = (Config.WIN_WIDTH - this.levelInfo.paddleWidth()) * 0.5;
        double y = Config.WIN_HEIGHT - Config.BORDER_SIZE - Config.PADDLE_H;
        Rectangle rect = new Rectangle(x, y, levelInfo.paddleWidth(),
                Config.PADDLE_H);

        Paddle paddle = new Paddle(rect, this.levelInfo.PaddleColor(),
                this.keyboard, this.levelInfo.paddleSpeed());

        paddle.addToGame(this);
    }

    /**
     * Init balls.
     * Method initializes the balls for the game and adds them to it.
     */
    public void initBalls() {
        double genX = Config.MID_SCREEN_W;
        double y = Config.WIN_HEIGHT - Config.PADDLE_H * 2.5;

        List<Velocity> velocities = levelInfo.initialBallVelocities();

        for (int i = 0; i < this.levelInfo.numberOfBalls(); i++) {
            Ball b = new Ball(genX, y, Config.BALL_SIZE, Config.BALL_CLR);
            b.setGameEnvironment(this.environment);
            b.setVelocity(velocities.get(i));
            b.addToGame(this);
            this.remainingBalls.increase(1);
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

        if (this.levelInfo.numberOfBlocksToRemove() == 0) {
            this.gameStatus = Config.WIN_CODE;
            this.running = false;
        }

        if (remainingBalls.getValue() == 0) {
            this.gameStatus = Config.LOSE_CODE;
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

    /**
     * Gets game status.
     *
     * @return the game status
     */
    public int getGameStatus() {
        return this.gameStatus;
    }

    /**
     * Gets level info.
     *
     * @return the level info
     */
    public LevelInformation getLevelInfo() {
        return levelInfo;
    }
}
