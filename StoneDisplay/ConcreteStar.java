package StoneDisplay;

import java.awt.geom.Path2D;

/**
 * This is a concrete stoneShape.
 *
 * It creates a star as a cloneable shape.
 *
 */
public class ConcreteStar extends CloneableShape {

    /**
     * @Constructor creates a concrete stoneShape and adds it to the shapePath
     * @param width width of the shape
     * @param x the x-coordinate of the shape
     * @param y the y coordinate of the shape
     */
    public ConcreteStar(int width, double x, double y) {
        super(width, x, y);
        Path2D.Double tempPath = new Path2D.Double();
        tempPath.moveTo(x + (double) width / (double) 2, y + 0);
        tempPath.lineTo(x + width, y + width);
        tempPath.lineTo(x + 0, y + (double) width - (double) width * Math.tan(0.628319));
        tempPath.lineTo(x + width, y + (double) width - (double) width * Math.tan(0.628319));
        tempPath.lineTo(x + 0, y + width);
        super.addShape(tempPath);
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
        return new ConcreteStar(this.width, x, y);
    }
}