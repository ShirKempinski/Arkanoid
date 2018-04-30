/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {

    private double dx;
    private double dy;

    /**
     * constructor.
     *
     * @param dx - the change in the position on the x axis.
     * @param dy - the change in the position on the y axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * getDX.
     *
     * @return the change in the position on the x axis.
     */
    public double getDX() {
        return this.dx;
    }

    /**
     * getDY.
     *
     * @return the change in the position on the y axis.
     */
    public double getDY() {
        return this.dy;
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p - the previous point
     * @return the new positioned point
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.getDX(), p.getY() + this.getDY());
    }

    /**
     * Take to values of x and y and return a new point with position (x+dx, y+dy).
     *
     * @param x - the previous x value.
     * @param y - the previous y value.
     * @return the new positioned point
     */
    public Point applyToPoint(double x, double y) {
        return new Point(x + this.getDX(), y + this.getDY());
    }

    /**
     * set speed according to the given size. the larger the size is, the slower it moves.
     * (if the size is larger than 50, the speed remains the same)
     *
     * @param size - the given size
     * @return the speed.
     */
    public static double speedGenerator(double size) {
        if (size < 50) {
            return (20 * 10) / size;
        }
        return (20 * 10) / 50;
    }

    /**
     * takes angle (in degrees) and speed and returns the suitable velocity.
     *
     * @param angle - the angle
     * @param speed - the speed
     * @return the suitable velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // convert the angle from degrees to radians.
        double radianAngle = (angle * Math.PI) / 180;
        // calculate the velocity's dx and dy.
        double dx = speed * Math.sin(radianAngle);
        double dy = (-1) * (speed * Math.cos(radianAngle));
        return new Velocity(dx, dy);
    }

    /**
     * get the ball's speed.
     *
     * @return the ball's speed
     */
    public double getSpeed() {
        return Math.sqrt(Math.pow(this.dx, 2) + Math.pow(this.dy, 2));
    }
}
