/******
 * Name: Tamar Werthaim
 * ID: 321579039
 * Assigment: Ex5
 ******/
package Game;

import Collision.Collidable;
import Collision.Velocity;
import Geometry.Point;
import Geometry.Rectangle;
import Sprites.Ball;
import Sprites.Block;
import Sprites.Paddle;
import Sprites.SpriteCollection;
import Sprites.ScoreIndicator;
import Sprites.Sprite;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * The {@code Game.Game} class manages the core logic and loop of the ball-and-blocks game.
 * It initializes the game environment with blocks, walls, a paddle, and balls,
 * and runs the animation loop that continuously updates and draws the game state.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private KeyboardSensor keyboard;
    private Counter counterBlock;
    private Counter counterBall;
    private Counter score;
    private ScoreTrackingListener scoreTrackingListener;

    /**
     * Constructs a new {@code Game.Game} object, initializing the GUI, sprite collection,
     * environment, and keyboard input.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = new GUI("Sprites.Ball and Blocks", 800, 600);
        this.keyboard = this.gui.getKeyboardSensor();
        this.counterBlock = new Counter();
        this.counterBall = new Counter();
        this.score = new Counter();
        this.scoreTrackingListener = new ScoreTrackingListener(score);
    }

    /**
     * Adds a {@code Collision.Collidable} object to the game environment.
     *
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds a {@code Sprites.Sprite} object to the game.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initializes the game:
     * - Creates balls with velocity and adds them to the game.
     * - Adds rows of colored blocks in a pyramid-like pattern.
     * - Adds surrounding wall blocks.
     * - Adds a paddle controlled by the keyboard.
     */
    public void initialize() {
        BlockRemover blockRemover = new BlockRemover(this, counterBlock);
        BallRemover ballRemover = new BallRemover(this, counterBall);

        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        scoreIndicator.addToGame(this);

        // Create balls
        for (int i = 0; i < 3; i++) {
            int x = 50;
            int y = 50;
            Ball ball = new Ball(x, y, 5, Color.BLACK, this.environment);
            Velocity v = Velocity.fromAngleAndSpeed(5 * (i + 1), 7);
            ball.setVelocity(v);
            ball.addToGame(this);
            this.counterBall.increase(1);
        }

        // Create rows of blocks
        for (int i = 0; i < 12; i++) {
            Block block = new Block(new Rectangle(new Point(190 + (50 * i), 100), 50, 20), Color.CYAN);
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(this.scoreTrackingListener);
            this.counterBlock.increase(1);
        }
        for (int i = 0; i < 11; i++) {
            Block block = new Block(new Rectangle(new Point(240 + (50 * i), 120), 50, 20), Color.ORANGE);
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(this.scoreTrackingListener);
            this.counterBlock.increase(1);
        }
        for (int i = 0; i < 10; i++) {
            Block block = new Block(new Rectangle(new Point(290 + (50 * i), 140), 50, 20), Color.LIGHT_GRAY);
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(this.scoreTrackingListener);
            this.counterBlock.increase(1);
        }
        for (int i = 0; i < 9; i++) {
            Block block = new Block(new Rectangle(new Point(340 + (50 * i), 160), 50, 20), Color.PINK);
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(this.scoreTrackingListener);
            this.counterBlock.increase(1);
        }
        for (int i = 0; i < 8; i++) {
            Block block = new Block(new Rectangle(new Point(390 + (50 * i), 180), 50, 20), Color.GREEN);
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(this.scoreTrackingListener);
            this.counterBlock.increase(1);
        }
        for (int i = 0; i < 7; i++) {
            Block block = new Block(new Rectangle(new Point(440 + (50 * i), 200), 50, 20), Color.MAGENTA);
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(this.scoreTrackingListener);
            this.counterBlock.increase(1);
        }

        // Create wall blocks
        Color paleSkyBlue = new Color(191, 239, 255);
        Block topWall = new Block(new Rectangle(new Point(0, 20), 800, 10), paleSkyBlue);
        Block leftWall = new Block(new Rectangle(new Point(0, 20), 10, 600), paleSkyBlue);
        Block rightWall = new Block(new Rectangle(new Point(790, 20), 10, 600), paleSkyBlue);
        Block bottomWall = new Block(new Rectangle(new Point(0, 590), 800, 10), paleSkyBlue);
        bottomWall.addHitListener(ballRemover);

        topWall.addToGame(this);
        leftWall.addToGame(this);
        rightWall.addToGame(this);
        bottomWall.addToGame(this);

        // Add paddle
        Paddle paddle = new Paddle(this.keyboard, new Rectangle(new Point(400, 580), 100, 10));
        paddle.addToGame(this);
    }

    /**
     * Removes a collidable object from the game environment.
     *
     * @param c the collidable object to remove.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * Removes a sprite from the game.
     *
     * @param s the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }


    /**
     * Runs the game loop, repeatedly updating and rendering the game screen.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (this.counterBlock.getValue() > 0 && this.counterBall.getValue() > 0) {
            long startTime = System.currentTimeMillis();

            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        if (this.counterBlock.getValue() == 0) {
            score.increase(100);
            System.out.println("You Win!");
        } else {
            System.out.println("Game Over.");
        }
        System.out.println("Score: " + score.getValue());
        gui.close();
    }

    /**
     * Main method to start the game.
     *
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
