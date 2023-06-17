package Game.GameManager;

import Animations.AnimationRunner;
import Animations.KeyPressStoppableAnimation;
import Game.Levels.LevelInformation;
import Game.Menus.GameOver;
import Game.Menus.YouWin;
import Utils.Misc.Config;
import Utils.Misc.Counter;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * Controls the flow of the game, i.e. running each level, managing win and
 * loss conditions and screens.
 */
public class GameFlow {
    private final AnimationRunner runner;
    private final KeyboardSensor keyboard;
    private final Counter score;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar  the animation runner
     * @param ks  the keyboard sensor
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.runner = ar;
        this.keyboard = ks;
        this.score = new Counter();
    }

    /**
     * Instantiates a new Game flow.
     *
     * @param game the game
     */
    public GameFlow(Game game) {
        this(game.getAnimationRunner(), game.getKeyboard());
    }


    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboard,
                    this.runner, this.score);

            level.run();

            int status = level.getGameStatus();
            if (status == Config.WIN_CODE) {
                this.score.increase(Config.CLEAR_SCORE);
            } else if (status == Config.LOSE_CODE) {
                this.runner.run(new KeyPressStoppableAnimation(this.keyboard,
                        "space", new GameOver(this.score)));
                return;
            }
        }
        this.runner.run(new KeyPressStoppableAnimation(this.keyboard,
                "space", new YouWin(this.score)));

    }
}
