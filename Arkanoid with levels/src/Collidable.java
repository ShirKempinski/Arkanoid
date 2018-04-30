/**
 * things that the ball can collide with.
 */
public interface Collidable {

    /**
     * getCollisionRectangle.
     *
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * calculates the new velocity expected after the hit, based on the force the object inflicted on us.
     *
     * @param collisionPoint  - the intersection point of the collidable and the ball.
     * @param currentVelocity - of the ball.
     * @param hitter - the hitting ball.
     * @return the new velocity expected after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
