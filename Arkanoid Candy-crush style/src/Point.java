/**
 * a point is a location on a surface, and it contains a x and y values.
 */
public class Point {

    private double x;
    private double y;

    /**
     * Constructor.
     *
     * @param x - point's x
     * @param y - point's y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Distance - calculate the distance between this point and the other point.
     *
     * @param other - the other point
     * @return the distance of this point from the other point
     */
    public double distance(Point other) {
        double distance = Math.sqrt(((this.x - other.getX()) * (this.x - other.getX()))
                + (this.y - other.getY()) * (this.y - other.getY()));
        return distance;
    }

    /**
     * Equals - Get an other point and check if the're equals.
     *
     * @param other point.
     * @return true is the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        if (this.x == other.getX() && this.y == other.getY()) {
            return true;
        }
        return false;
    }

    /**
     * getX - this point's x.
     *
     * @return the x value of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * getY - this point's y.
     *
     * @return the y value of this point
     */
    public double getY() {
        return this.y;
    }
}