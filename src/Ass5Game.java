/******
 * Name: Tamar Werthaim
 * ID: 321579039
 * Assigment: Ex5
 ******/

import Game.Game;

/**
 * The Game.Ass3Game class serves as the entry point for the game.
 * It creates a new Game.Game instance, initializes it with game elements,
 * and starts the main animation loop.
 */
public class Ass5Game {

    /**
     * The main method used to launch the game.
     * It creates and runs a new game.
     *
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
