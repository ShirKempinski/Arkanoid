import java.awt.Color;
import java.util.Random;

import biuoop.DrawSurface;

/**
 * a Ball has a center point, size, color and velocity and environment.
 */
public class Ball implements Sprite {

    private Point center;
    private int size;
    private Color color;
    private Velocity velocity;
    private GameEnvironment environment;

    /**
     * simple constructor.
     *
     * @param x     - x value of ball's center.
     * @param y     - y value of ball's center.
     * @param r     - ball's radius (size).
     * @param color - ball's color.
     */
    public Ball(int x, int y, int r, Color color) {
        this.color = color;
        this.center = new Point((double) x, (double) y);
        if (r < 0) {
            this.size = -r;
        } else {
            this.size = r;
        }
    }

    /**
     * constructor with velocity.
     *
     * @param x        - x value of ball's center.
     * @param y        - y value of ball's center.
     * @param r        - ball's size (radius).
     * @param color    - ball's color.
     * @param velocity - ball's velocity.
     * @param game     - the game environment.
     */
    public Ball(int x, int y, int r, Color color, Velocity velocity, GameEnvironment game) {
        this.center = new Point((double) x, (double) y);
        if (r < 0) {
            this.size = -r;
        } else {
            this.size = r;
        }
        this.color = color;
        this.velocity = velocity;
        this.environment = game;
    }

    /**
     * getX.
     *
     * @return x value of the ball's center.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * getY.
     *
     * @return y value of the ball's center.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * getSize.
     *
     * @return the ball's radius.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * getColor.
     *
     * @return the ball's color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface - to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle((int) this.getX(), (int) (this.getY()), this.getSize());
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.getX(), (int) (this.getY()), this.getSize());
    }

    /**
     * setVelocity with a given velocity.
     *
     * @param v - the velocity.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * setVelocity with dx and dy values (creates a new velocity object).
     *
     * @param dx - the new velocity dx value.
     * @param dy - the new velocity dy value.
     */
    public void setVelocity(double dx, double dy) {
        Velocity v = new Velocity(dx, dy);
        this.velocity = v;
    }

    /**
     * getVelocity.
     *
     * @return the ball's velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * set a random color.
     *
     * @param rand - the random object
     * @return a random color
     */
    public static Color randomColor(Random rand) {
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new Color(r, g, b);
    }

    /**
     * getTrajectory.
     * @param dt - time
     * @return the ball's movement vector.
     */
    public Line getTrajectory(double dt) {
        Velocity v = new Velocity(this.getVelocity().getDX() * dt, this.getVelocity().getDY() * dt);
        Point p = v.applyToPoint(this.center);
        return new Line(this.center, p);
    }

    @Override
    public void timePassed(double dt) {
        moveOneStep(dt);
    }

    /**
     * moves the ball according to it's velocity and considering the blocks around.
     * @param dt - time.
     */
    public void moveOneStep(double dt) {
        CollisionInfo info = this.environment.getClosestCollision(getTrajectory(dt));
        if (info == null) {
            this.center = this.getTrajectory(dt).end();
        } else {
            if (isBallInBlock(info)) {
                return;
            }
            double nextX = Math.round(info.collisionPoint().getX());
            double nextY = Math.round(info.collisionPoint().getY());
            if (this.getVelocity().getDY() != 0) {
                nextY = (nextY - 0.00001 * this.getVelocity().getDY() / Math.abs(this.getVelocity().getDY()));
            }
            if (this.getVelocity().getDX() != 0) {
                nextX = (nextX - 0.00001 * this.getVelocity().getDX() / Math.abs(this.getVelocity().getDX()));
            }
            this.center = new Point(nextX, nextY);
            this.velocity = info.collisionObject().hit(this, info.collisionPoint(), this.getVelocity());
        }
    }

    /**
     * Checks if the ball is in the block.
     * @param info - the information of the collision
     * @return true or false.
     */
    public boolean isBallInBlock(CollisionInfo info) {
        if (info.collisionObject().getCollisionRectangle().getRight().isPointOnSegment(info.collisionPoint())
                && this.velocity.getDX() > 0) {
            this.center = new Point(info.collisionPoint().getX() + 0.0001, info.collisionPoint().getY());
            return true;
        } else if (info.collisionObject().getCollisionRectangle().getLeft().isPointOnSegment(info.collisionPoint())
                && this.velocity.getDX() < 0) {
            this.center = new Point(info.collisionPoint().getX() - 0.0001, info.collisionPoint().getY());
            return true;
        } else if (info.collisionObject().getCollisionRectangle().getUp().isPointOnSegment(info.collisionPoint())
                && this.velocity.getDY() < 0) {
            this.center = new Point(info.collisionPoint().getX(), info.collisionPoint().getY() - 0.0001);
            return true;
        } else if (info.collisionObject().getCollisionRectangle().getDown().isPointOnSegment(info.collisionPoint())
                && this.velocity.getDY() > 0) {
            this.center = new Point(info.collisionPoint().getX(), info.collisionPoint().getY() + 0.0001);
            return true;
        }
        return false;
    }

    /**
     * Add the ball to the game.
     *
     * @param game - the game.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.getBallsCounter().increase(1);
    }

    /**
     * Remove the ball from the game.
     * @param g the game
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.getBallsCounter().decrease(1);
    }
}