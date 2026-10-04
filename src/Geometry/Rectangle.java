package Geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * The Geometry.Rectangle class represents a rectangle defined by an upper-left point,
 * a width, and a height. It provides methods to get its sides as lines and to find
 * intersection points with other lines.
 */
public class Rectangle {

    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Creates a new rectangle with a given upper-left point, width, and height.
     *
     * @param upperLeft the upper-left point of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;

        Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        Point lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Point lowerRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
    }

    /**
     * Returns a list of intersection points between this rectangle and a given line.
     * The method checks for intersections with each of the four edges of the rectangle.
     *
     * @param line the line to check intersection with
     * @return a list of intersection points (possibly empty)
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> listP = new ArrayList<>();
        List<Line> lines = new ArrayList<>();
        lines.add(getLeft());
        lines.add(getRight());
        lines.add(getTop());
        lines.add(getBottom());
        Point point;
        for (Line lineRec : lines) {
            point = line.intersectionWith(lineRec);
            if (point != null) {
                listP.add(point);
            }
        }
        return listP;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return the upper-left point
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * Returns the left side of the rectangle as a line.
     *
     * @return the left edge of the rectangle
     */
    public Line getLeft() {
        return new Line(upperLeft,
                new Point(upperLeft.getX(), upperLeft.getY() + height));
    }

    /**
     * Returns the right side of the rectangle as a line.
     *
     * @return the right edge of the rectangle
     */
    public Line getRight() {
        return new Line(
                new Point(upperLeft.getX() + width, upperLeft.getY()),
                new Point(upperLeft.getX() + width, upperLeft.getY() + height));
    }

    /**
     * Returns the top side of the rectangle as a line.
     *
     * @return the top edge of the rectangle
     */
    public Line getTop() {
        return new Line(upperLeft,
                new Point(upperLeft.getX() + width, upperLeft.getY()));
    }

    /**
     * Returns the bottom side of the rectangle as a line.
     *
     * @return the bottom edge of the rectangle
     */
    public Line getBottom() {
        return new Line(
                new Point(upperLeft.getX(), upperLeft.getY() + height),
                new Point(upperLeft.getX() + width, upperLeft.getY() + height));
    }

    /**
     * Checks whether the given point lies inside the bounds of this rectangle.
     *
     * @param p the point to check.
     * @return true if the point is within the rectangle (including edges), false otherwise.
     */
    public boolean contains(Point p) {
        double x = p.getX();
        double y = p.getY();
        return x >= this.upperLeft.getX()
                && x <= this.upperLeft.getX() + this.width
                && y >= this.upperLeft.getY()
                && y <= this.upperLeft.getY() + this.height;
    }

}
