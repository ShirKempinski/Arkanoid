/**
 * a Rectangle has an upper-left point, width and length.
 */
public class Rectangle {

    private Point upperLeft;
    private double width;
    private double height;
    private Line left;
    private Line right;
    private Line up;
    private Line down;

    /**
     * Contractor.
     *
     * @param upperleft - the upper left point
     * @param width     - the rectangel's width
     * @param height    - the rectangel's height
     */
    public Rectangle(Point upperleft, double width, double height) {
        this.upperLeft = upperleft;
        this.width = width;
        this.height = height;
        this.left = new Line(upperLeft, new Point(upperLeft.getX(), upperLeft.getY() + height));
        this.right = new Line(new Point(upperLeft.getX() + width, upperLeft.getY()),
                new Point(upperLeft.getX() + width, upperLeft.getY() + height));
        this.up = new Line(upperLeft, new Point(upperLeft.getX() + width, upperLeft.getY()));
        this.down = new Line(new Point(upperLeft.getX(), upperLeft.getY() + height),
                new Point(upperLeft.getX() + width, upperLeft.getY() + height));
    }

    /**
     * intersectionPoints.
     *
     * @param line - the given line
     * @return a list of the intersection points between the rectangle an a given line
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.ArrayList<Point> intersectionPoints = new java.util.ArrayList<Point>();
        if (this.left.isIntersecting(line)) {
            intersectionPoints.add(this.left.intersectionWith(line));
        }
        if (this.right.isIntersecting(line)) {
            intersectionPoints.add(this.right.intersectionWith(line));
        }
        if (this.up.isIntersecting(line)) {
            intersectionPoints.add(this.up.intersectionWith(line));
        }
        if (this.down.isIntersecting(line)) {
            intersectionPoints.add(this.down.intersectionWith(line));
        }
        return intersectionPoints;
    }

    /**
     * getUpperLeft.
     *
     * @return the upper left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * getWidth.
     *
     * @return the rectangle's width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * getLength.
     *
     * @return the rectangle's length
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * getLeft.
     *
     * @return the rectangle's left line
     */
    public Line getLeft() {
        return this.left;
    }

    /**
     * getRight.
     *
     * @return the rectangle's right line
     */
    public Line getRight() {
        return this.right;
    }

    /**
     * getUp.
     *
     * @return the rectangle's up line
     */
    public Line getUp() {
        return this.up;
    }

    /**
     * getDown.
     *
     * @return the rectangle's down line
     */
    public Line getDown() {
        return this.down;
    }

    /**
     * getLowerRight.
     * @return lower right point
     */
    public Point getLowerRight() {
        return new Point(this.getUpperLeft().getX() + this.width, this.getUpperLeft().getY() + this.getHeight());
    }
}
