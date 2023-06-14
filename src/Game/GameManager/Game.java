package Game.GameManager;

import Animations.AnimationRunner;
import Game.Levels.DirectHit;
import Game.Levels.Green3;
import Game.Levels.LevelInformation;
import Game.Levels.WideEasy;
import Utils.Misc.Config;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private GUI gui;
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;

    public Game(){
        this.gui = new GUI("Arkanoid", Config.WIN_WIDTH, Config.WIN_HEIGHT);
        this.keyboardSensor = gui.getKeyboardSensor();
        this.animationRunner = new AnimationRunner(gui);
    }

    public AnimationRunner getAnimationRunner(){
        return this.animationRunner;
    }

    public GUI getGui(){
        return this.gui;
    }

    public KeyboardSensor getKeyboard() {
        return keyboardSensor;
    }

    public List<LevelInformation> defaultLevels(){
        ArrayList<LevelInformation> levels = new ArrayList<>();
        levels.add(new DirectHit());
        levels.add(new WideEasy());
        levels.add(new Green3());

        return levels;
    }
}
