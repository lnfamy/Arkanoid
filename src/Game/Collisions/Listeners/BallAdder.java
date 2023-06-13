package Game.Collisions.Listeners;

import Game.GameManager.GameLevel;
import Utils.Geometry.Velocity;
import Sprites.Ball;
import Sprites.Block;
import Utils.Misc.Config;
import Utils.Misc.Counter;

/**
 * The type Ball adder.
 */
public class BallAdder implements HitListener {
    private final GameLevel game;
    private final Counter remainingBalls;

    /**
     * Instantiates a new Ball adder.
     *
     * @param game     the game
     * @param remBalls the remaining balls in the game
     */
    public BallAdder(GameLevel game, Counter remBalls) {
        this.game = game;
        this.remainingBalls = remBalls;
    }

    public BallAdder(GameLevel game, int remBalls){
        this.game = game;
        this.remainingBalls = new Counter();
        this.remainingBalls.increase(remBalls);
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        double x = hitter.getX(), y = hitter.getY();
        double theta = Math.toDegrees(Math.acos(hitter.getVelocity().getDy()
                / Config.BALL_SPEED));
        double addToTheta = 5;

        //removing special block listener
        beingHit.removeHitListener(this);

        Ball b = new Ball(x, y, Config.BALL_SIZE, Config.BALL_CLR);
        b.setVelocity(Velocity.fromAngleAndSpeed(theta + addToTheta,
                Config.BALL_SPEED));
        remainingBalls.increase(1);
        b.addToGame(this.game);
        b.setGameEnvironment(this.game.getEnvironment());
    }
}
