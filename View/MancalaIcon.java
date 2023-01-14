package View;

import View.Formatter;

import javax.swing.*;
import java.awt.*;

public class MancalaIcon extends StoneHolder implements Icon{

    private int width;
    private int height;
    private Formatter myFormat;


    public MancalaIcon(int w, int h, Formatter f){
        super(0);
        width = w; height = h; myFormat = f;
    }

    public void setMyFormatter(Formatter newF){
        myFormat = newF;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        //paint the stones in the boundaries of the icon
        //Icon should be the color given in the formatter
        Graphics2D g2 = (Graphics2D) g;
        drawStones(g2,getIconWidth(),getIconHeight(),numStones,myFormat);

    }

    @Override
    public int getIconWidth() {
        return width;
    }

    @Override
    public int getIconHeight() {
        return height;
    }
}