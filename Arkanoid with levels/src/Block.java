import java.awt.Color;
import java.util.ArrayList;
import biuoop.DrawSurface;

/**
 * Block is a collidable rectangle.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private Rectangle rect;
    private Color color;
    private int hits;
    private ArrayList<HitListener> hitListeners;

    /**
     * constractor.
     *
     * @param rect  - the shape of the block
     * @param color - the bock's color
     * @param hits -  counter.
     */
    public Block(Rectangle rect, Color color, int hits) {
        this.rect = rect;
        this.color = color;
        this.hits = hits;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * getCollisionRectangle.
     *
     * @return this rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * draw the block on the given DrawSurface.
     *
     * @param surface - to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
    }

    /**
     * Remove this block from the game.
     * @param game the game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * hit calculates the new velocity expected after the hit, based on the force the object inflicted on us.
     *
     * @param collisionPoint  - the intersecting point of the block and the ball.
     * @param currentVelocity - of the ball.
     * @param hitter - the hitter ball.
     * @return the new velocity.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        collisionPoint = new Point(Math.round(collisionPoint.getX()), Math.round(collisionPoint.getY()));
        double newDx = currentVelocity.getDX();
        double newDy = currentVelocity.getDY();

        this.notifyHit(hitter);
        if (this.rect.getLeft().isPointOnSegment(collisionPoint)
                || this.rect.getRight().isPointOnSegment(collisionPoint)) {
            newDx = -newDx;
        }

        if (this.rect.getUp().isPointOnSegment(collisionPoint)
                || this.rect.getDown().isPointOnSegment(collisionPoint)) {
            newDy = -newDy;
        }
        if (this.hits != 0) {
            this.hits--;
        }
        return new Velocity(newDx, newDy);
    }

    /**
     * count the time that has passed.
     */
    public void timePassed() {
        return;
    }

    /**
     * Add this block to the collidables and the sprites lists.
     *
     * @param game - the game.
     */
    public void addToGame(GameLevel game) {
        game.addCollidable(this);
        game.addSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * getHits.
     * @return the block's hits.
     */
    public int getHits() {
        return this.hits;
    }

    /**
     * notifies all of the registered HitListener objects by calling their hitEvent method.
     * @param hitter - the hitter ball.
     */
    public void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        ArrayList<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl: listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
