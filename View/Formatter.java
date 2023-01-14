package View;

import StoneDisplay.StoneShape;

import java.awt.*;

/**
 * this is a formatter object to be held by the Board Icon class and used by the board Icon class to
 * determine the likeness of the board
 */
public class Formatter {
    private Color formatColor;
    private StoneShape formatShape;
    private int stoneWidth;

    public Formatter(Color c, StoneShape s,int w){
        this.formatColor = c; this.formatShape = s; stoneWidth = w;
    }

    public void setColor(Color c){ formatColor = c; }
    public void setFormatShape(StoneShape s){ formatShape = s; }
    public void setStoneWidth(int w){ stoneWidth = w; }

    public Color getColor(){ return formatColor; }
    public StoneShape getShape(){ return formatShape; }
    public int getStoneWidth(){ return stoneWidth; }

}