import Model.DataModel;
import StoneDisplay.StoneShape;
import View.Formatter;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import View.FormatButton;

/**
 * this is a custom JFrame class that is used upon instantiation to selsect a format for the board to display stones
 * it implemets change listener so that it can be notified by model
 */
public class FormatFrame extends JFrame implements ChangeListener {

    private Formatter format1;
    private Formatter format2;
    private DataModel myModel;

    /**
     * this is a constructor so that primary frame tester can instantiate a format frame
     * @param f1 the first format for the user to choose from
     * @param f2 the second format for the user to choose from
     * @param mDM the data model reference so that the frame can update the model
     */
    public FormatFrame(Formatter f1, Formatter f2, DataModel mDM){
        format1 = f1; format2 = f2; myModel = mDM;
        setLayout(new BorderLayout());

        JLabel textLabel = new JLabel("Please click on one of the format boxes");
        this.add(textLabel, BorderLayout.NORTH);

        JPanel formatPanel = new JPanel();
        formatPanel.setLayout(new FlowLayout());

        FormatButton fb1 = new FormatButton(format1,myModel);
        FormatButton fb2 = new FormatButton(format2,myModel);

        formatPanel.add(fb1);
        formatPanel.add(fb2);

        this.add(formatPanel,BorderLayout.SOUTH);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
    }

    /**
     * this method is called in data model by notify listeners whenever data model makes any changes
     * can be repainted made invisible and disposed by model
     * references flags in the model class using accessors
     * @param e model object reference
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        this.setVisible(myModel.isFormatFrameVisible());
        this.repaint();

        if (myModel.isFormatFrameClosed()){
            this.dispose();
        }
    }
}
