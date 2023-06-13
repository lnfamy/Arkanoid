import Animations.AnimationRunner;
import Game.GameManager.Game;
import Game.GameManager.GameFlow;
import Game.GameManager.GameLevel;
import Game.Levels.DirectHit;
import Game.Levels.Green3;
import Game.Levels.LevelInformation;
import Game.Levels.WideEasy;
import Utils.Misc.Config;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;

/**
 * The type GameLevel test.
 */
public class GameTest {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Game game = new Game();
        GameFlow gameFlow = new GameFlow(game);
        gameFlow.runLevels(game.defaultLevels());
    }
}
