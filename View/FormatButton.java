package View;

import Model.DataModel;
import StoneDisplay.StoneShape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

public class FormatButton extends JButton {

    private Formatter format;
    private DataModel myModel;

    private static final int width = 200;
    private static final int height = 200;

    public FormatButton(Formatter f, DataModel dm){
        format = f; myModel = dm;

        this.setPreferredSize(new Dimension(width,height));
        this.setIcon(new FormatIcon(width,height,format));
        this.addActionListener(new FormatButtonListener());
    }

    private class FormatIcon implements Icon {

        private Formatter myFormat;
        private int width;
        private int height;

        public FormatIcon(int w, int h, Formatter f){
            width = w; height = h; myFormat = f;
        }

        public Formatter getMyFormat(){
            return myFormat;
        }

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g;
            Rectangle2D.Double background = new Rectangle2D.Double(0,0,width,height);
            g2.setColor(myFormat.getColor());
            g2.fill(background);
            g2.setColor(Color.BLACK);

            int tempX = width/2 - myFormat.getStoneWidth()/2;
            int tempY = height/2 - myFormat.getStoneWidth()/2;
            StoneShape tempStone = myFormat.getShape().cloneAt(tempX,tempY);

            tempStone.fill(g2);
            g2.setColor(Color.RED);
            tempStone.draw(g2);
            g2.setColor(Color.BLACK);
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

    private class FormatButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            myModel.setSelectedFormat(FormatButton.this.format);
            myModel.startTurn(1);
            //myModel.setPrimaryFrameVisible(true);
            //System.out.println("myModel.setPrimaryFrameVisible(true);");
            myModel.setStoneNumFrameVisible(true);
            myModel.setFormatFrameClosed(true);
        }
    }
}
