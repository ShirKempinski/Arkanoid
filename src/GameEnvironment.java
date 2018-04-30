import java.util.ArrayList;

/**
 * the game environment.
 */
public class GameEnvironment {

    private ArrayList<Collidable> collidables;

    /**
     * constractor.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * addCollidable to the list.
     *
     * @param c - the new collidable.
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * getCollidables.
     *
     * @return the collidables list
     */
    public ArrayList<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * Gives the information about the closest collision that is going to occur.
     *
     * @param trajectory - the ball's next move.
     * @return a Collision info object if there's one, on null.
     */

    public CollisionInfo getClosestCollision(Line trajectory) {

        ArrayList<Collidable> nearestBlocks = new ArrayList<Collidable>();
        ArrayList<Point> collisions = new ArrayList<Point>();

        for (Collidable c : this.collidables) {
            if (!c.getCollisionRectangle().intersectionPoints(trajectory).isEmpty()) {
                nearestBlocks.add(c);
                collisions.add(trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle()));
            }
        }

        if (nearestBlocks.isEmpty()) {
            return null;
        }

        Point nearestCollisionPoint = collisions.get(0);
        Collidable nearestCollidable = nearestBlocks.get(0);
        for (int i = 0; i < collisions.size(); i++) {
            if (collisions.get(i).distance(trajectory.start()) < nearestCollisionPoint.distance(trajectory.start())) {
                nearestCollisionPoint = collisions.get(i);
                nearestCollidable = nearestBlocks.get(i);
            }
        }
        return new CollisionInfo(nearestCollidable, nearestCollisionPoint);
    }
}
