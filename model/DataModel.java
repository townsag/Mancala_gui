package Model;

import java.util.ArrayList;
import View.Formatter;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This is the data model class It stores and manages all of the data used by
 * the the view
 *
 * This class also stores all of the dlags for different panels and objects that
 * are required to display the view
 *
 * This class manages and stores the data for the moves made by current players
 * and also checks for the wining player
 */
public class DataModel {

    private PlayerInfo player1Committed;
    private PlayerInfo player2Committed;

    private PlayerInfo player1InProgress;
    private PlayerInfo player2InProgress;

    private int currentPlayerNum;
    private ArrayList<ChangeListener> myListeners;

    private int startingNumStones;
    private Formatter selectedFormat;

    private boolean isFormatFrameVisible;
    private boolean isFormatFrameClosed;

    private boolean isPrimaryFrameVisible;
    private boolean isPrimaryFrameClosed;

    private boolean isStoneNumFrameVisible;
    private boolean isStoneNumFrameClosed;

    private String message;

    private boolean continueIsActive;
    private boolean undoIsActive;

    private boolean extraTurnFlag;
    private int undoCounter;

    /**
     * @Constructor initializes the PlayerInfo objects, the default message and all
     *              of the flags
     */
    public DataModel() {

        player1Committed = new PlayerInfo(1);
        player2Committed = new PlayerInfo(2);

        player1InProgress = new PlayerInfo(1);
        player2InProgress = new PlayerInfo(2);

        myListeners = new ArrayList<ChangeListener>();

        message = "default message";

        isFormatFrameVisible = true;
        isFormatFrameClosed = false;

        isPrimaryFrameVisible = false;
        isPrimaryFrameClosed = false;

        isStoneNumFrameVisible = false;
        isStoneNumFrameClosed = false;
    }

    /**
     * Sets the format style for the Mancala board.
     *
     * @param sF the selected format style.
     */
    public void setSelectedFormat(Formatter sF) {
        selectedFormat = sF;
    }

    /**
     * Accessor for the format style for the Mancala board.
     *
     * @return selectedFormat the format style for the Mancala board.
     */
    public Formatter getSelectedFormat() {
        return selectedFormat;
    }

    /**
     * Accessor for the initial number of stones in each hole on the mancala board.
     *
     * @return startingNumStones the starting number of stones
     */
    public int getStartingNumStones() {
        return startingNumStones;
    }

    /**
     * sets the initial number of stones in each hole on the mancala board.
     *
     * @param startingNum the starting number of stones.
     */
    public void setStartingNumStones(int startingNum) {
        this.startingNumStones = startingNum;
        player1InProgress.setAllValues(startingNumStones);
        player2InProgress.setAllValues(startingNumStones);

        player1Committed.setAllValues(startingNumStones);
        player2Committed.setAllValues(startingNumStones);
    }

    /**
     * adds a stone at the specified hole depending on which player made the move.
     *
     * @param playerNum the player number
     * @param holeIndex the location of the hole
     */
    public void addStoneTest(int playerNum, int holeIndex) {
        if (playerNum == 1) {
            player1InProgress.setHoleValue(holeIndex, player1InProgress.getHoleValue(holeIndex) + 1);
            player1InProgress.setMancalaValue(player1InProgress.getMancala() + 1);
        } else if (playerNum == 2) {
            player2InProgress.setHoleValue(holeIndex, player2InProgress.getHoleValue(holeIndex) + 1);
            player2InProgress.setMancalaValue(player2InProgress.getMancala() + 1);
        }
        notifyListeners();
    }

    /**
     * returns a new InfoWrapper object with the PlayerInfo object of player1 and
     * player2
     *
     * @return InfoWrapper()
     */
    public InfoWrapper getPlayerInfo() {
        return new InfoWrapper(player1InProgress, player2InProgress);
    }

    /**
     * Notifys if the format is visible.
     *
     * @return isFormatVisible flag for if format is visible or not.
     */
    public boolean isFormatFrameVisible() {
        return isFormatFrameVisible;
    }

    /**
     * mutator for formatFrameVisible
     *
     * @param formatFrameVisible flag for formatFrameVisible
     */
    public void setFormatFrameVisible(boolean formatFrameVisible) {
        isFormatFrameVisible = formatFrameVisible;
    }

    /**
     * accessor for isFormatFrameClosed
     *
     * @return isFormatClosed flag for if format is closed or not.
     */
    public boolean isFormatFrameClosed() {
        return isFormatFrameClosed;
    }

    /**
     * mutator for isFormatFrameClosed
     *
     * @param formatFrameClosed flag for formatFrame Closed
     */
    public void setFormatFrameClosed(boolean formatFrameClosed) {
        isFormatFrameClosed = formatFrameClosed;
        notifyListeners();
    }

    /**
     * accessor for isPrimaryFrameVisible
     *
     * @return isPrimaryFraneVisible flag for primary frame is visible or not
     */
    public boolean isPrimaryFrameVisible() {
        return isPrimaryFrameVisible;
    }

    /**
     * mutator for isPrimaryFrameVisible. notifys the ChangeListener of the new
     * state of the PrimaryFrame
     *
     * @param primaryFrameVisible flag for isPrimaryFrameVisible
     */
    public void setPrimaryFrameVisible(boolean primaryFrameVisible) {
        isPrimaryFrameVisible = primaryFrameVisible;
        // values of the two player info objects have already been initialized
        // state changed in primary frame will use accessors in datamodel to get
        // the values associated with each hole
        notifyListeners();
    }

    /**
     * accessor for isPrimaryFrameClosed.
     *
     * @return isPrimaryFrameClosed flag for isPrimaryFrameClosed
     */
    public boolean isPrimaryFrameClosed() {
        return isPrimaryFrameClosed;
    }

    /**
     * mutator for isPrimaryFrameClosed. notifys the changeListeners of the new
     * state of the primary frame
     *
     * @param primaryFrameClosed flag for isPrimaryFrameClosed
     */
    public void setPrimaryFrameClosed(boolean primaryFrameClosed) {
        isPrimaryFrameClosed = primaryFrameClosed;
        notifyListeners();
    }

    /**
     * accessor for isStoneNumFrameVisible
     *
     * @return isStoneNumFrameVisible flag for if the stone is visible or not
     */
    public boolean isStoneNumFrameVisible() {
        return isStoneNumFrameVisible;
    }

    /**
     * mutator of isStoneFrameVisible
     *
     * @param stoneNumFrameVisible flag for if stone number frame is visible or not
     */
    public void setStoneNumFrameVisible(boolean stoneNumFrameVisible) {
        isStoneNumFrameVisible = stoneNumFrameVisible;
        notifyListeners();
    }

    /**
     * accessor for isStoneNumFrameClosed
     *
     * @return isStoneNumFrameClosed flag for if the stoneNumFrame is closed or not
     */
    public boolean isStoneNumFrameClosed() {
        return isStoneNumFrameClosed;
    }

    /**
     * mutator foe isStoneNumFrameClosed. notifys the ChangeListener for the new
     * state of the stoneNumFrameClosed
     *
     * @param stoneNumFrameClosed flag for isStoneNumFrameClosed.
     */
    public void setStoneNumFrameClosed(boolean stoneNumFrameClosed) {
        isStoneNumFrameClosed = stoneNumFrameClosed;
        notifyListeners();
    }

    /**
     * return the message to be displayed.
     *
     * @return message the message to be displayed to notify the current player turn
     */
    public String getMessage() {
        return message;
    }

    /**
     * accessor for continueIsActive
     *
     * @return continueIsActive flag for if continue button is active
     */
    public boolean isContinueIsActive() {
        return continueIsActive;
    }

    /**
     * mutator for continueIsActive notifys the ChangeListener
     *
     * @param continueIsActive flag for if continue button is active
     */
    public void setContinueIsActive(boolean continueIsActive) {
        this.continueIsActive = continueIsActive;
        notifyListeners();
    }

    /**
     * accessor for undoIsActive
     *
     * @return undoIsActive flag for if undo is active
     */
    public boolean isUndoIsActive() {
        return undoIsActive;
    }

    /**
     * mutator for undoIsActive. botifys the ChangeListener
     *
     * @param undoIsActive flag for if undo is active
     */
    public void setUndoIsActive(boolean undoIsActive) {
        this.undoIsActive = undoIsActive;
        notifyListeners();
    }

    /**
     * Attach a listener to the Model
     *
     * @param cl the listener
     */
    public void attach(ChangeListener cl) {
        myListeners.add(cl);
    }

    /**
     * calls stateChanged for all ChangeListeners
     *
     */
    private void notifyListeners() {
        for (ChangeListener cl : myListeners) {
            cl.stateChanged(new ChangeEvent(this));
        }
    }

    /**
     * starts the turn for the current player
     *
     * @param playerNum the current Player
     */
    public void startTurn(int playerNum) {
        // player1.setActive(true);
        currentPlayerNum = playerNum;
        undoCounter = 0;
        message = "player number: " + currentPlayerNum + " please choose a hole";
        if (playerNum == 1) {
            player1InProgress.setActive(true);
        } else {
            player2InProgress.setActive(true);
        }
        // add check for six pits are empty
        notifyListeners();
    }

    /**
     * calls the distribute stones function for the current player distribute stones
     * according to the stone hole selected notifys the ChangeListeners
     *
     * @param index     the location for the hole
     * @param playerNum the current player
     */
    public void userChoice(int index, int playerNum) {
        distributeStones(index, playerNum); // will set extra turn flag to false and then to true if appropriate
        message = "This is how the result would look. Would you like to continue? If not please undo.";
        if (extraTurnFlag) {
            message += "\n Player num: " + playerNum + " will get an extra turn";
        }
        continueIsActive = true;
        if(undoCounter < 3){
            undoIsActive = true;
        } else {
            message += "\nno more undos left";
        }
        player1InProgress.setActive(false);
        player2InProgress.setActive(false);
        notifyListeners();
    }

    /**
     * Commits the most recent turn made by current Player
     *
     * this also checks whether the game has ended or not.
     */
    public void continueTurn() {
        // update values
        player1Committed.shallowAssignment(player1InProgress);
        player2Committed.shallowAssignment(player2InProgress);
        // make continue button inert
        continueIsActive = false;
        undoIsActive = false;
        undoCounter = 0;

        // check to see that current player has won the game
        if (currentPlayerNum == 1) {
            if (player1Committed.isEmpty()) {
                endGame(1);
                notifyListeners();
                return;
            }
        } else if (currentPlayerNum == 2) {
            if (player2Committed.isEmpty()) {
                endGame(2);
                notifyListeners();
                return;
            }
        }

        if (extraTurnFlag) {
            if (currentPlayerNum == 1) {
                startTurn(1);
            } else {
                startTurn(2);
            }
        } else {
            if (currentPlayerNum == 1) {
                startTurn(2);
            } else {
                startTurn(1);
            }
        }

    }

    /**
     * undos the current players most recent turn.
     *
     * sets the appropriate flags for the buttons
     *
     * This is done by obtaining the board data from
     * player1Commited/player2Committed object which stores the changes made in the
     * last turn
     */
    public void undoTurn() {
        player1InProgress.shallowAssignment(player1Committed);
        player2InProgress.shallowAssignment(player2Committed);

        continueIsActive = false;
        undoIsActive = false;
        undoCounter++;

        if (currentPlayerNum == 1) {
            player1InProgress.setActive(true);
            player2InProgress.setActive(false);
        } else {
            player1InProgress.setActive(false);
            player2InProgress.setActive(true);
        }

        message = "player num: " + currentPlayerNum + "redo your turn";
        notifyListeners();
    }

    /**
     * distributes the stones in the Mancala board when current player selects a
     * hole
     *
     * checks after distributing for if the stone ends in an empty hole or in the current players mancala
     *
     * sets the extra turn flag
     *
     * @param index     the location of the hole.
     * @param playerNum the current player
     */
    public void distributeStones(int index, int playerNum) {

        extraTurnFlag = false;
        if (playerNum == 1) {
            int startingNumStones = player1InProgress.getHoleValue(index);
            player1InProgress.setHoleValue(index, 0);
            if (startingNumStones == 0) {
                return;
            }
            int counterTemp = startingNumStones;
            while (counterTemp > 0) {
                for (int i = index + 1; i < 6; i++) {
                    if (counterTemp > 0) {
                        player1InProgress.setHoleValue(i, player1InProgress.getHoleValue(i) + 1);
                        counterTemp--;

                        // grab stones
                        if (counterTemp == 0 && player1InProgress.getHoleValue(i) == 1) {
                            int stones = player1InProgress.getHoleValue(i) + player2InProgress.getHoleValue(5 - i);
                            player1InProgress.setHoleValue(i, 0);
                            player2InProgress.setHoleValue(5 - i, 0);
                            player1InProgress.setMancalaValue(player1InProgress.getMancala() + stones);
                        }
                    }
                }
                if (counterTemp > 0) {
                    player1InProgress.setMancalaValue(player1InProgress.getMancala() + 1);
                    if (counterTemp == 1) {
                        extraTurnFlag = true;
                    }
                    counterTemp--;
                }
                if (counterTemp > 0) {
                    for (int i = 0; i < 6; i++) {
                        if (counterTemp > 0) {
                            player2InProgress.setHoleValue(i, player2InProgress.getHoleValue(i) + 1);
                            counterTemp--;
                        }
                    }
                }
                if (counterTemp > 0) {
                    index = -1;
                }

            }

        } else if (playerNum == 2) {
            int startingNumStones = player2InProgress.getHoleValue(index);
            player2InProgress.setHoleValue(index, 0);
            if (startingNumStones == 0) {
                return;
            }
            int counterTemp = startingNumStones;
            while (counterTemp > 0) {
                for (int i = index + 1; i < 6; i++) {
                    if (counterTemp > 0) {
                        player2InProgress.setHoleValue(i, player2InProgress.getHoleValue(i) + 1);
                        counterTemp--;

                        if (counterTemp == 0 && player2InProgress.getHoleValue(i) == 1) {
                            int stones = player2InProgress.getHoleValue(i) + player1InProgress.getHoleValue(5 - i);
                            player2InProgress.setHoleValue(i, 0);
                            player1InProgress.setHoleValue(5 - i, 0);
                            player2InProgress.setMancalaValue(player2InProgress.getMancala() + stones);
                        }
                    }

                    // grab stones

                }
                if (counterTemp > 0) {
                    if (counterTemp > 0) {
                        player2InProgress.setMancalaValue(player2InProgress.getMancala() + 1);
                        if (counterTemp == 1) {
                            extraTurnFlag = true;
                        }
                        counterTemp--;
                    }
                }
                if (counterTemp > 0) {
                    for (int i = 0; i < 6; i++) {
                        if (counterTemp > 0) {
                            player1InProgress.setHoleValue(i, player1InProgress.getHoleValue(i) + 1);
                            counterTemp--;
                        }
                    }
                }
                if (counterTemp > 0) {
                    index = -1;
                }

            }
        }
    }

    /**
     * This method checks if the game has ended, meaning if one of the two players
     * has won. this method is called when either players set of holes is completely
     * empty.
     *
     * If so it grabs the remaining stones in the opponents holes and adds it to
     * their mancala and whoever has the highest number of stones in their mancala
     * wins
     *
     * @param playerNum the current player
     */
    private void endGame(int playerNum) {
        message = "The game is over!";
        if (playerNum == 1) {
            message += "\nPlayer: " + 2 + " captured their remaining stones";
            player2InProgress.captureRemainingStones();
        } else {
            message += "\nPlayer: " + 1 + " captured their remaining stones";
            player1InProgress.captureRemainingStones();
        }

        message += "\nPlayer 1: " + player1InProgress.getMancala();
        message += "\nPlayer 2: " + player2InProgress.getMancala();

        if (player1InProgress.getMancala() > player2InProgress.getMancala()) {
            message += "\nPlayer 1 wins!!!";
        } else {
            message += "\nPlayer 2 wins!!!";
        }

    }

}