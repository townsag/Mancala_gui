import Model.DataModel;
import Model.InfoWrapper;
import Model.PlayerInfo;
import View.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;

/**
 * this is a custom JFrame class which is used to display the mancala board and the primary user interface
 * it implements change listener so that it can be notified of changes to the data model
 */
public class PrimaryFrame extends JFrame implements ChangeListener {

    public static final int BOARD_WIDTH = 1000;
    public static final int BOARD_HEIGHT = 250;

    private DataModel myData;
    private Formatter myFormat;

    private MancalaIcon leftMancala;
    private MancalaIcon rightMancala;
    private ArrayList<HoleButton> playerOneButtons;
    private ArrayList<HoleButton> playerTwoButtons;
    private JTextArea instructionArea;
    private ContinueButton continueButton;
    private UndoButton undoButton;

    /**
     * this is a constructor so that primary frame tester can instantiate a primary frame object
     * it instantiates all of the anonymous swing components as assigning values to the member variable
     * swing components
     * @param model Data model object reference to be used when mutating the data model
     * @param format formatter to be used to paint the mancala board componets
     */
    public PrimaryFrame(DataModel model, Formatter format){

        myData = model;
        myFormat = format;
        //myHoleButtons = new ArrayList<>();
        playerOneButtons = new ArrayList<>();
        playerTwoButtons = new ArrayList<>();

        //create the right and left mancala labels and icons
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new BorderLayout());

        JLabel leftLabel = new JLabel();
        JLabel rightLabel = new JLabel();

        leftMancala = new MancalaIcon(BOARD_WIDTH/8,BOARD_HEIGHT,myFormat);
        rightMancala = new MancalaIcon(BOARD_WIDTH/8,BOARD_HEIGHT,myFormat);

        leftLabel.setIcon(leftMancala);
        rightLabel.setIcon(rightMancala);

        boardPanel.add(leftLabel,BorderLayout.WEST);
        boardPanel.add(rightLabel,BorderLayout.EAST);

        //create the center buttons/ holes
        JPanel holePanel = new JPanel();
        holePanel.setLayout(new GridLayout(2,6));
        //holePanel.setBackground(format.getColor());


        //initialize the hole buttons then add them to the hole panel
        for(int i = 0; i < 6; i++){
            HoleButton p1Temp = new HoleButton(BOARD_WIDTH/8,BOARD_HEIGHT/2, myFormat, myData, i,1);
            HoleButton p2Temp = new HoleButton(BOARD_WIDTH/8,BOARD_HEIGHT/2, myFormat, myData, i,2);
            p1Temp.setNumStones(0);
            p2Temp.setNumStones(0);
            playerOneButtons.add(p1Temp);
            playerTwoButtons.add(p2Temp);
        }

        //add the player 2 (top row) buttons in reverse order
        for(int i = 0; i < 6; i++){
            holePanel.add(playerTwoButtons.get(5-i));
        }

        //add the player 1 (bottom row) buttons in correct order
        for(int i = 0; i < 6; i++){
            holePanel.add(playerOneButtons.get(i));
        }
        boardPanel.add(holePanel,BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        instructionArea = new JTextArea(5,20);
        continueButton = new ContinueButton(myData);
        undoButton = new UndoButton(myData);


        bottomPanel.add(instructionArea);
        bottomPanel.add(continueButton);
        bottomPanel.add(undoButton);


        this.setLayout(new BorderLayout());
        this.add(boardPanel, BorderLayout.NORTH);
        this.add(bottomPanel,BorderLayout.SOUTH);
        this.setResizable(false);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(false);
        this.pack();



        //add the text area which will give messages to the users

        //add the two buttons "undo" and "continue"

    }

    /**
     * this is a private void method that is called whenever the state changed function is called
     * it updates all of the componets of the board with new values from data model and then repaints the
     * board
     */
    private void propagateChanges(){
        //update all of the formatters
        leftMancala.setMyFormatter(myFormat);
        rightMancala.setMyFormatter(myFormat);
        for(HoleButton hb : playerOneButtons){
            hb.setMyFormat(myFormat);
        }
        for(HoleButton hb : playerTwoButtons){
            hb.setMyFormat(myFormat);
        }

        //update all the numbers of stones in each stone holder
        InfoWrapper tempW = myData.getPlayerInfo();
        PlayerInfo temp1 = tempW.getP1();
        PlayerInfo temp2 = tempW.getP2();

        leftMancala.setNumStones(temp2.getMancala());
        rightMancala.setNumStones(temp1.getMancala());

        for(int i = 0; i < 6; i++ ){
            playerOneButtons.get(i).setNumStones(temp1.getHoleValue(i));
            playerTwoButtons.get(i).setNumStones(temp2.getHoleValue(i));
        }

        //update which buttons are active
        for(int i = 0; i < 6; i++){
            if(temp1.getHoleValue(i) != 0) {
                playerOneButtons.get(i).setIsActive(temp1.getIsActive());
            } else {
                playerOneButtons.get(i).setIsActive(false);
            }
            if(temp2.getHoleValue(i) != 0) {
                playerTwoButtons.get(i).setIsActive(temp2.getIsActive());
            } else {
                playerTwoButtons.get(i).setIsActive(false);
            }
        }

        continueButton.setIsActive(myData.isContinueIsActive());
        undoButton.setIsActive(myData.isUndoIsActive());

        //update the text field
        instructionArea.setText(myData.getMessage());
    }

    /**
     * this function satisfies the requiremets of the change listener interface
     * it is called by data model whenever the data model updates the change listeners
     * @param e
     */
    @Override
    public void stateChanged(ChangeEvent e) {

        myFormat = myData.getSelectedFormat();
        this.setVisible(myData.isPrimaryFrameVisible());
        propagateChanges();
        this.repaint();

        if (myData.isPrimaryFrameClosed()){
            this.dispose();
        }
    }
}