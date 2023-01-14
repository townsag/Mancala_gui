import Model.DataModel;
import View.Formatter;
import View.MancalaIcon;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * this is a custom JFrame class that implements the change listener interface
 * it is used to get from the user the format which they want to use to display the board
 */
public class StoneNumFrame extends JFrame implements ChangeListener{

    private Formatter myFormat;
    private DataModel myData;
    private MancalaIcon stoneNumDisplay;
    private JLabel displayLabel;
    private int sliderValue;

    private static final int ICON_WIDTH = 250;

    /**
     * this is the constructor used to get a new stone num frame by the primary frame tester class
     * @param mF this is the reference of the format used to style the frame
     * @param mD this is the reference of the data model used to both recieve updates about when the frame
     *           should be visible and to mutate data model when the user chooses a starting number of stones
     */
    public StoneNumFrame(Formatter mF, DataModel mD){
        myFormat = mF; myData = mD;
        stoneNumDisplay = new MancalaIcon(ICON_WIDTH,ICON_WIDTH,myFormat);
        stoneNumDisplay.setNumStones(4);
        sliderValue = 4;

        displayLabel = new JLabel();
        displayLabel.setIcon(stoneNumDisplay);

        setLayout(new BorderLayout());
        setResizable(false);

        JSlider stoneNumSlider = new JSlider(JSlider.HORIZONTAL,2,5,4);
        stoneNumSlider.setMajorTickSpacing(1);
        stoneNumSlider.setPaintLabels(true);
        stoneNumSlider.setPaintTicks(true);
        stoneNumSlider.addChangeListener(new mySliderListener());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.add(new JLabel("Please select the number of stones"));
        JButton chooseButton = new JButton("choose");
        chooseButton.addActionListener(new ChooseButtonListener());
        topPanel.add(chooseButton);

        add(topPanel,BorderLayout.NORTH);
        add(displayLabel,BorderLayout.CENTER);
        add(stoneNumSlider,BorderLayout.SOUTH);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(false);
        this.pack();


    }

    /**
     * this functions is called by state changed to update the values of the stone num frame
     */
    private void propagateChanges(){
        stoneNumDisplay.setMyFormatter(myFormat);
    }

    /**
     * this function is called by data model when the data model updates its change listeners
     * this function repaints the stone num frame and disposes it if necessary =
     * @param e
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        myFormat = myData.getSelectedFormat();
        this.setVisible(myData.isStoneNumFrameVisible());
        propagateChanges();
        this.repaint();

        if(myData.isStoneNumFrameClosed()){
            this.dispose();
        }
    }

    /**
     * this is the change listener attached to the slider
     * it updates the member variables of stone num display
     * as well as repainting the display to represent the selected
     * number of stones
     */
    private class mySliderListener implements ChangeListener{

        @Override
        public void stateChanged(ChangeEvent e) {
            JSlider tempS = (JSlider) e.getSource();
            if(!tempS.getValueIsAdjusting()){
                StoneNumFrame.this.sliderValue = tempS.getValue();
                StoneNumFrame.this.stoneNumDisplay.setNumStones(tempS.getValue());
                StoneNumFrame.this.displayLabel.repaint();
            }



        }
    }

    /**
     * this is the change listener attached to the choose button object
     * it updates data model when the user selects a specific number of stones to
     * initialize the display with
     */
    private class ChooseButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //StoneNumFrame.this.setVisible(false);
            //myData.setNumStones(StoneNumFrame.this.sliderValue);
            myData.setStartingNumStones(sliderValue);
            myData.setStoneNumFrameVisible(false);
            myData.setStoneNumFrameClosed(true);
            myData.setPrimaryFrameVisible(true);
        }
    }
}
