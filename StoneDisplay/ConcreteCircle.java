package StoneDisplay;

import java.awt.geom.Ellipse2D;

/**
 * This is a concrete stoneShape.
 *
 * It creates a circle as a cloneable shape.
 *
 */
public class ConcreteCircle extends CloneableShape {
    /**
     * @Constructor creates a concrete stoneShape and adds it to the shapePath
     * @param width width of the shape
     * @param x the x-coordinate of the shape
     * @param y the y-coordinate of the shape
     */
    public ConcreteCircle(int width, double x, double y) {
        super(width, x, y);
        super.addShape(new Ellipse2D.Double(x, y, width, width));
    }

    /**
     * clones this concrete StoneShape at the given coordinates
     *
     * This is required so that a stone shape can be passed to a view component and
     * the the view component can create its own stone shapes
     *
     * This allows the mancala icons and the hole button icons to repaint themselves
     * with different number of stones.
     *
     * implemented from stoneShape
     *
     * @param x the x-coordinate of the stoneShape
     * @param y the y-coordinate of the stoneShape
     */
    @Override
    public StoneShape cloneAt(double x, double y) {
        return new ConcreteCircle(this.width, x, y);
    }
}