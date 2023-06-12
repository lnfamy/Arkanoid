package Game.GameManager;

import Animations.AnimationRunner;
import Game.Levels.LevelInformation;
import Utils.Misc.Counter;
import biuoop.KeyboardSensor;

import java.util.List;

public class GameFlow {
    private final AnimationRunner runner;
    private final KeyboardSensor keyboard;
    private final Counter score = new Counter();

    /**
     * Instantiates a new Game flow.
     *
     * @param ar the ar
     * @param ks the ks
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.runner = ar;
        this.keyboard = ks;
    }

    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboard,
                    this.runner, this.score);

            level.initialize();

            int k = 0;
            while (k < 2 /*level still has blocks/balls*/) {
                level.run();
                k++;
            }

            if (k >= 2/*no more balls*/) {
                //dsdsf
                System.out.println("leave me alone bitch");
            }
        }
    }
}
