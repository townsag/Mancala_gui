package StoneDisplay;

import java.awt.*;

/**
 * This interface sets an outline for a stoneShape
 */
public interface StoneShape {
    /**
     * clones this concrete StoneShape at the given coordinates
     *
     * This is required so that a stone shape can be passed to a view component and
     * the the view component can create its own stone shapes
     *
     * This allows the mancala icons and the hole button icons to repaint themselves
     * with different number of stones.
     *
     * @param x the x-coordinate of the shape
     * @param y the y-coordinate of the shape
     * @return StoneShape a clone of a stoneShape.
     */
    public StoneShape cloneAt(double x, double y);

    /**
     * draw objects using the Graphics2D class
     *
     * @param g2 the Graphics component
     */
    public void draw(Graphics2D g2);

    /**
     * fills in an object using the Graphics2D class
     *
     * @param g2 the Graphics component
     */
    public void fill(Graphics2D g2);
}