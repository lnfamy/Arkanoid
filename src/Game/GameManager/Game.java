package Game.GameManager;

import Animations.AnimationRunner;
import Game.Levels.LevelInformation;
import Game.Levels.DirectHit;
import Game.Levels.WideEasy;
import Game.Levels.Green3;
import Utils.Misc.Config;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Game.
 */
public class Game {
    private final GUI gui;
    private final KeyboardSensor keyboardSensor;
    private final AnimationRunner animationRunner;
    private final List<LevelInformation> levelsToRun;

    /**
     * Instantiates a new Game.
     * Default constructor that doesn't receive arguments. Runs the default
     * level sequence accordingly.
     */
    public Game() {
        this.gui = new GUI("Arkanoid", Config.WIN_WIDTH, Config.WIN_HEIGHT);
        this.keyboardSensor = gui.getKeyboardSensor();
        this.animationRunner = new AnimationRunner(gui);
        this.levelsToRun = defaultLevels();
    }

    /**
     * Instantiates a new Game.
     *
     * @param arguments the arguments
     */
    public Game(String[] arguments) {
        this.gui = new GUI("Arkanoid", Config.WIN_WIDTH, Config.WIN_HEIGHT);
        this.keyboardSensor = gui.getKeyboardSensor();
        this.animationRunner = new AnimationRunner(gui);
        this.levelsToRun = levelsFromArgs(arguments);
    }

    private static List<LevelInformation> levelsFromArgs(String[] arg) {
        ArrayList<LevelInformation> levels = new ArrayList<>();
        for (String s : arg) {
            if (s.equals("1")) {
                levels.add(new DirectHit());
            } else if (s.equals("2")) {
                levels.add(new WideEasy());
            } else if (s.equals("3")) {
                levels.add(new Green3());
            }
        }
        return levels;
    }

    /**
     * Gets animation runner.
     *
     * @return the animation runner
     */
    public AnimationRunner getAnimationRunner() {
        return this.animationRunner;
    }

    /**
     * Gets gui.
     *
     * @return the gui
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * Gets keyboard.
     *
     * @return the keyboard
     */
    public KeyboardSensor getKeyboard() {
        return keyboardSensor;
    }

    /**
     * Default levels list.
     *
     * @return the list
     */
    public List<LevelInformation> defaultLevels() {
        ArrayList<LevelInformation> levels = new ArrayList<>();
        levels.add(new DirectHit());
        levels.add(new WideEasy());
        levels.add(new Green3());

        return levels;
    }

    /**
     * Gets levels to run.
     *
     * @return the levels to run
     */
    public List<LevelInformation> getLevelsToRun() {
        return this.levelsToRun;
    }

}