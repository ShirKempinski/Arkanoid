import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The Paddle is a rectangle that is controlled by the arrow keys,
 * and moves according to the player key presses.
 */
public class Paddle implements Sprite, Collidable {

    private KeyboardSensor keyboard;
    private Rectangle rect;
    private Color color;
    private int speed;

    /**
     * constructor.
     *
     * @param k - the keyboard sensor.
     * @param r - the rectangle.
     * @param c - the passle's color.
     * @param s - the game speed.
     */
    public Paddle(KeyboardSensor k, Rectangle r, Color c, int s) {
        this.keyboard = k;
        this.rect = r;
        this.color = c;
        this.speed = s;
    }

    /**
     * Moves the paddle left.
     */
    public void moveLeft() {
        if (this.rect.getUpperLeft().getX() <= 20) {
            return;
        }
        this.rect = new Rectangle(new Point(this.rect.getUpperLeft().getX() - this.speed,
                this.rect.getUpperLeft().getY()), this.rect.getWidth(), this.rect.getHeight());
    }

    /**
     * Moves the paddle right.
     */
    public void moveRight() {
        if (this.rect.getUpperLeft().getX() + this.rect.getWidth() >= 780) {
            return;
        }
        this.rect = new Rectangle(new Point(this.rect.getUpperLeft().getX() + this.speed,
                this.rect.getUpperLeft().getY()), this.rect.getWidth(), this.rect.getHeight());    }

    /**
     * check if the "left" or "right" keys are pressed, and if so move it accordingly.
     */
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }

        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draw the paddle.
     *
     * @param d - the surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
    }

    /**
     * get the collision rectangle.
     *
     * @return the collision rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * hit calculates the new velocity expected after the hit, based on the force the object inflicted on us.
     *
     * @param collisionPoint  - the intersecting point of the block and the ball.
     * @param currentVelocity - of the ball.
     * @param hitter - the hitting ball.
     * @return the new velocity.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        if (this.rect.getUp().isPointOnSegment(collisionPoint)) {
            if (collisionPoint.getX() <= this.rect.getUpperLeft().getX() + this.rect.getWidth() / 5) {
                return Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
            } else if (collisionPoint.getX() <= this.rect.getUpperLeft().getX() + 2 * this.rect.getWidth() / 5) {
                return Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
            } else if (collisionPoint.getX() <= this.rect.getUpperLeft().getX() + 3 * this.rect.getWidth() / 5) {
                return Velocity.fromAngleAndSpeed(0, currentVelocity.getSpeed());
            } else if (collisionPoint.getX() <= this.rect.getUpperLeft().getX() + 4 * this.rect.getWidth() / 5) {
                return Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
            } else {
                return Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
            }
        } else if (this.rect.getDown().isPointOnSegment(collisionPoint)) {
            if (collisionPoint.getX() <= this.rect.getUpperLeft().getX() + this.rect.getWidth() / 5) {
                return Velocity.fromAngleAndSpeed(240, currentVelocity.getSpeed());
            } else if (collisionPoint.getX() <= this.rect.getUpperLeft().getX() + 2 * this.rect.getWidth() / 5) {
                return Velocity.fromAngleAndSpeed(210, currentVelocity.getSpeed());
            } else if (collisionPoint.getX() <= this.rect.getUpperLeft().getX() + 3 * this.rect.getWidth() / 5) {
                return Velocity.fromAngleAndSpeed(180, currentVelocity.getSpeed());
            } else if (collisionPoint.getX() <= this.rect.getUpperLeft().getX() + 4 * this.rect.getWidth() / 5) {
                return Velocity.fromAngleAndSpeed(150, currentVelocity.getSpeed());
            } else {
                return Velocity.fromAngleAndSpeed(120, currentVelocity.getSpeed());
            }
        } else {
            double newDx = currentVelocity.getDX();
            if (this.rect.getLeft().isPointOnSegment(collisionPoint)
                    || this.rect.getRight().isPointOnSegment(collisionPoint)) {
                newDx = -newDx;
            }
            return new Velocity(newDx, currentVelocity.getDY());
        }
    }

    /**
     * Add this paddle to the game.
     *
     * @param g - the game.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
