/**
 * information about the collision.
 */
public class CollisionInfo {

    private Collidable c;
    private Point collisionPoint;

    /**
     * constractor.
     *
     * @param c              - the collidable.
     * @param collisionPoint - the collision point.
     */
    public CollisionInfo(Collidable c, Point collisionPoint) {
        this.c = c;
        this.collisionPoint = collisionPoint;
    }

    /**
     * collisionPoint().
     *
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * the collidable object involved in the collision.
     *
     * @return the colidable
     */
    public Collidable collisionObject() {
        return this.c;
    }
}