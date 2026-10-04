package Sprites;

import Collision.Collidable;
import Collision.Velocity;
import Game.Game;
import Geometry.Point;
import Geometry.Rectangle;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * The Sprites.Paddle class represents a controllable paddle in the game.
 * It implements the Sprites.Sprite and Collision.Collidable interfaces, allowing it to move
 * left and right in response to keyboard input and respond to collisions with the ball.
 */
public class Paddle implements Sprite, Collidable {
    private Rectangle paddle;
    private biuoop.KeyboardSensor keyboard;
    private java.awt.Color color;

    private static final double MOVE_AMOUNT = 5;
    static final double COMPARISON_THRESHOLD = 0.00001;

    /**
     * Constructs a Sprites.Paddle with a specified keyboard sensor and shape.
     *
     * @param keyboard the keyboard sensor to control paddle movement
     * @param paddle   the rectangle representing the paddle's size and position
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Rectangle paddle) {
        this.paddle = paddle;
        this.keyboard = keyboard;
        this.color = Color.YELLOW;
    }

    /**
     * Moves the paddle left by a fixed amount.
     * If it reaches the left edge, it wraps to the right side.
     */
    public void moveLeft() {
        double newX = paddle.getUpperLeft().getX() - MOVE_AMOUNT;
        if (newX < 10) {
            newX = 790 - paddle.getWidth();
        }
        paddle.getUpperLeft().setX(newX);
    }

    /**
     * Moves the paddle right by a fixed amount.
     * If it reaches the right edge, it wraps to the left side.
     */
    public void moveRight() {
        double newX = paddle.getUpperLeft().getX() + MOVE_AMOUNT;
        if (newX > 790 - paddle.getWidth()) {
            newX = 10;
        }
        paddle.getUpperLeft().setX(newX);
    }

    /**
     * Updates the paddle's position based on user keyboard input.
     * Called once per frame.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draws the paddle on the given drawing surface.
     *
     * @param d the surface to draw on
     */
    public void drawOn(DrawSurface d) {
        Color midLightBlue = new Color(100, 149, 237);
        d.setColor(midLightBlue);
        int x = (int) paddle.getUpperLeft().getX();
        int y = (int) paddle.getUpperLeft().getY();
        d.fillRectangle(x, y, (int) paddle.getWidth(), (int) paddle.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, (int) paddle.getWidth(), (int) paddle.getHeight());
    }

    /**
     * Returns the collision rectangle of the paddle.
     *
     * @return the paddle's rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    /**
     * Handles the collision logic when the ball hits the paddle.
     * Divides the paddle into 5 equal regions and adjusts the ball's angle accordingly.
     *
     * @param collisionPoint  the point at which the ball collided
     * @param currentVelocity the velocity of the ball before the hit
     * @return the new velocity after the hit
     */
    @Override
    public Velocity hit(Ball b, Point collisionPoint, Velocity currentVelocity) {

        if (doubleEquals(collisionPoint.getX(), paddle.getLeft().start().getX())
                || doubleEquals(collisionPoint.getX(), paddle.getRight().start().getX())) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        double part = this.paddle.getWidth() / 5;
        double startX = this.paddle.getUpperLeft().getX();
        double endX = paddle.getUpperLeft().getX() + paddle.getWidth();
        int area = 0;
        for (double i = startX; i <= endX; i += part) {
            area++;
            if (collisionPoint.getX() >= i && collisionPoint.getX() < i + part) {
                break;
            }
        }
        if (area > 5) {
            area = 5;
        }

        double angle;
        switch (area) {
            case 1:
                angle = 300;
                break;
            case 2:
                angle = 330;
                break;
            case 3:
                return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            case 4:
                angle = 30;
                break;
            case 5:
            default:
                angle = 60;
                break;
        }

        return Velocity.fromAngleAndSpeed(angle, currentVelocity.getSpeed());
    }

    /**
     * Adds this paddle to the given game as both a Sprites.Sprite and Collision.Collidable.
     *
     * @param g the game to add the paddle to
     */
    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Compares two double values with a precision threshold.
     *
     * @param a first value.
     * @param b second value.
     * @return true if values are considered equal, false otherwise.
     */
    public boolean doubleEquals(double a, double b) {
        return Math.abs(a - b) < COMPARISON_THRESHOLD;
    }

    @Override
    public boolean isDynmic() {
        return true;
    }
}
