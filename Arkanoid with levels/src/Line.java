/**
 * a segment of a line, which contains a start and end points.
 */
public class Line {

    private Point start;
    private Point end;

    /**
     * Constructor by points.
     *
     * @param start - get the start point
     * @param end   - get the end point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor by x and y values.
     *
     * @param x1 - start's x
     * @param y1 - start's y
     * @param x2 - end's x
     * @param y2 - end's y
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * length calculate the distance between the line's start and end points.
     *
     * @return the length of the line
     */
    public double length() {
        double length = this.start.distance(this.end);
        return length;
    }

    /**
     * middle calculates the middle point of the line.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / 2.0;
        double y = (this.start.getY() + this.end.getY()) / 2.0;
        Point middle = new Point(x, y);
        return middle;
    }

    /**
     * this line's start point.
     *
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * this line's end point.
     *
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * slope calculate this line's slope.
     *
     * @return the slope of the line
     */
    public Double slope() {
        Double slope = new Double(0);
        // if the slope is undefined, return infinity. otherwise calculate the slope and return it
        if (this.start.getX() == this.end.getX()) {
            slope = Double.POSITIVE_INFINITY;
        } else {
            slope = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
        }
        return slope;
    }

    /**
     * check if the given point is on the segment or not.
     * @param p - point.
     * @return true if the point is on the segment, false otherwise
     */
    public boolean isPointOnSegment(Point p) {
        if (this.start().distance(p) + this.end.distance(p) == this.start().distance(this.end())) {
            return true;
        }
        return false;
    }

    /**
     * calculates line intercept.
     *
     * @return intercept
     */
    public double getIntersept() {
        double intersept = this.start.getY() - (this.slope() * this.start.getX());
        return intersept;
    }

    /**
     * checks if this line intersects with the other line.
     *
     * @param other line.
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        if (this.intersectionWith(other) != null) {
            return true;
        }
        return false;

    }

    /**
     * calculate the intersection point of this line and the other line.
     *
     * @param other line.
     * @return the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        double denominator = (other.end.getY() - other.start.getY()) * (this.end.getX() - this.start.getX())
                - (other.end.getX() - other.start.getX()) * (this.end.getY() - this.start.getY());
        if (denominator == 0) {
            return null;
        }
        double a = this.start.getY() - other.start.getY();
        double b = this.start.getX() - other.start.getX();
        double numerator1 = (other.end.getX() - other.start.getX()) * a - (other.end.getY() - other.start.getY()) * b;
        double numerator2 = (this.end.getX() - this.start.getX()) * a - (this.end.getY() - this.start.getY()) * b;
        a = numerator1 / denominator;
        b = numerator2 / denominator;
        double x = this.start.getX() + (a * (this.end.getX() - this.start.getX()));
        double y = this.start.getY() + (a * (this.end.getY() - this.start.getY()));
        if (a >= 0 && a <= 1 && b >= 0 && b <= 1) {
            return new Point(x, y);
        }
        return null;
    }

    /**
     * equals - Get an other line and check if the're equals.
     *
     * @param other line.
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        if ((this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end) && this.end.equals(other.start))) {
            return true;
        }
        return false;
    }

    /**
     * return the closest intersection point of the given rectangle to the start of the line.
     *
     * @param rect - the given rectangle
     * @return the closest intersection point or null.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> intersectionPoints = rect.intersectionPoints(this);
        if (intersectionPoints.isEmpty()) {
            return null;
        }

        Point closest = intersectionPoints.get(0);
        for (int i = 0; i < intersectionPoints.size(); i++) {
            if (intersectionPoints.get(i).distance(this.start) < closest.distance(this.start)) {
                closest = intersectionPoints.get(i);
            }
        }
        return closest;
    }
}