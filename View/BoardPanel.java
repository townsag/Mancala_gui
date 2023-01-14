package View;
import Model.DataModel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.text.Normalizer;
import java.util.ArrayList;

public class BoardPanel extends JPanel {

    private Formatter myFormat;
    private DataModel myData;

    private MancalaIcon leftMancala;
    private MancalaIcon rightMancala;
    private ArrayList<HoleButton> myHoleButtons;

    public BoardPanel(int BOARD_WIDTH, int BOARD_HEIGHT, DataModel d, Formatter f){
        myHoleButtons = new ArrayList<>();
        myData = d;
        myFormat = f;

        setLayout(new BorderLayout());

        JLabel leftLabel = new JLabel();
        JLabel rightLabel = new JLabel();

        leftMancala = new MancalaIcon(BOARD_WIDTH/8,BOARD_HEIGHT,myFormat);
        rightMancala = new MancalaIcon(BOARD_WIDTH/8,BOARD_HEIGHT,myFormat);

        leftLabel.setIcon(leftMancala);
        rightLabel.setIcon(rightMancala);

        add(leftLabel,BorderLayout.WEST);
        add(rightLabel,BorderLayout.EAST);

        JPanel holePanel = new JPanel();
        holePanel.setLayout(new GridLayout(2,6));
        holePanel.setBackground(myFormat.getColor());

        for(int i = 0; i < 12; i++){
            //JButton tempHoleButton = new JButton(String.valueOf(i));
            HoleButton tempHoleButton = new HoleButton(BOARD_WIDTH/8,BOARD_HEIGHT/2, myFormat,myData, i,10);
            tempHoleButton.setNumStones(i);
            holePanel.add(tempHoleButton);
            myHoleButtons.add(tempHoleButton);
        }

        add(holePanel,BorderLayout.CENTER);
        leftMancala.setNumStones(1);
        rightMancala.setNumStones(20);

    }

}