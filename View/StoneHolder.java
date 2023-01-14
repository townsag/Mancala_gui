package View;

import StoneDisplay.StoneShape;
import View.Formatter;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * this is an abstract class that each object that displays stones inherits from
 * it helps icons draw the correct number of stones
 * it holds a numStones value that is used for display purposes only, not part of data model
 */
public abstract class StoneHolder {

    protected int numStones;

    /**
     * this is a protected constructor called by classes that inherit from this interface
     * @param i the number of stones the display will start with
     */
    protected StoneHolder(int i){
        numStones = i;
    }

    /**
     * this is an accessopr function used to set the number of stones that will be displayed
     * @param i the number of stones
     */
    public void setNumStones(int i){ numStones = i; }

    /**
     * this is a function called by the classes that extend this class to display the stones on an icon
     * @param g2 the context in which the stones will be displayed
     * @param iconWidth the width of the icon the stones will be displayed on
     * @param iconHeight the height of the icon the stones will be displayed on
     * @param numStones the number of stones to be displayed
     * @param myFormat the format of the view on which the stones will be displayed
     */
    protected void drawStones(Graphics2D g2, int iconWidth, int iconHeight, int numStones, Formatter myFormat){

        Stroke defaultStroke = g2.getStroke();

        //draw the background boxes
        Rectangle2D.Double background = new Rectangle2D.Double(0,0,iconWidth,iconHeight);
        g2.setColor(myFormat.getColor());
        g2.fill(background);
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(7.0f));
        g2.draw(background);
        g2.setStroke(defaultStroke);

        //draw the stones inside the box
        for(int i = 0; i < numStones; i++){
            //decided to make it work with just circular stones first
            //will let the shape of the stone be determined by the formatter later

            double stoneWidth = myFormat.getStoneWidth();
            //algorithmically calculate the x position based on the width of the icon and width of the stone
            double tempX = iconWidth /(double) 5 + (i%2)* ((double)3*iconWidth/(double)5 - stoneWidth);
            double tempY = ( Math.floor(i/2) / Math.ceil( numStones /(double)2) )* (iconHeight - 2 * stoneWidth) + stoneWidth;

            //Ellipse2D.Double tempStone = new Ellipse2D.Double(tempX, tempY,stoneWidth,stoneWidth);
            StoneShape tempStone = myFormat.getShape().cloneAt(tempX,tempY);
            //g2.fill(tempStone);
            tempStone.fill(g2);
            g2.setColor(Color.RED);
            //g2.draw(tempStone);
            tempStone.draw(g2);
            g2.setColor(Color.BLACK);
        }
    }
}