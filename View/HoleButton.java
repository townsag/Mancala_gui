package View;

import Model.DataModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * this is a custom button class held by the primary frame
 * it serves the purpose of both displaying the number of stones in each hole and notifying the model whenever a
 * hole is chosen by the user
 * the buttons are only set to be active when it is that users turn to pick a hole
 * this is accomplished by using accessor functions to check flags in data model
 */
public class HoleButton extends JButton {

    private int width;
    private int height;
    private Formatter myFormat;
    private DataModel myModel;
    private HoleIcon icon;

    private int index;
    private int playerNum;

    /**
     * this is the constructor used by primary frame to get new hole buttons
     * @param w the width of the hole button icon
     * @param h the height of the hole button icon
     * @param f the format used to display the stones
     * @param dm the data model that the hole button will get data from and push data to
     * @param i the index on the board that this button represents
     * @param pNum the player number associated with this hole button
     */
    public HoleButton(int w, int h, Formatter f, DataModel dm, int i, int pNum){
        width = w; height = h; myFormat = f; myModel = dm; index = i; playerNum = pNum;
        setPreferredSize(new Dimension(width,height));
        icon = new HoleIcon(width, height);
        this.setIcon(icon);
        //this.addActionListener(new HoleButtonListener());

    }

    /**
     * an accessor to set the number of stones in the button
     * called by primary frame methods
     * @param n the number of stones
     */
    public void setNumStones(int n){
        icon.setNumStones(n);
    }

    /**
     * a mutator used in primary frame to set the format of a button
     * @param f
     */
    public void setMyFormat(Formatter f){ myFormat = f; }

    /**
     * a mutator used by methods in primary frame to make a nutton active or inactive
     * this method cleans the button of any action listeners before adding new action listeners to it
     * @param flag
     */
    public void setIsActive(boolean flag){
        removeAllActionListeners();
        if (flag){
            this.addActionListener(new HoleButtonListener());
        }
    }

    /**
     * private method used by the set is active method to remove all action listeners from the button
     */
    private void removeAllActionListeners(){
        ActionListener[] tempArray = this.getActionListeners();
        if(tempArray != null) {
            for (int i = 0; i < tempArray.length; i++) {
                this.removeActionListener(tempArray[i]);
            }
        }
    }

    /**
     * inner class used by the hole button to display the number of stones on the button
     * instantiated in the hole button constructor
     * uses the stone holder abstract class to paint the icon
     */
    private class HoleIcon extends StoneHolder implements Icon{

        private int iconWidth;
        private int iconHeight;


        /**
         * constructor used to get a new hole icon
         * @param w the width of the icon
         * @param h the height of the icon
         */
        public HoleIcon(int w, int h){
            super(0);
            iconWidth = w; iconHeight = h;
        }

        /**
         * function called by the rapaint method to disolay the likeness of the stones on the icon
         * @param c not used
         * @param g the context in which the things are to be painted
         * @param x not used
         * @param y not used
         */
        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            //paints the number of stones on top of the button
            Graphics2D g2 = (Graphics2D) g;
            drawStones(g2,getIconWidth(),getIconHeight(),numStones,HoleButton.this.myFormat);
        }

        /**
         * returns the width of the hole button icon
         * @return the width
         */
        @Override
        public int getIconWidth() {
            return iconWidth;
        }

        /**
         * returns the height of the hole button icon
         * @return the height of the icon
         */
        @Override
        public int getIconHeight() {
            return iconHeight;
        }
    }

    /**
     * private inner class attached to each hole button to notify the data model
     * refrences the field in Hole Button
     */
    private class HoleButtonListener implements ActionListener{
        /**
         * called when the hole button is pressed
         * calles a nutator function in data model with the index of the pressed button and the player associated with
         * the pressed button
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            //myModel.addStoneTest(playerNum, index);
            myModel.userChoice(index,playerNum);
        }
    }

}