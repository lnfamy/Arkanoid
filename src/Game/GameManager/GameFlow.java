package Game.GameManager;

import Animations.AnimationRunner;
import Game.Levels.LevelInformation;
import Utils.Misc.Config;
import Utils.Misc.Counter;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

public class GameFlow {
    private final AnimationRunner runner;
    private final KeyboardSensor keyboard;
    private final GUI gui;
    private final Counter score;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar the animation runner
     * @param ks the keyboard sensor
     */
    public GameFlow(GUI gui, AnimationRunner ar, KeyboardSensor ks) {
        this.runner = ar;
        this.keyboard = ks;
        this.gui = gui;
        this.score = new Counter();
    }

    public GameFlow(Game game) {
        this(game.getGui(), game.getAnimationRunner(), game.getKeyboard());
    }


    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboard,
                    this.runner, this.score);

            level.initialize();
            level.run();

            int status = level.getGameStatus();
            if (status == Config.WIN_CODE) {
                System.out.println("You won!");
            } else if (status == Config.LOSE_CODE) {
                System.out.println("You lost!");
                return;
            }

        }
    }
}
