import Game.GameManager.Game;
import Game.GameManager.GameFlow;

/**
 * The type GameLevel test.
 */
public class Ass6Game {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Game game;
        if (args.length > 0) {
            game = new Game(args);
        } else {
            game = new Game();
        }
        GameFlow gameFlow = new GameFlow(game);
        gameFlow.runLevels(game.getLevelsToRun());
        game.getGui().close();
    }
}
