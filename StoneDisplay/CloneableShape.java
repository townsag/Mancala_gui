package StoneDisplay;

import java.awt.*;
import java.awt.geom.Path2D;

/**
 * This class creates a path for cloneable shapes.
 *
 * It follows the stoneShape interface.
 */
public abstract class CloneableShape implements StoneShape {
    private Path2D.Double shapePath;
    private double xPos;
    private double yPos;
    protected int width;

    /**
     * @Constructor initializes the instance variables of cloneableShape
     *
     * @param w width of the shape
     * @param x the x-coordinate of the shape
     * @param y the y-coordinate of the shape
     */
    protected CloneableShape(int w, double x, double y) {
        shapePath = new Path2D.Double();
        width = w;
        xPos = x;
        yPos = y;
    }

    /**
     * adds a shape to the shapePath
     *
     * @param s A shape
     */
    protected void addShape(Shape s) {
        shapePath.append(s, false);
    }

    /**
     * draws the shapes in the shapePath
     */
    @Override
    public void draw(Graphics2D g2) {
        g2.draw(shapePath);
    }

    /**
     * fills in the shapes in the shapePath
     */
    @Override
    public void fill(Graphics2D g2) {
        g2.fill(shapePath);
    }
}